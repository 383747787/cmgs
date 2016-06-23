package com.zstu.bysj.cmgs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zstu.bysj.cmgs.model.dto.SearchDto;
import com.zstu.bysj.cmgs.model.entity.OrderEntity;
import com.zstu.bysj.cmgs.service.OrderService;
import com.zstu.bysj.cmgs.util.ResultWrapper;

/**
 * 订单
 * 
 * @author irving
 *
 */
@Controller
public class OrderController {

	@Resource
	OrderService orderService;

	/**
	 * 订单列表界面
	 */
	@RequestMapping("/order/list")
	public ModelAndView listView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/order/orderList");
		return mv;
	}

	/**
	 * 订单编辑界面
	 */
	@RequestMapping("/order/edit")
	public ModelAndView editView(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("jsp/order/orderEdit");
		return mv;
	}

	/**
	 * 获得订单列表
	 */
	@RequestMapping("/order/list/get/data")
	public @ResponseBody SearchDto<OrderEntity> getOrderList(@RequestBody SearchDto<OrderEntity> param) {
		OrderEntity order = new OrderEntity();
		order.setUserPhone(param.getSearch().getUserPhone());
		order.setBusinessName(param.getSearch().getBusinessName());
		List<OrderEntity> orderList = orderService.findList(order, param.getStart(), param.getPageSize());
		param.setData(orderList);
		param.setTotalSize(orderService.findCount(order));
		return param;
	}

	/**
	 * 根据id查询
	 */
	@RequestMapping("/order/find")
	public @ResponseBody String findById(Integer id) {
		OrderEntity order = orderService.findById(id);
		return ResultWrapper.success(order);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/order/save")
	public @ResponseBody String saveOrder(@RequestBody OrderEntity order) {
		orderService.save(order);
		return ResultWrapper.success("订单信息更新成功");
	}

}
