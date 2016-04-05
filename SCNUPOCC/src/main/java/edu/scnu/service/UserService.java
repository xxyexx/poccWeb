package main.java.edu.scnu.service;

import java.util.List;

import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.User;

public interface UserService {
	/**
	 * 外部用户(user)登录验证
	 * @param account 对应loginID(登录账号)
	 * @param password	对应passwd(密码)
	 * @return 1.success(登录成功,跳转到主页);2.inexistence(账号不存在);3.wrongPassword(密码错误);
	 */
	String checkLogin(String account,String password);
	
	/**
	 * 根据用户账号获取一个用户类型
	 * @param account 对应loginID(登录账号)
	 * @return user 一个用户
	 */
	User getUser(String account);
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	User getUser(int id);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
	
	/**
	 * 批量生成用户，生成的用户院校信息与计费信息和model一致
	 * @param model 用户模板
	 * @param sclCode4 学校代号，长度为四（如：SCNU）
	 * @param dptCode2 学院代号，长度为二（如：01）
	 * @param fisrt4 起始序号（如：1）
	 * @param num 生成数量（如：100）
	 * @return true：生成成功；false：生成失败
	 * 
	 */
	public boolean createUser(User model, String sclCode4, String dptCode2,
			int fisrt4, int num);
	
	/**
	 * 根据模板获取用户page分页
	 * @param user 用户模板
	 * @param page	分页模板
	 * @return Page<User> 分页属性与page一致，用户院校信息与user一致
	 */
	public Page<User> getUserPage(User user, Page<User> page);
	
	public boolean deleteUsers(List<String> list);


}
