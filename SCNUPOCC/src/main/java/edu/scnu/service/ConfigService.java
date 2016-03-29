package main.java.edu.scnu.service;

import java.util.List;

import main.java.edu.scnu.entity.Config;

public interface ConfigService {

	
	/**根据类型config类型返回config列表
	 * @return
	 */
	public List<Config> getConfigByItemType(String type);
	
	/**返回所有config对象
	 * @return
	 */
	public List<Config> getConfigAll();
	
	/**根据id获得config对象
	 * @param id
	 * @return
	 */
	public Config getConfig(int id);
	
	/**更新config对象
	 * @param config
	 * @return
	 */
	public boolean updateConfig(Config config);
	
	/**删除config对象
	 * @param id 需要删除config的id
	 * @return
	 */
	public boolean deleteConfig(int id);
	
}
