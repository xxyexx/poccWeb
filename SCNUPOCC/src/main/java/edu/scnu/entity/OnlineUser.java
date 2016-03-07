package main.java.edu.scnu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author l
 *在线用户
 */
@Entity(name="onlineUser")
public class OnlineUser {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String loginID;//在线用户的登录ID
	private String userName;//用户名称
	private Date loginTime;//登录时间
	private int onlineTime;//在线时长(分钟)
	private int isTesting;//是否在线实验中(0|1)
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(int onlineTime) {
		this.onlineTime = onlineTime;
	}
	public int getIsTesting() {
		return isTesting;
	}
	public void setIsTesting(int isTesting) {
		this.isTesting = isTesting;
	}
	
}
