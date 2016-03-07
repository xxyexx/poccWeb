package main.java.edu.scnu.service;

import java.util.List;

import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;

public interface HomeworkService {
	/**
	 * 查询该班级的新作业(截止时间没有超过今天)
	 * @param classID 班级id
	 * @return List
	 */
	List<Homework> getNewHW(String classID);
	/**
	 * 查询该班级所有作业
	 * @param classID 班级id
	 * @return List
	 */
	List<Homework> getAllHW(String classID);
	/**
	 * 查询自己未完成的作业
	 * @param classID 班级id
	 * @param studNo 学号
	 * @return List
	 */
	List<Homework> getUnfinishedHW(String classID,String studNo);
	/**
	 * 查询自己已提交的作业列表
	 * @param classID 班级id
	 * @param studNo 学号
	 * @return List
	 */
	List<HWSubmit> getfinishedHW(String classID,String studNo);
}
