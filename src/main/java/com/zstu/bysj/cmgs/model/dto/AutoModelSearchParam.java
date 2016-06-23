package com.zstu.bysj.cmgs.model.dto;

import java.math.BigDecimal;

public class AutoModelSearchParam {

	private Integer brandId;

	private Integer seriesId;

	private String name;

	private BigDecimal displacement;

	private String carEngineType;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getDisplacement() {
		return displacement;
	}

	public void setDisplacement(BigDecimal displacement) {
		this.displacement = displacement;
	}

	public String getCarEngineType() {
		return carEngineType;
	}

	public void setCarEngineType(String carEngineType) {
		this.carEngineType = carEngineType;
	}

}
