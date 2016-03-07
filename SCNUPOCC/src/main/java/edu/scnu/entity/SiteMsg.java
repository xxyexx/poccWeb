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
 *个人站内消息
 */
@Entity(name="sitemsg")
public class SiteMsg implements Comparable<SiteMsg>{
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String senderID;//发送者ID,对应内部帐号ID
	private String sender;//发送者名称,发送者实际名称，便于处理的冗余字段
	private String receiverID;//接收者ID
	private String receiver;//接收者名称
	private String recvGroup;//接收组,对应内部帐号ID或会员登录ID列表
	private String title;//标题
	private String message;//正文
	private Date sendTime;//发送时间
	private int isPublic;//是否公告信息(0|1)
	private int read;//是否已阅读(0|1)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getRecvGroup() {
		return recvGroup;
	}
	public void setRecvGroup(String recvGroup) {
		this.recvGroup = recvGroup;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public String getSendTimeFormat(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(sendTime);
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	
	@Override
	public int compareTo(SiteMsg o) {
		if(this.sendTime.before(o.getSendTime())){
			return 1;
		}else if(this.sendTime.equals(o.getSendTime())){
			return 0;
		}
		return -1;
	}
	

	
}
