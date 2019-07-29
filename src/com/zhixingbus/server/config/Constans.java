package com.zhixingbus.server.config;

import java.util.HashMap;

public class Constans {
	
	public static final int token_effective_time = 60 * 60; //单位秒，默认一个小时
	public static final String cookiePassword = "zhixingbus188";
	public static final String cookieName = "zhixingbus_admin";
	public static final int cookie_active_time = 60 * 60 * 2; //单位秒，默认2个小时
	
	public static final HashMap<Integer, String> userSexMapping = new HashMap<Integer, String>();
	static{
		userSexMapping.put(0, "保密");
		userSexMapping.put(1, "男");
		userSexMapping.put(2, "女");
	}
	
//	public static final HashMap<String, BusGPSInfoModel> busGpsMap = new HashMap<String, BusGPSInfoModel>();
	
	public static final String baidu_ak = "EA13c38f7fcb5cd39bfb79ffd4f4fb06";

}
