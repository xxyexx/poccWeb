package test.testEntity;


import main.java.edu.scnu.entity.Province;
import main.java.edu.scnu.entity.School;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestSchool {

	@Test
	public void test() {
		//获取配置
		Configuration conf = new Configuration().configure();
		//获取ServiceRegistry
		ServiceRegistry sr = new ServiceRegistryBuilder()
		.applySettings(conf.getProperties())
		.buildServiceRegistry(); 
		//配置我们的SessionFactory
		SessionFactory sf = conf.buildSessionFactory(sr);
		//获取我们的Session
		Session sess = sf.openSession();
		//开始我们的事务
		Transaction tx = sess.beginTransaction();
		//
		School school = new School();
		//Province province = new Province();
		school=(School) sess.get(School.class,1);
		//school.getProvince();
		System.out.println(school.toString());
		System.out.println(school.getProvince().getId());
		System.out.println(school.getProvince().getProvince());
		System.out.println(school.getProvince().getSchools().size());
		//提交事务
		tx.commit();
		//关闭Session
		sess.close();
		//关闭SessionFactory
		sf.close();
		
	}

	
	@Test
	public void testProvince() {
		//获取配置
		Configuration conf = new Configuration().configure();
		//获取ServiceRegistry
		ServiceRegistry sr = new ServiceRegistryBuilder()
		.applySettings(conf.getProperties())
		.buildServiceRegistry(); 
		//配置我们的SessionFactory
		SessionFactory sf = conf.buildSessionFactory(sr);
		//获取我们的Session
		Session sess = sf.openSession();
		//开始我们的事务
		Transaction tx = sess.beginTransaction();
		//
		//School school = new School();
		Province province = new Province();
		//school=(School) sess.get(School.class,1);
		province= (Province) sess.get(Province.class, 1);
		//System.out.println(province.getSchools().size());
		System.out.println(province.getProvince());
		//提交事务
		tx.commit();
		//关闭Session
		sess.close();
		//关闭SessionFactory
		sf.close();
		
	}
}
