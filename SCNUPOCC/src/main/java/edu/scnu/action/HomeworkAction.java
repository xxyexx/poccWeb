package main.java.edu.scnu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.HomeworkService;
import main.java.edu.scnu.util.FileUtil;
import main.java.edu.scnu.util.PoccManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="homeworkAction")
@Scope("prototype")
public class HomeworkAction extends ActionSupport {
	private HttpServletRequest request;
	@Resource(name="homeworkService")
	private HomeworkService homeworkService;
	private User user;
	//提交作业
	private String hwID;//要提交作业的id
	private String hwsdesc;//学生备注
	private String hwSubmitID;//已提交过的hwSubmit.ID
	private File hwfile; //上传的作业文件
    private String hwfileFileName; //作业文件名称
    private String hwfileContentType; //作业文件类型
	//文件流
	private InputStream fileInputStream;
	//文件下载名称
	private String filename;
	
	public HomeworkAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
	}
	
	/**
	 * 显示学生作业列表
	 */
	public String show_StudHW(){
		//未完成作业
		List<Homework> unfinHWList = homeworkService.getUnfinishedHW(user.getClassID()+"", user.getAcctID());
		//已完成作业
		List<HWSubmit> finHWList = homeworkService.getfinishedHW(user.getClassID()+"", user.getAcctID());
		//所有作业
		List<Homework> allHWList = homeworkService.getAllHW(user.getClassID()+"");
		
		request.setAttribute("unfinHWList", unfinHWList);
		request.setAttribute("finHWList", finHWList);
		request.setAttribute("allHWList", allHWList);
		return SUCCESS;
	}

	/**
	 * 显示作业详情
	 */
	public String show_HWdetails(){
		int hwID = Integer.parseInt(request.getParameter("hwID"));
		//作业详情
		Homework homework = homeworkService.getHomeworkbyID(hwID);
		//若未提交则是null
		HWSubmit hwSubmit = null;
		hwSubmit = homeworkService.getHWSubmit(user.getAcctID(), hwID);
		
		request.setAttribute("homework", homework);
		request.setAttribute("hwSubmit", hwSubmit);
		return SUCCESS;
	}
	/**
	 * 提交作业
	 */
	public String submit_Homework(){
		//realpath:作业文件保存的文件夹
		String realpath = PoccManager.Homework_file_dir;
		if(hwSubmitID.equals("non-existent")){//新提交的作业
			if(hwfile!=null){//保存作业
				HWSubmit hwSubmit = new HWSubmit();
				hwSubmit.setStudNo_acctID(user.getAcctID());//学生内部账号
				hwSubmit.setHomework(homeworkService.getHomeworkbyID(Integer.parseInt(hwID)));//作业
				hwSubmit.setFilename(hwfileFileName);//提交作业的文件名称
				hwSubmit.setSubmitTime(new Date());//提交时间
				hwSubmit.setHwsdesc(hwsdesc);
				hwSubmit.setChecked(0);//未批改
				hwSubmit.setScore(-1);//默认未批改分数
				//保存文件到本地
				hwSubmit.setFileURL(FileUtil.saveFile(hwfile, realpath, hwfileFileName));
				//修改数据库记录
	            homeworkService.saveHWSubmit(hwSubmit);
	            //重新设置session中未做作业数量
	            Query_HWNum(user.getClassID(),user.getAcctID());
			}
		}else{//修改已提交作业
			HWSubmit hwSubmit = homeworkService.getHWSubmitByid(Integer.parseInt(hwSubmitID));
			if(hwfile!=null){//保存作业
				hwSubmit.setFilename(hwfileFileName);//修改文件名
				//删除旧文件
				FileUtil.deleteFile(hwSubmit.getFileURL());
				//添加新文件
				hwSubmit.setFileURL(FileUtil.saveFile(hwfile, realpath, hwfileFileName));
			}
			if(hwfile!=null||!hwsdesc.equals(hwSubmit.getHwsdesc())){
				hwSubmit.setHwsdesc(hwsdesc);//修改备注信息
				homeworkService.saveHWSubmit(hwSubmit);
			}
		}
		return SUCCESS;
	}
	/**
	 * 下载作业文件
	 */
	public String download_Homework(){
		//已提交作业ID
		HWSubmit hwSubmit = homeworkService.getHWSubmitByid(Integer.parseInt(hwSubmitID));
		String file_url = hwSubmit.getFileURL();
		File file = new File(file_url);
		try {
			filename = new String(hwSubmit.getFilename().getBytes(), "ISO8859-1");
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
		return "download";
	}
	
	/**
	 * session中设置header头部未做的作业数量
	 */
	public void Query_HWNum(String classID,String stud_AcctID){
		String unfinHWNum = null;
		List<Homework> UnfinishedHWList 
						= homeworkService.getUnfinishedHW(classID, stud_AcctID);
		if(UnfinishedHWList!=null) {
			if(UnfinishedHWList.size()!=0){
				unfinHWNum=UnfinishedHWList.size()+"";
			}
		}
		request.getSession().setAttribute("UnfinHWNum", unfinHWNum);
	}
	
	//getter
	public File getHwfile() {
		return hwfile;
	}
	public String getHwfileFileName() {
		return hwfileFileName;
	}
	public String getHwfileContentType() {
		return hwfileContentType;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public String getFilename() {
		return filename;
	}

	//setter
	public void setHomeworkService(HomeworkService homeworkService) {
		this.homeworkService = homeworkService;
	}
	public void setHwID(String hwID) {
		this.hwID = hwID;
	}
	/**hwsdesc.trim():去除备注信息空格*/
	public void setHwsdesc(String hwsdesc) {
		this.hwsdesc = hwsdesc.trim();
	}
	public void setHwfile(File hwfile) {
		this.hwfile = hwfile;
	}
	public void setHwfileFileName(String hwfileFileName) {
		this.hwfileFileName = hwfileFileName;
	}
	public void setHwfileContentType(String hwfileContentType) {
		this.hwfileContentType = hwfileContentType;
	}
	public void setHwSubmitID(String hwSubmitID) {
		this.hwSubmitID = hwSubmitID;
	}
	
}
