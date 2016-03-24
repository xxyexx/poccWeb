package main.java.edu.scnu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.SchoolClass;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ClassService;
import main.java.edu.scnu.service.ExperimentService;
import main.java.edu.scnu.service.HomeworkService;
import main.java.edu.scnu.util.FileUtil;
import main.java.edu.scnu.util.PoccManager;

import org.apache.commons.lang3.StringUtils;
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
	@Resource(name="experimentService")
	private ExperimentService experimentService;
	@Resource(name="classService")
	private ClassService classService;
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
	private Homework homework;//教师添加作业,部分属性自动装配
	private String expID;//添加作业：实验id
	private String classID[];//添加作业:对应班级id数组
	private String closeDate;//添加作业:作业截止提交日期
	private String hwdesc;//备注
	private String stud;//unfin,fin,all:学生作业列表条件
	private List <String> score;//教师添加分数
	private List <String> HWSubmitID;//分数对应的id
	public HomeworkAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
		stud = "finished";
	}
	
	/**
	 * 显示教师作业列表
	 */
	public String show_TeacherHW(){
		//已发布作业
		List<Homework> hwList = homeworkService.getHomeworkbyTeacherID(user.getAcctID());
		//班级列表
		HashMap<Integer,String> classMap = new HashMap<Integer, String>();
		List<SchoolClass> classList = classService.getClassAll();
		for (int i=0;i<classList.size();i++) {
			classMap.put(classList.get(i).getId(), classList.get(i).getClassName());
		}
		//实验列表
		HashMap<Integer, String> expMap= new HashMap<Integer, String>();
		List<Experiment> expList = experimentService.getAllExper();
		for (int i=0;i<expList.size();i++) {
			expMap.put(expList.get(i).getId(), expList.get(i).getTitle());
		}
		request.setAttribute("HWList", hwList);
		request.setAttribute("EXPMap", expMap);
		request.setAttribute("ClassMap", classMap);
		return SUCCESS;
	}
	/**
	 * 教师添加作业
	 */
	public String add_Homework(){
		//作业对应的实验
		Experiment exp = experimentService.getExperiment(Integer.parseInt(expID));
		//班级列表
		String classid = StringUtils.join(classID, "|");
		int totalStudNum = 0;
		for (String id : classID) {
			totalStudNum += classService.getSchoolClassByid(Integer.parseInt(id)).getTotalStudents();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date closedate = null;
		try {
			closedate = sdf.parse(closeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		homework.setTeacherID_acctID(user.getAcctID());
		homework.setAssignDate(new Date());
		homework.setCloseDate(closedate);
		homework.setExperiment(exp);
		homework.setClassID(classid);
		homework.setTotalPerson(totalStudNum);
		homework.setState(1);
		homework.setHwdesc(hwdesc);
		homeworkService.addHomework(homework);
		return SUCCESS;
	}
	/**
	 * 教师撤销作业（当前作业提交人数为0）
	 */
	public String delete_Homework(){
		Homework hw = homeworkService.getHomeworkbyID(Integer.parseInt(hwID));
		//当前作业提交人数为0,且是本人操作
		if(hw.getTeacherID_acctID().equals(user.getAcctID())&&hw.getSubmittedCount()==0){
			homeworkService.deleteHomework(Integer.parseInt(hwID));
		}
		return SUCCESS;
	}
	/**
	 * 教师改变作业状态
	 */
	public String changeState_Homework(){
		Homework hw = homeworkService.getHomeworkbyID(Integer.parseInt(hwID));
		if(hw.getCloseDate().after(new Date())){
			if(hw.getState()==1){
				hw.setState(0);
			}else{
				hw.setState(1);
			}
			homeworkService.updateHomework(hw);	
		}
		return SUCCESS;
	}
	/**
	 * 教师检查（批改）作业
	 */
	public String check_Homework(){
		hwID = request.getParameter("hwID");
		Homework homework = homeworkService.getHomeworkbyID(Integer.parseInt(hwID));//作业详情
		//该作业已完成学生列表
		List<HWSubmit> hwsList =  homeworkService.getfinishedHWSList(Integer.parseInt(hwID));
		//该作业所有学生列表
		String classID[] = homework.getClassID().split("//|");//班级数组
		List<User> studList = new ArrayList<User>();
		for (String s : classID) {
			List <User> sList = classService.getStudByClassID(s);
			if(sList!=null)studList.addAll(sList);
		}
		//该作业未完成学生列表
		List<User> unfinishedStudList = new ArrayList<User>();
		if(hwsList.size()!=0){
			for (User user : studList) {	
				boolean flag = true;
				for (HWSubmit hws : hwsList) {
					if(hws.getUser().getId()==user.getId()){//已交作业
						flag = false;
						break;
					}
				}
				if(flag){
					unfinishedStudList.add(user);
				}
			}
		}else{
			unfinishedStudList.addAll(studList);
		}
		//已交作业人数
		request.setAttribute("submitNum", hwsList.size());
		//平均分
		int sum = 0;
		for (HWSubmit hws : hwsList) {
			sum = sum + hws.getScore();
		}
		request.setAttribute("avg",hwsList.size()!=0?sum/hwsList.size():0);
		//标识当前查询是全部学生还是未完成学生
		if(request.getParameter("stud")!=null){
			stud = request.getParameter("stud");
		}
		request.setAttribute("stud", stud);
		request.setAttribute("HWSList", hwsList);
		request.setAttribute("StudList", studList);
		request.setAttribute("UnfinishedStudList", unfinishedStudList);
		request.setAttribute("homework", homework);

		return "checkHomework";
	}
	/**
	 * 教师添加分数
	 */
	public String score_Homework(){
		for(int i=0;i<score.size();i++){
			HWSubmit hwsubmit = homeworkService.getHWSubmitByid(Integer.parseInt(HWSubmitID.get(i)));
			hwsubmit.setScore(Integer.parseInt(score.get(i)));
			homeworkService.saveHWSubmit(hwsubmit);
		}
		return "addScore";
	}
	/**
	 * 显示学生作业列表
	 */
	public String show_StudHW(){
		//所有作业
		List<Homework> allHWList = homeworkService.getAllHW(user.getClassID()+"");
		//未完成作业
		List<Homework> unfinHWList = homeworkService.getUnfinishedHW(user.getClassID()+"", user.getAcctID());
		//已完成作业
		List<HWSubmit> finHWList = homeworkService.getfinishedHW(user.getClassID()+"", user.getAcctID());
		request.setAttribute("unfinHWList", unfinHWList);
		request.setAttribute("finHWList", finHWList);
		request.setAttribute("allHWList", allHWList);
		return SUCCESS;
	}
	/**
	 * 学生显示作业详情
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
	 * 学生提交作业
	 */
	public String submit_Homework(){
		String root_dir = PoccManager.ROOT_DIR;//root_dir:文件保存的根目录
		String path = PoccManager.Homework_file_dir;//path:作业文件保存的文件夹
		Homework homework = homeworkService.getHomeworkbyID(Integer.parseInt(hwID));
		if(new Date().before(homework.getCloseDate()))//截止时间
		if(hwSubmitID.equals("non-existent")){//新提交的作业
			if(hwfile!=null){//保存作业
				HWSubmit hwSubmit = new HWSubmit();
				hwSubmit.setUser(user);//对应学生
				hwSubmit.setHomework(homework);//作业
				hwSubmit.setFilename(hwfileFileName);//提交作业的文件名称
				hwSubmit.setSubmitTime(new Date());//提交时间
				hwSubmit.setHwsdesc(hwsdesc);
				hwSubmit.setChecked(0);//未批改
				hwSubmit.setScore(-1);//默认未批改分数
				//保存文件到本地
				hwSubmit.setFileURL(FileUtil.saveFile(hwfile,root_dir, path, hwfileFileName));
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
				hwSubmit.setFileURL(FileUtil.saveFile(hwfile,root_dir,path, hwfileFileName));
			}
			if(hwfile!=null||!hwsdesc.equals(hwSubmit.getHwsdesc())){
				hwSubmit.setHwsdesc(hwsdesc);//修改备注信息
				homeworkService.saveHWSubmit(hwSubmit);
			}
		}
		return SUCCESS;
	}
	/**
	 * 学生下载已交作业文件
	 */
	public String download_Homework(){
		//已提交作业ID
		HWSubmit hwSubmit = homeworkService.getHWSubmitByid(Integer.parseInt(hwSubmitID));
		String root_dir = PoccManager.ROOT_DIR;//root_dir:文件保存的根目录
		String file_url = root_dir + hwSubmit.getFileURL();
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
	 * 学生session中设置header头部未做的作业数量
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
	public String getHwID() {
		return hwID;
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
	public void setExperimentService(ExperimentService experimentService) {
		this.experimentService = experimentService;
	}
	public void setClassService(ClassService classService) {
		this.classService = classService;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public void setExpID(String expID) {
		this.expID = expID;
	}
	public void setClassID(String[] classID) {
		this.classID = classID;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public void setHwdesc(String hwdesc) {
		this.hwdesc = hwdesc.trim();
	}
	public void setScore(List<String> score) {
		this.score = score;
	}
	public void setHWSubmitID(List<String> hWSubmitID) {
		HWSubmitID = hWSubmitID;
	}
	
}
