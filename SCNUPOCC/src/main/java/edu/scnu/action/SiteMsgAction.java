package main.java.edu.scnu.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.resource.cci.ConnectionSpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ClassService;
import main.java.edu.scnu.service.SiteMsgService;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.PoccManager;

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
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="classService")
	private ClassService classService;	
	private User user;
	private String userType;
	private SiteMsg siteMsg;//添加一个消息（公告）
	private String ispublic;//消息0.公告1
	private String receivers;//单发接收者ids
	private String []recvgroup;//教师群发接收者列表
	private String msgID;//消息id
	
	public SiteMsgAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
		userType  = (user!=null)?user.getUserType():"";//用户类型用于区分跳转
	}
	/**
	 * 信箱首页
	 */
	public String show_Msg(){
		
		//未读消息
		List<SiteMsg> unreadMsgList = siteMsgService.getUnreadMsg(user);
		//已读消息
		List<SiteMsg> readMsgList = siteMsgService.getReadMsg(user);
		//已发消息
		List<SiteMsg> sentMsgList = siteMsgService.getSentMsg(user.getAcctID());
		//单发消息接收者列表
		Map<String, List<User>> receMap = siteMsgService.getReceMap(user);
		//班级列表
		HashMap<Integer,String> classMap = new HashMap<Integer, String>();
		List<SchoolClass> classList = classService.getClassByTeacherID(user.getAcctID());
		for (int i=0;i<classList.size();i++) {
			classMap.put(classList.get(i).getId(), classList.get(i).getClassName());
		}
		request.setAttribute("UnreadMsgList", unreadMsgList);
		request.setAttribute("ReadMsgList", readMsgList);
		request.setAttribute("SentMsgList", sentMsgList);
		request.setAttribute("receMap", receMap);
		request.setAttribute("ClassMap", classMap);
		return SUCCESS;
	}
	/**
	 * 读消息内容
	 */
	public String read_Msg(){
		SiteMsg Msg = siteMsgService.readMsg(user,Integer.parseInt(msgID));
		request.setAttribute("Msg", Msg);
		return "read";
	}
	public String delete_Msg(){
		siteMsgService.deleteMsg(user, Integer.parseInt(msgID));
		return "send";
	}
	/**
	 * 新增一条消息
	 */
	public String send_Msg(){
		siteMsg.setSenderID(user.getAcctID());
		siteMsg.setSender(user.getAcctName());
		if(ispublic.equals("0")){//消息
			String reces[] = receivers.split(";");
			String receName = userService.getUserByID(Integer.parseInt(reces[0])).getAcctName();//获取第一个名字
			if(reces.length>1){
				if(user.getUserType().equals(PoccManager.teacher)){//教师发给学生
					siteMsg.setReceiver(receName+"等"+reces.length+"个学生");
				}else if(user.getUserType().equals(PoccManager.student)){//学生发给教师
					siteMsg.setReceiver(receName+"等"+reces.length+"个教师或管理员");
				}else{
					siteMsg.setReceiver(receName+"等"+reces.length+"人");
				}
			}else{
				siteMsg.setReceiver(receName);
			}
			siteMsg.setReceiverID(StringUtils.join(reces, "|"));
		}else{//公告
			siteMsg.setRecvGroup(StringUtils.join(recvgroup, "|"));//群发组
			if(user.getUserType().equals(PoccManager.teacher)){//教师群发给学生
				siteMsg.setReceiver(StringUtils.join(recvgroup, ",")+"班");
			}
		}
		siteMsg.setSendTime(new Date());
		siteMsg.setIsread(0);
		siteMsg.setIsPublic(Integer.parseInt(ispublic));
		siteMsgService.sendMsg(siteMsg);
		return "send";
	}
	
	//get
	public String getUserType() {
		return userType;
	}
	public SiteMsg getSiteMsg() {
		return siteMsg;
	}
	//set
	public void setSiteMsgService(SiteMsgService siteMsgService) {
		this.siteMsgService = siteMsgService;
	}
	public void setSiteMsg(SiteMsg siteMsg) {
		this.siteMsg = siteMsg;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	public void setRecvgroup(String[] recvgroup) {
		this.recvgroup = recvgroup;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	
}
