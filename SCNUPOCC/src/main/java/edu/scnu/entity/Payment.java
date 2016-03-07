package main.java.edu.scnu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author l
 *缴费简表
 */
@Entity(name="payment")
public class Payment {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int acctID;//缴费帐号ID
	private Date payDate;//缴费日期
	private double amount;//缴费金额
	private Date rentDate1;//购买租期起始
	private Date rentDate2;//购买租期终止
	private int quantity;//购买帐号数量
	private String coupon;//优惠类型
	private int discount;//折扣额
	private int moreMonth;//赠送月份
	private String operator;//操作员
	private Date opTime;//操作时间
	private String memo;//备注
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAcctID() {
		return acctID;
	}
	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
