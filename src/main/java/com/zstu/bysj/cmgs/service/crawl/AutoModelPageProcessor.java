package com.zstu.bysj.cmgs.service.crawl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import com.zstu.bysj.cmgs.model.dto.SeriesParseDto;
import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.util.AutoConstants;

/**
 * 抓取车型数据
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoModelPageProcessor implements PageProcessor {

	private static Logger logger = LoggerFactory.getLogger(AutoModelPageProcessor.class);
	private boolean recursive;
	private Site site;

	public AutoModelPageProcessor() {
		this(true);// 默认是采用页面递归抓取
	}

	public AutoModelPageProcessor(boolean recursive) {
		this.recursive = recursive;
		Set<Integer> acceptStatCode = new HashSet<Integer>();
		acceptStatCode.add(200);
		acceptStatCode.add(302);
		site = Site.me().setRetryTimes(5).setCycleRetryTimes(10)
		           .setTimeOut(50000) // 设置50秒超时时间
		           .setSleepTime(200)
		           .setAcceptStatCode(acceptStatCode);
	}

	@Override
	public void process(Page page) {

		int seriesId = NumberUtils.toInt(page.getUrl().regex(AutoConstants.REG_SERIES_URL, 1).get());
		String seriesName = page.getHtml().xpath("//div[@class='main-title']/a/text()").get();

		SeriesParseDto seriesParseDto = new SeriesParseDto();
		seriesParseDto.setSeriesId(seriesId);
		seriesParseDto.setSeriesName(seriesName);
		logger.info("正在解析车系：{} - {}", seriesId, seriesName);
		// 解析车系级别
		String classify = page.getHtml().xpath("//ul[@class='lever-ul']/li[1]/text()").regex(".*：(.*)", 1).get();
		if (StringUtils.isNotBlank(classify)) {
			seriesParseDto.setSeriesClassify(classify);
		}
		// 解析“在售”车型
		List<Selectable> sellNodes = page.getHtml().xpath("//ul[@class='interval01-list']/li").nodes();
		AutoModel autoModel = null;
		for (Selectable sellNode : sellNodes) {
			autoModel = new AutoModel();
			// 模型名称和ID
			Selectable seriesNode = sellNode.xpath("/li/div/div/p[1]/a");
			autoModel.setName(seriesNode.xpath("/a/text()").get());
			Integer id = NumberUtils.toInt(seriesNode.xpath("/a/@href")
			                                         .regex(AutoConstants.REG_MODEL_CONFIG_URL_LEFT, 1).get());
			autoModel.setId(id);
			autoModel.setSeriesId(seriesId);
			// 模型指导价，格式：“x.xx万” 或 “暂无报价”
			String guidePrice = sellNode.xpath("//div[@class='interval01-list-guidance']/div/text()").regex("(.*)万", 1)
			                            .get();
			autoModel.setGuidePrice(BigDecimal.valueOf(NumberUtils.toDouble(guidePrice)));
			logger.info("正在解析车型：{} - {}", autoModel.getName(), autoModel.getGuidePrice());
			seriesParseDto.addModel(autoModel);
		}
		page.putField("seriesParseDto", seriesParseDto);
		// 如果需要递归找页面，才进行页面URL的递归检查
		if (recursive) {
			// 解析“停售”和“即将销售”的车型的 URL 地址
			List<Selectable> stopSellNodes = page.getHtml().xpath("//div[@class='tab-nav border-t-no']/ul/li").nodes();
			String stopSellUrl = null;
			for (Selectable stopSellNode : stopSellNodes) {
				// 找出 li中的样式不等于 current 的节点
				String liClass = stopSellNode.xpath("/li/@class").get();
				if (!StringUtils.equals(liClass, "current") && !StringUtils.equals(liClass, "disabled")) {
					stopSellUrl = stopSellNode.xpath("/li/a").links().get();
					if (StringUtils.isNotBlank(stopSellUrl)) {
						page.addTargetRequest(stopSellUrl);
					}
				}
			}
			// 解析车型列表出现分页的情况
			if (page.getUrl().regex(AutoConstants.REG_SERIES_URL_PAGE_ONE).match()) {
				List<Selectable> pageNodes = page.getHtml().xpath("//div[@class='price-page02']/div[@class='page']/a")
				                                 .css("a[href~=/price/series-]").nodes();
				for (Selectable pageNode : pageNodes) {
					page.addTargetRequest(pageNode.links().get());
				}
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

}
