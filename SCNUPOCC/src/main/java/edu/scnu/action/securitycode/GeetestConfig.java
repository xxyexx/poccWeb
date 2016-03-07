package main.java.edu.scnu.action.securitycode;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String captcha_id = "1574cf119c03149aa60bfb3b2f92b7e1";
	private static final String private_key = "d309ed2c95cb741e5232dea85a3e7a39";

	public static final String getCaptcha_id() {
		return captcha_id;
	}

	public static final String getPrivate_key() {
		return private_key;
	}

}
