package com.zstu.bysj.cmgs.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.zstu.bysj.cmgs.model.entity.AutoModel;

public class SeriesParseDto {
	// 车系ID
	private int seriesId;
	// 车系名称
	private String seriesName;
	// 车系级别
	private String seriesClassify;
	// 车系车型列表
	private List<AutoModel> models = new ArrayList<AutoModel>();

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSeriesClassify() {
		return seriesClassify;
	}

	public void setSeriesClassify(String seriesClassify) {
		this.seriesClassify = seriesClassify;
	}

	public List<AutoModel> getModels() {
		return models;
	}

	public void addModel(AutoModel model) {
		this.models.add(model);
	}
}
