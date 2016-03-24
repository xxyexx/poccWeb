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

import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.PoccFile;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ExperimentService;
import main.java.edu.scnu.util.FileUtil;
import main.java.edu.scnu.util.PoccManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="experimentAction")
@Scope("prototype")
public class ExperimentAction extends ActionSupport {
	@Resource(name="experimentService")
	private ExperimentService experimentService;
	private HttpServletRequest request;
	private User user;
	/**用户类型:合作方(partner),院校管理员(manager),
	 * 教师(teacher),学生(student),免费试用帐号(freeAccount)*/
	private String userType;
	private File pocc; //上传的文件
    private String poccFileName; //文件名称
    private String poccContentType; //文件类型
	private String expID; //要操作的实验项目ID
	//文件流
	private InputStream fileInputStream;
	//文件下载名称
	private String filename;
	private Experiment experiment;//教师添加实验项目
	private String expDesc;//教师添加实验：实验说明
	
	
	public ExperimentAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
		userType  = (user!=null)?user.getUserType():"";//用户类型用于跳转到不同页面
	}
	/**
	 * user实验列表
	 */
	public String show_experiments(){
		//实验列表
		List<Experiment> expList = experimentService.getAllExper();
		request.setAttribute("ExpList", expList);
		//实验存档列表
		List<PoccFile> poccfileList = experimentService.getAllPoccFile(user.getAcctID());
		request.setAttribute("FileList", poccfileList);
		return SUCCESS;
	}
	/**
	 * 添加实验
	 */
	public String add_Experiment(){
		experiment.setUser(user);
		experiment.setExpDesc(expDesc);
		experimentService.addExperiment(experiment);
		return SUCCESS;
	}
	/**
	 * 删除实验
	 */
	public String delete_Experiment(){
		Experiment exp = experimentService.getExperiment(Integer.parseInt(expID));
		if(exp.getUser().getAcctID().equals(user.getAcctID())){
			experimentService.deleteExperiment(Integer.parseInt(expID));
		}
		return SUCCESS;
	}
	/**
	 * 根据实验id进入实验,显示实验内容
	 */
	public String show_experimentDetail(){
		int id  = Integer.parseInt(request.getParameter("id"));//获取实验id
		Experiment exp = experimentService.getExperiment(id);
		List<PoccFile> poccfileList = experimentService.getPoccFileByid(user.getAcctID(), id);
		if(exp==null){
			exp = experimentService.getExperiment(1);
			//实验存档列表
			poccfileList = experimentService.getPoccFileByid(user.getAcctID(), 1);
		}
		request.setAttribute("Experiment", exp);
		request.setAttribute("PoccfileList", poccfileList);
		return SUCCESS;
	}
	/**
	 * 保存实验过程文件
	 */
	public String save_file(){
		String root_dir=PoccManager.ROOT_DIR;//根目录
		String path = PoccManager.Temporary_file_dir;//实验临时文件保存文件夹
		if (pocc != null) {
			//新文件
			PoccFile poccfile = new PoccFile();
			poccfile.setUser_acctID(user.getAcctID());
			Experiment exp = experimentService.getExperiment(Integer.parseInt(expID));
			poccfile.setExperiment(exp);
			int versionID = 0;
			int fileNum = experimentService.getAllPoccFile(user.getAcctID()).size();//文件存档个数
			if(fileNum<5){
				versionID=fileNum+1;
			}else{ versionID=5;}
			poccfile.setFile_name(user.getLoginID()+"_"+exp.getTitle()+"_"+versionID+"_"+poccFileName);//文件名
			poccfile.setUpload_time(new Date());
			//保存文件到磁盘
			poccfile.setFile_url(FileUtil.saveFile(pocc, root_dir,path, poccFileName));
	        //保存文件信息到数据库
            experimentService.savePoccFile(poccfile);
		}
		
		return "savefile";
	}
	/**
	 * 实验过程文件下载
	 */
	public String download_file(){
		int poccFileid = Integer.parseInt(request.getParameter("poccFileid"));
		//获取文件保存路径
		PoccFile poccfile = experimentService.getPoccFile(poccFileid);
		String path = PoccManager.ROOT_DIR;	//path:文件保存的根目录
		String file_url = path + poccfile.getFile_url();//磁盘绝对路径
		File file = new File(file_url);
		try {
			filename = new String(poccfile.getFile_name().getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "download";
	}
	
	/**
	 * 删除实验存档
	 */
	public String delete_file(){
		int poccFileid = Integer.parseInt(request.getParameter("poccFileid"));
		PoccFile pf = experimentService.getPoccFile(poccFileid);
		if(pf.getUser_acctID().equals(user.getAcctID())){
			experimentService.deletePoccFile(poccFileid,user.getAcctID());
		}
		return "deletefile";
	}
	
	//set
	public void setExperimentService(ExperimentService experimentService) {
		this.experimentService = experimentService;
	}
	public void setExpID(String expID) {
		this.expID = expID;
	}
	public void setPoccContentType(String poccContentType) {
		this.poccContentType = poccContentType;
	}
	public void setPoccFileName(String poccFileName) {
		this.poccFileName = poccFileName;
	}
	public void setPocc(File pocc) {
		this.pocc = pocc;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc.trim();
	}
	//get
	public String getExpID() {
		return expID;
	}
	public File getPocc() {
		return pocc;
	}
	public String getPoccFileName() {
		return poccFileName;
	}
	public String getPoccContentType() {
		return poccContentType;
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
