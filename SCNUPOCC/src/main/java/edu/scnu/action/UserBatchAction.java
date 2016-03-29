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
import main.java.edu.scnu.util.DateUtil;
import main.java.edu.scnu.util.MD5Util;
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
	private String userType;//用户类型（student,teacher,manager）
	
	//计费信息
	private int monthlyRent;//基本月租
	private String rentDate1;//起始时间
	private String rentDate2;//结束时间
	private String couponType;//优惠类型
	private int discount;//折扣
	private int moreMonth;//赠送月份
	
	//excel文件
	private File excelFile;
	private List<String> uid;
	
	

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
		if (schoolID<=0) return SUCCESS;
		School school = this.getSchool(schoolID);		
		Dept dept = this.getDept(deptID);
		String did = StringUtil.getStr2(dept.getDeptID());
		
		//生成模板用户		
		User user = new User();
		//用户账号信息
		user.setAcctTag(school.getSchoolName());
		user.setAcctType(dept.getDeptName());
		user.setUserType("student");
		user.setPasswd("");		
		user.setAcctID("");
		user.setAcctName("");
		//计费信息
		user.setRentDate1(DateUtil.getDateByMonth(rentDate1));
		user.setRentDate2(DateUtil.getDateByMonth(rentDate2));
		user.setMonthlyRent((double)monthlyRent);
		if ("discount".equals(this.couponType)){
			//折扣优惠
			user.setCouponType("discount");
			user.setDiscount(discount);
			user.setMoreMonth(0);
		}else if("moreMonth".equals(this.couponType)){
			//赠送月份
			user.setCouponType("moreMonth");
			user.setDiscount(100);
			user.setMoreMonth(moreMonth);
		}else if("free".equals(this.couponType)){
			//免费账号
			user.setCouponType("free");
			user.setDiscount(100);
			user.setMoreMonth(0);
		}else{
			//无优惠
			user.setCouponType("discount");
			user.setDiscount(100);
			user.setMoreMonth(0);
		}
		//锁定状态
		user.setPayTag(0);
		user.setLockTag(1);
		
		//生成用户
		userService.createUser(user, school.getSchoolID(), did, first, num);
		
		//生成院校管理员
		String result = userService.checkLogin(school.getSchoolID().toLowerCase(), "123");
		if ("inexistence".equals(result)&&school.getId()>0){
			//如果院校管理员不存在，则生成
			User manager = new User();
			manager.setAcctID(school.getSchoolID()+"01"+DateUtil.getYear4()+"0001");
			manager.setLoginID(school.getSchoolID().toLowerCase());
			manager.setPasswd(MD5Util.md5Encode(school.getSchoolID().toLowerCase()));
			manager.setUserType("manager");
			manager.setAcctTag(school.getSchoolName());
			manager.setAcctType("");
			manager.setAcctName(school.getSchoolName()+"管理员");
			userService.updateUser(manager);
		}
		
		return SUCCESS;		
	}

	public String delete(){
		
		
		
			userService.deleteUsers(uid);
		
		
		
		return managerView();
	} 
	
	public String uploadfile(){
		
		try {
			Page<User> userPage = (Page<User>) request.getSession().getAttribute("userPage");
			
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
	        	
	        	//开始绑定
	        	if (userPage.getList().size()<rowNum) break;
	        	User user = userPage.getList().get(rowNum-1);
	        	user.setLoginID(number);
	        	user.setAcctName(name);
	        	
//	        	System.out.println("学号："+number);
//	        	System.out.println("姓名："+name);
//	        	System.out.println();	        	
	        }
	        request.setAttribute("userPage", userPage);
	        
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
	
	public String relate(){
		@SuppressWarnings("unchecked")
		Page<User> userPage = (Page<User>) request.getSession().getAttribute("userPage");
		for (User user : userPage.getList()){
			userService.updateUser(user);
		}
		
		
		return "relateView";
		
	}
	
	public String createView(){	
		request.getSession().setAttribute("ProvinceMap", provinceService.getProvinceMap());
		return "createView";		
	}
	
	public String relateView(){
		
		User user = new User();
		Page<User> page = new Page<User>(0);
		School school = this.getSchool(schoolID);		
		Dept dept = this.getDept(deptID);
		user.setAcctTag(school.getSchoolName());
		user.setAcctType(dept.getDeptName());
		user.setUserType("student");
		Page<User> userPage = null;
		if (school.getId()>0){
			userPage = userService.getUserPage(user, page);
		}
		request.setAttribute("userPage", userPage);
		request.setAttribute("schoolID", schoolID);
		request.setAttribute("deptID", deptID);
		
		request.getSession().setAttribute("userPage", userPage);
		request.getSession().setAttribute("ProvinceMap", provinceService.getProvinceMap());
		
		return "relateView";		
	}
	
	public String managerView(){
		User user = new User();
		School school = this.getSchool(schoolID);		
		Dept dept = this.getDept(deptID);
		user.setAcctTag(school.getSchoolName());
		user.setAcctType(dept.getDeptName());
		Page<User> page = new Page<User>(0);
		Page<User> userPage = null;
		if (school.getId()>0){
			userPage = userService.getUserPage(user, page);
		}
		request.setAttribute("userPage", userPage);
//		System.out.println(this.schoolID);
//		System.out.println(this.deptID);
		return "managerView";
	}
	
	private School getSchool(int schoolID){
		School school = provinceService.getSchool(schoolID);
		if (school==null){
			school = new School();
			school.setSchoolName("不限");
		}
		return school;		
	}
	
	private Dept getDept(int deptID){
		Dept dept = provinceService.getDept(deptID);
		if (dept==null){
			dept = new Dept();
			dept.setDeptName("不限");
		}
		return dept;		
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

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setMonthlyRent(int monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public void setRentDate1(String rentDate1) {
		this.rentDate1 = rentDate1;
	}

	public void setRentDate2(String rentDate2) {
		this.rentDate2 = rentDate2;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setMoreMonth(int moreMonth) {
		this.moreMonth = moreMonth;
	}
	
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public void setUid(List<String> uid) {
		this.uid = uid;
	}

	
}








