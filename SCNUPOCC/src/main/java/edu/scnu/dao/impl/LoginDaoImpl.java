package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.LoginDao;
import main.java.edu.scnu.entity.Login;

@Repository(value="loginDao")
public class LoginDaoImpl extends BaseDaoImpl<Login> 
	implements LoginDao
{

}
