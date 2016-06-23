package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.AutoSeries;

public interface AutoSeriesMapper {
	
	List<AutoSeries> findList(Map<String, Object> param);
    
	int findCount(Map<String, Object> param);
	
	AutoSeries findById(Integer id);
	
	void insert(AutoSeries autoSeries);
	
	void update(AutoSeries autoSeries);
	
}