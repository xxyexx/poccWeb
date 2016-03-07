package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.HWSubmit;

public interface HWSubmitDao extends BaseDao<HWSubmit>{
	/**
	 * 学生内部账号 查询学生提交的所有作业
	 * @param studNo 学生内部账号
	 * @return List
	 */
	List<HWSubmit> findByStud_acctID(String studNo);
}
