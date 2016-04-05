package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;

public interface SiteMsgDao extends BaseDao<SiteMsg>{
	/**
	 * 根据ReceiverID查询接收者信箱列表
	 * @param ID user.id
	 * @return List
	 */
	List<SiteMsg> findByReceiverID(String ID);
	/**
	 * 查询发送者信箱列表
	 * @param User_acctID
	 * @return List
	 */
	List<SiteMsg> findBySenderID(String User_acctID);
	/**
	 * recvGroup查询接收组信箱列表
	 * **群发规则***************************************************
	 * 根据发送者身份:
	 * ①平台管理员:发给整个网站的公告或消息recvGroup=WEBSITE
	 * ②合作方:发给所代理学校的所有院校管理员,学校列表（acctTag1|acctTag2）
	 * ③院校管理员:发给该学校的所有老师和学生(all)、所有老师(allTeacher)、所有学生(allStudent)
	 * ④教师:发给自己教学班的所有学生（所有班级）或几个班级(ClassID1|ClassID2)
	 *************************************************************
	 * @param userType 用户类型
	 * recvGroup包括：整个网站（WEBSITE）、合作方（partner）、院校管理员（manager）、教师（teacher）
	 * @return List
	 */
	List<SiteMsg> findByGroup(User user);
}
