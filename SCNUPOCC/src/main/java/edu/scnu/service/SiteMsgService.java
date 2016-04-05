package main.java.edu.scnu.service;

import java.util.List;
import java.util.Map;

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
	 * 接收者查询全部消息
	 * @param user
	 * @return List
	 */
	List<SiteMsg> getAllMsg(User user);
	/**
	 * 查询已发送消息
	 * @param User_acctID 发送者ID
	 * @return List
	 */
	List<SiteMsg> getSentMsg(String User_acctID);
	/**
	 * 接收者查询公告
	 * @param user
	 * @return
	 */
	List<SiteMsg> getPublicMsg(User user);
	/**
	 * 发送消息（公告）
	 * @param siteMsg
	 */
	void sendMsg(SiteMsg siteMsg);
	/**
	 * 获取当前用户可单发信息的其他用户
	 * @param user 
	 * @return 接收者 map如：华南师范大学管理员：院校管理员list
	 */ 
	Map<String,List<User>> getReceMap(User user);
	/**
	 * 读一条消息
	 * @param id SiteMsg.id
	 * @return SiteMsg
	 */
	SiteMsg readMsg(User user,int id);
	/**
	 * 删除一条已读消息
	 * @param user
	 * @param id SiteMsg.id
	 */
	void deleteMsg(User user,int id);
}
