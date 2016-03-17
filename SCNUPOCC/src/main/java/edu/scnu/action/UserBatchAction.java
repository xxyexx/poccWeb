package main.java.edu.scnu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.School;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ProvinceService;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.StringUtil;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户批量操作，包括批量新增、用户绑定
 * 
 * @author mozilla
 *
 */

@Controller(value="userBatchAction")
@Scope("prototype")
public class UserBatchAction extends ActionSupport{
		
	//账号信息
	private int schoolID; //学校ID
	private int deptID;	//学院ID
	private int fisrt;	//开始顺序
	private int num;	//生成数量
	
	//计费信息
	
	
	
	//Service
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="provinceService")
	private ProvinceService provinceService;
	private HttpServletRequest request;
	
	public UserBatchAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	public String create(){
		
		System.out.println("This is UserBatchAction.create()");
		
		School school = provinceService.getSchool(schoolID);
		Dept dept = provinceService.getDept(deptID);
		
		//生成模板用户
		User user = new User();
		user.setPasswd("");
		String did = StringUtil.getStr2(dept.getDeptID());
		
		//生成用户
		userService.createUser(user, school.getSchoolID(), did, fisrt, num);
		
		return SUCCESS;		
	}
	
	public String view(){
		System.out.println("This is UserBatchAction.view()");
		request.setAttribute("ProvinceMap", provinceService.getProvinceMap());
		
		return "view";
		
	}
	
	

	public void setNum(int num) {
		this.num = num;
	}


	
}








