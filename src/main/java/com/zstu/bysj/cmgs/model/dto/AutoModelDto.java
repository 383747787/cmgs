package com.zstu.bysj.cmgs.model.dto;

import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.util.PageList;

public class AutoModelDto extends PageList<AutoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5679524825385367443L;

	private AutoModelSearchParam search;

	public AutoModelSearchParam getSearch() {
		return search;
	}

	public void setSearch(AutoModelSearchParam search) {
		this.search = search;
	}

}
