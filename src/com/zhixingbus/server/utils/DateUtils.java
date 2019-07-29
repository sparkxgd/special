package com.zhixingbus.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateUtils {

	public static String date2String(Date date){
		if(date == null) return "";
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");  
		return new DateTime(date.getTime()).toString(format);
	}
	public static String getCurrentTime(){
		Date date=new Date();
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");  
		return new DateTime(date.getTime()).toString(format);
	}
	
	public static String date2StringSlash(Date date){
		if(date == null) return "";
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");  
		return new DateTime(date.getTime()).toString(format);
	}
	
	public static Date string2Date(String stringDate){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");  
		return DateTime.parse(stringDate, format).toDate();
	}
	public static Date string2DateAdd(String stringDate){
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"); 
		Date d=DateTime.parse(stringDate, format).toDate();
		d.setHours(d.getHours()+2);
		return d;
	}
	public static String date2ymdString(Date date){
		SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd");  
		return format.format(new Date());
	}
	public static String date2y_m_dString(Date date){
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");  
		return format.format(new Date());
	}
	public static void main(String[] args) {
		System.out.println(date2StringSlash(new Date()));
	}
}

