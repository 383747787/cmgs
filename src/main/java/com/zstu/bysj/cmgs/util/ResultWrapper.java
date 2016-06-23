package com.zstu.bysj.cmgs.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

/**
 * 返回结果工具类
 * 
 * @author irving
 *
 */
public class ResultWrapper {

	public static String success(Object object) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("success", true);
		map.put("data", object);
		String json = JSONArray.toJSONString(map);
		return json;
	}

	public static String success(Object object, String msg) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("success", true);
		map.put("msg", msg);
		map.put("data", object);
		String json = JSONArray.toJSONString(map);
		return json;
	}

	public static String fail(String errorMsg) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("success", false);
		map.put("msg", errorMsg);
		String json = JSONArray.toJSONString(map);
		return json;
	}
}
