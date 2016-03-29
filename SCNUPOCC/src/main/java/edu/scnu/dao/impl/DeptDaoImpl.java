package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.DeptDao;
import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.School;

@Repository(value="deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept> 
	implements DeptDao
{
	@Override
	public List<Dept> findBySchoolID(int id) {
		String hql= "from dept where schoolID = ?";
		return find(hql, id);
	}
	
	@Override
	public Dept findByDeptName(School school, String deptName) {
		String hql = "select d from dept d where d.school = ? and d.deptName = ?";
		Object params[]={school,deptName};
		List<Dept> deptList = find(hql, params);
		if(deptList!=null&&deptList.size()!=0){
			return deptList.get(0);
		}else{
			return null;
		}
	}

}
