package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.ServiceTypeMapper;
import com.zstu.bysj.cmgs.model.entity.ServiceTypeEntity;
import com.zstu.bysj.cmgs.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

	@Resource
	private ServiceTypeMapper serviceTypeMapper;

	@Override
	public List<ServiceTypeEntity> findList(ServiceTypeEntity serviceType, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (null != serviceType) {
			param.put("lv", serviceType.getLv());
			param.put("parent", serviceType.getParent());
			param.put("name", serviceType.getName());
		}
		return serviceTypeMapper.findList(param);
	}

	@Override
	public int findCount(ServiceTypeEntity serviceType) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != serviceType) {
			param.put("lv", serviceType.getLv());
			param.put("parent", serviceType.getParent());
			param.put("name", serviceType.getName());
		}
		return serviceTypeMapper.findCount(param);
	}

	@Override
	public ServiceTypeEntity findById(Integer id) {
		return serviceTypeMapper.findById(id);
	}

	private void insert(ServiceTypeEntity serviceType) {
		serviceTypeMapper.insert(serviceType);
	}

	private void update(ServiceTypeEntity serviceType) {
		serviceTypeMapper.update(serviceType);
	}

	@Override
	public void save(ServiceTypeEntity serviceType) {
		ServiceTypeEntity entity = this.findById(serviceType.getId());
		if (null != entity) {
			this.update(serviceType);
		} else {
			this.insert(serviceType);
		}
	}

}
