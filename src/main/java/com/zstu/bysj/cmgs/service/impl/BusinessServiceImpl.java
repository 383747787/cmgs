package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.BusinessMapper;
import com.zstu.bysj.cmgs.model.entity.BusinessEntity;
import com.zstu.bysj.cmgs.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Resource
	private BusinessMapper businessMapper;

	@Override
	public List<BusinessEntity> findList(BusinessEntity business, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (null != business) {
			param.put("name", business.getName());
			param.put("city", business.getCity());
		}
		return businessMapper.findList(param);
	}

	@Override
	public int findCount(BusinessEntity business) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != business) {
			param.put("name", business.getName());
			param.put("city", business.getCity());
		}
		return businessMapper.findCount(param);
	}

	@Override
	public BusinessEntity findById(Integer id) {
		return businessMapper.findById(id);
	}

	private void insert(BusinessEntity business) {
		businessMapper.insert(business);
	}

	private void update(BusinessEntity business) {
		businessMapper.update(business);
	}

	@Override
	public void save(BusinessEntity business) {
		BusinessEntity entity = this.findById(business.getId());
		if (null != entity) {
			this.update(business);
		} else {
			this.insert(business);
		}
	}

}
