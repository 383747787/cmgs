package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.AutoSeries;

/**
 * 车系
 * 
 * @author irving
 *
 */
public interface AutoSeriesService {

	List<AutoSeries> findList(AutoSeries autoSeries, Integer start, Integer pageSize);

	int findCount(AutoSeries autoSeries);

	List<AutoSeries> findList(Integer brandId);

	AutoSeries findById(Integer id);

	void save(AutoSeries autoSeries);

}