package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.ProvinceDao;
import main.java.edu.scnu.entity.Province;

@Repository(value="provinceDao")
public class ProvinceDaoImpl extends BaseDaoImpl<Province> 
	implements ProvinceDao
{
	@Override
	public List<Province> findIsUsing() {
		//getSimpleName得到className如:Province,要转成跟表名一致:province
		
		return find("select en from "
			+Province.class.getSimpleName().toLowerCase()+" en "
			+"where en.is_using = 1");
	}

}
