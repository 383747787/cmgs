package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.AutoSeriesMapper;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoSeriesService;

@Service
public class AutoSeriesServiceImpl implements AutoSeriesService {

	@Resource
	private AutoSeriesMapper autoSeriesMapper;

	@Override
	public List<AutoSeries> findList(AutoSeries autoSeries, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (null != autoSeries) {
			param.put("brandId", autoSeries.getBrandId());
			param.put("name", autoSeries.getName());
		}
		return autoSeriesMapper.findList(param);
	}

	@Override
	public int findCount(AutoSeries autoSeries) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != autoSeries) {
			param.put("brandId", autoSeries.getBrandId());
			param.put("name", autoSeries.getName());
		}
		return autoSeriesMapper.findCount(param);
	}

	@Override
	public List<AutoSeries> findList(Integer brandId) {
		AutoSeries autoSeries = new AutoSeries();
		autoSeries.setBrandId(brandId);
		return this.findList(autoSeries, null, null);
	}

	@Override
	public AutoSeries findById(Integer id) {
		return autoSeriesMapper.findById(id);
	}

	private void insert(AutoSeries autoSeries) {
		autoSeriesMapper.insert(autoSeries);
	}

	private void update(AutoSeries autoSeries) {
		autoSeriesMapper.update(autoSeries);
	}

	@Override
	public void save(AutoSeries autoSeries) {
		AutoSeries entity = this.findById(autoSeries.getId());
		if (null == entity) {
			this.insert(autoSeries);
		} else {
			this.update(autoSeries);
		}
	}

}
