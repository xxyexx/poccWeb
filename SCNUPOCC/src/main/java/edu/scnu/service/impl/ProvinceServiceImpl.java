package main.java.edu.scnu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.DeptDao;
import main.java.edu.scnu.dao.ProvinceDao;
import main.java.edu.scnu.dao.SchoolDao;
import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.Province;
import main.java.edu.scnu.entity.School;
import main.java.edu.scnu.service.ProvinceService;

@Service("provinceService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ProvinceServiceImpl implements ProvinceService{
	@Resource(name="provinceDao")
	private ProvinceDao provinceDao;
	@Resource(name="schoolDao")
	private SchoolDao schoolDao;
	@Resource(name="deptDao")
	private DeptDao deptDao;
	
	@Transactional(readOnly=true)
	public Map<String, String> getProvinceMap() {
		Map<String, String> provinceMap = new HashMap<String, String>();
		List<Province> provinceList = provinceDao.findIsUsing();
		for (int i = 0; i < provinceList.size(); i++) {
			provinceMap.put(String.valueOf(provinceList.get(i).getId()), provinceList.get(i).getProvince());
		}
		return provinceMap;
	}
	
	@Override
	public Map<String, String> getDeptMap(int schoolID) {
		Map<String, String> deptMap = new HashMap<String, String>();
		List<Dept> deptList = deptDao.findBySchoolID(schoolID);
		for (Dept dept : deptList) {
			deptMap.put(String.valueOf(dept.getId()), dept.getDeptName());
		}
		return deptMap;
	}
	
	@Override
	public Map<String, String> getSchoolMap(int provinceID) {
		Map<String, String> schoolMap = new HashMap<String, String>();
		List<School> schoolList = schoolDao.findByProvinceID(provinceID);
		for (School school : schoolList){
			schoolMap.put(String.valueOf(school.getId()), school.getSchoolName());
		}
		return schoolMap;
	}
	
	@Override
	public School getSchool(int schoolID) {
		return schoolDao.get(School.class, schoolID);
	}
	
	@Override
	public Dept getDept(int deptID){
		return deptDao.get(Dept.class, deptID);
	}

	//getter,setter
	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}
	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	

	



	
	
}
