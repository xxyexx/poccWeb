package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.Dept;

public interface DeptDao extends BaseDao<Dept>{
	
	/**
	 * 根据schoolID查询该学校的学院list
	 * @param id schoolID
	 * @return deptList
	 */
	List<Dept> findBySchoolID(int id);
}
