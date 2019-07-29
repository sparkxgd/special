package com.zhixingbus.server.service.constants;

/**
 * 红包状态
 * <p/>
 * Date: 2014/8/8 Time: 18:09
 * 
 * @author XIAO
 */
public enum RedpackStatus {
	INIT(-1, "还没有生成红包"),//日志记录有用
	NOT_OPEN(0, "未拆开（未领取）"), 
	OPENED(1, "已拆开（已领取）"), 
	OVERDUED(2, "已过期（已过期）"), 
	DELETED(3, "已删除"),
	OPENING(4, "待拆开（领取中）");
	
	RedpackStatus(int code, String desc) {
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
	 public static String getStatusDesc(int type) {
			switch (type) {
			case -1:
				return INIT.getDesc();
			case 0:
				return NOT_OPEN.getDesc();
			case 1:
				return OPENED.getDesc();
			case 2:
				return OVERDUED.getDesc();
			case 3:
				return DELETED.getDesc();
			case 4:
				return OPENING.getDesc();
			default:
				return String.valueOf(type);
			}
		}
}
