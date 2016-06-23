package com.zstu.bysj.cmgs.service.crawl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoBrandService;
import com.zstu.bysj.cmgs.service.AutoSeriesService;
import com.zstu.bysj.cmgs.util.AutoConstants;
import com.zstu.bysj.cmgs.util.PinYinTransfer;

/**
 * 抓取车系数据
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoSeriesPageProcessor implements PageProcessor {

	private static Logger logger = LoggerFactory.getLogger(AutoSeriesPageProcessor.class);
	private Site site;
	private boolean recursive;
	private AutoBrand brand;

	@Resource
	AutoBrandService autoBrandService;
	@Resource
	AutoSeriesService autoSeriesService;

	public AutoSeriesPageProcessor() {
		this(true);// 默认是采用页面递归抓取
	}

	public AutoSeriesPageProcessor(boolean recursive) {
		this.recursive = recursive;
		Set<Integer> acceptStatCode = new HashSet<Integer>();
		acceptStatCode.add(200);
		acceptStatCode.add(302);
		site = Site.me().setRetryTimes(5).setCycleRetryTimes(10).setTimeOut(30000)
		           .setSleepTime(200).setAcceptStatCode(acceptStatCode);
	}

	@Override
	public void process(Page page) {
		int brandId = NumberUtils.toInt(page.getUrl().regex(AutoConstants.REG_BRAND_INTERNAL_URL, 1).get());
		this.brand = autoBrandService.findById(brandId);
		// 解析车系列表
		List<AutoSeries> seriesList = this.parseAutoSeries(page);
		// 解析分页及“停售”列表子页面URL地址
		if (recursive) {
			this.parsePageSplits(page);
		}
		// 数据输出
		page.putField("brand", this.brand);
		page.putField("seriesList", seriesList);
		for (AutoSeries series : seriesList) {
			logger.info("解析车系： {}[{}], {}, 价格 {}-{}万", series.getName(), series.getId(), series.getClassify(),
			        series.getGuidePriceLow(), series.getGuidePriceHigh());
		}
	}

	private List<AutoSeries> parseAutoSeries(Page page) {
		List<AutoSeries> seriesList = new ArrayList<AutoSeries>();
		List<Selectable> seriesNodes = page.getHtml().xpath("//div[@class='list-cont']").nodes();
		for (Selectable seriesNode : seriesNodes) {
			int seriesId = NumberUtils.toInt(seriesNode.xpath("/div/@data-value").get());
			String seriesName = seriesNode.xpath("//div[@class='main-title']/a/text()").get();
			String guidePrice = seriesNode.xpath("//span[@class='font-arial']/text()").get();
			String classify = seriesNode.xpath("//ul[@class='lever-ul']/li").nodes().get(0).xpath("/li/text()")
			                            .regex(".*：(.*)", 1).get();

			AutoSeries autoSeries = new AutoSeries();
			// 如果品牌和车系名称相同，或者品牌名称为英文或数字的，不做前缀去除处理
			if (StringUtils.equals(seriesName, brand.getName()) || Pattern.matches("[\\w|\\d]*", brand.getName())) {
				autoSeries.setName(seriesName);
			} else {
				// 去除前缀与品牌一致的字符
				autoSeries.setName(StringUtils.removeStart(seriesName, brand.getName()).trim());
			}
			autoSeries.setId(seriesId);
			autoSeries.setBrandId(brand.getId());
			autoSeries.setMark(PinYinTransfer.single().toPinyin(brand.getName()));
			// 解析指导价：358.88-428.00万
			if (StringUtils.isNotBlank(guidePrice)) {
				Matcher match = Pattern.compile("(.*)-(.*)万").matcher(guidePrice);
				if (match.matches()) {
					autoSeries.setGuidePriceLow(NumberUtils.createBigDecimal(match.group(1)));
					autoSeries.setGuidePriceHigh(NumberUtils.createBigDecimal(match.group(2)));
				}
			}
			autoSeries.setClassify(classify);
			if (StringUtils.isNotBlank(autoSeries.getClassify())) {
				autoSeries.setWashCarType(this.transferWashCarType(autoSeries.getClassify()));
			}
			seriesList.add(autoSeries);
		}
		return seriesList;
	}

	/**
	 * 检查分页
	 */
	private void parsePageSplits(Page page) {
		// 添加本页的分页
		List<Selectable> aNodes = page.getHtml().xpath("//div[@class='price-page']/div[@class='page']")
		                              .css("a[href~=/price/brand-]").nodes();
		if (aNodes.size() > 0) {
			for (Selectable aNode : aNodes) {
				logger.info("解析“本页”的分页地址：{}", aNode.links().get());
				page.addTargetRequest(aNode.links().get());
			}
		}
		// 解析“停售”和“即将销售”的车系的 URL 地址
		List<Selectable> stopSellNodes = page.getHtml().xpath("//div[@class='tab-nav border-t-no']/ul/li").nodes();
		String stopSellUrl = null;
		for (Selectable stopSellNode : stopSellNodes) {
			// 找出 li中的样式不等于 current 的节点
			String liClass = stopSellNode.xpath("/li/@class").get();
			if (!StringUtils.equals(liClass, "current") && !StringUtils.equals(liClass, "disabled")) {
				stopSellUrl = stopSellNode.xpath("/li/a").links().get();
				if (StringUtils.isNotBlank(stopSellUrl)) {
					logger.info("解析“在售”、“停售”或“即将销售”的分页地址：{}", stopSellUrl);
					page.addTargetRequest(stopSellUrl);
				}
			}
		}
	}

	/**
	 * 洗车类型
	 * 
	 * @param classify
	 * @return
	 */
	private int transferWashCarType(String classify) {
		int washCarType = 1;
		if (StringUtils.containsIgnoreCase(classify, "SUV")) {
			washCarType = 2;
		} else if (StringUtils.containsIgnoreCase(classify, "MPV")) {
			washCarType = 3;
		}
		return washCarType;
	}

	@Override
	public Site getSite() {
		return site;
	}

}
