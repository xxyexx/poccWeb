package main.java.edu.scnu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.ConfigDao;
import main.java.edu.scnu.entity.Config;
import main.java.edu.scnu.service.ConfigService;

@Service("configService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ConfigServiceImpl implements ConfigService {

	@Resource(name="")
	private ConfigDao configDao;
	
	@Override
	public List<Config> getConfigByItemType(String type) {
		return configDao.getConfigByItemType(type);
	}

	@Override
	public List<Config> getConfigAll() {
		return configDao.findAll(Config.class);
	}

	@Override
	public boolean deleteConfig(int id) {
		Config config = configDao.get(Config.class, id);
		configDao.delete(config);
		return true;
	}

	@Override
	public Config getConfig(int id) {
		return configDao.get(Config.class, id);
	}

	@Override
	public boolean updateConfig(Config config) {
		configDao.update(config);
		return true;
	}

	
	
}
