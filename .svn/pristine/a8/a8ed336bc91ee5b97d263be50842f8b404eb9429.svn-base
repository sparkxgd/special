package com.zhixingbus.server.service.constants;

/**
 * 促销活动状态
 * <p/>
 * Date: 2014/8/8
 * Time: 18:09
 * @author XIAO
 */
public enum PromotionStatus {
  SUCCESS(0, "创建成功"),
  PROCEEDING(1,"正在发布"),
  CLOSED(2,"正常关闭");

  PromotionStatus(int code, String desc) {
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
		case 0:
			return SUCCESS.getDesc();
		case 1:
			return PROCEEDING.getDesc();
		case 2:
			return CLOSED.getDesc();
		default:
			return String.valueOf(type);
		}
	}
}
