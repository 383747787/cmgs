package com.zstu.bysj.cmgs.model.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 订单
 * 
 * @author irving
 *
 */
public class OrderEntity {

	private Integer id;

	private Integer type;

	private Integer businessId;

	private String businessName;

	private Integer userId;

	private String userPhone;

	private Integer lv1ServiceType;

	private String lv1ServiceName;

	private Integer lv2ServiceType;

	private String lv2ServiceName;

	private String packageName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date paidTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifyTime;

	private Integer isDeleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getLv1ServiceType() {
		return lv1ServiceType;
	}

	public void setLv1ServiceType(Integer lv1ServiceType) {
		this.lv1ServiceType = lv1ServiceType;
	}

	public Integer getLv2ServiceType() {
		return lv2ServiceType;
	}

	public void setLv2ServiceType(Integer lv2ServiceType) {
		this.lv2ServiceType = lv2ServiceType;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Date getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getLv1ServiceName() {
		return lv1ServiceName;
	}

	public void setLv1ServiceName(String lv1ServiceName) {
		this.lv1ServiceName = lv1ServiceName;
	}

	public String getLv2ServiceName() {
		return lv2ServiceName;
	}

	public void setLv2ServiceName(String lv2ServiceName) {
		this.lv2ServiceName = lv2ServiceName;
	}

}
