package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.PageSetDao;
import main.java.edu.scnu.entity.PageSet;

@Repository(value="pageSetDao")
public class PageSetDaoImpl extends BaseDaoImpl<PageSet> 
	implements PageSetDao
{

}
