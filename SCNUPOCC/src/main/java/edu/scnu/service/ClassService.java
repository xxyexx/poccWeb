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
}
