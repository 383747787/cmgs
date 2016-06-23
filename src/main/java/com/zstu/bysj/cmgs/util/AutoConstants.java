package com.zstu.bysj.cmgs.util;

/**
 * 汽车之家链接
 * 
 * @author irving
 *
 */
public interface AutoConstants {

	// String[] BRAND_ALPHA_CODES = new String[] { "G", "T", "L" };
	String[] BRAND_ALPHA_CODES = new String[] { "A", "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "O", "P",
	        "Q", "R", "S", "T", "W", "X", "Y", "Z" };

	// 品牌入口页
	String BRAND_URL = "http://www.autohome.com.cn/grade/carhtml/ALPHA_CODE.html";
	// 品牌入口页正则表达式
	String REG_BRAND_URL = "http://www\\.autohome\\.com\\.cn/grade/carhtml/(\\w).html";

	// 内页品牌入口页
	String BRAND_INTERNAL_URL = "http://car.autohome.com.cn/price/brand-{ID}.html";
	String REG_BRAND_INTERNAL_URL = "http://car\\.autohome\\.com\\.cn/price/brand-(\\d+)([-\\d]*)?.html";

	// 车系详情页
	String SERIES_URL = "http://car.autohome.com.cn/price/series-{ID}.html";
	String REG_SERIES_URL_PAGE_ONE = "http://car\\.autohome\\.com\\.cn/price/series-(\\d+)([-\\d]*)?-1.html";
	String REG_SERIES_URL = "http://car\\.autohome\\.com\\.cn/price/series-(\\d+)([-\\d]*)?.html";

	// 二手车车系入口
	String SERIES_URL_168 = "http://www.che168.com/handler/UsedCarListV4.ashx?action=serieslist&brand=";
	String SERIES_URL_168_NEW = "http://api.che168.com/auto/GetCarSeriesByBrandId.ashx?type=2&_appid=2sc&brandid=";

	// 车型配置入口页
	String MODEL_CONFIG_URL = "http://www.autohome.com.cn/spec/%d/#pvareaid=101605";
	String REG_MODEL_CONFIG_URL_LEFT = "http://www\\.autohome\\.com\\.cn/spec/(\\d+)/";
	String REG_MODEL_CONFIG_URL = "http://www\\.autohome\\.com\\.cn/spec/(\\d+)/#pvareaid=101605";
	String MODEL_CONFIG_DETAIL_URL = "http://car.autohome.com.cn/config/spec/%d.html";
	String REG_MODEL_CONFIG_DETAIL_URL = "http://car\\.autohome\\.com\\.cn/config/spec/(\\d+)\\.html";

	// 车型图片入口页
	String MODEL_IMG_URL = "http://car.autohome.com.cn/pic/series-s%d/%d-1.html";
	String REG_MODEL_IMG_URL = "http://car\\.autohome\\.com\\.cn/pic/series-s(\\d+)/(\\d+)-1\\.html";

	// 加油卡充值问题页
	String HOW_QUANCUN_URL = "/ddyc/oilcard/earmark.html";
	String HOW_DISTRIBUTION_URL = "/ddyc/oilcard/preDistribute.html";
	String HOW_INVOICE_URL = "/ddyc/oilcard/invoice.html";
	String NEEDINVOICE_MSG = "发票请去加油站圈存时向加油站工作人员索取。";

	// 违章查询
	String NO_LICENSE_BUTTON_DOC = "违章代缴需上传行驶证";
	String PROCESSING_BUTTON_DOC = "您有代缴订单正在处理中";
	String UNPORCESS_BUTTON = "修改车辆信息";
	String CAR_INFO_NOT_PERFECT = "您的车辆信息不完善，请修改";
	String CITY_NOT_OPEN = "您选择的城市未开通违章查询";
	String MODIFY_QUERY_CITY = "修改查询城市";
	String MEMBER_DOC = "*年卡特权将在支付时自动抵扣";
	String VIOLATION_NOTICE = "1.在典典平台下单后，请勿自行再次缴纳，否则由此造成重复缴纳的损失将由您自己承担；\n2.下单后典典将需要5-10分钟确认您的订单，确认成功即可在线支付，请耐心等待。";
	String NOPROCESS_VIOLATION_NOTICE = "";
	String PROCESSING_VIOLATION_NOTICE = "";
	String PROCESSED_VIOLATION_NOTICE = "交管局网站数据存在延时，如遇部分已处理违章在交管局查询结果为未处理，请无需担心！";

}
