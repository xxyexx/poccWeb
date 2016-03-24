package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.entity.Page;

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
	
	Page<User> findPage(Page<User> modelPage, User modelUser);

	/**
	 * 根据班级ID查询这个班级所有学生
	 * @param classID
	 * @return user list
	 */
	List<User> findByClassID(String classID);

}
