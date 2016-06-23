package com.zstu.bysj.cmgs.service.crawl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoSeriesService;

/**
 * 持久化车系数据
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoSeriesPipeline implements Pipeline {

	private static Logger logger = LoggerFactory.getLogger(AutoSeriesPipeline.class);

	@Resource
	private AutoSeriesService autoSeriesService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		AutoBrand brand = (AutoBrand) resultItems.get("brand");
		@SuppressWarnings("unchecked")
		List<AutoSeries> seriesList = (List<AutoSeries>) resultItems.get("seriesList");
		logger.info("开始保存汽车之家车系数据，品牌ID: {}, 品牌名称: {}, 车系长度: {}", brand.getName(), brand.getName(), seriesList.size());
		for (AutoSeries series : seriesList) {
			logger.info("保存汽车之家车系数据，车系ID: {}, 车系名称: {},", series.getId(), series.getName());
			autoSeriesService.save(series);
		}

	}

}
