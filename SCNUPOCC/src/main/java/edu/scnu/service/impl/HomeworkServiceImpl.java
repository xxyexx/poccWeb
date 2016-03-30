package main.java.edu.scnu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.HWSubmitDao;
import main.java.edu.scnu.dao.HomeworkDao;
import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.service.HomeworkService;

@Service("homeworkService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class HomeworkServiceImpl implements HomeworkService{
	@Resource(name="homeworkDao")
	private HomeworkDao homeworkDao;
	@Resource(name="hwsubmitDao")
	private HWSubmitDao hwsubmitDao;

	@Override
	@Transactional(readOnly=true)
	public List<Homework> getNewHW(String classID) {
		List<Homework> hwList = homeworkDao.findByClassID(classID);
		List<Homework> list = new ArrayList<Homework>();
		Date newdate = new Date();//今天时间
		for (Homework homework : hwList) {
			if(newdate.before(homework.getCloseDate())){
				list.add(homework);
			}
		}
		return list;
	}
	
	@Override
	//@Transactional(readOnly=true) 修改超过提交期限的作业
	public List<Homework> getAllHW(String classID) {
		return  homeworkDao.findByClassID(classID);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Homework> getUnfinishedHW(String classID, int id) {
		List<Homework> newHWList = getNewHW(classID);//新作业
		List<HWSubmit> finishedHWList = getfinishedHW(classID,id);//已提交的作业
		List<Homework> unfinishedHWList = new ArrayList<Homework>();
		for (Homework homework : newHWList) {
			boolean flag = false;
			for (HWSubmit hwSubmit : finishedHWList) {
				if(hwSubmit.getHomework().getId()==homework.getId()){
					flag = true;//已交作业
				}
			}
			if(flag == false){
				unfinishedHWList.add(homework);
			}
		}
		return unfinishedHWList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<HWSubmit> getfinishedHW(String classID, int id) {
		return hwsubmitDao.findByStud_ID(id);
	}	
	
	@Override
	@Transactional(readOnly=true)
	public Homework getHomeworkbyID(int id) {
		return homeworkDao.get(Homework.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public HWSubmit getHWSubmit(int id, int hwID) {
		List<HWSubmit> hwlist = hwsubmitDao.findByStud_ID(id);
		HWSubmit hws = null;
		for (HWSubmit hwSubmit : hwlist) {
			if(hwSubmit.getHomework().getId()==hwID){
				hws = hwSubmit;
				break;
			}
		}
		return hws;
	}
	@Override
	public boolean saveHWSubmit(HWSubmit hwSubmit) {
		hwsubmitDao.update(hwSubmit);
		return true;
	}
	@Override
	@Transactional(readOnly=true)
	public HWSubmit getHWSubmitByid(int hwSubmitID) {
		return hwsubmitDao.get(HWSubmit.class, hwSubmitID);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Homework> getHomeworkbyTeacherID(String teacher_acctID) {
		return homeworkDao.findByTeacher_acctID(teacher_acctID);
	}
	@Override
	public boolean addHomework(Homework homework) { 
		homeworkDao.update(homework);
		return true;
	}
	@Override
	public void deleteHomework(int id) {
		homeworkDao.delete(Homework.class, id);
	}
	@Override
	public void updateHomework(Homework homework) {
		homeworkDao.update(homework);
	}
	@Override
	public List<HWSubmit> getfinishedHWSList(int hwID) {
		Homework hw = homeworkDao.get(Homework.class, hwID);
		return hwsubmitDao.findByHWID(hw);
	}
	
	
	//getter,setter
	public HomeworkDao getHomeworkDao() {
		return homeworkDao;
	}
	public void setHomeworkDao(HomeworkDao homeworkDao) {
		this.homeworkDao = homeworkDao;
	}

	public HWSubmitDao getHwsubmitDao() {
		return hwsubmitDao;
	}

	public void setHwsubmitDao(HWSubmitDao hwsubmitDao) {
		this.hwsubmitDao = hwsubmitDao;
	}


}
