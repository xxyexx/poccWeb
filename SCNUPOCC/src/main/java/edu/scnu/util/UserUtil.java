package main.java.edu.scnu.util;

import main.java.edu.scnu.entity.User;

public class UserUtil {

	/**
	 *返回一个计费信息和院校信息与原用户一致的新用户
	 */	
	public User getCloneUser(User u){
		User result = new User();
		result.setPasswd(u.getPasswd());
		result.setUserType(u.getUserType());
		result.setAcctTag(u.getAcctTag());
		result.setAcctName(u.getAcctName());
		result.setRentDate1(u.getRentDate1());
		result.setRentDate2(u.getRentDate2());
		result.setCouponType(u.getCouponType());
		result.setMonthlyRent(u.getMonthlyRent());
		result.setDiscount(u.getDiscount());
		result.setMoreMonth(u.getMoreMonth());
		result.setPayTag(u.getPayTag());
		result.setLockTag(u.getLockTag());
		result.setPayment(u.getPayment());
		result.setPayDate(u.getPayDate());
		result.setManagerID("");
		result.setClassID(u.getClassID());
		result.setIconURL(u.getIconURL());
		return result;		
	}
	
}
