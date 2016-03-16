package mytest.testService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.UserService;

public class UserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		User user = new User();
		user.setId(0);
		user.setAcctID("20122004000");
		user.setUserType("student");
		user.setManagerID("2012000");
		user.setAcctTag("华南师范大学");
		user.setClassID("1");
		userService.createUser(user, "SCNU", "10", 1, 10);		
	}

}
