package com.zstu.bysj.cmgs.dao;

import java.util.List;
import java.util.Map;

import com.zstu.bysj.cmgs.model.entity.AutoModel;

public interface AutoModelMapper {

	List<AutoModel> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	AutoModel findById(Integer id);

	void insert(AutoModel autoModel);

	void update(AutoModel autoModel);

}