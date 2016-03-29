package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.Config;

public interface ConfigDao extends BaseDao<Config> {

	public List<Config> getConfigByItemType(String type);
}
