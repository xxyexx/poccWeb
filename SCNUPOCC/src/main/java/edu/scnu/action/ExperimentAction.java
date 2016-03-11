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
	private File pocc; //上传的文件
    private String poccFileName; //文件名称
    private String poccContentType; //文件类型
	private String expID;
	//文件流
	private InputStream fileInputStream;
	//文件下载名称
	private String filename;
	
	public ExperimentAction(){
		this.request = ServletActionContext.getRequest();
		user = (User) request.getSession().getAttribute("User");
	}
	/**
	 * 学生实验列表
	 */
	public String show_studExper(){
		//实验列表
		List<Experiment> expList = experimentService.getAllExper();
		request.setAttribute("ExpList", expList);
		//实验存档列表
		List<PoccFile> poccfileList = experimentService.getAllPoccFile(user.getAcctID());
		request.setAttribute("FileList", poccfileList);
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
		String realpath = PoccManager.Temporary_file_dir;
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
			//保存文件到本地
			poccfile.setFile_url(FileUtil.saveFile(pocc, realpath, poccFileName));
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
		String file_url = poccfile.getFile_url();
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
		experimentService.deletePoccFile(poccFileid,user.getAcctID());
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
	
}
