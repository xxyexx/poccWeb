package main.java.edu.scnu.service;

import java.util.Map;

public interface ProvinceService {
	/**
	 * 获取省份Map
	 * @return Map:key(省份ID)value(省份名)
	 */
	Map<Integer, String> getProvinceMap();
}
