package com.zstu.bysj.cmgs.dao;

import com.zstu.bysj.cmgs.model.entity.UserEntity;

public interface UserMapper {

	UserEntity findByName(String name);

	UserEntity findById(Integer id);

	void insert(UserEntity user);

	void update(UserEntity user);

}