package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.School;

public interface SchoolDao extends BaseDao<School>{
	/**
	 * 根据ProvinceID查询该省的学校list
	 * @param id ProvinceID
	 * @return schoolList
	 */
	List<School> findByProvinceID(int id);
	/**
	 * 根据省份名查询该省的学校list
	 * @param provinceName 省份名
	 * @return schoolList
	 */
	List<School> findByProvinceName(String provinceName);
	/**
	 * 根据学校名查询该学校
	 * @param schoolName
	 * @return School
	 */
	School findSchoolByName(String schoolName);
}
