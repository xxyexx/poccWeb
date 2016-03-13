package main.java.edu.scnu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author liu
 *平台外部用户(包括:合作方,院校管理员,教师,学生,免费试用用户)
 */
@Entity(name="user")
public class User {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String acctID;//内部帐号ID
	private String loginID;//登录名
	private String passwd;
	//用户类型:合作方(partner),院校管理员(manager),教师(teacher),学生(student),免费试用帐号(freeAccount)
	private String userType;
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
	//所属管理者内部ID,学生帐号:ManagerID是他所在教学班的教师帐号ID;
	//院校管理员帐号:合作方帐号ID;
	private String managerID;
	//只对学生帐号有效，其它帐号，classID=0
	private String classID;//所属班级ID
	private Date createDate;//帐号创建时间
	private Date lastLoginTime;
	private String lastLoginIP;
	private String iconURL;//头像
	
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getRentDate1Format() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(rentDate1);
	}	
	public void setRentDate1(Date rentDate1) {
		this.rentDate1 = rentDate1;
	}
	public Date getRentDate2() {
		return rentDate2;
	}
	public String getRentDate2Format() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(rentDate2);
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
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
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
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	
	
}
