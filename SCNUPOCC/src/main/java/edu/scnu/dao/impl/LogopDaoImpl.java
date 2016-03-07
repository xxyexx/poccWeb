package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.LogopDao;
import main.java.edu.scnu.entity.Logop;

@Repository(value="logopDao")
public class LogopDaoImpl extends BaseDaoImpl<Logop>
	implements LogopDao
{

}
