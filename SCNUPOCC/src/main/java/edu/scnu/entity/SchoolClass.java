package main.java.edu.scnu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author l
 *班级
 */
@Entity(name="schoolclass")
public class SchoolClass {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String teacherID_acctID;//教师ID
	private String className;//班级名称
	private String scdesc;//班级说明
	private int totalStudents;//班级人数上限
	private int currentStudents;//班级真实人数
	private Date createDate;//创建班级时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherID_acctID() {
		return teacherID_acctID;
	}
	public void setTeacherID_acctID(String teacherID_acctID) {
		this.teacherID_acctID = teacherID_acctID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className.trim();
	}
	public String getScdesc() {
		return scdesc;
	}
	public void setScdesc(String scdesc) {
		this.scdesc = scdesc.trim();
	}
	public int getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getCreateDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(createDate);
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getCurrentStudents() {
		return currentStudents;
	}
	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}
	
}
