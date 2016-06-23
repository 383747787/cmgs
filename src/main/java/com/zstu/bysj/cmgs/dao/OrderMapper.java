package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.OrderEntity;

public interface OrderMapper {

	List<OrderEntity> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	OrderEntity findById(Integer id);

	void insert(OrderEntity order);

	void update(OrderEntity order);

}