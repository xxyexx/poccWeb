package main.java.edu.scnu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.SchoolClassDao;
import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ClassService;

@Service("classService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ClassServiceImpl implements ClassService{
	@Resource(name="schoolClassDao")
	private SchoolClassDao schoolClassDao;
	@Resource(name="userDao")
	private UserDao userDao;
	
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
	public List<User> getStudByClassID(String classID) {
		return userDao.findByClassID(classID);
	}
	//set
	public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
		this.schoolClassDao = schoolClassDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
