package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.ConfigDao;
import main.java.edu.scnu.entity.Config;

@Repository(value="configDao")
public class ConfigDaoImpl  extends BaseDaoImpl<Config> 
	implements ConfigDao
{

	@Override
	public List<Config> getConfigByItemType(String type){
		
		String hql = "from config en where 1=1";
		if (type!=null&&!type.isEmpty()){
			hql += "and en.itemType = "+type;
		}		
		return find(hql);
		
	}
	
}
