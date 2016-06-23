package com.zstu.bysj.cmgs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zstu.bysj.cmgs.dao.AutoBrandMapper;
import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.service.AutoBrandService;

@Service
public class AutoBrandServiceImpl implements AutoBrandService {

	@Resource
	private AutoBrandMapper autoBrandMapper;

	@Override
	public List<AutoBrand> findList(AutoBrand autoBrand, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);
		if (null != autoBrand) {
			param.put("name", autoBrand.getName());
		}
		return autoBrandMapper.findList(param);
	}

	@Override
	public int findCount(AutoBrand autoBrand) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != autoBrand) {
			param.put("name", autoBrand.getName());
		}
		return autoBrandMapper.findCount(param);
	}

	@Override
	public List<AutoBrand> findList() {
		return this.findList(null, null, null);
	}

	@Override
	public AutoBrand findById(Integer id) {
		return autoBrandMapper.findById(id);
	}

	private void insert(AutoBrand autoBrand) {
		autoBrandMapper.insert(autoBrand);
	}

	private void update(AutoBrand autoBrand) {
		autoBrandMapper.update(autoBrand);
	}

	@Override
	public void save(AutoBrand autoBrand) {
		AutoBrand entity = this.findById(autoBrand.getId());
		if (null == entity) {
			this.insert(autoBrand);
		} else {
			this.update(autoBrand);
		}

	}

}
