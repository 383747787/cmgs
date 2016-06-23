package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.ServiceTypeEntity;

public interface ServiceTypeMapper {

	List<ServiceTypeEntity> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	ServiceTypeEntity findById(Integer id);

	void insert(ServiceTypeEntity serviceType);

	void update(ServiceTypeEntity serviceType);

}