package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.SchoolClass;

public interface SchoolClassDao extends BaseDao<SchoolClass>{

	/**
	 * 查询该教师管理的所有班级
	 * @param teacherID_acctID 教师内部账号id
	 * @return ClassList
	 */
	List<SchoolClass> getClassList(String teacherID_acctID);
}
