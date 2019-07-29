package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;
/**
* 文 件 名 :com.zhixingbus.server.model.WeixinLogModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年7月4日 12:38:30
*<br>
* 描   述：微信接口日志
*<br>
* 版 本 号：v1.0
 */
public class WeixinLogBean extends Data<WeixinLogBean> {
	public static final String GET_LIST=RedPackService.HOST+"zeus/get_weixinglog_list";//获取列表
	public static final String GET_MODEL=RedPackService.HOST+"zeus/get_weixinglog_model";//获取详情

	/**
	 * 主键
	 * @return
	 */
	private String id;
	/**
	 * 发送给微信的信息
	 * @return
	 */
	private String sendMsg;
	/**
	 * 接收微信的信息
	 * @return
	 */
	private String receiveMsg;
	/**
	 * 发送时间
	 * @return
	 */
	private String sendTime;
	/**
	 * 接收时间
	 * @return
	 */
	private String receiveTime;
	/**
	 * 类名
	 * @return
	 */
	private String className;
	/**
	 * 事件
	 * @return
	 */
	private String even;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
	public String getReceiveMsg() {
		return receiveMsg;
	}
	public void setReceiveMsg(String receiveMsg) {
		this.receiveMsg = receiveMsg;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getEven() {
		return even;
	}
	public void setEven(String even) {
		this.even = even;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static WeixinLogBean getObject(String id) {
		HttpClient client=new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		WeixinLogBean model=null;
		try {
			r = client.sendMsg(params);
			model=new WeixinLogBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<WeixinLogBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue) {
			HttpClient client=new HttpClient(GET_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			String r=null;
			Rpage<WeixinLogBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new WeixinLogBean().jackson2page(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
}
