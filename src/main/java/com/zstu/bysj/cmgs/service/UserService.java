package com.zstu.bysj.cmgs.service;

import com.zstu.bysj.cmgs.model.entity.UserEntity;

/**
 * 用户登录
 * 
 * @author irving
 *
 */
public interface UserService {

	UserEntity checkUser(UserEntity user);

	Boolean registerUser(UserEntity user);

	UserEntity findById(Integer id);

	void updateUser(UserEntity user);

}
