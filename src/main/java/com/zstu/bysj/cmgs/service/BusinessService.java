package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.BusinessEntity;

/**
 * 商户
 * 
 * @author irving
 *
 */
public interface BusinessService {

	List<BusinessEntity> findList(BusinessEntity business, Integer start, Integer pageSize);

	int findCount(BusinessEntity business);

	BusinessEntity findById(Integer id);

	void save(BusinessEntity business);

}