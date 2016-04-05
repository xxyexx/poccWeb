package main.java.edu.scnu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 记录接收消息用户已读消息，已删除消息
 */
@Entity(name="historysitemsg")
public class HistorySiteMsg {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//定义与User关联
	@ManyToOne(targetEntity=SiteMsg.class)
	@JoinColumn(name="siteMsg_ID",referencedColumnName="id")
	private SiteMsg siteMsg;//消息
	private int receiverID;//接收者id user.id
	private int isread;//是否标记为已阅读(0|1)
	private int isdelete;//是否标记为删除不再显示(0|1)
	private Date readTime;//读消息时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SiteMsg getSiteMsg() {
		return siteMsg;
	}
	public void setSiteMsg(SiteMsg siteMsg) {
		this.siteMsg = siteMsg;
	}
	public int getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	
}
