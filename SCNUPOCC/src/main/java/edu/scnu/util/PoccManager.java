package main.java.edu.scnu.util;

public class PoccManager {
	/**文件保存目录*/
	public static String ROOT_DIR = ReadProperty.getProperty("ROOT_DIR", "/home/poccWeb/");
	/**实验临时文件保存目录*/
	public static String Temporary_file_dir = ROOT_DIR+"temporaryfiles/";
	/**学生作业文件保存目录*/
	public static String Homework_file_dir = ROOT_DIR+"homeworkfiles/";
	/**学生头像文件保存目录*/
	public static String User_Icon_dir = ROOT_DIR+"picture/";	
	/**学生默认头像*/
	public static final String StudentDefaultIcon = "res/images/UserDefaultPic3.jpg";
	
	
} 
