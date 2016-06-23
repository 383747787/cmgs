package com.zstu.bysj.cmgs.model.dto;

import com.zstu.bysj.cmgs.util.PageList;

public class SearchDto<T> extends PageList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873218066911282125L;
	private SearchParam search;

	public SearchParam getSearch() {
		return search;
	}

	public void setSearch(SearchParam search) {
		this.search = search;
	}

}
