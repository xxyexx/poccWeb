package main.java.edu.scnu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.SchoolClassDao;
import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.service.ClassService;

@Service("classService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ClassServiceImpl implements ClassService{
	@Resource(name="schoolClassDao")
	private SchoolClassDao schoolClassDao;
	
	@Override
	public SchoolClass getSchoolClassByid(int id) {
		return schoolClassDao.get(SchoolClass.class, id);
	}

	//set
	public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
		this.schoolClassDao = schoolClassDao;
	}
}
