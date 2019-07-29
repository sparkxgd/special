package com.zhixingbus.server.utils;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * 文 件 名 :com.zhixingbus.server.utilsRedpackUtil.java <br>
 * 创 建 人：xiao <br>
 * 日 期：20152015年7月2日下午4:54:00 <br>
 * 修 改 人：xiao <br>
 * 日 期：20152015年7月2日下午4:54:00 <br>
 * 描 述：红包相关的工具类 <br>
 * 版 本 号：v1.0
 */
public class RedpackUtil {
	/**
	 * 日期加上天数
	 * 
	 * @param date
	 *            日期
	 * @param intDate
	 *            天数
	 * @return
	 */
	public static Date dateAdd(Date date, int intDate) {
		return new Date(date.getTime() + (intDate * 24 * 60 * 60 * 1000));
	}

	/**
	 * 红包平均分配的方式 原理：红包总金额按红包总个数平分，取出整数，剩余的给最后一个
	 * 
	 * @param totalAmount总金额
	 * @param redPackAmount红包数
	 * @param sendRedpackNum已发红包的个数
	 * @return double带小数点
	 */
	public static double averageToDouble(double totalAmount, int redPackAmount, int sendRedpackNum) {
		/* 判断 */
		BigDecimal total = new BigDecimal(totalAmount);
		BigDecimal amount = new BigDecimal(redPackAmount);
		int surplus = redPackAmount - sendRedpackNum;//剩余的红包数
		double values = total.doubleValue() / amount.doubleValue();
		double value = 0;
		if (surplus > 1) {//
			value = new BigDecimal(values).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} else if (surplus == 1) {
			double b = new BigDecimal(values).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double sum = b * sendRedpackNum;// 计算已经发送了多少钱
			value = new BigDecimal(totalAmount - sum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		}
		return value;
	}

	/**
	 * 红包平均分配的方式，
	 * 
	 * @param totalAmount总金额
	 * @param redPackAmount红包数
	 * @return double带小数点
	 */
	public static int averageToInt(double totalAmount, int redPackAmount, int sendRedpackNum) {

		return 1;
	}

	/**
	 * 将红包金额元转换为分
	 * 
	 * @return
	 */
	public static int unitaryToPenny(BigDecimal amount) {
		double amountd =amount.doubleValue();
		return (new Double(amountd * 100)).intValue();
	}

	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StringUtil.isBlankOrEmpty(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {

		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}

		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}
	/**
	 * 判断是否int
	 * @param value
	 * @return是，返回true
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	/**
	 * 获取本机ip地址
	 * @return
	 */
	public static String getLocalIp(){
		InetAddress addr;
		String ip="127.0.0.1";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();//获得本机IP
//			addr.getHostName()toString;//获得本机名称
		} catch (UnknownHostException e) {
			return ip;
		}
		return ip;
	}
	public static String mapToString(Map<String, String[]> map) {
		SortedMap smap = new TreeMap<String, String>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			smap.put(k, v);
		}
		return smap.toString();
	}
	public static void main(String[] args) {
		double t = 113;
		int c = 7;
		RedpackUtil.averageToDouble(t, c, 0);
		RedpackUtil.averageToDouble(t, c, 1);
		RedpackUtil.averageToDouble(t, c, 2);
		RedpackUtil.averageToDouble(t, c, 3);
		RedpackUtil.averageToDouble(t, c, 4);
		RedpackUtil.averageToDouble(t, c, 5);
		RedpackUtil.averageToDouble(t, c, 6);
	}
}
