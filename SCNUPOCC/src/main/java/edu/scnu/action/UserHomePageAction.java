package main.java.edu.scnu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ExperimentService;
import main.java.edu.scnu.service.HomeworkService;
import main.java.edu.scnu.service.SiteMsgService;
import main.java.edu.scnu.util.PoccManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="userHomePageAction")
@Scope("prototype")
public class UserHomePageAction extends ActionSupport {
	@Resource(name="homeworkService")
	private HomeworkService homeworkService;
	
	@Resource(name="experimentService")
	private ExperimentService experimentService;
	
	@Resource(name="siteMsgService")
	private SiteMsgService siteMsgService;
	
	private HttpServletRequest request;
	private User user;
	
	public UserHomePageAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
	}
	
	@Override
	public String execute() throws Exception {
		if(user.getUserType().equals(PoccManager.student)){//学生用户
			List<Homework> newHWList = homeworkService.getNewHW(user.getClassID());
			if(newHWList.size()>5){//设置最新作业最多5个
				newHWList = newHWList.subList(0, 5);
			}
			request.setAttribute("NewHWList", newHWList);
			//已批改作业数量
			Query_CheckedHWnum(user.getClassID(),user.getAcctID());
		}else if(user.getUserType().equals(PoccManager.teacher)){//教师
			List<Homework> list = homeworkService.getHomeworkbyTeacherID(user.getAcctID());
			if(list.size()>5){list = list.subList(0, 5);}
			request.setAttribute("HWList", list); 
		}
		//实验列表
		List<Experiment> expList = experimentService.getAllExper();
		if(expList.size()>6){
			expList = expList.subList(0, 6);
		}
		request.setAttribute("ExpList", expList);
		//公告消息列表
		List<SiteMsg> msgList = siteMsgService.getPublicMsg(user);
		if(expList.size()>12){
			expList = expList.subList(0, 12);
		}
		request.setAttribute("MsgList", msgList);
		return SUCCESS;
	}

	/**
	 * 设置学生已批改作业数量
	 */
	public void Query_CheckedHWnum(String classID,String stud_AcctID){
		//新作业中,已完成作业且已批改
		List<Homework> newHWList = homeworkService.getNewHW(classID);
		List<HWSubmit> finishedHWList = homeworkService.getfinishedHW(classID, stud_AcctID);
		int num = 0;
		for (Homework homework : newHWList) {
			for (HWSubmit hwSubmit : finishedHWList) {
				if(hwSubmit.getHomework().getId()==homework.getId()
						&&hwSubmit.getChecked()==1){
					num++;
					break;
				}
			}
		}
		request.setAttribute("CheckedHWnum", num);
	}
	
	//setter
	public void setHomeworkService(HomeworkService homeworkService) {
		this.homeworkService = homeworkService;
	}
	public void setExperimentService(ExperimentService experimentService) {
		this.experimentService = experimentService;
	}
	public void setSiteMsgService(SiteMsgService siteMsgService) {
		this.siteMsgService = siteMsgService;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
