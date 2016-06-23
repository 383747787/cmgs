package com.zstu.bysj.cmgs.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author irving
 *
 */
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2661564531978689808L;

	private Integer id;

	private String name;

	private String password;

	private String realName;

	private String email;

	private Date createTime;

	private Date updateTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
