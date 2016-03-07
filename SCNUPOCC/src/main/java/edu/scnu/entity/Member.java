package main.java.edu.scnu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author L
 * member:平台内部用户(包括admin,cashier,member)
 */
@Entity(name="member")
public class Member {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String loginID;//登录ID
	private String passwd;
	//memberType:会员类型admin(系统管理员)、cashier(操作员)、member(平台会员)
	private String memberType;
	private String memberName;//姓名
	private String mobile;
	private String email;
	private Date createTime;//帐号创建时间
	private Date lastLoginTime;//最近登录时间
	private String lastLoingIP;//最近登录IP
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoingIP() {
		return lastLoingIP;
	}
	public void setLastLoingIP(String lastLoingIP) {
		this.lastLoingIP = lastLoingIP;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", loginID=" + loginID + ", passwd="
				+ passwd + ", memberType=" + memberType + ", memberName="
				+ memberName + ", mobile=" + mobile + ", email=" + email
				+ ", createTime=" + createTime + ", lastLoginTime="
				+ lastLoginTime + ", lastLoingIP=" + lastLoingIP + "]";
	}
	
	
}
