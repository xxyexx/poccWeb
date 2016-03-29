package main.java.edu.scnu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.DeptDao;
import main.java.edu.scnu.dao.SchoolClassDao;
import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ClassService;
import main.java.edu.scnu.util.PoccManager;

@Service("classService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ClassServiceImpl implements ClassService{
	@Resource(name="schoolClassDao")
	private SchoolClassDao schoolClassDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="deptDao")
	private DeptDao deptDao;
	
	@Override
	@Transactional(readOnly=true)
	public SchoolClass getSchoolClassByid(int id) {
		return schoolClassDao.get(SchoolClass.class, id);
	}
	@Override	
	@Transactional(readOnly=true)
	public List<SchoolClass> getClassAll() {
		return schoolClassDao.findAll(SchoolClass.class);
	}
	@Override
	@Transactional(readOnly=true)
	public List<User> getStudByClassID(String classID) {
		return userDao.findByClassID(classID);
	}
	@Override
	public void addClass(SchoolClass sc) {
		schoolClassDao.update(sc);
	}
	@Override
	public void deleteClass(String classID) {
		List<User> userList = userDao.findByClassID(classID);
		//先解散班级的学生，再删除班级
		if(userList!=null){
			for (User user : userList) {
				user.setClassID("0");
				user.setManagerID("");
				userDao.update(user);
			}
		}
		schoolClassDao.delete(SchoolClass.class, Integer.parseInt(classID));
	}
	@Override
	public void groupClass(List<String> userID, String classID) {
		String managerID = schoolClassDao
				.get(SchoolClass.class, Integer.parseInt(classID))
				.getTeacherID_acctID();//该班级管理者id
		for (String s : userID) {
			User user = userDao.get(User.class,Integer.parseInt(s));
			user.setClassID(classID);
			user.setManagerID(managerID);
			userDao.update(user);
		}
		SchoolClass school = getSchoolClassByid(Integer.parseInt(classID));
		school.setCurrentStudents(school.getCurrentStudents()+userID.size());
		schoolClassDao.update(school);
	}
	@Override
	public void deleteStudfromClass(List<String> userID, String classID) {
		for (String s : userID) {
			User user = userDao.get(User.class, Integer.parseInt(s));
			user.setClassID("0");
			user.setManagerID("");
			userDao.update(user);
		}
		SchoolClass school = schoolClassDao.get(SchoolClass.class, Integer.parseInt(classID));
		school.setCurrentStudents(school.getCurrentStudents()-userID.size());
		schoolClassDao.update(school);
	}
	@Override
	@Transactional(readOnly=true)
	public List<SchoolClass> getClassByTeacherID(String teacherID_acctID) {
		return schoolClassDao.getClassList(teacherID_acctID);
	}
	@Override
	@Transactional(readOnly=true)
	public List<User> getStudByDeptID(int deptID) {
		Dept dept = deptDao.get(Dept.class, deptID);
		List<User> list = new ArrayList<User>();
		List<User> userList = userDao
				.findByDeptName(dept.getSchool().getSchoolName(), dept.getDeptName());
		if(userList!=null){
			for(int i=0;i<userList.size();i++){
				User user = userList.get(i);
				if(user.getUserType().equals(PoccManager.student)
						&&(user.getClassID()==null
						||user.getClassID().equals("0"))){
					list.add(user);
				}
			}
		}
		return list;
	}
	//set
	public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
		this.schoolClassDao = schoolClassDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
}
