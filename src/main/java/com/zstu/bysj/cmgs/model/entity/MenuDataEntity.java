package com.zstu.bysj.cmgs.model.entity;

import java.io.Serializable;
import java.util.List;

public class MenuDataEntity implements Serializable{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 7486825551082236126L;

	private Integer id;
	
	private Boolean active;
	
	private Integer doLog;
	
	private Integer getLog;
	
	private Integer isVirtual;
	
	private String name;
	
	private Integer type;
	
	private String path;
	
	private Integer parent;
	
	private List<MenuDataEntity> children; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getDoLog() {
		return doLog;
	}

	public void setDoLog(Integer doLog) {
		this.doLog = doLog;
	}

	public Integer getGetLog() {
		return getLog;
	}

	public void setGetLog(Integer getLog) {
		this.getLog = getLog;
	}

	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public List<MenuDataEntity> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDataEntity> children) {
		this.children = children;
	}
	
}
