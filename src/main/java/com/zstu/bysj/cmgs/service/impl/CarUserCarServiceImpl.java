package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.CarUserCarMapper;
import com.zstu.bysj.cmgs.model.entity.CarUserCarEntity;
import com.zstu.bysj.cmgs.service.CarUserCarService;

@Service
public class CarUserCarServiceImpl implements CarUserCarService {

	@Resource
	private CarUserCarMapper carUserCarMapper;

	@Override
	public List<CarUserCarEntity> findList(CarUserCarEntity carUser, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (null != carUser) {
			param.put("carNum", carUser.getCarNum());
		}
		return carUserCarMapper.findList(param);
	}

	@Override
	public int findCount(CarUserCarEntity carUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != carUser) {
			param.put("carNum", carUser.getCarNum());
		}
		return carUserCarMapper.findCount(param);
	}

	@Override
	public CarUserCarEntity findById(Integer id) {
		return carUserCarMapper.findById(id);
	}

	private void insert(CarUserCarEntity carUser) {
		carUserCarMapper.insert(carUser);
	}

	private void update(CarUserCarEntity carUser) {
		carUserCarMapper.update(carUser);
	}

	@Override
	public void save(CarUserCarEntity car) {
		CarUserCarEntity entity = this.findById(car.getId());
		if (null != entity) {
			this.update(car);
		} else {
			this.insert(car);
		}
	}

}
