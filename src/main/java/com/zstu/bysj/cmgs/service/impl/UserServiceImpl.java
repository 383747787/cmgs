package com.zstu.bysj.cmgs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.UserMapper;
import com.zstu.bysj.cmgs.model.entity.UserEntity;
import com.zstu.bysj.cmgs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public UserEntity checkUser(UserEntity loginUser) {
		UserEntity user = userMapper.findByName(loginUser.getName());
		if (user == null) {
			return null;
		}
		if (!user.getPassword().equals(loginUser.getPassword())) {
			return null;
		}
		return user;
	}

	@Override
	public Boolean registerUser(UserEntity registerUser) {
		UserEntity user = userMapper.findByName(registerUser.getName());
		if (user != null) {
			return false;
		}
		userMapper.insert(registerUser);
		return true;
	}

	@Override
	public UserEntity findById(Integer id) {
		return userMapper.findById(id);
	}

	@Override
	public void updateUser(UserEntity user) {
		userMapper.update(user);
	}

}
