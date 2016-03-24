package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.PaymentDao;
import main.java.edu.scnu.entity.Page;
import main.java.edu.scnu.entity.Payment;

@Repository(value="paymentDao")
public class PaymentDaoImpl extends BaseDaoImpl<Payment> 
	implements PaymentDao
{

	@Override
	public Page<Payment> findPage(Payment paymentModel, Page<Payment> pageModel) {
		// TODO Auto-generated method stub
		List<Payment> list = findAll(Payment.class);
		Page<Payment> page = new Page<Payment>(0);
		page.setList(list);
		return page;
	}

}
