package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.ProvinceDao;
import main.java.edu.scnu.entity.Province;

@Repository(value="provinceDao")
public class ProvinceDaoImpl extends BaseDaoImpl<Province> 
	implements ProvinceDao
{

}
