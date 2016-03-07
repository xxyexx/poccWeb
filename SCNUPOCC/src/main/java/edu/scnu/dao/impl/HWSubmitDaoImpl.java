package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.HWSubmitDao;
import main.java.edu.scnu.entity.HWSubmit;

@Repository(value="hwsubmitDao")
public class HWSubmitDaoImpl extends BaseDaoImpl<HWSubmit>
	implements HWSubmitDao
{

	@Override
	public List<HWSubmit> findByStud_acctID(String studNo) {
		String hql = "select h from hwsubmit h where h.studNo_acctID = ?";
		List<HWSubmit> list = find(hql,studNo);
		return list;
	}

}
