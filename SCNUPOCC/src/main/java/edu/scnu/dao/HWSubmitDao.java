package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;

public interface HWSubmitDao extends BaseDao<HWSubmit>{
	/**
	 * 学生内部账号 查询学生提交的所有作业
	 * @param studNo 学生内部账号
	 * @return List
	 */
	List<HWSubmit> findByStud_acctID(String studNo);
	/**
	 * 根据Homework作业ID查询这个作业的所有提交
	 * @param hwID 作业id
	 * @return
	 */
	List<HWSubmit> findByHWID(Homework hw);
	
}
