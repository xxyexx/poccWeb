package main.java.edu.scnu.service;

import java.util.Map;

import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.School;

public interface ProvinceService {
	
	Map<String, String> getProvinceMap();
	
	/**
	 * 根据省份ID获取该省所有的学校
	 * @param provinceID 省份ID
	 * @return Map:key(学校ID)value(学校名)
	 */
	Map<String, String> getSchoolMap(int provinceID);
	
	/**
	 * 根据学校ID获取该学校所有学院
	 * @param schoolID 学校ID
	 * @return Map:key(学院ID)value(学院)
	 */
	Map<String, String> getDeptMap(int schoolID);
	/**
	 * 根据学校名称获取该学校所有学院
	 * @param schoolName user.acctTag
	 * @return Map:key(学院ID)value(学院)
	 */
	Map<String, String> getDeptMapBySchool(String schoolName);
	
	/**
	 * 根据学校ID获取该学校
	 * @param schoolID 学校ID
	 * @return
	 */
	School getSchool(int schoolID);	
	
	/**
	 * 根据学院ID获取该学院
	 * @param deptID
	 * @return
	 */
	Dept getDept(int deptID);
	/**
	 * 根据学校名和学院名获取该学院
	 * @param deptname
	 * @return
	 */
	Dept getDeptBydeptname(String schoolname,String deptname);
}
