package main.java.edu.scnu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.HomeworkDao;
import main.java.edu.scnu.entity.Homework;

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
			if (homework.getClassID().contains(classID)) {
				list.add(homework);
			}
		}
		return list;
	}

}
