package main.java.edu.scnu.service;

import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.Payment;

public interface PaymentService {

	/**
	 * 获得缴费记录
	 * @param id 计费记录id
	 * @return
	 */
	public Payment getPayment(int id);
	
	/**
	 * 新增或更新缴费记录
	 * @return true:更新成功;false:更新失败
	 */
	public boolean updatePayment(Payment payment);	
	
	/**
	 * 删除缴费记录
	 * @return true:删除成功;false:删除失败
	 */
	public boolean deletePayment(int id);
	
	/**
	 * 根据模板显示缴费记录
	 * @param modelPayment
	 * @param modelPage
	 * @return
	 */
	public Page<Payment> getPaymentPage(Payment modelPayment, Page<Payment> modelPage);
	
}
