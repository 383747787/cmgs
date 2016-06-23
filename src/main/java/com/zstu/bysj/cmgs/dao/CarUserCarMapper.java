package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.CarUserCarEntity;

public interface CarUserCarMapper {

	List<CarUserCarEntity> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	CarUserCarEntity findById(Integer id);

	void insert(CarUserCarEntity car);

	void update(CarUserCarEntity car);

}