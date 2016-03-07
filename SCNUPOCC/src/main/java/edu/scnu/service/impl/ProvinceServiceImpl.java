package main.java.edu.scnu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.ProvinceDao;
import main.java.edu.scnu.entity.Province;
import main.java.edu.scnu.service.ProvinceService;

@Service("provinceService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ProvinceServiceImpl implements ProvinceService{
	@Resource(name="provinceDao")
	private ProvinceDao provinceDao;
	
	@Transactional(readOnly=true)
	public Map<Integer, String> getProvinceMap() {
		Map<Integer, String> provinceMap = new HashMap<Integer, String>();
		List<Province> provinceList = provinceDao.findAll(Province.class);
		for (int i = 0; i < provinceList.size(); i++) {
			provinceMap.put(i, provinceList.get(i).getProvince());
		}
		return provinceMap;
	}

	//getter,setter
	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}
	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	
}
