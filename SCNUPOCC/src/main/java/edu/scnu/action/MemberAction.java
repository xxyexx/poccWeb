package main.java.edu.scnu.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Member;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.MemberService;
import main.java.edu.scnu.util.MD5Util;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller(value="memberAction")
@Scope("prototype")
public class MemberAction extends ActionSupport{
	
	private int id;
	private String loginId;
	private String password;
	/**用户类型:平台管理员(admin),操作员(cashier),平台会员(member)*/
	private String memberType;
	private String memberName;
	private String mobile;
	private String email;
	private String result;	
	
	@Resource(name="memberService")
	private MemberService memberService;	
	private HttpServletRequest request;
	
	public String create(){
		Member member = new Member();
		member.setLoginID(loginId);
		member.setPasswd(MD5Util.md5Encode(password));
		member.setMemberType(memberType);
		member.setMemberName(memberName);
		member.setMobile(mobile);
		member.setEmail(email);
		member.setCreateTime(new Date());
		memberService.update(member);
		return createView();		
	}
	
	public String update(){
		Member member = memberService.getMember(this.id);
		member.setLoginID(loginId);
		if (this.password!=null&&!"".equals(this.password)){
			member.setPasswd(MD5Util.md5Encode(password));
		}		
		member.setMemberType(memberType);
		member.setMemberName(memberName);
		member.setMobile(mobile);
		member.setEmail(email);
		memberService.update(member);
		return createView();	
	}
	
	public String createView(){
		Page<Member> memberPage = new Page<Member>(0);
		memberPage.setPageSize(1000);
		Member member = new Member();
		memberPage = memberService.getMemberPage(member, memberPage);
		request.setAttribute("memberPage",memberPage);
		memberPage.getList().size();
		return "createView";
	}
	
	public String delete(){
		memberService.delete(this.id);
		return createView();
	}
	
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
			Member member = memberService.getMember(loginId);
			member.setLastLoingIP(ip);
			member.setLastLoginTime(loginTime);
			memberService.update(member);
			request.getSession().setAttribute("Member", member);
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
	public void setId(int id) {
		this.id = id;
	}
	
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



	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
	
	
	
	
	
	
	
	
}
