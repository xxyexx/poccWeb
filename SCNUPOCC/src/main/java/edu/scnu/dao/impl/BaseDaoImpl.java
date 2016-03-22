package main.java.edu.scnu.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import main.java.edu.scnu.dao.BaseDao;

public abstract class BaseDaoImpl <T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> entityClazz,Serializable id) {
		return (T) getSessionFactory().getCurrentSession()
				.get(entityClazz, id);
	}

	@Override
	public Serializable save(T entity) {
		return getSessionFactory().getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<T> entityClazz,Serializable id) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete "+entityClazz.getSimpleName().toLowerCase()
					+" en where en.id = :id")
					.setParameter("id", id)
					.executeUpdate();
	}
	
	@Override
	public List<T> findAll(Class<T> entityClazz) {
		//getSimpleName得到className如:Province,要转成跟表名一致:province
		
		return find("select en from "
			+entityClazz.getSimpleName().toLowerCase()+" en");
	}

	@Override
	public long findCound(Class<T> entityClazz) {
		List<?> l = find("select count(*) from "+entityClazz.getSimpleName().toLowerCase());
		//返回查询得到的实体总数
		if(l!=null&&l.size()==1){
			return (Long)l.get(0);
		}
		return 0;
	}

	//根据HQL语句查询实体
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql) {
		return (List<T>)getSessionFactory().getCurrentSession()
				.createQuery(hql)
				.list();
	}
	
	//根据带占位符参数的HQL语句设置参数
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql,Object... params) {
		//创建查询
		Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
		//为包含占位符的HQL语句设置参数
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>)query.list();
	}
	
	/**
	 * 使用HQL语句进行分页查询操作
	 * @param hql 需要查询的HQL语句
	 * @param pageNo 查询第pageNo页的记录
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,int pageNo,int pageSize){
		//创建查询
		return getSessionFactory().getCurrentSession()
				.createQuery(hql)
				//执行分页
				.setFirstResult((pageNo-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
	}
	
	/**
	 * 使用HQL语句进行分页查询操作
	 * @param hql 需要查询的HQL语句
	 * @param pageNo 查询第pageNo页的记录
	 * @param pageSize 每页需要显示的记录数
	 * @param params 如果	HQL带占位符参数,params用于传入占位符参数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,int pageNo,int pageSize
			,Object... params){
		//创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		//为包含占位符的HQL语句设置参数		
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		//执行分页,并返回查询结果
		return	query.setFirstResult((pageNo-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
	}
	
}
