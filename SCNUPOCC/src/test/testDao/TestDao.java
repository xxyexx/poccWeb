package test.testDao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.User;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDao {

	@Test
	public void test() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = userDao.get(User.class, 1).getCreateDate();
		System.out.println(sdf.format(date));
	}

}
