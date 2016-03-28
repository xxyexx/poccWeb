package main.java.edu.scnu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.PaymentDao;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.Payment;
import main.java.edu.scnu.service.PaymentService;


@Service("paymentService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class PaymentServiceImpl implements PaymentService {
	
	@Resource(name="paymentDao")
	PaymentDao paymentDao;
	
	@Override
	public Payment getPayment(int id){
		return paymentDao.get(Payment.class, id);
	}
	
	@Override
	public boolean updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentDao.update(payment);
		return true;
	}

	@Override
	public boolean deletePayment(int id) {
		Payment payment = paymentDao.get(Payment.class, id);
		paymentDao.delete(payment);
		return false;
	}
	
	@Override
	public Page<Payment> getPaymentPage(Payment modelPayment,
			Page<Payment> modelPage) {
		// TODO Auto-generated method stub
		return paymentDao.findPage(modelPayment, modelPage);
	}



}
