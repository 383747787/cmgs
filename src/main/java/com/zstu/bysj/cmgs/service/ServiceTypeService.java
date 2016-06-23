package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.ServiceTypeEntity;

/**
 * 服务种类
 * 
 * @author irving
 *
 */
public interface ServiceTypeService {

	List<ServiceTypeEntity> findList(ServiceTypeEntity serviceType, Integer start, Integer pageSize);

	int findCount(ServiceTypeEntity serviceType);

	ServiceTypeEntity findById(Integer id);

	void save(ServiceTypeEntity serviceType);

}