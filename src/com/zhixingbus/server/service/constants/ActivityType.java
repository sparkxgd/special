package com.zhixingbus.server.service.constants;

/**
 * 红包活动类型
 * <p/>
 * Date: 2014/8/8 Time: 18:09
 * 
 * @author XIAO
 */
public enum ActivityType {
	COMMON(0, "普通红包活动"), BUSINESS(1, "商家红包活动");
	ActivityType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getTypeDesc(int type) {
		switch (type) {
		case 0:
			return COMMON.getDesc();
		case 1:
			return BUSINESS.getDesc();
		default:
			return String.valueOf(type);
		}
	}
}
