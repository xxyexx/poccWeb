package main.java.edu.scnu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name="province")
public class Province {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String province;//省份名
	private String province_code;
	private int is_using;//0 不启用；1 启用
	//在Service一个事务范围内,只要session还没有关闭就不会出现懒加载异常;
	@OneToMany(targetEntity=School.class,
			mappedBy="province",fetch=FetchType.LAZY)
	private Set<School> schools = new HashSet<School>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIs_using(int is_using) {
		this.is_using = is_using;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public Set<School> getSchools() {
		return schools;
	}
	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}

	
}
