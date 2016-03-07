package main.java.edu.scnu.dao;

import main.java.edu.scnu.entity.User;

public interface UserDao extends BaseDao<User>{

	/**
	 * 根据登录账号查找用户
	 * @param loginID 登录账号
	 * @return user 返回该用户
	 */
	User findByLoginID(String loginID);
	/**
	 * 根据内部账号查找用户
	 * @param acctID 内部账号
	 * @return user 返回该用户
	 */
	User findByacctID(String acctID);
}
