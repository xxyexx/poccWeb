package main.java.edu.scnu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.HistorySiteMsgDao;
import main.java.edu.scnu.dao.SchoolClassDao;
import main.java.edu.scnu.dao.SiteMsgDao;
import main.java.edu.scnu.dao.UserDao;
import main.java.edu.scnu.entity.HistorySiteMsg;
import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.SiteMsgService;
import main.java.edu.scnu.util.PoccManager;

@Service("siteMsgService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class SiteMsgServiceImpl implements SiteMsgService{
	@Resource(name="siteMsgDao")
	private SiteMsgDao siteMsgDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="schoolClassDao")
	private SchoolClassDao schoolClassDao;
	@Resource(name="historySiteMsgDao")
	private HistorySiteMsgDao historySiteMsgDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getUnreadMsg(User user) {
		//全部消息
		List<SiteMsg> list = getAllMsg(user);
		//去除用户已删除的消息
		List<HistorySiteMsg> deleteMsgList =  historySiteMsgDao.getDeleteMsgByReceiverID(user.getId(), 1);
		for (HistorySiteMsg historySiteMsg : deleteMsgList) {
			if(list.contains(historySiteMsg.getSiteMsg())){
				list.remove(historySiteMsg.getSiteMsg());
			}
		}
		//用户已读消息
		List<SiteMsg> MsgList =  getReadMsg(user);
		for (SiteMsg siteMsg : MsgList) {
			if(list.contains(siteMsg)){
				list.remove(siteMsg);
			}
		}
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getReadMsg(User user) {
		//用户已读消息
		List<HistorySiteMsg> HSMlist =  historySiteMsgDao.getHistoryMsgByReceiverID(user.getId(), 1,0);
		List<SiteMsg> list = new ArrayList<SiteMsg>();
		for (HistorySiteMsg hsm : HSMlist) {
			if(hsm.getIsdelete()!=1){//未删除
				list.add(hsm.getSiteMsg());
			}
		}
		Collections.sort(list);
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public List<SiteMsg> getAllMsg(User user) {
		//个人信息
		List<SiteMsg> list = siteMsgDao.findByReceiverID(user.getId()+"");
		//组群发信息
		List<SiteMsg> grouplist = siteMsgDao.findByGroup(user);
		list.addAll(grouplist);
		Collections.sort(list);
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
		//全部消息（包含公告）
		List<SiteMsg> list1 = getAllMsg(user);
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
	@Override
	public Map<String, List<User>> getReceMap(User user) {
		Map<String, List<User>> receMap = new HashMap<String, List<User>>();
		//教师用户(消息：本校的院校管理员，本校的其他教师，自己教学班的学生;公告：自己教学班的学生帐号) 
		if(PoccManager.teacher.equals(user.getUserType())){
			List<User> managerList = userDao.findUserBySchool(user.getAcctTag(), PoccManager.manager);
			receMap.put(user.getAcctTag()+"管理员", managerList);
			List<User> teacherList = userDao.findUserBySchool(user.getAcctTag(), PoccManager.teacher);	
			User self = null;//需要排除自己
			for (User user2 : teacherList) {//登录时user的ip地址和时间都已变化,或重新equal方法
				if(user2.getId()==user.getId()){
					self = user2;
				}
			}
			teacherList.remove(self);
			receMap.put("其他教师", teacherList);
			List<SchoolClass> classList = schoolClassDao.getClassList(user.getAcctID());//该教师所教班级
			for(int i =0;i<classList.size();i++){
				SchoolClass sc = classList.get(i);
				List<User> studentList = userDao.findByClassID(sc.getId()+"");
				if(studentList!=null&&studentList.size()!=0){
					receMap.put(sc.getClassName(),studentList );
				}
			}
		}else if(PoccManager.student.equals(user.getUserType())){
			//学生:消息：本校的院校管理员，自己所在教学班的教师
			List<User> managerList = userDao.findUserBySchool(user.getAcctTag(), PoccManager.manager);
			receMap.put(user.getAcctTag()+"管理员", managerList);
			SchoolClass sc = schoolClassDao.get(SchoolClass.class, Integer.parseInt(user.getClassID()));
			User teacher = userDao.findByacctID(sc.getTeacherID_acctID());
			List<User> teacherList = new ArrayList<User>();
			teacherList.add(teacher);
			receMap.put(sc.getClassName()+"教师", teacherList);
		}
		return receMap;
	}
	@Override
	public SiteMsg readMsg(User user,int id) {
		SiteMsg msg = siteMsgDao.get(SiteMsg.class, id);
		List<SiteMsg> list1 = getAllMsg(user);//接收者查询全部消息
		List<SiteMsg> list2 = getSentMsg(user.getAcctID());//查询已发送的消息
		if(list1.contains(msg)||list2.contains(msg)){//验证权限（这个之后再移到aspect）
			//添加已读。。。
			if(!list2.contains(msg)){//不是自己发的消息不用添加到已读列表
				HistorySiteMsg hsm = historySiteMsgDao.getHistoryMsgBySiteMsg(user.getId(), msg);
				if(hsm==null){
					hsm = new HistorySiteMsg();
					hsm.setSiteMsg(msg);//已读的消息
					hsm.setIsread(1);//已读
					hsm.setReceiverID(user.getId());//该用户id已读消息
				}
				hsm.setReadTime(new Date());//当前查看消息时间
				historySiteMsgDao.update(hsm);
			}
			return msg;
		}else{
			return null;
		}
	}
	@Override
	public void deleteMsg(User user, int id) {
		SiteMsg msg = siteMsgDao.get(SiteMsg.class, id);//要删除的消息
		List<SiteMsg> readMsg = getReadMsg(user);
		if(readMsg.contains(msg)){
			HistorySiteMsg hsm = historySiteMsgDao.getHistoryMsgBySiteMsg(user.getId(), msg);
			if(hsm!=null){
				hsm.setIsdelete(1);
				historySiteMsgDao.update(hsm);
			}
		}
	}
	//setter
	public void setSiteMsgDao(SiteMsgDao siteMsgDao) {
		this.siteMsgDao = siteMsgDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
		this.schoolClassDao = schoolClassDao;
	}
	public void setHistorySiteMsgDao(HistorySiteMsgDao historySiteMsgDao) {
		this.historySiteMsgDao = historySiteMsgDao;
	}

}
