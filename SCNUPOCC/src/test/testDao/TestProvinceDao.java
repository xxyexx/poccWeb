package test.testDao;

import main.java.edu.scnu.dao.SchoolDao;
import main.java.edu.scnu.entity.School;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProvinceDao {

	@Test
	public void test() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SchoolDao schoolDao = (SchoolDao) ctx.getBean("schoolDao");
		//Province province = provinceDao.get(Province.class, 1);
		School school =  schoolDao.get(School.class,2);
		System.out.println(school.getSchoolName());
		//System.out.println(province.getSchools().size());
	}

}
