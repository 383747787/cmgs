package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;

/**
 * 品牌
 * 
 * @author irving
 *
 */
public interface AutoBrandService {

	List<AutoBrand> findList(AutoBrand autoBrand, Integer start, Integer pageSize);

	int findCount(AutoBrand autoBrand);

	List<AutoBrand> findList();

	AutoBrand findById(Integer id);

	void save(AutoBrand autoBrand);

}