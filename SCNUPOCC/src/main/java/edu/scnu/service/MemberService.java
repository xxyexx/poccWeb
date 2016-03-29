package main.java.edu.scnu.service;

import main.java.edu.scnu.entity.Member;
import main.java.edu.scnu.entity.Page;

public interface MemberService {

	/**
	 * 内部用户(member)登录验证
	 * @param loginID 对应loginID(登录账号)
	 * @param password	对应passwd(密码)
	 * @return 1.success(登录成功,跳转到主页);2.inexistence(账号不存在);3.wrongPassword(密码错误);
	 */
	public String checkLogin(String loginID,String password);
	
	/**
	 * 根据用户账号获取一个用户类型
	 * @param longinID 对应loginID(登录账号)
	 * @return member 一个用户
	 */
	public Member getMember(String longinID);
	
	/**
	 * 根据id获取一个用户类型
	 * @param id 对应id(登录账号)
	 * @return member 一个用户
	 */
	public Member getMember(int id);
	
	/**
	 * 新增、更新会员
	 * @param member
	 * @return
	 */
	public boolean update(Member member);
	
	/**根据id删除member
	 * @param member
	 * @return
	 */
	public boolean delete(int id);
	
	/**
	 * 获取会员列表
	 * @return
	 */
	public Page<Member> getMemberPage(Member memberModel, Page<Member> pageModel);
	
}





