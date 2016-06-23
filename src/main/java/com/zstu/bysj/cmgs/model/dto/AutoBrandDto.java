package com.zstu.bysj.cmgs.model.dto;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.util.PageList;

public class AutoBrandDto extends PageList<AutoBrand>{

	/**
	 * 
	 */
    private static final long serialVersionUID = 5679524825385367443L;
    
    private AutoBrandSearchParam search;

	public AutoBrandSearchParam getSearch() {
		return search;
	}

	public void setSearch(AutoBrandSearchParam search) {
		this.search = search;
	}
    
}
