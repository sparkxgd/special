package com.zhixingbus.server.utils;

import java.util.Date;

import com.zhixingbus.server.config.Constans;

public class CookieUtils {
	
	
	//生成的cookie格式为：username|时间|MD5(MD5由用户名+密码+时间+cookie密码组成)
	public static String createUserLoginCookieString(String username,String password){
		long now = new Date().getTime();
		return username+"|"+now+"|"+MD5Util.string2MD5(username+password+now+Constans.cookiePassword);
	}
	

	/**
	 * 验证cookie是否有效
	 * @param username 用户名
	 * @param password 密码
	 * @param cookieString cookie信息
	 * @return
	 */
	public static boolean validateUserLoginCookie(String username,String password,String cookieString){
		long now = getCookieCreateDateFromCookie(cookieString);
		String md5 = getMD5FromCookie(cookieString);
		if(md5.equals(MD5Util.string2MD5(username+password+now+Constans.cookiePassword))){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 验证cookie是否过期
	 * @param cookieString cookie信息
	 * @return
	 */
	public static boolean validateUserLoginCookie(String cookieString){
		if(StringUtil.isBlankOrEmpty(cookieString)){
			return false;
		}
		long loginTime = getCookieCreateDateFromCookie(cookieString);
		
		long nowtime=new Date().getTime();
		
		if(nowtime-loginTime>Constans.cookie_active_time*1000){//kookie过期
			return false;
		}
		return true;
	}
	
	
	/**
	 * 从cookie中获取账户名称
	 * @param cookieString
	 * @return
	 */
	public static String getNameFromCookie(String cookieString){
		String info[] = cookieString.split("\\|");
		if(info!=null && info.length == 3){
			return info[0];
		}
		return null;
	}
	
	/**
	 * 从cookie中获取账户登录时间
	 * @param cookieString
	 * @return
	 */
	public static long getCookieCreateDateFromCookie(String cookieString){
		String info[] = cookieString.split("\\|");
		long t=0l;
		if(info!=null && info.length == 3){
			 try {
				t=Long.valueOf(info[1]);
			} catch (NumberFormatException e) {
				return t;
			}
		}
		return t;
	}
	
	/**
	 * 从cookie中获取md5
	 * @param cookieString
	 * @return
	 */
	public static String getMD5FromCookie(String cookieString){
		String info[] = cookieString.split("\\|");
		if(info!=null && info.length == 3){
			return info[2];
		}
		return null;
	}
}
