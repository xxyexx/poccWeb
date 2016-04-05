package main.java.edu.scnu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.service.ProvinceService;
import main.java.edu.scnu.service.UserService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="userChangeAction")
@Scope("prototype")
public class UserChangeAction extends ActionSupport {

	private int id;
	private String name;
	private String loginId;
	private String password;
	private String mobile;
	private String email;
	
	
	//Service
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="provinceService")
	private ProvinceService provinceService;
	private HttpServletRequest request;
	
	public UserChangeAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	public String update(){
		
		request.setAttribute("left", "teacher");
		return null;
		
	}
	
	
	
	
	
}
