package main.java.edu.scnu.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**返回长度为4的当前年份，如"2015","2016"
	 * @return "2016"
	 */
	public static String getYear4(){
		//TODO
		return "2016";		
	}
	
	
	/**
	 * 返回当月1号的Date类型对象
	 * @param month (符合"yyyy/mm"格式，如"2016/02")
	 * @return 如"2016/02/01 00:00:00"
	 */
	public static Date getDateByMonth(String month){
		Date date = null;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			date = df.parse(month+"/01 00:00:00");			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;		
	}
	
	/**
	 * 返回当天0点的Date类型对象
	 * @param month (符合"yyyy/mm/dd"格式，如"2016/02/03")
	 * @return 如"2016/02/03 00:00:00"
	 */
	public static Date getDateByDate(String date){
		Date result = null;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			result = df.parse(date+" 00:00:00");			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public static void main(String[] args) {
		Date date = DateUtil.getDateByMonth("2016/04");
		System.out.println(date);
	}

}
