package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.ConfigDao;
import main.java.edu.scnu.entity.Config;

@Repository(value="configDao")
public class ConfigDaoImpl  extends BaseDaoImpl<Config> 
	implements ConfigDao
{
	
}
