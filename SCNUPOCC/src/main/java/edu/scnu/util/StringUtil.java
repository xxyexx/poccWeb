package main.java.edu.scnu.util;

public class StringUtil {
	
	/**
	 * 返回数字长度为4的字符串形式
	 * 如  1 --> "0001"
	 * @param i 0~9999 整数
	 * @return "0000"~"9999"
	 */
	public static String getStr4(int i){
		if (i<0||i>9999){
			System.out.println("StringUtil:输入错误！");
			return "ERROR";
		}
		String str = "000"+String.valueOf(i);
		return str.substring(str.length()-4);
		
	}

}
