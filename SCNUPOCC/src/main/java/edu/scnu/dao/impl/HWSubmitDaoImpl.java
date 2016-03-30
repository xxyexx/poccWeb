package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.HWSubmitDao;
import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.User;

@Repository(value="hwsubmitDao")
public class HWSubmitDaoImpl extends BaseDaoImpl<HWSubmit>
	implements HWSubmitDao
{

	@Override
	public List<HWSubmit> findByStud_ID(User user) {
		String hql = "select h from hwsubmit h where h.user = ?";
		List<HWSubmit> list = find(hql,user);
		return list;
	}

	@Override
	public List<HWSubmit> findByHWID(Homework hw) {
		String hql = "select h from hwsubmit h where h.homework = ?";
		List<HWSubmit> list = find(hql,hw);
		return list;
	}

}
