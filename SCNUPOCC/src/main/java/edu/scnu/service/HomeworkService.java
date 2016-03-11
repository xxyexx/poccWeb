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
	
	/**
	 * 根据作业id获取作业
	 * @param id 作业id
	 * @return Homework
	 */
	Homework getHomeworkbyID(int id);
	/**
	 * 根据学生账号和该作业的id获取提交的作业
	 * @param stud_acctID
	 * @param hwID
	 * @return HWSubmit
	 */
	HWSubmit getHWSubmit(String stud_acctID,int hwID);
	/**
	 * saveOrUpdate保存或更新作业上传
	 * @param hwSubmit
	 * @return
	 */
	boolean saveHWSubmit(HWSubmit hwSubmit);
	/**
	 * 根据id获取已上传作业记录
	 * @param hwSubmitID
	 * @return
	 */
	HWSubmit getHWSubmitByid(int hwSubmitID);
	
	
}
