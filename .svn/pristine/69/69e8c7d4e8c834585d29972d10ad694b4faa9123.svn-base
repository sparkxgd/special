package com.zhixingbus.server.constants;

import com.zhixingbus.server.utils.Utils;

/**
 * 通知级别状态
 * 2015年12月25日 10:25:05
 * @author XIAO
 */
public enum ArticleLevel {
	COMMON(0, "普通"),
	INTERMEDIATE(1,"中级"),
	SENIOR(2,"高级");

  ArticleLevel(int code, String desc) {
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
			return COMMON.getDesc();
		case 1:
			return INTERMEDIATE.getDesc();
		case 2:
			return SENIOR.getDesc();
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
	public static void createSelect(StringBuffer select, String name,int status){
		select.append("<select  name='level'>");
		switch (status) {
		case 1:
			select.append("<option value='0'>"+COMMON.getDesc()+"</option>");
			select.append("<option value='1' selected='selected'>"+INTERMEDIATE.getDesc()+"</option>");
			select.append("<option value='2'>"+SENIOR.getDesc()+"</option>");
			break;
		case 2:
			select.append("<option value='0'>"+COMMON.getDesc()+"</option>");
			select.append("<option value='1'>"+INTERMEDIATE.getDesc()+"</option>");
			select.append("<option value='2' selected='selected'>"+SENIOR.getDesc()+"</option>");
			break;
			
		default:
			select.append("<option value='0'  selected='selected'>"+COMMON.getDesc()+"</option>");
			select.append("<option value='1'>"+INTERMEDIATE.getDesc()+"</option>");
			select.append("<option value='2'>"+SENIOR.getDesc()+"</option>");
			break;
		}
		select.append("</select>");
		
	}
}
