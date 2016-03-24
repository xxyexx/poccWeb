package main.java.edu.scnu.util;

public class PoccManager {
	/**文件保存目录*/
	public static String ROOT_DIR = ReadProperty.getProperty("ROOT_DIR", "/home/poccWeb/");
	/**实验临时文件保存目录*/
	public static String Temporary_file_dir = "temporaryfiles/";
	/**学生作业文件保存目录*/
	public static String Homework_file_dir = "homeworkfiles/";
	/**学生头像文件保存目录*/
	public static String User_Icon_dir = "picture/";	
	/**学生默认头像*/
	public static final String StudentDefaultIcon = "res/images/UserDefaultPic3.jpg";
	/**学生用户类型*/
	public static final String student = "student";
	/**教师用户类型*/
	public static final String teacher = "teacher";
	/**管理员用户类型*/
	public static final String manager = "manager";
	/**合作方用户类型*/
	public static final String partner = "partner";
	/**免费用户类型*/
	public static final String freeAccount = "freeAccount";
	
} 
