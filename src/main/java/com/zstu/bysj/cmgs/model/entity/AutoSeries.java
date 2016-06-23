package com.zstu.bysj.cmgs.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 车系
 * 
 * @author irving
 *
 */
public class AutoSeries {
	// 车系id
	private Integer id;
	// 品牌id
	private Integer brandId;
	// 车系名称
	private String name;
	// 车系拼音
	private String mark;
	// 最低价格
	private BigDecimal guidePriceLow;
	// 最高价格
	private BigDecimal guidePriceHigh;
	// 种类
	private String classify;
	// 大小车型
	private Integer washType;
	// 洗车类型
	private Integer washCarType;
	// 是否有效
	private Integer isValid;
	// 创建时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	// 更新时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public BigDecimal getGuidePriceLow() {
		return guidePriceLow;
	}

	public void setGuidePriceLow(BigDecimal guidePriceLow) {
		this.guidePriceLow = guidePriceLow;
	}

	public BigDecimal getGuidePriceHigh() {
		return guidePriceHigh;
	}

	public void setGuidePriceHigh(BigDecimal guidePriceHigh) {
		this.guidePriceHigh = guidePriceHigh;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public Integer getWashType() {
		return washType;
	}

	public void setWashType(Integer washType) {
		this.washType = washType;
	}

	public Integer getWashCarType() {
		return washCarType;
	}

	public void setWashCarType(Integer washCarType) {
		this.washCarType = washCarType;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
