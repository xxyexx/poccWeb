package main.java.edu.scnu.service;

import java.util.List;

import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.User;

public interface ClassService {
	/**
	 *  根据班级id获取班级
	 * @param id SchoolClass.id
	 * @return SchoolClass
	 */
	 SchoolClass getSchoolClassByid(int id);
	 /**
	  * 获取全部的Schoolclass
	  * @return list
	  */
	 List<SchoolClass> getClassAll();
	 /**
	  * 根据班级id获取这个班级所有学生
	  * @param String user.classID
	  * @return user list
	  */
	 List<User> getStudByClassID(String classID);
	 /**
	  * 获取教师管理的所有班级
	  * @param teacherID_acctID 教师内部账号id
	  * @return SchoolClass list
	  */
	 List<SchoolClass> getClassByTeacherID(String teacherID_acctID);
	 /**
	  * 教师添加班级
	  * @param sc
	  */
	 void addClass(SchoolClass sc);
	 /**
	  * 教师删除班级(先解散学生再删除班级)
	  * @param classID
	  * @return
	  */
	 void deleteClass(String classID);
	 /**
	  * 教师添加学生进班级
	  * @param userID 学生ID列表
	  * @param classID 班级ID
	  */
	 void groupClass(List<String> userID,String classID);
	 /**
	  * 教师移除班级内的学生
	  * @param userID
	  * @param classID
	  */
	 void deleteStudfromClass(List<String> userID,String classID);
	 /**
	  * 教师根据学院id查询没有被分班的学生（classID=0）
	  * @param deptID 学院id
	  * @return studentlist
	  */
	 List<User> getStudByDeptID(int deptID);
}
