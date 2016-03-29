package main.java.edu.scnu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.MemberDao;
import main.java.edu.scnu.entity.Member;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.service.MemberService;
import main.java.edu.scnu.util.MD5Util;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class MemberServiceImpl implements MemberService {

	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	@Override
	@Transactional(readOnly=true)
	public String checkLogin(String loginID, String password) {
		String result=null;
		Member member = null;
		member = memberDao.findByLoginID(loginID);
		if(member==null){
			//找不到用户
			result = "inexistence";
		}else if(!MD5Util.md5Encode(password).equals(member.getPasswd())){
			//密码错误
			result = "wrongPassword";
		}else{
			result = "success";
		}
		return result;
	}

	@Override
	public Member getMember(String longinID) {
		Member member = null;
		member = memberDao.findByLoginID(longinID);
		return member;
	}
	
	@Override
	public Member getMember(int id){
		Member member = null;
		member = memberDao.get(Member.class, id);
		return member;
	}

	@Override
	public boolean update(Member member) {
		memberDao.update(member);
		return true;
	}

	@Override
	public Page<Member> getMemberPage(Member memberModel, Page<Member> pageModel) {
		return memberDao.getMemberPage(memberModel, pageModel);
	}

	@Override
	public boolean delete(int id) {
		Member member = memberDao.get(Member.class, id);
		memberDao.delete(member);
		return true;
	}

}
