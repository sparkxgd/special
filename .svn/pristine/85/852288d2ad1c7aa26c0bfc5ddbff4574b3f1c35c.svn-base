package com.zhixingbus.server.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
* 文 件 名 :com.zhixingbus.server.controller.weixin.pay.clientWeixinHttpClient.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：20152015年6月30日下午1:59:39
*<br>
* 修 改 人：xiao
*<br>
* 日   期：20152015年6月30日下午1:59:39
*<br>
* 描   述：
*<br>
* 版 本 号：v1.0
 */
public class HttpClient {
	private String url;
	private String charset="utf-8";
	private String returnMsg;
	public HttpClient(){
		
	}
	public HttpClient(String url){
		this.url=url;
	}
	/**
	 * 发送消息(带参数)
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String sendMsg(List<BasicNameValuePair> params) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer sb = new StringBuffer();
		try {
			HttpPost httpPost = new HttpPost(url);
			/*将参数封装到表单请求体中*/
		    UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params, charset);
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			InputStream inputStream = null;
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 从request中取得输入流
					inputStream = entity.getContent();
					BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
					String line;
					while ((line = in.readLine()) != null) {
						sb.append(line);
					}
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
				if (inputStream != null) {
					// 释放资源
					inputStream.close();
					inputStream = null;
				}
			}
		} finally {
			httpclient.close();
		}
		return sb.toString();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {}
}
