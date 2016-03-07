package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.PoccFileDao;
import main.java.edu.scnu.entity.PoccFile;

@Repository(value="poccFileDao")
public class PoccFileDaoImpl  extends BaseDaoImpl<PoccFile>
	implements PoccFileDao
{

	@Override
	public List<PoccFile> findByUserID(String user_acctID) {
		String hql = "select p from poccfile p where p.user_acctID = ?";
		return find(hql, user_acctID);
	}


}
