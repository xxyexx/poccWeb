package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.SchoolDao;
import main.java.edu.scnu.entity.School;

@Repository(value="schoolDao")
public class SchoolDaoImpl extends BaseDaoImpl<School> 
	implements SchoolDao
{	
	@Override
	public List<School> findByProvinceID(int id) {
		String hql= "from school where provinceID = ?";
		return find(hql, id);
	}

	@Override
	public List<School> findByProvinceName(String provinceName) {
		String hql= "from school where province = ?";
		return find(hql, provinceName);
	}

	@Override
	public School findSchoolByName(String schoolName) {
		String hql = "select s from school s where s.schoolName = ?";
		List<School> schoolList = find(hql, schoolName);
		if(schoolList!=null&&schoolList.size()!=0){
			return schoolList.get(0);
		}else{
			return null;	
		}
	}

}
