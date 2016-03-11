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
 *作业
 */
@Entity(name="homework")
public class Homework {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int teacherID_acctID;//教师ID
	//private int expID;//实验项目
	//定义与实验项目关联的Experiment实体
	@ManyToOne(targetEntity=Experiment.class)
	@JoinColumn(name="expID",referencedColumnName="id")
	private Experiment experiment;
	private String title;//作业名
	private Date assignDate;//布置作业日期
	private Date closeDate;//作业关闭提交日期
	private String hwdesc;//作业要求
	private String classID;//目标班级
	private int state;//关闭/接收状态(0|1)
	private int totalPerson;//应交人数
	private int submittedCount;//已交人数
	private int checkedCount;//已批改人数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacherID_acctID() {
		return teacherID_acctID;
	}
	public void setTeacherID_acctID(int teacherID_acctID) {
		this.teacherID_acctID = teacherID_acctID;
	}
	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	//yy-MM-dd格式
	public String getCloseDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(closeDate);
	}
	public String getAssignDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(assignDate);
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getHwdesc() {
		return hwdesc;
	}
	public void setHwdesc(String hwdesc) {
		this.hwdesc = hwdesc;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTotalPerson() {
		return totalPerson;
	}
	public void setTotalPerson(int totalPerson) {
		this.totalPerson = totalPerson;
	}
	public int getSubmittedCount() {
		return submittedCount;
	}
	public void setSubmittedCount(int submittedCount) {
		this.submittedCount = submittedCount;
	}
	public int getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}
	
}
