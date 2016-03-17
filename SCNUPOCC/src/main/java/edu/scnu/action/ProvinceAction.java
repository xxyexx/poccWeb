package main.java.edu.scnu.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.service.ProvinceService;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="provinceAction")
@Scope("prototype")
public class ProvinceAction extends ActionSupport {

	
	//查询信息
	private String provinceID;
	private String schoolID;
	private String deptID;
	
	//返回信息：省份，学校，学院
	private Map<String, String> schoolMap;
	private Map<String, String> deptMap; 
	
	@Resource(name="provinceService")
	private ProvinceService provinceService;
	private HttpServletRequest request;
	
	public ProvinceAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	//获取学校
	public String getSchool(){
		this.schoolMap = provinceService.getSchoolMap(Integer.parseInt(provinceID));
		return "getSchool";
	}
	
	//获取学院
	public String getDept(){		
		this.deptMap = provinceService.getDeptMap(Integer.parseInt(schoolID));	
		return "getDept";		
	}

	public Map<String, String> getSchoolMap() {
		return schoolMap;
	}

	public Map<String, String> getDeptMap() {
		return deptMap;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
}
