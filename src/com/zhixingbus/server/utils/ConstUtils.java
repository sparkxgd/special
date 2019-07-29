package com.zhixingbus.server.utils;

public class ConstUtils {

	// 用户注册返回状态码常量
	public static final String REGISTER_RETURN_CODE = "registerCode";// 注册返回码

	public static final int EMAIL_EMPTY = 1;// 邮箱地址为空
	public static final int USERNAME_EMPTY = 2;// 用户名为空
	public static final int PASSWORD_EMPTY = 3;// 密码为空
	public static final int EMAIL_EXIST = 4;// 邮箱已存在
	public static final int USERNAME_EXIST = 5;// 用户名已存在
	public static final int REGISTER_SUCCESS = 6;// 注册成功
	public static final int NICKNAME_EMPTY = 7;// 昵称为空
	public static final int VALIDATE_CODE_EMPTY  = 8;// 验证码为空
	public static final int VALIDATE_CODE_INVALID  = 9;// 验证码无效，请重新获取
	public static final int VALIDATE_CODE_ERROR  = 10;// 验证码错误，请重新输入
	public static final int NICKNAME_EXIST  = 11;// 昵称已经存在

	public static final String LOGIN_RETURN_CODE = "loginCode";// 登录返回码
	public static final int LOGIN_USERNAME_EMPTY = 1;// 用户名为空
	public static final int LOGIN_PASSWORD_EMPTY = 2;// 密码为空
	public static final int USERNAME_NOT_EXIST = 3;// 用户不存在
	public static final int LOGIN_SUCCESS = 4;// 登录成功
	public static final int PASSWORD_ERROR = 5;// 密码错误

	// 用户临时数据之找回密码
	public static final int TYPE_USER_PWD = 1;
	public static final int URL_INVALID = 2;// 该链接已经失效
	public static final int RESET_SUCCESS = 3;// 重设密码成功
	public static final int RESET_FAIL = 4;// 重设密码失败

	// 第三方登录
	public static final int THIRD_LOGIN_EMPTY = 1;// 第三方id为空
	public static final int THIRD_LOGIN_SUCCESS = 2;// 第三方登录成功
	public static final int THIRD_NOT_EXIST = 3;// 第三方id不存在

	// 用户反馈意见
	public static final int SUGGEST_STATUS_ADD = 1;// 用户提交反馈意见
	public static final int SUGGEST_STATUS_PROCESSING = 2;// 处理中
	public static final int SUGGEST_STATUS_COMPLETE = 3;// 已完成
	
	//增加反馈意见
	public static final String ADD_SUGGEST = "addSuggest";
	public static final int SUGGEST_TITLE_EMPTY = 1;// 标题为空
	public static final int SUGGEST_EMPTY = 2;// 反馈意见为空
	public static final int SUGGEST_USERID_EMPTY = 3;// userId为空
	public static final int SUGGEST_SUCCESS = 4;// 反馈成功
	
	//查询反馈意见
	public static final int QUERY_SUGGEST = 0;
	public static final int QUERY_USERID_EMPTY = 1;// userId为空
	public static final int QUERY_SUGGEST_ORDERNO = 1;
	
	//用户头像文件夹
	public static final String HEAD_FILE = "head";
	public static final String HEAD_FILE_SUFFIX = ".JPG.BMP.DIB.RLE.EMF.GIF.JPG.JPEG.JPE.JIF.PCX.DCX.PIC.PNG.TGA.TIF.TIFFXIF.WMF.JFIF";
	
	//获取验证码的时间间隔
	public static final int INTERVAL = 60 * 1000;//秒
	
	//公交车查询范围
	public static final double BUS_RANGE = 0.01;
	
	//用户远离车道（用户10米范围内没有站点）
	public static final double STATION_RANGE = 0.00007;
	
	
	public static final int UNBIND_ERROR_CNT = 1;
	
	public static final String RETURN_CODE = "returnCode";
	public static final String R_LIST = "rList";
	public static final String R_MODEL = "rModel";
	public static final String VERSION = "version";
	
}
