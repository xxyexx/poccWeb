package main.java.edu.scnu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.HomeworkService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="homeworkAction")
@Scope("prototype")
public class HomeworkAction extends ActionSupport {
	private HttpServletRequest request;
	@Resource(name="homeworkService")
	private HomeworkService homeworkService;
	
	public HomeworkAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	/**
	 * 显示学生作业列表
	 */
	public String show_StudHW(){
		User user = (User) request.getSession().getAttribute("User");
		//未完成作业
		List<Homework> unfinHWList = homeworkService.getUnfinishedHW(user.getClassID()+"", user.getAcctID());
		//已完成作业
		List<HWSubmit> finHWList = homeworkService.getfinishedHW(user.getClassID()+"", user.getAcctID());
		//所有作业
		List<Homework> allHWList = homeworkService.getAllHW(user.getClassID()+"");
		
		request.setAttribute("unfinHWList", unfinHWList);
		request.setAttribute("finHWList", finHWList);
		request.setAttribute("allHWList", allHWList);
		return SUCCESS;
	}

	
	//setter
	public void setHomeworkService(HomeworkService homeworkService) {
		this.homeworkService = homeworkService;
	}
}
