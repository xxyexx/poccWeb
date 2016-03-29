package main.java.edu.scnu.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.Payment;
import main.java.edu.scnu.service.PaymentService;
import main.java.edu.scnu.util.DateUtil;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="paymentAction")
@Scope(value="prototype")
public class PaymentAction extends ActionSupport {
	
	private int id;
	private int acctID;//缴费帐号ID
	private String payDate;//缴费日期
	private double amount;//缴费金额
	private String rentDate1;//购买租期起始
	private String rentDate2;//购买租期终止
	private int quantity;//购买帐号数量
	private String coupon;//优惠类型
	private int discount;//折扣额
	private int moreMonth;//赠送月份
	private String operator;//操作员
	private String opTime;//操作时间
	private String memo;//备注
	
	private HttpServletRequest request;
	
	@Resource(name="paymentService")
	PaymentService paymentService;
	
	

	public PaymentAction(){
		this.request = ServletActionContext.getRequest();
	}

	public String update(){
		Payment payment = this.getPayment(id);
		payment.setAcctID(0);
		payment.setPayDate(DateUtil.getDateByDate(this.payDate));
		payment.setAmount(this.amount);
		payment.setRentDate1(DateUtil.getDateByMonth(this.rentDate1));
		payment.setRentDate2(DateUtil.getDateByMonth(this.rentDate2));
		payment.setQuantity(this.quantity);
		payment.setCoupon(this.coupon);
		payment.setDiscount(this.discount);
		payment.setMoreMonth(this.moreMonth);
		payment.setMemo(this.memo);
		payment.setOperator("操作人");
		payment.setOpTime(new Date());
		payment.setMemo(this.memo);
		paymentService.updatePayment(payment);
		return paymentView();		
	}
	
	public String paymentView(){
		
		Page<Payment> paymentPage = new Page<Payment>(0);
		Payment payment = new Payment();
		paymentPage.setPageSize(100);
		paymentPage = paymentService.getPaymentPage(payment, paymentPage);
		request.setAttribute("paymentPage", paymentPage);
		return "paymentView";
		
	}
	private Payment getPayment(int id){
		Payment payment = null;
		if (id>0) payment = paymentService.getPayment(id);
		else payment = new Payment();
		return payment;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}


	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public void setRentDate1(String rentDate1) {
		this.rentDate1 = rentDate1;
	}


	public void setRentDate2(String rentDate2) {
		this.rentDate2 = rentDate2;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public void setMoreMonth(int moreMonth) {
		this.moreMonth = moreMonth;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	

}
