package main.java.edu.scnu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Dept;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.School;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ProvinceService;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.ModelUtil;
import main.java.edu.scnu.util.StringUtil;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户批量操作，包括批量新增、用户绑定
 * 
 * @author mozilla
 *
 */

@Controller(value="userBatchAction")
@Scope("prototype")
public class UserBatchAction extends ActionSupport{
		
	//账号信息
	private int schoolID; //学校ID
	private int deptID;	//学院ID
	private int first;	//开始顺序
	private int num;	//生成数量
	
	//计费信息
	
	//excel文件
	private File excelFile;
	
	//Service
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="provinceService")
	private ProvinceService provinceService;
	private HttpServletRequest request;
	
	public UserBatchAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	public String create(){
		
		School school = provinceService.getSchool(schoolID);		
		Dept dept = null;
		if (deptID>0){
			//如果选择了学院;
			dept = provinceService.getDept(deptID);
		}else{
			//如果没有选学院
			dept = ModelUtil.getDefaultDept();
		}
		String did = StringUtil.getStr2(dept.getDeptID());
		
		//生成模板用户
		User user = new User();
		user.setAcctTag(school.getSchoolName());
		user.setAcctType(dept.getDeptName());
		user.setUserType("student");
		user.setPasswd("");		
		user.setAcctID("");
		user.setAcctName("");		
		user.setPayTag(0);
		user.setLockTag(1);
		//生成用户
		userService.createUser(user, school.getSchoolID(), did, first, num);
		
		return SUCCESS;		
	}

	public String uploadfile(){
		
		try {
			FileInputStream fis = new FileInputStream(excelFile);
			Workbook workbook = WorkbookFactory.create(fis); //这种方式 Excel 2003/2007/2010 都是可以处理的  
//	        int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
	        Sheet sheet = workbook.getSheetAt(0);
	        int numberCol = 0; 	//学号列号
	        int nameCol = 0;	//姓名列号
	        for (int col = 0; col<sheet.getRow(0).getPhysicalNumberOfCells(); col++){
	        	String value = sheet.getRow(0).getCell(col).getStringCellValue();
	        	if ("学生学号".equals(value)||"学号".equals(value)
	        			||"学生账号".equals(value)||"账号".equals(value)){
	        		numberCol = col;
	        	}
	        	if ("学生姓名".equals(value)||"姓名".equals(value)){
	        		nameCol = col;
	        	}
	        }	        
//	        System.out.println("学号列："+numberCol);
//	        System.out.println("姓名列："+nameCol);	        
	        for (int rowNum = 1; rowNum<sheet.getPhysicalNumberOfRows(); rowNum++){
	        	Row row = sheet.getRow(rowNum);
	        	String name;
	        	String number;
//	        	//把数值型学号转换为字符串型
	        	if (row.getCell(numberCol).getCellType()==Cell.CELL_TYPE_NUMERIC){
	        		double d = row.getCell(numberCol).getNumericCellValue();
	        		DecimalFormat df=new DecimalFormat("0"); 
	        		number = df.format(d);
	        	}else number = row.getCell(numberCol).getStringCellValue();
	        	
	        	name = row.getCell(nameCol).getStringCellValue();
	        	System.out.println("学号："+number);
	        	System.out.println("姓名："+name);
	        	System.out.println();
	        	
	        }
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "relateView";		
	}
	
	public String createView(){	
		request.getSession().setAttribute("ProvinceMap", provinceService.getProvinceMap());
		return "createView";		
	}
	
	public String relateView(){
		
//		Page<User> userPage = new Page<User>(0);
		User user = new User();
		Page<User> page = new Page<User>(0);
		user.setAcctTag("不限");
		user.setAcctType("不限");
		Page<User> userPage = userService.getUserPage(user, page);
//		userPage.setPageSize(10);
		request.setAttribute("userPage", userPage);
		System.out.println("listSize="+userPage.getList().size());
//		System.out.println();
		request.getSession().setAttribute("ProvinceMap", provinceService.getProvinceMap());
		
		return "relateView";		
	}
	
	public String managerView(){
		User user = new User();
		School school = provinceService.getSchool(schoolID);		
		Dept dept = null;
		if (deptID>0){
			//如果选择了学院;
			dept = provinceService.getDept(deptID);
		}else{
			//如果没有选学院
			dept = new Dept();
			dept.setDeptName("不限");
		}
		if (school!=null) user.setAcctTag(school.getSchoolName());
		else user.setAcctTag("不限");
		user.setAcctType(dept.getDeptName());
		Page<User> page = new Page<User>(0);		
		Page<User> userPage = userService.getUserPage(user, page);
		request.setAttribute("userPage", userPage);
//		System.out.println(this.schoolID);
//		System.out.println(this.deptID);
		return "managerView";
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}


	
}








