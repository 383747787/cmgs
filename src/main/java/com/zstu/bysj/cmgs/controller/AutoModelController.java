package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.AutoModelDto;
import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.service.AutoModelService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 车型
 * 
 * @author irving
 *
 */
@Controller
public class AutoModelController {
	@Resource
	AutoModelService autoModelService;

	/**
	 * 车型列表界面
	 */
	@RequestMapping("/brand/model/list")
	public ModelAndView modelView(Integer brandId, Integer seriesId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/brand/modelList");
		mv.addObject("brandId", brandId);
		mv.addObject("seriesId", seriesId);
		return mv;
	}

	/**
	 * 获得车型列表
	 */
	@RequestMapping("/brand/model/list/get/data")
	public @ResponseBody AutoModelDto getModelList(@RequestBody AutoModelDto param) {
		AutoModel autoModel = new AutoModel();
		autoModel.setSeriesId(param.getSearch().getSeriesId());
		autoModel.setName(param.getSearch().getName());
		autoModel.setDisplacement(param.getSearch().getDisplacement());
		autoModel.setCarEngineType(param.getSearch().getCarEngineType());
		List<AutoModel> ModelList = autoModelService.findList(autoModel, param.getStart(), param.getPageSize());
		param.setData(ModelList);
		param.setTotalSize(autoModelService.findCount(autoModel));
		return param;
	}

	/**
	 * 根据车系获得所有车型
	 */
	@RequestMapping("/brand/model/all")
	public @ResponseBody String getAllModel(Integer seriesId) {
		AutoModel autoModel = new AutoModel();
		autoModel.setSeriesId(seriesId);
		List<AutoModel> ModelList = autoModelService.findList(autoModel, null, null);
		return ResultWrapper.success(ModelList);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/brand/model/save")
	public @ResponseBody String saveModel(@RequestBody AutoModel autoModel) {
		autoModelService.save(autoModel);
		return ResultWrapper.success("车型更新成功");

	}

}
