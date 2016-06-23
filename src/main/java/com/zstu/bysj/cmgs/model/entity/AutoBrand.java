package com.zstu.bysj.cmgs.model.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 品牌
 * 
 * @author irving
 *
 */
public class AutoBrand {
	// 品牌ID
	private Integer id;
	// 品牌名
	private String name;
	// 拼音首字母
	private String alphaCode;
	// 品牌全拼标识
	private String mark;
	// 品牌图标
	private String icon;
	// 是否有效
	private Integer isValid;
	// 创建时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	// 修改时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.alphaCode = StringUtils.left(StringUtils.upperCase(this.mark), 1);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAlphaCode() {
		return alphaCode;
	}

	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

}