package main.java.edu.scnu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.DateUtil;
import main.java.edu.scnu.util.MD5Util;
import main.java.edu.scnu.util.ModelUtil;
import main.java.edu.scnu.util.StringUtil;

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
	public boolean createUser(User model, String sclCode, String dptCode2,
			int fisrt4, int num) {
		
		for(int i=0; i<num; i++){
			
			User newUser = (new ModelUtil()).getCloneUser(model);
			String aid = sclCode + dptCode2 + DateUtil.getYear4() + StringUtil.getStr4(i+1);
			newUser.setAcctID(aid);
			newUser.setLoginID("");
			newUser.setPasswd(model.getPasswd());
			userDao.update(newUser);
		}
		
		return true;
	}
	
	@Override
	public boolean deleteUsers(List<String> idList) {
		// TODO Auto-generated method stub
		for (String s:idList){
			int id = Integer.valueOf(s);
			User user = userDao.get(User.class, id);
			userDao.delete(user);
		}
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public User getUser(String account) {
		User user = null;
		user = userDao.findByLoginID(account);
		return user;
	}	
	
	@Override
	@Transactional(readOnly=true)
	public User getUser(int id) {
		User user = null;
		user = userDao.get(User.class, id);
		return user;
	}
	
	@Override
	public boolean updateUser(User user) {
		userDao.update(user);
		return true;
	}
	
	@Override
	public Page<User> getUserPage(User user, Page<User> page) {		
		return userDao.findPage(page, user);
	}
	
	//getter,setter
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	

}
