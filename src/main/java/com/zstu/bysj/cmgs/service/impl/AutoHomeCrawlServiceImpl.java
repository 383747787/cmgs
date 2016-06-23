package com.zstu.bysj.cmgs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Spider;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoBrandService;
import com.zstu.bysj.cmgs.service.AutoHomeCrawlService;
import com.zstu.bysj.cmgs.service.AutoModelService;
import com.zstu.bysj.cmgs.service.AutoSeriesService;
import com.zstu.bysj.cmgs.service.crawl.AutoModelExtDataPageProcessor;
import com.zstu.bysj.cmgs.service.crawl.AutoModelExtDataPageProcessor2;
import com.zstu.bysj.cmgs.service.crawl.AutoModelExtDataPipeline;
import com.zstu.bysj.cmgs.service.crawl.AutoModelExtDataPipeline2;
import com.zstu.bysj.cmgs.service.crawl.AutoModelPageProcessor;
import com.zstu.bysj.cmgs.service.crawl.AutoModelPipeline;
import com.zstu.bysj.cmgs.service.crawl.AutoSeriesPageProcessor;
import com.zstu.bysj.cmgs.service.crawl.AutoSeriesPipeline;
import com.zstu.bysj.cmgs.util.AutoConstants;

/**
 * 爬虫获取车系车型数据
 * 
 * <pre>
 * 〈一句话是什么〉
 * 〈详细描述做什么〉
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class AutoHomeCrawlServiceImpl implements AutoHomeCrawlService {

	private static Logger logger = LoggerFactory.getLogger(AutoHomeCrawlServiceImpl.class);

	@Resource
	private AutoBrandService autoBrandService;
	@Resource
	private AutoSeriesService autoSeriesService;
	@Resource
	private AutoModelService autoModelService;
	@Resource
	private AutoSeriesPageProcessor autoSeriesPageProcessor;
	@Resource
	private AutoSeriesPipeline autoSeriesPipeline;
	@Resource
	private AutoModelPageProcessor autoModelPageProcessor;
	@Resource
	private AutoModelPipeline autoModelPineline;
	@Resource
	private AutoModelExtDataPageProcessor autoModelExtDataPageProcessor;
	@Resource
	private AutoModelExtDataPipeline autoModelExtDataPipeline;
	@Resource
	private AutoModelExtDataPageProcessor2 autoModelExtDataPageProcessor2;
	@Resource
	private AutoModelExtDataPipeline2 autoModelExtDataPipeline2;

	@Override
	public void crawlAll() {
		List<AutoBrand> brandList = autoBrandService.findList();
		for (AutoBrand brand : brandList) {
			Integer brandId = brand.getId();
			if (null == brandId || 0 == brandId) {
				continue;
			}
			this.crawlSeries(brandId);

			List<AutoSeries> serieslList = autoSeriesService.findList(brandId);
			for (AutoSeries seriesl : serieslList) {
				this.crawlModel(seriesl.getId());
			}
		}
	}

	@Override
	public void crawlBrand(Integer brandId) {
		if (null == brandId || 0 == brandId) {
			return;
		}
		this.crawlSeries(brandId);

		List<AutoSeries> serieslList = autoSeriesService.findList(brandId);
		for (AutoSeries seriesl : serieslList) {
			this.crawlModel(seriesl.getId());
		}
	}

	@Override
	public void crawlSeries(Integer brandId) {
		if (null == brandId || 0 == brandId) {
			return;
		}
		Spider spider = Spider.create(autoSeriesPageProcessor).addPipeline(autoSeriesPipeline)
		                      .addUrl(AutoConstants.BRAND_INTERNAL_URL.replaceAll("\\{ID\\}", String.valueOf(brandId)))
		                      .thread(5);
		spider.run();
	}

	@Override
	public void crawlModel(Integer seriesId) {
		if (null == seriesId || 0 == seriesId) {
			return;
		}
		Spider spider = Spider.create(autoModelPageProcessor).addPipeline(autoModelPineline)
		                      .addUrl(AutoConstants.SERIES_URL.replaceAll("\\{ID\\}", String.valueOf(seriesId)))
		                      .thread(5);
		spider.run();

		if (Spider.Status.Stopped == spider.getStatus()) {
			List<AutoModel> list = autoModelService.findList(seriesId);
			int total = list.size();
			AutoModel crawl = null;
			for (int i = 0; i < total; i++) {
				crawl = list.get(i);
				logger.info("正在抓取车型补充数据：{}[{}] - {}/{}", crawl.getName(), crawl.getId(), i + 1, total);
				String url = String.format(AutoConstants.MODEL_CONFIG_URL, crawl.getId());
				Spider.create(autoModelExtDataPageProcessor).addPipeline(autoModelExtDataPipeline)
				      .addUrl(url).thread(5).run();

				logger.info("正在抓取车型轮胎规格,整备质量和大小车型：{}[{}] - {}/{}", crawl.getName(), crawl.getId(), i + 1, total);
				String detailUrl = String.format(AutoConstants.MODEL_CONFIG_DETAIL_URL, crawl.getId());
				Spider.create(autoModelExtDataPageProcessor2).addPipeline(autoModelExtDataPipeline2)
				      .addUrl(detailUrl).thread(5).run();
			}
		}
	}

}
