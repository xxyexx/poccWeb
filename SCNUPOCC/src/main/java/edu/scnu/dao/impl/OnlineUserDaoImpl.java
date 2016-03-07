package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.OnlineUserDao;
import main.java.edu.scnu.entity.OnlineUser;

@Repository(value="onlineUserDao")
public class OnlineUserDaoImpl extends BaseDaoImpl<OnlineUser> 
	implements OnlineUserDao
{

}
