package main.java.edu.scnu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.util.PoccManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="downloadFileAction")
@Scope("prototype")
public class DownloadFileAction extends ActionSupport {
	//文件流
	private InputStream fileInputStream;
	//文件下载名称
	private String filename;
	private String filePath;
	private HttpServletRequest request;
	public DownloadFileAction(){
		this.request = ServletActionContext.getRequest();
	}
	
	@Override
	public String execute() throws Exception {
		filePath = null;
		if(request.getParameter("filePath")!=null){
			filePath = request.getParameter("filePath");
			String path = PoccManager.ROOT_DIR;	//path:文件保存的根目录
			String file_url = path + filePath; //文件绝对路径
			File file = new File(file_url);
			try {
				filename = new String(file.getName().getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	//get
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public String getFilename() {
		return filename;
	}
	//set
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
