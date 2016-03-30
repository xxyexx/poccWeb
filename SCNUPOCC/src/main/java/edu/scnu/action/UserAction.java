package main.java.edu.scnu.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.HomeworkService;
import main.java.edu.scnu.service.SiteMsgService;
import main.java.edu.scnu.service.UserService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
	private String account;//登录账号
	private String password;//密码
	//用户类型:合作方(partner),院校管理员(manager),教师(teacher),学生(student),免费试用帐号(freeAccount)
	private String userType;
	
	private String result;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="homeworkService")
	private HomeworkService homeworkService;
	@Resource(name="siteMsgService")
	private SiteMsgService siteMsgService;
	private HttpServletRequest request;
	
	public UserAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	//外部用户登录
	public String login(){
		//1)账号密码检测
		result = userService.checkLogin(account, password);
		//2)登录成功,
		if(result.equals("success")){
			User user = userService.getUser(account);
			//保存上次登陆时间,上次登陆IP到session
			request.getSession().setAttribute("lastLoginTime", user.getLastLoginTime());
			request.getSession().setAttribute("lastLoginIP", user.getLastLoginIP());
			//更新当前用户IP和登录时间
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
			user.setLastLoginIP(ip);
			user.setLastLoginTime(loginTime);
			userService.updateUser(user);
			//返回userType用于跳转
			userType = user.getUserType();
			request.getSession().setAttribute("User", user);
			if(user.getUserType().equals("student")){
				Query_HWNum(user);
			}
			Query_MsgNum(user);
		}

		return SUCCESS;
	}
	/**
	 *外部用户退出
	 */
	public String logout(){
		User user = (User) WebUtils.getSessionAttribute(request, "User");
		System.out.println(user.getAcctName()+"退出了系统");
		request.getSession().invalidate();
		return SUCCESS;
	}
	
	/**
	 * 设置未做的作业数量
	 */
	public void Query_HWNum(User user){
		String unfinHWNum = null;
		List<Homework> UnfinishedHWList 
						= homeworkService.getUnfinishedHW(user);
		if(UnfinishedHWList!=null) {
			if(UnfinishedHWList.size()!=0){
				unfinHWNum=UnfinishedHWList.size()+"";
			}
		}
		request.getSession().setAttribute("UnfinHWNum", unfinHWNum);
	}
	/**
	 * 设置未读消息数量
	 */
	public void Query_MsgNum(User user){
		String unreadMsgNum = null;
		List<SiteMsg> msgList = siteMsgService.getUnreadMsg(user);
		if(msgList!=null){
			if(msgList.size()!=0){
				unreadMsgNum = msgList.size()+"";
			}
		}
		request.getSession().setAttribute("UnreadMsgNum", unreadMsgNum);
	}
	
	//getter
	public String getResult() {
		return result;
	}
	public String getUserType() {
		return userType;
	}

	//setter
	public void setAccount(String account) {
		this.account = account.trim();
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setHomeworkService(HomeworkService homeworkService) {
		this.homeworkService = homeworkService;
	}
	public void setSiteMsgService(SiteMsgService siteMsgService) {
		this.siteMsgService = siteMsgService;
	}
	
}
