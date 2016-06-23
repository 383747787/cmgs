package com.zstu.bysj.cmgs.model.dto;

import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.util.PageList;

public class AutoSeriesDto extends PageList<AutoSeries>{

	/**
	 * 
	 */
    private static final long serialVersionUID = 5679524825385367443L;
    
    private AutoSeriesSearchParam search;

	public AutoSeriesSearchParam getSearch() {
		return search;
	}

	public void setSearch(AutoSeriesSearchParam search) {
		this.search = search;
	}
    
}
