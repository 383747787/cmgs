package com.zstu.bysj.cmgs.service.crawl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.zstu.bysj.cmgs.model.dto.SeriesParseDto;
import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.service.AutoModelService;

/**
 * 持久化车型数据
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoModelPipeline implements Pipeline {

	private static Logger logger = LoggerFactory.getLogger(AutoModelPipeline.class);

	@Resource
	private AutoModelService autoModelService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		logger.info("开始保存车型 - {}", resultItems);
		SeriesParseDto seriesParseDto = (SeriesParseDto) resultItems.get("seriesParseDto");
		List<AutoModel> list = seriesParseDto.getModels();
		for (AutoModel autoModel : list) {
			logger.info("保存车型：{} - {}", autoModel.getId(), autoModel.getName());
			autoModelService.save(autoModel);
		}
	}

}
