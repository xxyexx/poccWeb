package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.Homework;

public interface HomeworkDao extends BaseDao<Homework>{

	/**
	 * 根据classID查询所有的作业
	 * @param classID
	 * @return List
	 */
	List<Homework> findByClassID(String classID);
	/**
	 * 根据教师内部账号id查询该教师发布的作业
	 * @param acctID
	 * @return
	 */
	List<Homework> findByTeacher_acctID(String acctID);
}
