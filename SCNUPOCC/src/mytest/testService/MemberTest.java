package mytest.testService;

import main.java.edu.scnu.service.MemberService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MemberService memberService = (MemberService) ctx.getBean("memberService");
//		Member member = new Member();
		System.out.println(memberService.checkLogin("1001", "123456"));
		System.out.println(memberService.checkLogin("1002", "123456"));
		System.out.println(memberService.checkLogin("1001", "12456"));
		

	}

}
