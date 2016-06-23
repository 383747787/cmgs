package com.zstu.bysj.cmgs.service;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.AutoModel;

/**
 * 车型
 * 
 * @author irving
 *
 */
public interface AutoModelService {

	List<AutoModel> findList(AutoModel autoModel, Integer start, Integer pageSize);

	int findCount(AutoModel autoModel);

	List<AutoModel> findList(Integer seriesId);

	AutoModel findById(Integer id);

	void save(AutoModel autoModel);

}