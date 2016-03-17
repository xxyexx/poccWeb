package mytest.testService;

import java.util.Map;

import main.java.edu.scnu.service.ProvinceService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchoolTest {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");;
	
	public static void provinceTest(){
		
		ProvinceService provinceService = (ProvinceService) ctx.getBean("provinceService");
		
		Map<Integer, String> map = provinceService.getProvinceMap();
		
		System.out.println(map.get(19));		
	}
	public static void schoolTest(){
		
		ProvinceService provinceService = (ProvinceService) ctx.getBean("provinceService");
		Map<Integer, String> map = provinceService.getSchoolMap(19);
		System.out.println(map.get(1659));
		
	}
	
	public static void main(String[] args) {
				
//		SchoolTest.provinceTest();
		SchoolTest.schoolTest();
	}

}









