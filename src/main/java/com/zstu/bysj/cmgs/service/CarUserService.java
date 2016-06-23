package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.CarUserEntity;

/**
 * 车主
 * 
 * @author irving
 *
 */
public interface CarUserService {

	List<CarUserEntity> findList(CarUserEntity carUser, Integer start, Integer pageSize);

	int findCount(CarUserEntity carUser);

	CarUserEntity findById(Integer id);

	CarUserEntity findByPhone(String phone);

	void save(CarUserEntity carUser);

}