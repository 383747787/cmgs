package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.OrderEntity;

/**
 * 订单
 * 
 * @author irving
 *
 */
public interface OrderService {

	List<OrderEntity> findList(OrderEntity order, Integer start, Integer pageSize);

	int findCount(OrderEntity order);

	OrderEntity findById(Integer id);

	void save(OrderEntity order);

}