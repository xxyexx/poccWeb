package mytest.testDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.java.edu.scnu.dao.MemberDao;
import main.java.edu.scnu.entity.Member;

public class TestMember {

	
	public static void main(String[] args){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		MemberDao memberDao = (MemberDao) ctx.getBean("memberDao");
		Member member = memberDao.findByLoginID("1001");
		System.out.println(member);
	}
}



