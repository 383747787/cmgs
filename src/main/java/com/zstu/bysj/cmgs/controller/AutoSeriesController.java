package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.AutoSeriesDto;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoHomeCrawlService;
import com.zstu.bysj.cmgs.service.AutoSeriesService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 车系
 * 
 * @author irving
 *
 */
@Controller
public class AutoSeriesController {

	@Resource
	private AutoSeriesService autoSeriesService;
	@Resource
	private AutoHomeCrawlService autoHomeCrawlService;

	/**
	 * 车系列表界面
	 */
	@RequestMapping("/brand/series/list")
	public ModelAndView seriesView(Integer brandId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/brand/seriesList");
		mv.addObject("brandId", brandId);
		return mv;
	}

	/**
	 * 获得车系列表
	 */
	@RequestMapping("/brand/series/list/get/data")
	public @ResponseBody AutoSeriesDto getSeriesList(@RequestBody AutoSeriesDto param) {
		AutoSeries autoSeries = new AutoSeries();
		autoSeries.setBrandId(param.getSearch().getBrandId());
		autoSeries.setName(param.getSearch().getName());
		List<AutoSeries> SeriesList = autoSeriesService.findList(autoSeries, param.getStart(), param.getPageSize());
		param.setData(SeriesList);
		param.setTotalSize(autoSeriesService.findCount(autoSeries));
		return param;
	}

	/**
	 * 按品牌获得所有车系
	 */
	@RequestMapping("/brand/series/all")
	public @ResponseBody String getAllSeries(Integer brandId) {
		List<AutoSeries> SeriesList = autoSeriesService.findList(brandId);
		return ResultWrapper.success(SeriesList);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/brand/series/save")
	public @ResponseBody String saveSeries(@RequestBody AutoSeries autoSeries) {
		autoSeriesService.save(autoSeries);
		return ResultWrapper.success("车系更新成功");
	}

	/**
	 * 按车系同步数据
	 */
	@RequestMapping("/brand/series/crawl")
	public @ResponseBody String crawlSeries(Integer seriesId) {
		autoHomeCrawlService.crawlModel(seriesId);
		return ResultWrapper.success("按车系同步数据成功");
	}

}
