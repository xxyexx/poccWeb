package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.PaymentDao;
import main.java.edu.scnu.entity.Payment;

@Repository(value="paymentDao")
public class PaymentDaoImpl extends BaseDaoImpl<Payment> 
	implements PaymentDao
{

}
