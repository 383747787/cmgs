package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.SearchDto;
import com.zstu.bysj.cmgs.model.entity.BusinessEntity;
import com.zstu.bysj.cmgs.service.BusinessService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 商户
 * 
 * @author irving
 *
 */
@Controller
public class BusinessController {

	@Resource
	BusinessService businessService;

	/**
	 * 商户列表界面
	 */
	@RequestMapping("/business/list")
	public ModelAndView listView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/business/businessList");
		return mv;
	}

	/**
	 * 商户编辑界面
	 */
	@RequestMapping("/business/edit")
	public ModelAndView editView(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("jsp/business/businessEdit");
		return mv;
	}

	/**
	 * 获得商户列表
	 */
	@RequestMapping("/business/list/get/data")
	public @ResponseBody SearchDto<BusinessEntity> getBusinessList(@RequestBody SearchDto<BusinessEntity> param) {
		BusinessEntity business = new BusinessEntity();
		business.setName(param.getSearch().getName());
		business.setCity(param.getSearch().getCity());
		List<BusinessEntity> businessList = businessService.findList(business, param.getStart(), param.getPageSize());
		param.setData(businessList);
		param.setTotalSize(businessService.findCount(business));
		return param;
	}

	/**
	 * 根据id查询
	 */
	@RequestMapping("/business/find")
	public @ResponseBody String findById(Integer id) {
		BusinessEntity business = businessService.findById(id);
		return ResultWrapper.success(business);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/business/save")
	public @ResponseBody String saveBusiness(@RequestBody BusinessEntity business) {
		businessService.save(business);
		return ResultWrapper.success("商户信息更新成功");
	}

}
