package com.zstu.bysj.cmgs.dao;

import java.util.List;

import com.zstu.bysj.cmgs.model.entity.MenuDataEntity;

public interface MenuDataMapper {
	
	List<MenuDataEntity> getMenuData();
	
	List<MenuDataEntity> getChildren(Integer id);
	
}
