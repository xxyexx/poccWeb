package main.java.edu.scnu.entity;

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
@Entity(name="pageset")
public class PageSet {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String content;//发布内容
	private int setType;//内容类型(0:网站首页公告栏公告)(1:某个区域的链接)
	private String operator;//发布者,一般固定是平台管理员
	private Date pubTime;//发布时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSetType() {
		return setType;
	}
	public void setSetType(int setType) {
		this.setType = setType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	
}
