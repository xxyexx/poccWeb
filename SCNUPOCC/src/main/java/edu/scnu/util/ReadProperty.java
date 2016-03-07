package main.java.edu.scnu.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 通过 Properties对象读取配置文件
 */
public class ReadProperty {
	public static Properties props = new Properties();
	
	static {
		try {
			props.load(ReadProperty.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/** 私有构造方法 */
	private ReadProperty() {

	}

	/** 获取实例 */
	public static ReadProperty getInstance() {
		ReadProperty rp = new ReadProperty();
		// 初始化数据库连接类型
		return rp;
	}
	
	/** 获取资源文件里对应 key=value 中的value的值 */
	public static String getProperty(String key, String defaultValue) {
		String value = props.getProperty(key);
		
		return MyStringUtil.notEmpty(value) ? value : defaultValue;
	}
	
	/** 转换字符串 */
	public String CheckReplace(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if ((int) arr[i] == 92) {
				arr[i] = '/';
			}
		}
		s = new String(arr);
		return s;
	}
}
