package main.java.edu.scnu.service;

import main.java.edu.scnu.entity.Member;

public interface MemberService {

	/**
	 * 内部用户(member)登录验证
	 * @param loginID 对应loginID(登录账号)
	 * @param password	对应passwd(密码)
	 * @return 1.success(登录成功,跳转到主页);2.inexistence(账号不存在);3.wrongPassword(密码错误);
	 */
	String checkLogin(String loginID,String password);
	
	/**
	 * 根据用户账号获取一个用户类型
	 * @param account 对应loginID(登录账号)
	 * @return user 一个用户
	 */
	Member getMember(String longinID);
	
	
}





