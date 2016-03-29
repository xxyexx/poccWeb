package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.School;

public interface DeptDao extends BaseDao<Dept>{
	
	/**
	 * 根据schoolID查询该学校的学院list
	 * @param id schoolID
	 * @return deptList
	 */
	List<Dept> findBySchoolID(int id);
	/**
	 * 根据学校ID和学院名称查询该学院
	 * @param school School对象
	 * @param deptName
	 * @return Dept
	 */
	Dept findByDeptName(School school,String deptName);
}
