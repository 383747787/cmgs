package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.CarUserMapper;
import com.zstu.bysj.cmgs.model.entity.CarUserEntity;
import com.zstu.bysj.cmgs.service.CarUserService;

@Service
public class CarUserServiceImpl implements CarUserService {

	@Resource
	private CarUserMapper carUserMapper;

	@Override
	public List<CarUserEntity> findList(CarUserEntity carUser, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (carUser != null) {
			param.put("name", carUser.getName());
		}
		return carUserMapper.findList(param);
	}

	@Override
	public int findCount(CarUserEntity carUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (carUser != null) {
			param.put("name", carUser.getName());
		}
		return carUserMapper.findCount(param);
	}

	@Override
	public CarUserEntity findById(Integer id) {
		return carUserMapper.findById(id);
	}

	@Override
	public CarUserEntity findByPhone(String phone) {
		return carUserMapper.findByPhone(phone);
	}

	private void insert(CarUserEntity carUser) {
		carUserMapper.insert(carUser);
	}

	private void update(CarUserEntity carUser) {
		carUserMapper.update(carUser);
	}

	@Override
	public void save(CarUserEntity carUser) {
		CarUserEntity entity = this.findById(carUser.getId());
		if (null != entity) {
			this.update(carUser);
		} else {
			this.insert(carUser);
		}
	}

}
