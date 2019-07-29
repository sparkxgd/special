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
* 文 件 名 :com.zhixingbus.server.model.RedpackPayorderModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年8月19日 10:50:26
*<br>
* 描   述：红包支付订单
*<br>
* 版 本 号：v1.0
 */
public class RedpackPayorderBean extends Data<RedpackPayorderBean> {
	public static final String GET_LIST = RedPackService.HOST + "zeus/get_redpackpayorder_list";// 获取列表
	public static final String GET_MODEL = RedPackService.HOST + "zeus/get_redpackpayorder_model";// 获取详情
	public static final String GET_BY_FATHERID_LIST = RedPackService.HOST + "zeus/get_redpackpayorder_fatherid_list";// 获取所有列表

	/**
	 * 主键
	 * @return
	 */
	private String id;
	/**
	 * 红包id
	 * @return
	 */
	private String redpackId;
	/**
	 * 支付订单id
	 * @return
	 */
	private String payorderId;
	/**
	 * 支付类型0：微信企业支付 1：微信红包支付
	 * @return
	 */
	private int payorderType;
	/**
	 * 更新时间
	 * @return
	 */
	private String updateTime;
	
	/**
	 * appid
	 * @return
	 */
	private String mch_appid;
	
	/**
	 * 商户号
	 * @return
	 */
	private String mchid;
	/**
	 * 商户号
	 * @return
	 */
	private String openid;
	/**
	 * 订单状态
	 * @return
	 */
	private String orderStatus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRedpackId() {
		return redpackId;
	}
	public void setRedpackId(String redpackId) {
		this.redpackId = redpackId;
	}
	public String getPayorderId() {
		return payorderId;
	}
	public void setPayorderId(String payorderId) {
		this.payorderId = payorderId;
	}
	public int getPayorderType() {
		return payorderType;
	}
	public void setPayorderType(int payorderType) {
		this.payorderType = payorderType;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getMch_appid() {
		return mch_appid;
	}
	public void setMch_appid(String mch_appid) {
		this.mch_appid = mch_appid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<RedpackPayorderBean> getlist(int pageNumber, int pageSize, String searchKey, String searchValue) {
		HttpClient client = new HttpClient(GET_LIST);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		params.add(new BasicNameValuePair("searchKey", searchKey));
		params.add(new BasicNameValuePair("searchValue", searchValue));
		String r = null;
		Rpage<RedpackPayorderBean> page = null;
		try {
			r = client.sendMsg(params);
			page = new RedpackPayorderBean().jackson2page(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<RedpackPayorderBean> getlist(int pageNumber, int pageSize, String fatherId) {
		HttpClient client = new HttpClient(GET_BY_FATHERID_LIST);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		params.add(new BasicNameValuePair("fatherId", fatherId));
		String r = null;
		Rpage<RedpackPayorderBean> page = null;
		try {
			r = client.sendMsg(params);
			page = new RedpackPayorderBean().jackson2page(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static RedpackPayorderBean getObject(String id) {
		HttpClient client = new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r = null;
		RedpackPayorderBean model = null;
		try {
			r = client.sendMsg(params);
			model = new RedpackPayorderBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	
}
