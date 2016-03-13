package main.java.edu.scnu.service;

import main.java.edu.scnu.entity.SchoolClass;

public interface ClassService {
	/**
	 *  根据班级id获取班级
	 * @param id SchoolClass.id
	 * @return SchoolClass
	 */
	 SchoolClass getSchoolClassByid(int id);
}
