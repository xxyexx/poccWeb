package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.SchoolClassDao;
import main.java.edu.scnu.entity.SchoolClass;

@Repository(value="schoolClassDao")
public class SchoolClassDaoImpl extends BaseDaoImpl<SchoolClass> 
	implements SchoolClassDao
{

	@Override
	public List<SchoolClass> getClassList(String teacherID_acctID) {
		String hql = "select c from schoolclass c where c.teacherID_acctID = ?";
		return 	find(hql, teacherID_acctID);
	}

}
