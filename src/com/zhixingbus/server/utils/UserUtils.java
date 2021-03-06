package com.zhixingbus.server.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class UserUtils {

	// 网页后台登录密码：147851171aa
	private static final String username = "70201968";
	private static final String password = MD5Util.string2MD5("14785117191");

	/**
	 * 产生指定位数的随机数
	 * 
	 * @param count
	 * @return
	 */
	public static String getRandomStr(int count) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(str.length());
			sb.append("");
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}

	/*
	 * 功能: duanxin.cm JAVA HTTP接口 发送短信修改日期: 2014-03-19说明:
	 * http://api.duanxin.cm/?
	 * ac=send&username=账号&password=MD5位32密码&phone=号码&content=内容状态: 100 发送成功 101
	 * 验证失败 102 短信不足 103 操作失败 104 非法字符 105 内容过多 106 号码过多 107 频率过快 108 号码内容空 109
	 * 账号冻结 110 禁止频繁单条发送 111 系统暂定发送 112 号码不正确 120 系统升级
	 */
	public static String sendMsg(String content, String phone) {
		// return "100";
		String result = null;

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://api.momingsms.com/?");

		// 向StringBuffer追加用户名
		sb.append("action=send&username=");
		sb.append(username);

		// 向StringBuffer追加密码（密码采用MD5 32位 小写）
		sb.append("&password=");
		sb.append(password);

		sb.append("&encode=utf8");

		// 向StringBuffer追加手机号码
		sb.append("&phone=");
		sb.append(phone);
		HttpURLConnection connection = null;
		BufferedReader in = null;
		try {
			// 向StringBuffer追加消息内容转URL标准码
			sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));

			URL url = new URL(sb.toString());
			// 打开url连接
			connection = (HttpURLConnection) url.openConnection();

			// 设置url请求方式 'get' 或者 'post'
			connection.setRequestMethod("GET");

			// 发送
			in = new BufferedReader(new InputStreamReader(url.openStream()));

			// 返回发送结果
			result = in.readLine();
			// 返回结果为'100' 发送成功
			System.out.println(result);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
					in = null;
				}
				if(connection != null){
					connection.disconnect();
					connection = null;
				}
			} catch (Exception e2) {
			}
			
		}

		return result;
	}

	public static void main(String[] args) {
		sendMsg("测试短信", "14785117191");
	}
}
