package main.java.edu.scnu.dao;

import main.java.edu.scnu.entity.Member;
import main.java.edu.scnu.entity.Page;

public interface MemberDao extends BaseDao<Member>{
	
	/**
	 * 根据登录账号查找用户
	 * @param loginID 登录账号
	 * @return Member 返回该用户
	 */
	public Member findByLoginID(String loginID);
	
	/**
	 * 返回会员列表
	 * @param member
	 * @param page
	 * @return
	 */
	public Page<Member> getMemberPage(Member member,Page<Member> page);
}
