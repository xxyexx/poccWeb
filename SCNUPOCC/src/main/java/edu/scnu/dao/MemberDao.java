package main.java.edu.scnu.dao;

import main.java.edu.scnu.entity.Member;

public interface MemberDao extends BaseDao<Member>{
	
	/**
	 * 根据登录账号查找用户
	 * @param loginID 登录账号
	 * @return Member 返回该用户
	 */
	Member findByLoginID(String loginID);

}
