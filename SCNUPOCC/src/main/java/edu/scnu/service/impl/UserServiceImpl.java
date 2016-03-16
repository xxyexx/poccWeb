package main.java.edu.scnu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.MD5Util;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly=true)
	public String checkLogin(String account, String password) {
		String result=null;
		User user = null;
		user = userDao.findByLoginID(account);
		if(user==null){
			//找不到用户
			result = "inexistence";
		}else if(!MD5Util.md5Encode(password).equals(user.getPasswd())){
			//密码错误
			result = "wrongPassword";
		}else{
			result = "success";
		}
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public User getUser(String account) {
		User user = null;
		user = userDao.findByLoginID(account);
		return user;
	}	
	
	@Override
	public boolean updateUser(User user) {
		userDao.update(user);
		return true;
	}
	
	
	//getter,setter
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
