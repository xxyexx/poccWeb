package main.java.edu.scnu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.SiteMsgDao;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.SiteMsgService;

@Service("siteMsgService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class SiteMsgServiceImpl implements SiteMsgService{
	@Resource(name="siteMsgDao")
	private SiteMsgDao siteMsgDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getUnreadMsg(User user) {
		//个人信息
		List<SiteMsg> list1 = siteMsgDao.findByReceiverID(user.getAcctID());
		//组群发信息
		List<SiteMsg> grouplist = siteMsgDao.findByGroup(user);
		list1.addAll(grouplist);
		Collections.sort(list1);
		List<SiteMsg> list = new ArrayList<SiteMsg>();
		for (SiteMsg siteMsg : list1) {
			if(siteMsg.getRead()==0){//未读
				list.add(siteMsg);
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getReadMsg(User user) {
		//个人信息
		List<SiteMsg> list1 = siteMsgDao.findByReceiverID(user.getAcctID());
		//组群发信息
		List<SiteMsg> grouplist = siteMsgDao.findByGroup(user);
		list1.addAll(grouplist);
		Collections.sort(list1);
		List<SiteMsg> list = new ArrayList<SiteMsg>();
		for (SiteMsg siteMsg : list1) {
			if(siteMsg.getRead()==1){//已读
				list.add(siteMsg);
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getSentMsg(String User_acctID) {
		return siteMsgDao.findBySenderID(User_acctID);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getPublicMsg(User user) {
		//个人信息
		List<SiteMsg> list1 = siteMsgDao.findByReceiverID(user.getAcctID());
		//组群发信息
		List<SiteMsg> grouplist = siteMsgDao.findByGroup(user);
		list1.addAll(grouplist);
		Collections.sort(list1);
		List<SiteMsg> list = new ArrayList<SiteMsg>();
		for (SiteMsg siteMsg : list1) {
			if(siteMsg.getIsPublic()==1){//公告
				list.add(siteMsg);
			}
		}
		return list;
	}
	@Override
	public void sendMsg(SiteMsg siteMsg) {
		siteMsgDao.update(siteMsg);
	}
	
	//setter
	public void setSiteMsgDao(SiteMsgDao siteMsgDao) {
		this.siteMsgDao = siteMsgDao;
	}
	
}
