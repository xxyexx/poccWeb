package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.SchoolClassDao;
import main.java.edu.scnu.entity.SchoolClass;

@Repository(value="schoolClassDao")
public class SchoolClassDaoImpl extends BaseDaoImpl<SchoolClass> 
	implements SchoolClassDao
{

}
