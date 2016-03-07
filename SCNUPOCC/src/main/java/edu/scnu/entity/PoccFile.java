package main.java.edu.scnu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 实验临时文件
 * 实验过程状态文件的命名规则：
 * 学号_实验项目名称_x.pocc（x：1～5，代表提交的次数，具有版本标识的作用）
 */
@Entity(name="poccfile")
public class PoccFile {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//定义与实验关联的Experiment实体
	@ManyToOne(targetEntity=Experiment.class)
	@JoinColumn(name="exper_id",referencedColumnName="id")
	private Experiment experiment;
	
	private String user_acctID;
	private String file_name;
	private String file_url;
	private Date upload_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public String getUser_acctID() {
		return user_acctID;
	}
	public void setUser_acctID(String user_acctID) {
		this.user_acctID = user_acctID;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public String getUpload_timeFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(upload_time);
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	

	
}
