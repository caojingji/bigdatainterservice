package com.founder.interservice.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Copyright: HighLand'S Copyright (c) 2013
 * @Company: HighLand
 * @author zhujian
 * @E-mail:zhuj@bjhlxt.com
 * @careate 2013-9-25����13:27:26
 * @version 1.0 TODO �����ڴ�����
 */
public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);

	private static String datePattern = "yyyy-MM-dd";

	private static String timePattern = "HH:mm:ss";

	private static String datetimePattern = "yyyy-MM-dd HH:mm:ss";

	private static String dateminutePattern = "yyyy-MM-dd HH:mm";//ת�������� by-fujh

	private static String datePattern_CN = "yyyy��M��d��";

	private static String datetimePattern_CN = "yyyy��M��d��Hʱm��";

	public static String getDatePattern() {
		return datePattern;
	}

	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}
	
	public static final String getDate(Date aDate,String format) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(format);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}
	
	public static Date getCurrentDate(){
		return Calendar.getInstance().getTime();
	}

	public static final Date convertStringToDate(String aMask, String strDate) {
		SimpleDateFormat df = null; 
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}
		try {
			if(strDate != null && !"".equals(strDate)){
				date = df.parse(strDate);
			}
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		}

		return date;
	}

	public static final Date convertStringToDateTime(String strDate){
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(datetimePattern);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ datetimePattern + "'");
		}
		try {
			if(strDate != null && !"".equals(strDate)){
				date = df.parse(strDate);
			}
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		}

		return date;
	}
	
	/**
	 * ����ȷ������ʱ��string���ͣ�ת����date����
	 * @param strDate ��ȷ���֣��磺"2013-12-12 15:50"
	 * @return
	 */
	public static final Date convertStringToDateMinute(String strDate){
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(dateminutePattern);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ dateminutePattern + "'");
		}
		try {
			if(strDate != null && !"".equals(strDate)){
				date = df.parse(strDate);
			}
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		}
		return date;
	}
	
	/**
	 * ����ȷ������ʱ��string���ͣ�ת���ɷ��Ϲ����string����
	 * @param strDate ��ȷ���֣��磺"2013-12-12 15:50"
	 * @return
	 */
	public static final String convertStringToStringDateMinute(String strDate){
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(dateminutePattern);
		String ret = "";
		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ dateminutePattern + "'");
		}
		try {
			if(strDate != null && !"".equals(strDate)){
				date = df.parse(strDate);
				ret = df.format(date);
			}
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		}
		return ret;
	}

	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");
		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			df.setTimeZone(timeZoneChina);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static final String getDateTime(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(datetimePattern);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static final String convertDateToString(Date aDate) {
		return getDateTime(datePattern, aDate);
	}

	public static final String convertDateToString(String pattern, Date aDate) {
		return getDateTime(pattern, aDate);
	}

	public static Date convertStringToDate(String strDate) {
		Date aDate = null;
		if (log.isDebugEnabled()) {
			log.debug("converting date with pattern: " + datePattern);
		}
        if(strDate != null && !"".equals(strDate)){
        	aDate = convertStringToDate(datePattern, strDate);
        }
		
		return aDate;
	}
	/**
	 * ת�����ڳ�yyyy-MM-dd��ʽ
	 * @author fujh
	 * @param strDate
	 * @return
	 */
	public static Date convertStringToDateyyyyMMdd(String strDate) {
		Date aDate = null;
		if (log.isDebugEnabled()) {
			log.debug("converting date with pattern: yyyy-MM-dd");
		}
        if(strDate != null && !"".equals(strDate)){
        	aDate = convertStringToDate("yyyy-MM-dd", strDate);
        }
		
		return aDate;
	}
	/**
	 * ���ַ�����ʽyyyy/MM/dd HH24:MI:SS��yyyy/MM/dd ��yyyy/MM/dd HH24:MI:SS��yyyy/MM/dd��ʽ ת����������
	 * @author zhouwei
	 * @param
	 * @return Date
	 */
	public static Date commonStringToDate(String time){ 

	    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
	    time=time.trim() ;
	    int tempPos = time.indexOf("/") ;
	    int timePos = time.indexOf(" ");
	    Date date = null;
	    
	    if(tempPos>-1&&timePos>-1){
	    	 formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
	    }else if(tempPos>-1&&timePos<0){
	    	 formatter = new SimpleDateFormat ("yyyy/MM/dd");
	    }else if(time.indexOf("-")>-1&&timePos<0){
	    	 formatter = new SimpleDateFormat ("yyyy-MM-dd");
	    }else if(time.indexOf("-")>-1&&timePos>0){
	    	 formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	    }
	    
		try {
			if(time != null && !"".equals(time)){
				date = formatter.parse(time);
			}
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
		}

		return date;
	}
	
	public static int compareDate(Date date1, Date date2) {
		String d1 = getDateTime(datePattern, date1);
		String d2 = getDateTime(datePattern, date2);

		if ((d1 == null) && (d2 != null))
			return -1;
		if ((d1 != null) && (d2 == null))
			return 1;
		if ((d1 == null) && (d2 == null))
			return 0;
		return d1.compareTo(d2);
	}

	public static String convertDatetimeToChineseString(Date date) {
		DateFormat df = new SimpleDateFormat(datetimePattern_CN);
		String strDate = df.format(date);
		return strDate;
	}

	public static String convertDateToChineseString(Date date) {
		DateFormat df = new SimpleDateFormat(datePattern_CN);
		String strDate = df.format(date);
		return strDate;
	}
	
	//���yyyy-MM-dd HH:mm��ʽ�������ַ���
	public static String getYYYYMMDDHHMM() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = df.format(date);
		return strDate;
	}
	
	//��Ҫ��������·��жϣ��·ݵ������ж�
	public static String beforeDate(int p_days) {
		Date d = new Date();
		String s = "";
		int Yr, Mon, Day, Hour, Mi;
		String sYr, sMon, sDay, sHour, sMi;
		Yr = 1900 + d.getYear();
		Mon = d.getMonth();
		Mon++;
		Day = d.getDate();
		Hour = d.getHours();
		Mi = d.getMinutes();
		if (p_days > Day) {
			if (Mon != 1) {
				Mon--;
			} else {
				Mon = 12;
				Yr--;
			}
			Day = lastMonDay(Mon, Yr) - (p_days - Day);
		} else if (p_days == Day) {
			if (Mon != 1) {
				Mon--;
			} else {
				Mon = 12;
				Yr--;
			}
			Day = lastMonDay(Mon, Yr) - (p_days - Day);
		} else {
			Day = Day - p_days;
		}
		if (Mon < 10)
			sMon = "0" + String.valueOf(Mon);
		else
			sMon = String.valueOf(Mon);
		if (Day < 10)
			sDay = "0" + String.valueOf(Day);
		else
			sDay = String.valueOf(Day);
		if (Hour < 10)
			sHour = "0" + String.valueOf(Hour);
		else
			sHour = String.valueOf(Hour);
		if (Mi < 10)
			sMi = "0" + String.valueOf(Mi);
		else
			sMi = String.valueOf(Mi);
		s = String.valueOf(Yr) + "-" + sMon + "-" + sDay + " " + sHour + ":"
				+ sMi;
		return (s);

		//return "";
	}
	
	//qiwei �ж�year��mon���м���
	public static int lastMonDay(int mon, int year) {
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			return 31;
		} else {
			if (mon == 2) {
				if (isLeapYear(year)) {
					return 29;
				} else {
					return 28;
				}
			} else {
				return 30;
			}
		}
	}
	
	//qiwei �ж�year���Ƿ�Ϊ����
	public static boolean isLeapYear(int year) {
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	//��ȡ���µĵ�һ��
	public static Date getFirstDayInThisMonth(){
		Calendar cal = Calendar.getInstance();
		String thismonth = "";		
		if(cal.get(Calendar.MONTH)<9){
			thismonth = "0"+(cal.get(Calendar.MONTH)+1);
		}else{
			thismonth = ""+(cal.get(Calendar.MONTH)+1);
		}		
		String minccsj = cal.get(Calendar.YEAR)+"-"+thismonth+"-01";
		return  convertStringToDate("yyyy-MM-dd",minccsj);			
	}
	
	//��ȡ���������
	public static Date getDate(){		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String todayAsString = df.format(new Date());
		return convertStringToDate("yyyy-MM-dd",todayAsString);
	}
	
	/***
	 * ��ȡ�ϸ��µĽ���
	 * @param date
	 * @return
	 */
	public static String getDateOfLastMonth(Date date) {   
		Calendar calendar = Calendar. getInstance(); 
		calendar.setTime(date);  
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String ldate = sdf.format(calendar.getTime());
		return ldate;   
	} 
	
	//�������ռ�������
	public static int getAge(Date dateOfBirth) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dateOfBirth != null) {
			now.setTime(new Date());
			born.setTime(dateOfBirth);
			if (born.after(now)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return age;
	}
	
	/***
	 * ������ת����yyyyMMddhhmmss�ַ�����ʽ 
	 * @param date
	 * @return
	 */
	public static String convertDateToNospaceString(Date date) {   
		  String nospaceStr="";
		  if(date==null){
			  nospaceStr="";
		  }else{
			  nospaceStr=new SimpleDateFormat("yyyyMMddhhmmss").format(date);
		  }
		  return nospaceStr;
	}
	
	/***
	 * ���yyyyMMdd��ʽ�������ַ��� 
	 * @param
	 * @return
	 */
	public static String getYYYYMMDD() {
		String nowDate = "";
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			nowDate = df.format(new Date());
		}catch(Exception e){
			System.out.println("���yyyyMMdd��ʽ�����ڴ���"+e.getMessage());
		}
		return nowDate;
	}
	
	/**
	 * �õ���������ÿ�������
	 * @param
	 */
	public static Date[] getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Date[] dates = new Date[7];
        for (int i = 0; i < 7; i++) {
            dates[i] = (Date) calendar.getTime();
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

	public static void main(String[] args) {
		/*String a = getDatePattern();
		String flrsj = DateUtil.beforeDate(3);
		flrsj=flrsj.substring(0,10);
		System.out.println(flrsj);*/
		//System.out.println(new DateUtil().getYYYYMMDD());
	}
}