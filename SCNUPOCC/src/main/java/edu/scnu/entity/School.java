package main.java.edu.scnu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="school")
public class School {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String schoolID;//学校ID如:scnu
	private String schoolName;//学校名称
	//private int provinceID; //所属省份ID
	@ManyToOne(targetEntity=Province.class)
	@JoinColumn(name="provinceID",referencedColumnName="id")
	private Province province;
	@OneToMany(targetEntity=Dept.class,
			mappedBy="school",fetch=FetchType.LAZY)
	private Set<Dept> depts = new HashSet<Dept>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolID() {
		return schoolID;
	}
	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public Set<Dept> getDepts() {
		return depts;
	}
	public void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}
	
	@Override
	public String toString() {
		return "School [id=" + id + ", schoolID=" + schoolID + ", schoolName="
				+ schoolName + ", province=" + province + "]";
	}
	
}
