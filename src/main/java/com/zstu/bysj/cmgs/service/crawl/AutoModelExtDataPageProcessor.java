package com.zstu.bysj.cmgs.service.crawl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.util.AutoConstants;

/**
 * 抓取车型补充数据
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoModelExtDataPageProcessor implements PageProcessor {

	private static Logger logger = LoggerFactory.getLogger(AutoModelExtDataPageProcessor.class);
	private Site site;

	public AutoModelExtDataPageProcessor() {
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

		int id = NumberUtils.toInt(page.getUrl().regex(AutoConstants.REG_MODEL_CONFIG_URL, 1).get());

		// 解析车型
		List<Selectable> sellNodes = page.getHtml().xpath("//div[@class='cardetail-infor-car']/ul/li").nodes();
		AutoModel autoModel = new AutoModel();
		autoModel.setId(id);
		// 车主评分
		String score = nullToString(sellNodes.get(0).xpath("/li/a[@class='fn-fontsize14 font-bold']/text()").get());
		autoModel.setOwnerScore(stringToBigDecimal(score.replaceAll("分", "")));
		String ownerFuelConsumption = nullToString(sellNodes.get(1).xpath("/li/a/text()").get());
		if (ownerFuelConsumption.indexOf("L") != -1) {
			ownerFuelConsumption = ownerFuelConsumption.substring(0, ownerFuelConsumption.indexOf("L"));
		}
		autoModel.setOwnerFuelConsumption(stringToBigDecimal(ownerFuelConsumption));
		autoModel.setCarSize(nullToString(sellNodes.get(2).xpath("/li/text()").get()));
		String combinedFuelConsumption = nullToString(sellNodes.get(3).xpath("/li/text()").get());
		if (combinedFuelConsumption.indexOf("L") != -1) {
			combinedFuelConsumption = combinedFuelConsumption.substring(0, combinedFuelConsumption.indexOf("L"));
		}
		autoModel.setCombinedFuelConsumption(stringToBigDecimal(combinedFuelConsumption));

		String struct = nullToString(sellNodes.get(4).xpath("/li/text()").get());
		if (null != struct && !struct.isEmpty() && struct.contains("门") && struct.contains("座")) {
			autoModel.setCarDoors(Integer.valueOf(String.valueOf(struct.charAt(struct.indexOf("门") - 1))));
			autoModel.setCarSeats(Integer.valueOf(String.valueOf(struct.charAt(struct.indexOf("座") - 1))));
		} else {
			autoModel.setCarDoors(4);
			autoModel.setCarSeats(5);
		}

		if (struct.contains("SUV")) {
			autoModel.setModelWashType(2);
		} else if (struct.contains("MPV")) {
			autoModel.setModelWashType(3);
		} else {
			autoModel.setModelWashType(1);
		}

		autoModel.setVehicleWarranty(nullToString(sellNodes.get(5).xpath("/li/text()").get()));

		String engine = nullToString(sellNodes.get(6).xpath("/li/text()").get());
		String arr[] = engine.split(" ");
		String carEmissions = nullToString(arr[0]);
		autoModel.setIsTurbo(carEmissions.indexOf("T") != -1);
		autoModel.setDisplacement(stringToBigDecimal(carEmissions.replace("L", "").replace("T", "")));
		autoModel.setCarHorsepower(stringToBigDecimal(nullToString(arr[1]).replace("马力", "")));
		autoModel.setCarEngineType(nullToString(arr[2]));

		autoModel.setTransmission(nullToString(sellNodes.get(7).xpath("/li/text()").get()));
		autoModel.setCarDrivingWay(nullToString(sellNodes.get(8).xpath("/li/text()").get()));

		logger.info("正在解析车型补充数据：{}", autoModel.getId());
		page.putField("crawlExtData", autoModel);
	}

	private BigDecimal stringToBigDecimal(String str) {
		try {
			return new BigDecimal(str);
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	private String nullToString(String str) {
		if (null == str)
			return "";
		return str;
	}

	@Override
	public Site getSite() {
		return site;
	}

}
