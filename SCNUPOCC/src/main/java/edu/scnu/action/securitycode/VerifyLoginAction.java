package main.java.edu.scnu.action.securitycode;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.util.GeetestLib;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 极验验证:检测验证码是否正确
 * 使用post方式，返回验证结果, request表单中必须包含challenge, validate, seccode
 *
 */
@Controller(value="verifyLoginAction")
@Scope("prototype")
public class VerifyLoginAction extends ActionSupport {
	HttpServletRequest request;
	private String securityCode;
	
	private String result;
	public VerifyLoginAction(){
		request = ServletActionContext.getRequest();
	}
	
	public String doValidate() throws IOException{
		GeetestLib gtSdk = new GeetestLib();
		gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());
		//从session中获取gt-server状态
		int gt_server_status_code = GeetestLib.getGtServerStatusSession(request);
		//从session中获取challenge
		gtSdk.getChallengeSession(request);
		String gtResult = "fail";
		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
			gtResult = gtSdk.enhencedValidateRequest(request);
			//System.out.println(gtResult);
		} else {
			//gt-server非正常情况下，进行failback模式验证
			System.out.println("使用自定义验证码验证");
			gtResult = "fail";
			if(securityCode.equals(request.getSession().getAttribute("securityCode"))){
				gtResult = "success";
			}
		}
		if (gtResult.equals(GeetestLib.success_res)) {
			// 验证成功
			result = "success";

		} else if (gtResult.equals(GeetestLib.forbidden_res)) {
			// 验证被判为机器人
			result = "forbidden";
		} else {
			// 验证失败
			result = "fail";
		}
		return SUCCESS;
	}

	//getter
	public String getResult() {
		return result;
	}
	//setter
	public void setResult(String result) {
		this.result = result;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
}
