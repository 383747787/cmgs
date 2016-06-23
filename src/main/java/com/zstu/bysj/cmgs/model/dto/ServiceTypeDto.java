package com.zstu.bysj.cmgs.model.dto;

import com.zstu.bysj.cmgs.model.entity.ServiceTypeEntity;
import com.zstu.bysj.cmgs.util.PageList;

public class ServiceTypeDto extends PageList<ServiceTypeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6223416150974681394L;
	private ServiceTypeSearchParam search;

	public ServiceTypeSearchParam getSearch() {
		return search;
	}

	public void setSearch(ServiceTypeSearchParam search) {
		this.search = search;
	}

}
