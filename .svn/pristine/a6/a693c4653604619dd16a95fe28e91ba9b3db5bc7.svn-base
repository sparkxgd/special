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
* 文 件 名 :com.zhixingbus.server.model.BusinessFundsModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年8月19日 09:39:18
*<br>
* 描   述：商家资金信息
*<br>
* 版 本 号：v1.0
 */
public class BusinessFundsBean extends Data<BusinessFundsBean> {
	public static final String GET_LIST=RedPackService.HOST+"zeus/get_businessfunds_list";//获取列表
	public static final String GET_MODEL=RedPackService.HOST+"zeus/get_businessfunds_model";//获取详情
	public static final String GET_UPDATE=RedPackService.HOST+"zeus/businessfunds_update";//保存或更新
	public static final String GET_DEL=RedPackService.HOST+"zeus/businessfunds_del";//删除
	public static final String GET_ALL=RedPackService.HOST+"zeus/get_businessfunds_all";//获取所有列表
	/**
	 * 主键
	 * @return
	 */
	private String id;
	/**
	 * 商家id
	 * @return
	 */
	private String businessId;
	/**
	 * 可用余额
	 * @return
	 */
	private double availableMoney;
	/**
	 * 更新时间
	 * @return
	 */
	private String updateTime;
	/**
	 *  公司名称
	 * @return
	 */
	private String company;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public double getAvailableMoney() {
		return new BigDecimal(availableMoney/100).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	public void setAvailableMoney(int availableMoney) {
		this.availableMoney = availableMoney;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<BusinessFundsBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue) {
			HttpClient client=new HttpClient(GET_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			String r=null;
			Rpage<BusinessFundsBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new BusinessFundsBean().jackson2page(r);
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
	public static BusinessFundsBean getObject(String id) {
		HttpClient client=new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		BusinessFundsBean model=null;
		try {
			r = client.sendMsg(params);
			model=new BusinessFundsBean().jackson2Object(r);
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
			returnCode=new BusinessFundsBean().getReturnCode(r);
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
			returnCode=new BusinessFundsBean().getReturnCode(r);
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
	public static List<BusinessFundsBean> getall() {
			HttpClient client=new HttpClient(GET_ALL);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			String r=null;
			List<BusinessFundsBean> list=null;
			try {
				r = client.sendMsg(params);
				list=new BusinessFundsBean().jackson2List(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
	}
}
