package main.java.edu.scnu.service;

import java.util.List;

import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;

/**
 * 站内信箱
 */
public interface SiteMsgService {
	/**
	 * 查询未读消息
	 * @param User_acctID
	 * @return List
	 */
	List<SiteMsg> getUnreadMsg(User user);
	
	/**
	 * 查询已读消息
	 * @param User_acctID
	 * @return List
	 */
	List<SiteMsg> getReadMsg(User user);
	/**
	 * 查询已发送消息
	 * @param User_acctID 发送者ID
	 * @return List
	 */
	List<SiteMsg> getSentMsg(String User_acctID);
	/**
	 * 查询公告
	 * @param user
	 * @return
	 */
	List<SiteMsg> getPublicMsg(User user);
}
