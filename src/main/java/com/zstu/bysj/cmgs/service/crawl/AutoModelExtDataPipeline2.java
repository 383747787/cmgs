package com.zstu.bysj.cmgs.service.crawl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.service.AutoModelService;

/**
 * 持久化车型补充数据
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoModelExtDataPipeline2 implements Pipeline {

	private static Logger logger = LoggerFactory.getLogger(AutoModelExtDataPipeline2.class);

	@Resource
	private AutoModelService autoModelService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		logger.info("保存车型轮胎规格,整备质量和大小车型 - {}", resultItems);
		AutoModel autoModel = resultItems.get("crawlExtData2");
		autoModelService.save(autoModel);
	}

}
