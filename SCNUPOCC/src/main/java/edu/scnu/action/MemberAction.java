package main.java.edu.scnu.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.MemberService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller(value="memberAction")
@Scope("prototype")
public class MemberAction extends ActionSupport{
	
	private String loginId;
	private String password;	
	/**用户类型:平台管理员(admin),操作员(cashier),平台会员(member)*/
	private String memberType;
	private String result;	
	@Resource(name="memberService")
	private MemberService memberService;	
	private HttpServletRequest request;
	
	
	//内部用户登录
	public String login(){
		
		//1)账号密码检测
		result = memberService.checkLogin(loginId, password);
		//2)登录成功,
		if(result.equals("success")){
			//记录ip
//			User user = memberService.g
//			System.out.println("登录成功");
			String ip = request.getHeader(" x-forwarded-for ");
			if (ip == null || ip.length() == 0
					|| " unknown ".equalsIgnoreCase(ip)) {
				ip = request.getHeader(" Proxy-Client-IP ");
			}
			if (ip == null || ip.length() == 0
					|| " unknown ".equalsIgnoreCase(ip)) {
				ip = request.getHeader(" WL-Proxy-Client-IP ");
			}
			if (ip == null || ip.length() == 0
					|| " unknown ".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}   
			
			Date loginTime=new Date();
//			member.setLastLoginIP(ip);
//			user.setLastLoginTime(loginTime);
//			userService.updateUser(user);
		}
		return SUCCESS;
		
	}
	
	
	
	//getter
	public String getMemberType() {
		return memberType;
	}

	public String getResult() {
		return result;
	}

	//setter
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberAction(){
		this.request = ServletActionContext.getRequest();
	}
	

	
	
	
	
	
	
	
	
	
	
}
