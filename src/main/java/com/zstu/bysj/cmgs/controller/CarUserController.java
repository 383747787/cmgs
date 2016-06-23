package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.SearchDto;
import com.zstu.bysj.cmgs.model.entity.CarUserEntity;
import com.zstu.bysj.cmgs.service.CarUserService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 车主
 * 
 * @author irving
 *
 */
@Controller
public class CarUserController {

	@Resource
	CarUserService carUserService;

	/**
	 * 车主列表界面
	 */
	@RequestMapping("/car/user/list")
	public ModelAndView listView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/car/userList");
		return mv;
	}

	/**
	 * 车主编辑界面
	 */
	@RequestMapping("/car/user/edit")
	public ModelAndView editView(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("jsp/car/userEdit");
		return mv;
	}

	/**
	 * 获得车主列表
	 */
	@RequestMapping("/car/user/list/get/data")
	public @ResponseBody SearchDto<CarUserEntity> getCarUserList(@RequestBody SearchDto<CarUserEntity> param) {
		CarUserEntity carUser = new CarUserEntity();
		carUser.setName(param.getSearch().getName());
		List<CarUserEntity> carUserList = carUserService.findList(carUser, param.getStart(), param.getPageSize());
		param.setData(carUserList);
		param.setTotalSize(carUserService.findCount(carUser));
		return param;
	}

	/**
	 * 根据id查询
	 */
	@RequestMapping("/car/user/find")
	public @ResponseBody String findById(Integer id) {
		CarUserEntity carUser = carUserService.findById(id);
		return ResultWrapper.success(carUser);
	}

	/**
	 * 根据手机号查询
	 */
	@RequestMapping("/car/user/find/phone")
	public @ResponseBody String findByPhone(String phone) {
		CarUserEntity carUser = carUserService.findByPhone(phone);
		return ResultWrapper.success(carUser);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/car/user/save")
	public @ResponseBody String saveCarUser(@RequestBody CarUserEntity carUser) {
		carUserService.save(carUser);
		return ResultWrapper.success("车主信息更新成功");
	}

}
