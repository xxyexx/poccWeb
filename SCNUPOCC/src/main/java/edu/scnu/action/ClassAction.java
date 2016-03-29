package main.java.edu.scnu.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ClassService;
import main.java.edu.scnu.service.ProvinceService;
import main.java.edu.scnu.util.ExcelUtil;
import main.java.edu.scnu.util.PoccManager;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="classAction")
@Scope("prototype")
public class ClassAction extends ActionSupport {
	private HttpServletRequest request;
	private User user;
	@Resource(name="classService")
	private ClassService classService;
	@Resource(name="provinceService")
	private ProvinceService provinceService;
	//教师添加班级
	private SchoolClass schoolclass;
	private String classID;
	private List<String> userID;
	private String deptID;
	//文件流
	private InputStream fileInputStream;
	//文件下载名称
	private String filename;
	public ClassAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
	}
	/**
	 * 显示班级列表(教学班级管理首页)
	 */
	public String show_Class(){
		//班级列表
		List<SchoolClass> classList = classService
				.getClassByTeacherID(user.getAcctID());
		request.setAttribute("classList", classList);
		return SUCCESS;
	}
	/**
	 * 添加班级
	 */
	public String add_Class(){
		schoolclass.setTeacherID_acctID(user.getAcctID());//创建人
		schoolclass.setCreateDate(new Date());//创建日期
		if(user.getUserType().equals(PoccManager.teacher)){
			classService.addClass(schoolclass);//添加班级
		}
		return SUCCESS;
	}
	/**
	 * 教师删除班级
	 */
	public String delete_Class(){
		if(user.getUserType().equals(PoccManager.teacher)){
			classService.deleteClass(classID);//根据id删除班级
		}
		return SUCCESS;
	}
	/**
	 * 显示班级学生列表
	 */
	public String detail_Class(){
		List<User> studentList = classService.getStudByClassID(classID);
		int studNum = 0;//当前班级学生人数 
		if(studentList!=null){
			studNum = studentList.size();
		}
		SchoolClass schoolClass = classService.getSchoolClassByid(Integer.parseInt(classID));
		request.setAttribute("studentList", studentList);
		request.setAttribute("schoolClass", schoolClass);
		request.setAttribute("studNum", studNum);
		return "detail";
	}
	/**
	 * 将一个学生移出班级
	 */
	public String removeStud_Class(){
		if(userID!=null&&user.getUserType().equals(PoccManager.teacher)){
			classService.deleteStudfromClass(userID, classID);
		}
		return "removeStud";
	}
	/**
	 * 根据学院id查询没有被分班的学生
	 */
	public String addStudent_Class(){
		SchoolClass schoolClass = classService.getSchoolClassByid(Integer.parseInt(classID));
		Map<String,String> deptMap = provinceService.getDeptMapBySchool(user.getAcctTag());
		if(deptID==null&&user.getAcctType()!=null){//该教师没有设置自己的学院
			deptID = provinceService
					.getDeptBydeptname(user.getAcctTag(), user.getAcctType())
					.getId()+"";
		}
		List<User> otherStudents =null;//根据学院id查询没有被分班的学生
		if(!deptID.equals("0")){
			otherStudents = classService.getStudByDeptID(Integer.parseInt(deptID));
		}
		request.setAttribute("deptMap", deptMap);
		request.setAttribute("otherStudents", otherStudents);
		request.setAttribute("deptID", deptID);
		request.setAttribute("schoolClass", schoolClass);
		return "addStudent";
	}
	/**
	 * 给学生分班
	 */
	public String divideInto_Class(){
		if(userID!=null&&user.getUserType().equals(PoccManager.teacher)){
			classService.groupClass(userID, classID);
		}
		return "removeStud";
	}
	/**
	 * 导出excel
	 */
	public String exportExcel_Class(){
		SchoolClass schoolClass = classService.getSchoolClassByid(Integer.parseInt(classID));
		String name = schoolClass.getClassName()+"学生名单.xls";
		try {
			filename = java.net.URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("字符转码失败");
		}
		return "exportExcel";
	}
	public InputStream getExcelFile(){//getExcelFile()一定要与excelFile对应，否则会出现异常
		String title = "学生列表";
		String header[] = {"学号","姓名","学院"};//excel第一行
		List<String[]> list = new ArrayList<String[]>();//封装每行内容信息
		List<User> studentList = classService.getStudByClassID(classID);
		for (User user : studentList) {
			String []s=new String[header.length];
			s[0]=user.getLoginID();
			s[1]=user.getAcctName();
			s[2]=user.getAcctType();
			list.add(s);
		}
		return ExcelUtil.writeExcel(title, header, list);
	}
	//set
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}
	public void setSchoolclass(SchoolClass schoolclass) {
		this.schoolclass = schoolclass;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public void setUserID(List<String> userID) {
		this.userID = userID;
	}
	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	//get
	public SchoolClass getSchoolclass() {
		//这里要添加get方法以返回SchoolClass对象 用以执行之后的set属性操作
		//否则,每次set,SchoolClass里的属性都会new一个新的对象
		return schoolclass;
	}
	public String getClassID() {
		return classID;
	}
	public List<String> getUserID() {
		return userID;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public String getFilename() {
		return filename;
	}
	
}
