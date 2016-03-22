package mytest.testUtil;

import main.java.edu.scnu.util.MD5Util;

public class testUtil {

	
	public static void md5Test(){
		System.out.println(MD5Util.md5Encode("123456"));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testUtil.md5Test();
	}

}
