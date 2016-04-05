package main.java.edu.scnu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.SiteMsgDao;
import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.util.MyStringUtil;

@Repository(value="siteMsgDao")
public class SiteMsgDaoImpl extends BaseDaoImpl<SiteMsg> 
	implements SiteMsgDao
{

	@Resource(name="userDao")
	private UserDao userDao;
	@Override
	public List<SiteMsg> findByReceiverID(String id) {
		//接收者ID不为空
		String hql = "select s from sitemsg s where s.receiverID != null";
		List<SiteMsg> siteMsgList = find(hql);
		List<SiteMsg> list = new ArrayList<SiteMsg>();
		for (SiteMsg siteMsg : siteMsgList) {
			if(MyStringUtil.checkContain(siteMsg.getReceiverID(), id, "\\|")){
				list.add(siteMsg);
			}
		}
		return list;
	}

	@Override
	public List<SiteMsg> findBySenderID(String User_acctID) {
		String hql = "select s from sitemsg s where s.senderID = ?";
		return find(hql, User_acctID);
	}

	@Override
	public List<SiteMsg> findByGroup(User user) {
		//只取组群发的消息
		String hql = "select s from sitemsg s where s.recvGroup != null";
		List<SiteMsg> siteMsgList = find(hql);
		List<SiteMsg> list = new ArrayList<SiteMsg>();
		for (SiteMsg siteMsg : siteMsgList) {
			//网站公告,
			if(siteMsg.getRecvGroup().equals("WEBSITE")){
				list.add(siteMsg);
			}else if(user.getUserType().equals("manager")){
				//用户是院校管理员,收到来自合作方的群发消息
				if(MyStringUtil.checkContain(siteMsg.getRecvGroup(), user.getAcctTag(), "\\|")){
					list.add(siteMsg);
				}
			}else if(user.getUserType().equals("teacher")){
				//当前用户是教师,收到来自院校管理员的群发消息
				if(siteMsg.getRecvGroup().equals("allTeacher")||siteMsg.getRecvGroup().equals("all")){
					//同一个学校
					if(userDao.findByacctID(siteMsg.getSenderID()).getAcctTag().equals(user.getAcctTag())){
						list.add(siteMsg);
					}
				}
			}else if(user.getUserType().equals("student")){
				//当前用户是学生,收到来自校管理者或教师的群发消息
				if(siteMsg.getRecvGroup().equals("allStudent")||siteMsg.getRecvGroup().equals("all")){
					//同一个学校
					if(userDao.findByacctID(siteMsg.getSenderID()).getAcctTag().equals(user.getAcctTag())){
						list.add(siteMsg);
					}
				}else if(MyStringUtil.checkContain(siteMsg.getRecvGroup(), user.getClassID(), "\\|")){
					//是该学生班级的教师所发群消息:学生所属班级管理者ID=教师ID
					if(user.getManagerID().equals(siteMsg.getSenderID())){
						list.add(siteMsg);
					}
				}
			}
		}
		return list;
	}

	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
