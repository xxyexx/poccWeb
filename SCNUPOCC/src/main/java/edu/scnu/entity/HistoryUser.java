package main.java.edu.scnu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 
 * @author L
 * 删除的用户信息保存到历史账号表中
 */
@Entity(name="historyuser")
public class HistoryUser {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String acctID;//内部帐号ID
	private String loginID;//登录名
	private String passwd;
	private String acctTag;//帐号标识,学校
	private String acctType;//帐号类别,院系
	private String acctName;//姓名或机构
	private String mobile;
	private String email;
	private Date rentDate1;//使用租期起
	private Date rentDate2;//使用租期止
	private String couponType;//优惠类型
	private double monthlyRent;//月租,缺省是基本租金
	private int discount;//折扣,默认是100%
	private int moreMonth;//赠送月份
	private int payTag;//缴费标记:0-未缴费,1-已缴费
	private int lockTag;//锁定标记:0-启用,1-禁用
	private double payment;//应交费用
	private Date payDate;//缴费日期
	private int managerID;//所属管理者ID
	private int classID;//所属班级ID
	private Date createDate;//帐号创建时间
	private Date lastLoginTime;
	private String lastLoginIP;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAcctID() {
		return acctID;
	}
	public void setAcctID(String acctID) {
		this.acctID = acctID;
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
	public String getAcctTag() {
		return acctTag;
	}
	public void setAcctTag(String acctTag) {
		this.acctTag = acctTag;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
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
	public Date getRentDate1() {
		return rentDate1;
	}
	public void setRentDate1(Date rentDate1) {
		this.rentDate1 = rentDate1;
	}
	public Date getRentDate2() {
		return rentDate2;
	}
	public void setRentDate2(Date rentDate2) {
		this.rentDate2 = rentDate2;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public double getMonthlyRent() {
		return monthlyRent;
	}
	public void setMonthlyRent(double monthlyRent) {
		this.monthlyRent = monthlyRent;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getMoreMonth() {
		return moreMonth;
	}
	public void setMoreMonth(int moreMonth) {
		this.moreMonth = moreMonth;
	}
	public int getPayTag() {
		return payTag;
	}
	public void setPayTag(int payTag) {
		this.payTag = payTag;
	}
	public int getLockTag() {
		return lockTag;
	}
	public void setLockTag(int lockTag) {
		this.lockTag = lockTag;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", acctID=" + acctID + ", loginID=" + loginID
				+ ", passwd=" + passwd + ", acctTag=" + acctTag + ", acctType="
				+ acctType + ", acctName=" + acctName + ", mobile=" + mobile
				+ ", email=" + email + ", rentDate1=" + rentDate1
				+ ", rentDate2=" + rentDate2 + ", couponType=" + couponType
				+ ", monthlyRent=" + monthlyRent + ", discount=" + discount
				+ ", moreMonth=" + moreMonth + ", payTag=" + payTag
				+ ", lockTag=" + lockTag + ", payment=" + payment
				+ ", payDate=" + payDate + ", managerID=" + managerID
				+ ", classID=" + classID + ", createDate=" + createDate
				+ ", lastLoginTime=" + lastLoginTime + ", lastLoginIP="
				+ lastLoginIP + "]";
	}
	
}