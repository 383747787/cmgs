package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.BusinessEntity;

public interface BusinessMapper {

	List<BusinessEntity> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	BusinessEntity findById(Integer id);

	void insert(BusinessEntity business);

	void update(BusinessEntity business);

}