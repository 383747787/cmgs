package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.ServiceTypeDto;
import com.zstu.bysj.cmgs.model.entity.ServiceTypeEntity;
import com.zstu.bysj.cmgs.service.ServiceTypeService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 服务类型
 * 
 * @author irving
 *
 */
@Controller
public class ServiceTypeController {

	@Resource
	ServiceTypeService serviceTypeService;

	/**
	 * 服务类型显示界面
	 */
	@RequestMapping("/service/type/list")
	public ModelAndView listView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/service/typeList");
		return mv;
	}

	/**
	 * 服务类型编辑界面
	 */
	@RequestMapping("/service/type/edit")
	public ModelAndView editView(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("jsp/service/typeEdit");
		return mv;
	}

	/**
	 * 获得服务种类列表
	 */
	@RequestMapping("/service/type/list/get/data")
	public @ResponseBody ServiceTypeDto getServiceTypeList(@RequestBody ServiceTypeDto param) {
		ServiceTypeEntity serviceType = new ServiceTypeEntity();
		serviceType.setLv(param.getSearch().getLv());
		serviceType.setParent(param.getSearch().getParent());
		serviceType.setName(param.getSearch().getName());
		List<ServiceTypeEntity> serviceTypeList = serviceTypeService.findList(serviceType, param.getStart(),
		        param.getPageSize());
		param.setData(serviceTypeList);
		param.setTotalSize(serviceTypeService.findCount(serviceType));
		return param;
	}

	/**
	 * 获得全部一级类目
	 */
	@RequestMapping("/service/type/lv1")
	public @ResponseBody String getLv1ServiceTypeList() {
		ServiceTypeEntity serviceType = new ServiceTypeEntity();
		serviceType.setLv(1);
		List<ServiceTypeEntity> lv1ServiceTypeList = serviceTypeService.findList(serviceType, null, null);
		return ResultWrapper.success(lv1ServiceTypeList);
	}

	/**
	 * 获得全部二级类目
	 */
	@RequestMapping("/service/type/lv2")
	public @ResponseBody String getLv2ServiceTypeList(Integer parent) {
		ServiceTypeEntity serviceType = new ServiceTypeEntity();
		serviceType.setParent(parent);
		List<ServiceTypeEntity> lv2ServiceTypeList = serviceTypeService.findList(serviceType, null, null);
		return ResultWrapper.success(lv2ServiceTypeList);
	}

	/**
	 * 根据id查询
	 */
	@RequestMapping("/service/type/find")
	public @ResponseBody String findById(Integer id) {
		ServiceTypeEntity serviceType = serviceTypeService.findById(id);
		return ResultWrapper.success(serviceType);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/service/type/save")
	public @ResponseBody String saveServiceType(@RequestBody ServiceTypeEntity serviceType) {
		serviceTypeService.save(serviceType);
		return ResultWrapper.success("服务种类更新成功");
	}

	/**
	 * 一级类目列表
	 */
	@RequestMapping("/service/type/list/lv1")
	public @ResponseBody String lv1List() {
		ServiceTypeEntity serviceType = new ServiceTypeEntity();
		serviceType.setLv(1);
		List<ServiceTypeEntity> serviceTypeList = serviceTypeService.findList(serviceType, null, null);
		return ResultWrapper.success(serviceTypeList);
	}

}
