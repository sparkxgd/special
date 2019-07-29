package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;
/**
* 文 件 名 :com.zhixingbus.server.model.SponsorInfoModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年6月3日下午4:53:52
*<br>
* 描   述：赞助商信息
*<br>
* 版 本 号：v1.0
 */
public class BusinessInfoBean extends Data<BusinessInfoBean> {
	public static final String GET_LIST=RedPackService.HOST+"zeus/get_businessinfo_list";//获取红包活动列表
	public static final String GET_MODEL=RedPackService.HOST+"zeus/get_businessinfo_model";//获取红包活动列表
	public static final String GET_UPDATE=RedPackService.HOST+"zeus/businessinfo_update";//保存或更新
	public static final String GET_DEL=RedPackService.HOST+"zeus/businessinfo_del";//删除
	public static final String GET_ALL=RedPackService.HOST+"zeus/get_businessinfo_all";//获取红包活动列表

	/**
	 * 主键
	 * @return
	 */
	private String id;
	/**
	 *  公司名称
	 * @return
	 */
	private String company;
	/**
	 * 公司简称
	 * @return
	 */
	private String abbreviation;
	/**
	 * 联系人
	 * @return
	 */
	private String contacts;
	/**
	 * Telephone
	 * @return
	 */
	private String telephone;
	/**
	 * 地址
	 * @return
	 */
	private String address;
	/**
	 * 公司简介
	 * @return
	 */
	private String introduction;
	/**
	 * 更新时间
	 * @return
	 */
	private String updateTime;
	/**
	 * 状态
	 * @return
	 */
	private int status;
	/**
	 * 可用余额
	 * @return
	 */
	private double availableMoney;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public double getAvailableMoney() {
		return new BigDecimal(availableMoney/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public void setAvailableMoney(int availableMoney) {
		this.availableMoney = availableMoney;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<BusinessInfoBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue) {
			HttpClient client=new HttpClient(GET_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			String r=null;
			Rpage<BusinessInfoBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new BusinessInfoBean().jackson2page(r);
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
	public static BusinessInfoBean getObject(String id) {
		HttpClient client=new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		BusinessInfoBean model=null;
		try {
			r = client.sendMsg(params);
			model=new BusinessInfoBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	@SuppressWarnings("rawtypes")
	public static boolean updateOrSave(Map<String, String[]> map) {
		HttpClient client=new HttpClient(GET_UPDATE);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			params.add(new BasicNameValuePair(k, v));
		}
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new BusinessInfoBean().getReturnCode(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(returnCode==0){//定义0为成功
			return true;
		}
		return false;
	}
	public static boolean del(String id) {
		HttpClient client=new HttpClient(GET_DEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new BusinessInfoBean().getReturnCode(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(returnCode==0){//定义0为成功
			return true;
		}
		return false;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static List<BusinessInfoBean> getall() {
			HttpClient client=new HttpClient(GET_ALL);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			String r=null;
			List<BusinessInfoBean> list=null;
			try {
				r = client.sendMsg(params);
				list=new BusinessInfoBean().jackson2List(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
	}
}
