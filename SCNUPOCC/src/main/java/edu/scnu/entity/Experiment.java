package main.java.edu.scnu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author l
 *实验项目
 */
@Entity(name="experiment")
public class Experiment {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;//实验项目名称
	private String expDesc;//实验项目说明
	//定义与教师关联的User实体
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="writer_id",referencedColumnName="id")
	private User user;//撰写人,对应教师ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
	public String getExpDesc() {
		return expDesc;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc.trim();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	
}
