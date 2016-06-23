package com.zstu.bysj.cmgs.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 车型
 * 
 * @author irving
 *
 */
public class AutoModel {
	// 车型ID
	private Integer id;
	// 对象品牌的主键ID
	private Integer seriesId;
	// 车型名称
	private String name;
	// 车身结构
	private BigDecimal guidePrice;
	// 车身尺寸
	private String carSize;
	// 车门数
	private Integer carDoors;
	// 车座数
	private Integer carSeats;
	// 排量
	private BigDecimal displacement;
	// 马力
	private BigDecimal carHorsepower;
	// 发动机形式
	private String carEngineType;
	// 驱动方式
	private String carDrivingWay;
	// 车主油耗
	private BigDecimal ownerFuelConsumption;
	// 综合油耗
	private BigDecimal combinedFuelConsumption;
	// 整车质保
	private String vehicleWarranty;
	// 变速箱
	private String transmission;
	// 用户评分
	private BigDecimal ownerScore;
	// 是否涡轮增压
	private Boolean isTurbo;
	// 车身质量
	private BigDecimal carValue;
	// 前轮
	private String frontTyreSize;
	// 后轮
	private String rearTyreSize;
	// 车型洗车类型
	private Integer modelWashType;
	// 大小车型
	private Integer washType;
	// 是否有效
	private Integer isValid;
	// 创建时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	// 修改时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifyTime;
	// 图片
	private String imgUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getGuidePrice() {
		return guidePrice;
	}

	public void setGuidePrice(BigDecimal guidePrice) {
		this.guidePrice = guidePrice;
	}

	public String getCarSize() {
		return carSize;
	}

	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}

	public Integer getCarDoors() {
		return carDoors;
	}

	public void setCarDoors(Integer carDoors) {
		this.carDoors = carDoors;
	}

	public Integer getCarSeats() {
		return carSeats;
	}

	public void setCarSeats(Integer carSeats) {
		this.carSeats = carSeats;
	}

	public BigDecimal getDisplacement() {
		return displacement;
	}

	public void setDisplacement(BigDecimal displacement) {
		this.displacement = displacement;
	}

	public BigDecimal getCarHorsepower() {
		return carHorsepower;
	}

	public void setCarHorsepower(BigDecimal carHorsepower) {
		this.carHorsepower = carHorsepower;
	}

	public String getCarEngineType() {
		return carEngineType;
	}

	public void setCarEngineType(String carEngineType) {
		this.carEngineType = carEngineType;
	}

	public String getCarDrivingWay() {
		return carDrivingWay;
	}

	public void setCarDrivingWay(String carDrivingWay) {
		this.carDrivingWay = carDrivingWay;
	}

	public BigDecimal getOwnerFuelConsumption() {
		return ownerFuelConsumption;
	}

	public void setOwnerFuelConsumption(BigDecimal ownerFuelConsumption) {
		this.ownerFuelConsumption = ownerFuelConsumption;
	}

	public BigDecimal getCombinedFuelConsumption() {
		return combinedFuelConsumption;
	}

	public void setCombinedFuelConsumption(BigDecimal combinedFuelConsumption) {
		this.combinedFuelConsumption = combinedFuelConsumption;
	}

	public String getVehicleWarranty() {
		return vehicleWarranty;
	}

	public void setVehicleWarranty(String vehicleWarranty) {
		this.vehicleWarranty = vehicleWarranty;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public BigDecimal getOwnerScore() {
		return ownerScore;
	}

	public void setOwnerScore(BigDecimal ownerScore) {
		this.ownerScore = ownerScore;
	}

	public Boolean getIsTurbo() {
		return isTurbo;
	}

	public void setIsTurbo(Boolean isTurbo) {
		this.isTurbo = isTurbo;
	}

	public BigDecimal getCarValue() {
		return carValue;
	}

	public void setCarValue(BigDecimal carValue) {
		this.carValue = carValue;
	}

	public String getFrontTyreSize() {
		return frontTyreSize;
	}

	public void setFrontTyreSize(String frontTyreSize) {
		this.frontTyreSize = frontTyreSize;
	}

	public String getRearTyreSize() {
		return rearTyreSize;
	}

	public void setRearTyreSize(String rearTyreSize) {
		this.rearTyreSize = rearTyreSize;
	}

	public Integer getModelWashType() {
		return modelWashType;
	}

	public void setModelWashType(Integer modelWashType) {
		this.modelWashType = modelWashType;
	}

	public Integer getWashType() {
		return washType;
	}

	public void setWashType(Integer washType) {
		this.washType = washType;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
