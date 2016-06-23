package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.OrderMapper;
import com.zstu.bysj.cmgs.model.entity.OrderEntity;
import com.zstu.bysj.cmgs.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderMapper orderMapper;

	@Override
	public List<OrderEntity> findList(OrderEntity order, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (null != order) {
			param.put("userPhone", order.getUserPhone());
			param.put("businessName", order.getBusinessName());
		}
		return orderMapper.findList(param);
	}

	@Override
	public int findCount(OrderEntity order) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != order) {
			param.put("userPhone", order.getUserPhone());
			param.put("businessName", order.getBusinessName());
		}
		return orderMapper.findCount(param);
	}

	@Override
	public OrderEntity findById(Integer id) {
		return orderMapper.findById(id);
	}

	private void insert(OrderEntity order) {
		orderMapper.insert(order);
	}

	private void update(OrderEntity order) {
		orderMapper.update(order);
	}

	@Override
	public void save(OrderEntity order) {
		OrderEntity entity = this.findById(order.getId());
		if (null != entity) {
			this.update(order);
		} else {
			this.insert(order);
		}
	}

}
