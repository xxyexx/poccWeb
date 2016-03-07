package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.HistoryUserDao;
import main.java.edu.scnu.entity.HistoryUser;

@Repository(value="historyUserDao")
public class HistoryUserDaoImpl extends BaseDaoImpl<HistoryUser>
	implements HistoryUserDao
{

}
