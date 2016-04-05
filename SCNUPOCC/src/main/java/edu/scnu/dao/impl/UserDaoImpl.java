﻿package main.java.edu.scnu.dao.impl;

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
		if (!"".equals(modelUser.getAcctTag())&&!"不限".equals(modelUser.getAcctTag())) {
			hql.append(" and en.acctTag='"+modelUser.getAcctTag()+"'");
		}		
		if (!"".equals(modelUser.getAcctType())&&!"不限".equals(modelUser.getAcctType())){
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
	

	

	@Override
	public List<User> findByClassID(String classID) {
		String hql = "select u from user u where u.classID = ?";
		List<User> userList = find(hql, classID);
		return userList;
	}

	@Override
	public List<User> findByDeptName(String schoolName, String deptName) {
		String hql = "select u from user u where u.acctTag = ? and u.acctType = ?";
		Object params[] = {schoolName,deptName};
		return find(hql, params);
	}

	@Override
	public List<User> findUserBySchool(String schoolName,String userType) {
		String hql = "select u from user u where u.acctTag = ? and u.userType = ?";
		Object params[] = {schoolName,userType};
		return find(hql, params);
	}

}
