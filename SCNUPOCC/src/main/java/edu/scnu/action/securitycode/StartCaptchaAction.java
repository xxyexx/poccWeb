package main.java.edu.scnu.action.securitycode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import main.java.edu.scnu.util.GeetestLib;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 极验验证:设置验证码
 * 使用Get的方式返回c和capthca_id,此方式以实现前后端完全分离的开发模式
 *
 */
@Controller(value="startCaptchaAction")
@Scope("prototype")
public class StartCaptchaAction extends ActionSupport {
	HttpServletRequest request;
	HttpServletResponse response;
	public StartCaptchaAction(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
	}

	public void setChallenge() throws IOException{
		GeetestLib gtSdk = new GeetestLib();
		gtSdk.setCaptchaId(GeetestConfig.getCaptcha_id());
		gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());
		
		String resStr = "{}";
		
		//进行验证预处理
		if (gtSdk.preProcess() == 1) {
			// gt-server服务正常,预处理完成
			
			resStr = gtSdk.getSuccessPreProcessRes(); //预处理成功，获取标准返回
			gtSdk.setGtServerStatusSession(request, 1); //在session中设置gt-server服务状态

		} else {
			// 预处理失败
			
			resStr = gtSdk.getFailPreProcessRes(); //无法连接到gt-server服务器，进行相应处理, 获得返回
			gtSdk.setGtServerStatusSession(request, 0); //在session中设置gt-server服务状态
		}
		
		gtSdk.setChallengeSession(request); //将challenge设置到session中，二次验证进行challenge比对

		PrintWriter out = response.getWriter();
		out.println(resStr);
	}
}
