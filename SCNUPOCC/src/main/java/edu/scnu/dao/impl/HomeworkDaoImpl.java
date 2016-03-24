package main.java.edu.scnu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.HomeworkDao;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.util.MyStringUtil;

@Repository(value="homeworkDao")
public class HomeworkDaoImpl extends BaseDaoImpl<Homework>
	implements HomeworkDao
{
	@Override
	public List<Homework> findByClassID(String classID) {
		List<Homework> hwList = findAll(Homework.class);
		List<Homework> list = new ArrayList<Homework>();
		for (Homework homework : hwList) {
			//包含这个班级
			if (MyStringUtil.checkContain(homework.getClassID(), classID, "//|")) {
				//时间超过截止时间,修改状态为关闭
				if(new Date().after(homework.getCloseDate())){
					homework.setState(0);
					update(homework);
				}
				list.add(homework);
			}
		}
		return list;
	}

	@Override
	public List<Homework> findByTeacher_acctID(String acctID) {
		String hql = "select h from homework h where h.teacherID_acctID = ?";
		List<Homework> list = find(hql,acctID);
		return list;
	}

}
