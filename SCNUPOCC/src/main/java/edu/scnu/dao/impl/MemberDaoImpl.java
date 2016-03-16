package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.MemberDao;
import main.java.edu.scnu.entity.Member;
import main.java.edu.scnu.entity.User;

@Repository(value="memberDao")
public class MemberDaoImpl extends BaseDaoImpl<Member> 
	implements MemberDao
{

	@Override
	public Member findByLoginID(String loginID) {
		String hql = "select m from member m where m.loginID = ?";
		List<Member> memberList = find(hql, loginID);
		if(memberList.size()!=0){
			return memberList.get(0);
		}else{
			return null;
		}
	}

}
