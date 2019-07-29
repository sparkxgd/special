package com.zhixingbus.server.service.constants;

import com.zhixingbus.server.utils.Utils;

/**
 * 红包活动状态
 * <p/>
 * Date: 2014/8/8
 * Time: 18:09
 * @author XIAO
 */
public enum RedpackActivityStatus {
  SUCCESS(0, "创建成功"),
  RECIVE_MONey(1, "收到款项"),
  CHECK_FININSH(2,"审核完成"),
  PROCEEDING(3,"正在发布"),
  CLOSED(4,"正常关闭"),
  EXCEPTION_CLOSED(5,"异常关闭");



  RedpackActivityStatus(int code, String desc) {
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
			return RECIVE_MONey.getDesc();
		case 2:
			return CHECK_FININSH.getDesc();
		case 3:
			return PROCEEDING.getDesc();
		case 4:
			return CLOSED.getDesc();
		case 5:
			return EXCEPTION_CLOSED.getDesc();
		default:
			return String.valueOf(type);
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
		case 3:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		default:
			return false;
		}
	}
}
