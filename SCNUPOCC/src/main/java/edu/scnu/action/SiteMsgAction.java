package main.java.edu.scnu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.SiteMsgService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 信箱
 */
@Controller(value="siteMsgAction")
@Scope("prototype")
public class SiteMsgAction extends ActionSupport {
	private HttpServletRequest request;
	@Resource(name="siteMsgService")
	private SiteMsgService siteMsgService;
	private User user;
	private String userType;
	
	public SiteMsgAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
		userType  = (user!=null)?user.getUserType():"";//用户类型用于区分跳转
	}
	
	public String show_Msg(){
		
		//未读消息
		List<SiteMsg> unreadMsgList = siteMsgService.getUnreadMsg(user);
		//已读消息
		List<SiteMsg> readMsgList = siteMsgService.getReadMsg(user);
		//已发消息
		List<SiteMsg> sentMsgList = siteMsgService.getSentMsg(user.getAcctID());
		
		request.setAttribute("UnreadMsgList", unreadMsgList);
		request.setAttribute("ReadMsgList", readMsgList);
		request.setAttribute("SentMsgList", sentMsgList);
		
		return SUCCESS;
	}

	
	
	//get
	public String getUserType() {
		return userType;
	}
	//set
	public void setSiteMsgService(SiteMsgService siteMsgService) {
		this.siteMsgService = siteMsgService;
	}
}
