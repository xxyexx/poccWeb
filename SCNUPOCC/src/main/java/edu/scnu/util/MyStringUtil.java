package main.java.edu.scnu.util;

public class MyStringUtil {
	/**
	 * 检测以"//|"等符号分隔的字符串是否包含(班级列表等)
	 * @param s1 s1是否包含s2
	 * @param s2 
	 * @param regex 分隔的字符串
	 * @return true|false
	 */
	public static boolean checkContain(String s1,String s2,String regex){
		String str[] = s1.split(regex);
		for (String s : str) {
			if(s.equals(s2)){
				return true;
			}
		}
		return false;
	}
}
