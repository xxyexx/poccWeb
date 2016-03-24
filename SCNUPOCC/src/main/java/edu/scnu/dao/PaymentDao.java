package main.java.edu.scnu.dao;

import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.Payment;

public interface PaymentDao extends BaseDao<Payment>{
	
	public Page<Payment> findPage(Payment paymentModel,Page<Payment> pageModel);

}
