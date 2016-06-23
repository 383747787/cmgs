package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.CarUserEntity;

public interface CarUserMapper {

	List<CarUserEntity> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	CarUserEntity findById(Integer id);

	CarUserEntity findByPhone(String phone);

	void insert(CarUserEntity carUser);

	void update(CarUserEntity carUser);

}