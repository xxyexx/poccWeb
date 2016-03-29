package main.java.edu.scnu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import main.java.edu.scnu.entity.Config;
import main.java.edu.scnu.service.ConfigService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="configAction")
@Scope("prototype")
public class ConfigAction extends ActionSupport {

	private int id;
	private String item;
	private String value;
	private String itemType;
	private String displayName;
	private String memo;
		
	@Resource(name="configService")
	private ConfigService configService;	
	private HttpServletRequest request;

	public ConfigAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	public String update(){
		Config config = this.getConfig(this.id);
		config.setItem(this.item);
		config.setValue(this.value);
		config.setDisplayName(this.displayName);
		config.setMemo(this.memo);
		if ("tryInterval".equals(this.item)){
			config.setItemType("01");
		}else if("tryDuration".equals(this.item)){
			config.setItemType("01");
		}else if("monthRent".equals(this.item)){
			config.setItemType("02");
		}else if("discount".equals(this.item)){
			config.setItemType("03");
		}else if("moreMonth".equals(this.item)){
			config.setItemType("04");
		}
		configService.updateConfig(config);
		return view();
	}
	
	public String delete(){
		configService.deleteConfig(this.id);
		return view();
	}
	
	public String view(){
		List<Config> configList = configService.getConfigAll();
		request.setAttribute("configList", configList);
		return "view";
	}
	
	private Config getConfig(int id){
		Config result = null;
		if (id>0) result = configService.getConfig(id);
		else {
			result = new Config();
			result.setId(0);
		}
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}







