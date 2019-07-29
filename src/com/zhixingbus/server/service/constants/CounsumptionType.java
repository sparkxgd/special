package com.zhixingbus.server.service.constants;

/**
 * 消费类型
 * <p/>
 * Date: 2014/8/8 Time: 18:09
 * 
 * @author XIAOconsumptionType
 */
public enum CounsumptionType {
	IN_MOMEY(0, "入账"), 
	OUT_MOMEY(1, "支付");
	CounsumptionType(int code, String desc) {
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
	public static String getDesc(int code) {
		switch (code) {
		case 0:
			return IN_MOMEY.getDesc();
		case 1:
			return OUT_MOMEY.getDesc();
		default:
			return String.valueOf(code);
		}
	}
}
