package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.MemberDao;
import main.java.edu.scnu.entity.Member;

@Repository(value="memberDao")
public class MemberDaoImpl extends BaseDaoImpl<Member> 
	implements MemberDao
{

}
