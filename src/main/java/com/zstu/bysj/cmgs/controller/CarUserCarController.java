package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.SearchDto;
import com.zstu.bysj.cmgs.model.entity.CarUserCarEntity;
import com.zstu.bysj.cmgs.service.CarUserCarService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 车辆
 * 
 * @author irving
 *
 */
@Controller
public class CarUserCarController {

	@Resource
	CarUserCarService carUserCarService;

	/**
	 * 车辆列表界面
	 */
	@RequestMapping("/car/list")
	public ModelAndView listView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/car/carList");
		return mv;
	}

	/**
	 * 车辆编辑界面
	 */
	@RequestMapping("/car/edit")
	public ModelAndView editView(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("jsp/car/carEdit");
		return mv;
	}

	/**
	 * 获得车辆列表
	 */
	@RequestMapping("/car/list/get/data")
	public @ResponseBody SearchDto<CarUserCarEntity> getCarList(@RequestBody SearchDto<CarUserCarEntity> param) {
		CarUserCarEntity car = new CarUserCarEntity();
		car.setCarNum(param.getSearch().getCarNum());
		List<CarUserCarEntity> carUserList = carUserCarService.findList(car, param.getStart(), param.getPageSize());
		param.setData(carUserList);
		param.setTotalSize(carUserCarService.findCount(car));
		return param;
	}

	/**
	 * 根据id查询
	 */
	@RequestMapping("/car/find")
	public @ResponseBody String findById(Integer id) {
		CarUserCarEntity carUserCar = carUserCarService.findById(id);
		return ResultWrapper.success(carUserCar);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/car/save")
	public @ResponseBody String saveCar(@RequestBody CarUserCarEntity car) {
		carUserCarService.save(car);
		return ResultWrapper.success("车辆信息更新成功");
	}

}
