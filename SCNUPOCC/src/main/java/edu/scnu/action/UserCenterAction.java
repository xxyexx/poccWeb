package main.java.edu.scnu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ClassService;
import main.java.edu.scnu.service.UserService;
import main.java.edu.scnu.util.MD5Util;
import main.java.edu.scnu.util.FileUtil;
import main.java.edu.scnu.util.PoccManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="userCenterAction")
@Scope("prototype")
public class UserCenterAction extends ActionSupport {
	private HttpServletRequest request;
	@Resource(name="classService")
	private ClassService classService;
	@Resource(name="userService")
	private UserService userService;
	private User user;
	private String userType;
	private String pwdCheckResult;//返回旧密码比较结果
	private String password;//需要校验的密码
	private String newPassword;//新密码
	private String mobile;//手机
	private String email;//邮箱
	private File Icon;//头像
    private String IconFileName; //头像文件名称
    @SuppressWarnings("unused")
	private String IconContentType; //头像文件类型
	//文件流
	private InputStream fileInputStream;
	//文件下载名称
	private String filename;
	
	public UserCenterAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
		userType  = (user!=null)?user.getUserType():"";
	}
	
	/**
	 * 显示user个人信息
	 */
	public String show_Info(){
		if(userType.equals(PoccManager.student)){//学生显示班级
			SchoolClass schoolClass = classService.getSchoolClassByid(Integer.parseInt(user.getClassID()));
			request.setAttribute("className", schoolClass.getClassName());
		}

		return SUCCESS;
	}
	/**
	 * 获取User用户头像
	 */
	public String show_Icon(){
		String path = PoccManager.ROOT_DIR;//path:文件保存的根目录
		String file_url = null;
		String realpath = ServletActionContext.getServletContext().getRealPath("/");//网站根目录
		if(user.getIconURL().equals(PoccManager.StudentDefaultIcon)){//默认头像
			file_url = realpath + user.getIconURL();
		}else{
			file_url = path + user.getIconURL();
		}
		File file = new File(file_url);
		try {
			filename = new String(("用户头像").getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件下载失败");
		}
		
		return SUCCESS;
	}
	/**
	 * 修改user头像
	 */
	public String change_Icon(){
		//realpath:头像文件保存的文件夹
		String root_dir=PoccManager.ROOT_DIR;//根目录
		String path = PoccManager.User_Icon_dir;//头像文件保存文件夹
		if(Icon!=null){
			if(user.getIconURL()!=null){
				FileUtil.deleteFile(root_dir+user.getIconURL());
			}
			user.setIconURL(FileUtil.saveFile(Icon,root_dir,path,IconFileName));
			userService.updateUser(user);
		}
		return SUCCESS;
	}
	
	/**
	 * 检测旧密码
	 * @return ajax 
	 */ 
	public String check_password(){
		if(MD5Util.md5Encode(password).equals(user.getPasswd())){
			pwdCheckResult = "pwdTrue";
		}else{
			pwdCheckResult = "pwdWrong";
		}
		return SUCCESS;
	}
	/**
	 * 修改密码
	 */
	public String change_Password(){
		if(newPassword!=null){
			user.setPasswd(MD5Util.md5Encode(newPassword));
			userService.updateUser(user);//更新用户信息
		}
		return SUCCESS;
	}
	/**
	 * 更新user用户个人信息
	 */
	public String change_Info(){
		user.setMobile(mobile);
		user.setEmail(email);
		userService.updateUser(user);
		return SUCCESS;
	}
	
	//set
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile.trim();
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	public void setIcon(File icon) {
		Icon = icon;
	}
	public void setIconFileName(String iconFileName) {
		IconFileName = iconFileName;
	}
	public void setIconContentType(String iconContentType) {
		IconContentType = iconContentType;
	}

	//get
	public String getPwdCheckResult() {
		return pwdCheckResult;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public String getFilename() {
		return filename;
	}
	public String getUserType() {
		return userType;
	}

}
