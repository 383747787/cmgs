package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;

public interface AutoBrandMapper {
	
	List<AutoBrand> findList(Map<String, Object> param);
    
	int findCount(Map<String, Object> param);
	
	AutoBrand findById(Integer id);
	
	void insert(AutoBrand autoBrand);
	
	void update(AutoBrand autoBrand);
	
}