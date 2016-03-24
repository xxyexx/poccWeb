package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.DeptDao;
import main.java.edu.scnu.entity.Dept;

@Repository(value="deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept> 
	implements DeptDao
{
	

}
