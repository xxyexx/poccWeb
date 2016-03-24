package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.Page;
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
	
	@Override
	public Page<User> findPage(Page<User> modelPage, User modelUser){
//		find(hql, params)
		StringBuffer hql = new StringBuffer("select en from "
				+User.class.getSimpleName().toLowerCase()+" en where 1=1");
		if (!"不限".equals(modelUser.getAcctTag())) {
			hql.append(" and en.acctTag='"+modelUser.getAcctTag()+"'");
		}		
		if (!"不限".equals(modelUser.getAcctType())){
			hql.append(" and en.acctType='"+modelUser.getAcctType()+"'");
		}
		if (modelUser.getUserType()!=null&&!"".equals(modelUser.getUserType())) {
			hql.append(" and en.userType='"+modelUser.getUserType()+"'");
		}
		Page<User> page = new Page<User>(0);
		
		System.out.println(hql);
		page.setList(findByPage(hql.toString(), 0, 100));
		return page;
	}
	

	

}
