package main.java.edu.scnu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.School;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ExperimentService;
import main.java.edu.scnu.service.HomeworkService;
import main.java.edu.scnu.service.SiteMsgService;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.MD5Util;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="managerPageAction")
@Scope("prototype")
public class ManagerPageAction extends ActionSupport {

	private int id;
	private String name;
	private String loginId;
	private String password;
	private String mobile;
	private String email;
	
	@Resource(name="userService")
	private UserService userService;
	
	private HttpServletRequest request;
	
	public ManagerPageAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	//更新教师信息
	public String updateTea(){
		User teacher = userService.getUser(this.id);
		teacher.setAcctName(this.name);
		teacher.setLoginID(this.loginId);
		if (this.password!=null&&"".equals(this.password)){
			teacher.setPasswd(MD5Util.md5Encode(this.password));
		}
		teacher.setMobile(this.mobile);
		teacher.setEmail(this.email);
		
		userService.updateUser(teacher);
		
		request.setAttribute("left", "teacher");
		return view();
		
	}
	
	public String view(){		
		User user = (User) request.getSession().getAttribute("User");
		user.setAcctType("");
		//学生列表
		user.setUserType("student");
		Page<User> studentPage = new Page<User>(0);
		studentPage.setPageSize(1000);
		user.getRentDate1().toString().length();
		studentPage = userService.getUserPage(user, studentPage);
		request.setAttribute("studentPage", studentPage);		
		//教师列表
		user.setUserType("teacher");
		Page<User> teacherPage = new Page<User>(0);
		teacherPage.setPageSize(1000);
		teacherPage = userService.getUserPage(user, teacherPage);
		request.setAttribute("teacherPage", teacherPage);
		
		return "view";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
