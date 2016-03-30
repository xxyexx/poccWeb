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
	 * 查询该班级所有作业,修改该班级超过提交期限的作业
	 * homework.state=0不予许提交
	 * @param classID 班级id
	 * @return List
	 */
	List<Homework> getAllHW(String classID);
	/**
	 * 查询自己未完成的作业
	 * @param classID 班级id
	 * @param id user.id
	 * @return List
	 */
	List<Homework> getUnfinishedHW(String classID,int id);
	/**
	 * 查询自己已提交的作业列表
	 * @param classID 班级id
	 * @param id 学生id
	 * @return List
	 */
	List<HWSubmit> getfinishedHW(String classID,int id);
	
	/**
	 * 根据作业id获取作业
	 * @param id 作业id
	 * @return Homework
	 */
	Homework getHomeworkbyID(int id);
	/**
	 * 根据教师内部账号查询教师发布的所有作业
	 * @param teacher_acctID
	 * @return
	 */
	List<Homework> getHomeworkbyTeacherID(String teacher_acctID);
	/**
	 * 根据学生id和该作业的id获取提交的作业
	 * @param stud_id user.id
	 * @param hwID
	 * @return HWSubmit
	 */
	HWSubmit getHWSubmit(int stud_id,int hwID);
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
	/**
	 * 教师添加新作业
	 * @param homework
	 * @return
	 */
	boolean addHomework(Homework homework);
	/**
	 * 教师撤销作业
	 * @param id 作业id
	 */
	void deleteHomework(int id);
	/**
	 * 教师修改作业信息
	 * @param homework
	 */
	void updateHomework(Homework homework);
	/**
	 * 教师查询已完成作业学生列表
	 * @param hwID 作业id
	 * @return HWSubmit List
	 */
	List<HWSubmit> getfinishedHWSList(int hwID);
}
