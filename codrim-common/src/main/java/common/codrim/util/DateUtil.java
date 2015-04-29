package common.codrim.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
/**
 * 
 * @author Administrator
 *yyyy.MM.dd       HH:mm:ss
 */
public class DateUtil {
	
	public static String dateFormat = "yyyy-MM-dd";
	public static String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	/** 
	  *转换日期为utc时间
	  * @author xulin
	  * @date 2014年12月12日
	  *  @param localDate
	  *  @return
	  *  Date
	  */ 
	public static Date convertLocalToUTC(Date localDate) {
		Date utcDate;
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(localDate);
		rightNow.add(Calendar.HOUR_OF_DAY, -8);
		utcDate = rightNow.getTime();
		return utcDate;
	}
public static short getCurrentHour(){
	Calendar calendar=Calendar.getInstance();
	Integer i=calendar.get(Calendar.HOUR_OF_DAY);
	return i.shortValue();
}

	/** 
	  *转换long为string类型日期
	  * @author xulin
	  * @date 2014年12月12日
	  *  @param date
	  *  @param type
	  *  @return
	  *  String
	  */ 
	public static String getNowDateToString(long date, String type) {
		Date newDate = new Date(date);
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		sdf.setTimeZone(zone);
		return sdf.format(newDate);
	}

	/** 
	  *转换string 为date
	  * @author xulin
	  * @date 2014年12月12日
	  *  @param date
	  *  @param type
	  *  @return
	  *  String
	  */ 
	public static Date convertStringToDate(String date, String type) {
		if(StringUtil.isBlank(date)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		Date newDate;
	
			try {
				newDate = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	
		return newDate;
	}
	public static void main(String[] args) {
//		System.out.println(convertStringToDate("2014-11-29","yyyy-MM-dd"));
//		System.out.println(new Date());
//		System.out.println(getCurrentHour());
//		System.out.println(getNowDateToString(convertStringToDate("2014-11-29 01:00:00","yyyy-MM-dd HH:mm:ss").getTime(),"yyyy-MM-dd HH:mm:ss"));
//		
//		System.out.println(getYYYY());
//		System.out.println(getMM());
//		System.out.println(getDD());
//		System.out.println(getCurrentTimestap());
		
		
		//System.out.println(getDateDayDiff(parseDate("2015-01-03"), new Date()));
//		System.out.println(getNowDateToString(1421128544000l, "yyyy-MM-dd hh:mm:ss"));
//		System.out.println(getDateSecondDiff(parseDatetime("2015-01-12 10:52:00"), parseDatetime("2015-01-12 10:52:30")));
//		
		String[] availId = TimeZone.getAvailableIDs();      
		   
	      // checking available Ids
	      System.out.println("Available Ids are: ");
	      for (int i=0; i<availId.length; i++){
	         System.out.println(availId[i]);
	      } 
		
		
		
		Date oldDate = new Date(System.currentTimeMillis());
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss a");
		sdf.setTimeZone(zone);
		String string = sdf.format(oldDate);
		System.out.println("current  time is "+string+" with GMT+8/Beijing ");
		TimeZone zone1 = TimeZone.getTimeZone("Etc/GMT-5");
		sdf.setTimeZone(zone1);
		String string2 = sdf.format(oldDate);
		System.out.println("current  time is "+string2+" with GMT-5");
		
	
		
	}

	/** 
	  *转换long型为日期
	  * @author xulin
	  * @date 2014年12月12日
	  *  @param date
	  *  @param type
	  *  @return
	  *  String
	  */ 
	public static Date getNowDateToDate(long date, String type) {
		Date oldDate = new Date(date);
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		sdf.setTimeZone(zone);
		String string = sdf.format(oldDate);
		Date newDate = null;
		try {
			newDate = sdf.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}

	/** 
	  *获取后一天日期
	  * @author xulin
	  * @date 2014年12月12日
	  *  @param date
	  *  @param type
	  *  @return
	  *  String
	  */ 
	public static String getAfterNowDate(String date, String type) {
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		sdf.setTimeZone(zone);
		sdf.setTimeZone(zone);
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance(zone);
		rightNow.setTime(newDate);
		rightNow.add(Calendar.DAY_OF_MONTH, +1);
		newDate = rightNow.getTime();
		return sdf.format(newDate);
	}

	/** 
	  *获取前一天日期
	  * @author xulin
	  * @date 2014年12月12日
	  *  @param date
	  *  @param type
	  *  @return
	  *  String
	  */ 
	public static String getBeforeNowDate(String date, String type) {
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		sdf.setTimeZone(zone);
		sdf.setTimeZone(zone);
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar rightNow = Calendar.getInstance(zone);
		rightNow.setTime(newDate);
		rightNow.add(Calendar.DAY_OF_MONTH, -1);
		newDate = rightNow.getTime();
		return sdf.format(newDate);
	}
	
	public static String formatDateTime(String format, Date date) {
		if(date == null){
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String formatDateTime(Date date) {
		if(date == null){
			return "";
		}
		
		return formatDateTime(datetimeFormat, date);
	}
	
	public static String formatDate(Date date) {
		if(date == null){
			return "";
		}
		
		return formatDateTime(dateFormat, date);
	}
	
	public static Calendar toCalendar(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			return cal;
		}
	}
	
	public static Date parse(String format, String dateString) {
		if (dateString == null || dateString == "")
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		
		Date d = null;
		try {
			d = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	public static Date parseDate(String dateStr) {
		return parse(dateFormat, dateStr);
	}
	
	public static Date parseDatetime(String datetimeStr) {
		return parse(datetimeFormat, datetimeStr);
	}
	
	public static String getYYYY() {
		return formatDateTime("yyyy", new Date());
	}
	
	public static String getMM() {
		return formatDateTime("MM", new Date());
	}
	
	public static String getDD() {
		return formatDateTime("dd", new Date());
	}
	
	public static String getCurrentTimestap() {
		return formatDateTime("yyyyMMddHHmmss", new Date());
	}
	
	public static long getDateDayDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return 0;
		
		Long beginL = startDate.getTime();
		Long endL = endDate.getTime();
		
		return (endL - beginL)/(24 * 60 * 60 * 1000);
	}
	
	public static long getDateSecondDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return 0;
		
		Long beginL = startDate.getTime();
		Long endL = endDate.getTime();
		
		return (endL - beginL)/1000;
	}
}
