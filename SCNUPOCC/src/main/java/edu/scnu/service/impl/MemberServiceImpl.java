package main.java.edu.scnu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.MemberDao;
import main.java.edu.scnu.entity.Member;
import main.java.edu.scnu.service.MemberService;
import main.java.edu.scnu.util.MD5Util;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class MemberServiceImpl implements MemberService {

	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	@Override
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

}
