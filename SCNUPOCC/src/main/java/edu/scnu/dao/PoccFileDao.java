package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.PoccFile;

public interface PoccFileDao extends BaseDao<PoccFile>{
	/**
	 * 根据用户内部码ID查询实验文件存档
	 * @param user_acctid
	 * @return
	 */
	List<PoccFile> findByUserID(String user_acctid);
}
