package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.CarUserCarEntity;

/**
 * 车辆
 * 
 * @author irving
 *
 */
public interface CarUserCarService {

	List<CarUserCarEntity> findList(CarUserCarEntity carUser, Integer start, Integer pageSize);

	int findCount(CarUserCarEntity carUser);

	CarUserCarEntity findById(Integer id);

	void save(CarUserCarEntity car);

}