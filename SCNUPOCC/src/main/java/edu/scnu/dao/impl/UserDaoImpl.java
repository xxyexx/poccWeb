package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.User;

@Repository(value="userDao")
public class UserDaoImpl extends BaseDaoImpl<User> 
	implements UserDao
{

	@Override
	public User findByLoginID(String loginID) {
		String hql = "select u from user u where u.loginID = ?";
		List<User> userList = find(hql, loginID);
		if(userList.size()!=0){
			return userList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public User findByacctID(String acctID) {
		String hql = "select u from user u where u.acctID = ?";
		List<User> userList = find(hql, acctID);
		if(userList.size()!=0){
			return userList.get(0);
		}else{
			return null;
		}
	}

}
