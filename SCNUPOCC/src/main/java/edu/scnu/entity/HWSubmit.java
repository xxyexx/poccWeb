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
 * @author l
 *提交作业
 */
@Entity(name="hwsubmit")
public class HWSubmit {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//定义与User关联
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="stud_ID",referencedColumnName="id")
	private User user;
	//private int hwID;//作业ID
	//定义与作业关联的Homework实体
	@ManyToOne(targetEntity=Homework.class)
	@JoinColumn(name="hwID",referencedColumnName="id")
	private Homework homework;
	private String filename;//作业文档名称
	private String fileURL;//作业文档保存地址
	private Date submitTime;//作业提交时间
	private Date downloadTime;//教师下载日期
	private int checked;//未批改|已批改(0|1)
	private int score;//分数
	/**学生备注(改为hwsdesc,desc是mysql保留关键字，homework.hwdesc同理)*/
	private String hwsdesc;
	private String remark;//教师评语
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	//yy-MM-dd格式
	public String getSubmitTimeFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(submitTime);
	}
	public String getDownloadTimeFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(downloadTime);
	}
	public Date getDownloadTime() {
		return downloadTime;
	}
	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getHwsdesc() {
		return hwsdesc;
	}
	public void setHwsdesc(String hwsdesc) {
		this.hwsdesc = hwsdesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
