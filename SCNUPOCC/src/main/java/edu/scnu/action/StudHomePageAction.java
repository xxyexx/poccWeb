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

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="studHomePageAction")
@Scope("prototype")
public class StudHomePageAction extends ActionSupport {
	@Resource(name="homeworkService")
	private HomeworkService homeworkService;
	
	@Resource(name="experimentService")
	private ExperimentService experimentService;
	
	@Resource(name="siteMsgService")
	private SiteMsgService siteMsgService;
	
	private HttpServletRequest request;
	
	public StudHomePageAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	@Override
	public String execute() throws Exception {
		//设置作业.公告.等列表信息
		User user = (User) request.getSession().getAttribute("User");
		List<Homework> newHWList = homeworkService.getNewHW(user.getClassID());
		if(newHWList.size()>5){
			newHWList = newHWList.subList(0, 5);
		}
		request.setAttribute("NewHWList", newHWList);
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
		//已批改作业数量
		Query_CheckedHWnum(user.getClassID(),user.getId());
		return SUCCESS;
	}

	/**
	 * 设置已批改作业数量
	 */
	public void Query_CheckedHWnum(String classID,int stud_ID){
		//新作业中,已完成作业且已批改
		List<Homework> newHWList = homeworkService.getNewHW(classID);
		List<HWSubmit> finishedHWList = homeworkService.getfinishedHW(classID, stud_ID);
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
	
}
