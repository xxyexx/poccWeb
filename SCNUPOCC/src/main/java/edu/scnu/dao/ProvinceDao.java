package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.Province;

public interface ProvinceDao extends BaseDao<Province>{	
	
	/**
	 * 查找正在启用的省份
	 * @return
	 */
	public List<Province> findIsUsing();
	
}
