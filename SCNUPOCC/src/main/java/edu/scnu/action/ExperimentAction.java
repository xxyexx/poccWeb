package main.java.edu.scnu.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.PoccFile;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ExperimentService;

import org.apache.commons.io.FileUtils;
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
		String realpath = ServletActionContext.getServletContext().getRealPath("/poccFile");
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
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
			String saveTime = sdf.format(poccfile.getUpload_time());
			poccfile.setFile_url("poccFile/"+saveTime+poccFileName);
			//保存新文件到硬盘,为了防止文件名重复,保存时间+文件名称
			File savefile = new File(new File(realpath),saveTime+poccFileName);
			if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            try {
				FileUtils.copyFile(pocc, savefile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //保存文件信息到数据库
            experimentService.savePoccFile(poccfile);
		}
		
		return "savefile";
	}
	/**
	 * 删除实验存档
	 */
	public String delete_file(){
		int poccFileid = Integer.parseInt(request.getParameter("poccFileid"));
		experimentService.deletePoccFile(poccFileid);
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

	
}
