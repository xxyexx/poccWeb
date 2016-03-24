package main.java.edu.scnu.service;

import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.Payment;

public interface PaymentService {

	/**
	 * 根据模板显示缴费记录
	 * @param modelPayment
	 * @param modelPage
	 * @return
	 */
	public Page<Payment> getPaymentPage(Payment modelPayment, Page<Payment> modelPage);
	
}
