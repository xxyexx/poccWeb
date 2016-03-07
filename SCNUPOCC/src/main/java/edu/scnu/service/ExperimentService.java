package main.java.edu.scnu.service;

import java.util.List;

import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.PoccFile;

public interface ExperimentService {
	
	/**
	 * 查询所有的实验列表
	 * @return List
	 */
	List<Experiment> getAllExper();
	
	/**
	 * 查询该用户实验存档
	 * @param user_acctID
	 * @return List
	 */
	List<PoccFile> getAllPoccFile(String user_acctID);
	/**
	 * 根据实验id获取该实验内容
	 * @param id Experiment.id
	 * @return
	 */
	Experiment getExperiment(int id);
	
	/**
	 * 用户根据实验项目id查询该实验的存档
	 * @param user_acctID
	 * @param expID
	 * @return
	 */
	List<PoccFile> getPoccFileByid(String user_acctID,int expID);
	
	/**
	 * 保存实验
	 * @param poccfile
	 * @return
	 */
	boolean savePoccFile(PoccFile poccfile);
}
