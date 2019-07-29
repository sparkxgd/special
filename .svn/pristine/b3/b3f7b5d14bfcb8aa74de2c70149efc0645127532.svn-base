package com.zhixingbus.server.constants;

import com.zhixingbus.server.utils.Utils;

/**
 * 新闻发布状态
 * 2015年12月25日 10:25:05
 * 0：未发表。1：已发表。2：已经下架
 * @author XIAO
 */
public enum ArticleState {
	NOT_PUBLISHED(0, "未发表"),
	PUBLISHING(1,"发表中"),
	REMOVED(2,"已经下架");

  ArticleState(int code, String desc) {
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
			return NOT_PUBLISHED.getDesc();
		case 1:
			return PUBLISHING.getDesc();
		case 2:
			return REMOVED.getDesc();
		default:
			return String.valueOf(type);
		}
	}
  
  public static boolean isExit(int status){
	  switch (status) {
		case 0:
			return true;
		case 1:
			return true;
		case 2:
			return true;
		default:
			return false;
		}
  }

	public static boolean isExit(String status) {
		if (!Utils.isNumeric(status)) {
			return false;
		}
		int st = Integer.valueOf(status);
		switch (st) {
		case 0:
			return true;
		case 1:
			return true;
		case 2:
			return true;
		default:
			return false;
		}
	}
}
