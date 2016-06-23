package com.zstu.bysj.cmgs.service.crawl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.util.AutoConstants;

/**
 * 抓取车型轮胎规格,整备质量等
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Service
public class AutoModelExtDataPageProcessor2 implements PageProcessor {

	private static Logger logger = LoggerFactory.getLogger(AutoModelExtDataPageProcessor2.class);
	private Site site;

	public AutoModelExtDataPageProcessor2() {
		Set<Integer> acceptStatCode = new HashSet<Integer>();
		acceptStatCode.add(200);
		acceptStatCode.add(302);
		site = Site.me().setRetryTimes(5).setCycleRetryTimes(10)
		           .setTimeOut(50000) // 设置50秒超时时间
		           .setSleepTime(200)
		           .setAcceptStatCode(acceptStatCode);
	}

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		int id = NumberUtils.toInt(page.getUrl().regex(AutoConstants.REG_MODEL_CONFIG_DETAIL_URL, 1).get());
		AutoModel autoModel = new AutoModel();
		autoModel.setId(id);

		// 轮胎规格
		String html = page.toString();
		String TyreRegEx = "[0-9]+/[0-9]+ R[0-9]+";
		Pattern TyrePattern = Pattern.compile(TyreRegEx);

		// 前轮
		String fontTyreRegEx = "\\{\"name\":\"前轮胎规格\",\"valueitems\":\\[.*?\\{\"specid\":" + id
		        + ",\"value\":\"[0-9]+/[0-9]+ R[0-9]+.\"\\}";
		Pattern fontTyrePattern = Pattern.compile(fontTyreRegEx);
		Matcher fontTyreMatcher = fontTyrePattern.matcher(html);
		if (fontTyreMatcher.find()) {
			String front_tyre_size = fontTyreMatcher.group();
			Matcher matcher = TyrePattern.matcher(front_tyre_size);
			matcher.find();
			autoModel.setFrontTyreSize(matcher.group());
		}

		// 后轮
		String RearTyreRegEx = "\\{\"name\":\"后轮胎规格\",\"valueitems\":\\[.*?\\{\"specid\":" + id
		        + ",\"value\":\"[0-9]+/[0-9]+ R[0-9]+.\"\\}";
		Pattern RearTyrePattern = Pattern.compile(RearTyreRegEx);
		Matcher RearTyreMatcher = RearTyrePattern.matcher(html);
		if (RearTyreMatcher.find()) {
			String rear_tyre_size = RearTyreMatcher.group();
			Matcher matcher = TyrePattern.matcher(rear_tyre_size);
			matcher.find();
			autoModel.setRearTyreSize(matcher.group());
		}

		// 整备质量
		String carValueRegEx = "\\{\"name\":\"整备质量\\(kg\\)\",\"valueitems\":\\[.*?\\{\"specid\":" + id
		        + ",\"value\":\"[0-9]+\"\\}";
		Pattern carValuePattern = Pattern.compile(carValueRegEx);
		Matcher carValueMatcher = carValuePattern.matcher(html);

		if (carValueMatcher.find()) {
			String carValue = carValueMatcher.group();
			String[] strs = carValue.split("\"");
			autoModel.setCarValue(new BigDecimal(strs[strs.length - 2]));
		}

		// 大小车型
		String WashTypeRegEx = "\\{\"name\":\"级别\",\"valueitems\":\\[.*?\\{\"specid\":" + id
		        + ",\"value\":\".{0,20}\"\\}";
		Pattern WashTypePattern = Pattern.compile(WashTypeRegEx);
		Matcher WashTypeMatcher = WashTypePattern.matcher(html);

		if (WashTypeMatcher.find()) {
			String classify = WashTypeMatcher.group();
			String[] strs = classify.split("\"");
			classify = strs[strs.length - 2];

			if (classify.contains("小") || classify.contains("紧凑") ||
			        classify.contains("普通")) {
				autoModel.setWashType(1);
			} else {
				autoModel.setWashType(2);
			}
		}

		logger.info("正在解析轮胎规格,整备质量和大小车型：{}", autoModel.getId());
		page.putField("crawlExtData2", autoModel);
	}

}
