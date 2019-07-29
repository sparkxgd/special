package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.http.HttpUtil;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;

/**
 * 文 件 名 :com.zhixingbus.server.model.BusinessFundsLogModel.java <br>
 * 创 建 人：xiao <br>
 * 日 期：2015年6月3日下午4:53:52 <br>
 * 修 改 人：xiao <br>
 * 日 期：2015年8月19日 10:50:26 <br>
 * 描 述：商家资金管理日志信息 <br>
 * 版 本 号：v1.0
 */
public class BusinessFundsLogBean extends Data<BusinessFundsLogBean> {
	public static final String GET_LIST = RedPackService.HOST + "zeus/get_businessfundslog_list";// 获取列表
	public static final String GET_MODEL = RedPackService.HOST + "zeus/get_businessfundslog_model";// 获取详情
	public static final String GET_UPDATE = RedPackService.HOST + "zeus/businessfundslog_update";// 保存或更新
	public static final String GET_DEL = RedPackService.HOST + "zeus/businessfundslog_del";// 删除
	public static final String GET_ALL = RedPackService.HOST + "zeus/get_businessfundslog_all";// 获取所有列表
	public static final String GET_BY_FATHERID_LIST = RedPackService.HOST + "zeus/get_businessfundslog_fatherid_list";// 获取所有列表

	public static final int inMomey = 0;// 0：入账，1：出账
	public static final int outMomey = 1;// 0：入账，1：出账

	/**
	 * 主键
	 * 
	 * @return
	 */
	private String id;
	/**
	 * 资金管理id
	 * 
	 * @return
	 */
	private String businessFundsId;
	/**
	 * 消费类型，0：入账，1：出账
	 * 
	 * @return
	 */
	private int consumptionType;
	/**
	 * 变化金额
	 * 
	 * @return
	 */
	private double changeMoney;
	/**
	 * 更新时间
	 * 
	 * @return
	 */
	private String updateTime;
	/**
	 * 说明
	 * 
	 * @return
	 */
	private String remark;
	/**
	 * 公司名称
	 * 
	 * @return
	 */
	private String company;
	/**
	 * 可用余额
	 * 
	 * @return
	 */
	private double availableMoney;

	public double getAvailableMoney() {
		return new BigDecimal(availableMoney/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public void setAvailableMoney(int availableMoney) {
		this.availableMoney = availableMoney;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessFundsId() {
		return businessFundsId;
	}

	public void setBusinessFundsId(String businessFundsId) {
		this.businessFundsId = businessFundsId;
	}

	public int getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(int consumptionType) {
		this.consumptionType = consumptionType;
	}

	public double getChangeMoney() {
		return new BigDecimal(changeMoney/100).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	public void setChangeMoney(int changeMoney) {
		this.changeMoney = changeMoney;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public static Rpage<BusinessFundsLogBean> getlist(int pageNumber, int pageSize, String searchKey, String searchValue) {
		HttpClient client = new HttpClient(GET_LIST);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		params.add(new BasicNameValuePair("searchKey", searchKey));
		params.add(new BasicNameValuePair("searchValue", searchValue));
		String r = null;
		Rpage<BusinessFundsLogBean> page = null;
		try {
			r = client.sendMsg(params);
			page = new BusinessFundsLogBean().jackson2page(r);
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
	public static Rpage<BusinessFundsLogBean> getlist(int pageNumber, int pageSize, String fatherId) {
		HttpClient client = new HttpClient(GET_BY_FATHERID_LIST);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		params.add(new BasicNameValuePair("fatherId", fatherId));
		String r = null;
		Rpage<BusinessFundsLogBean> page = null;
		try {
			r = client.sendMsg(params);
			page = new BusinessFundsLogBean().jackson2page(r);
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
	public static BusinessFundsLogBean getObject(String id) {
		HttpClient client = new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r = null;
		BusinessFundsLogBean model = null;
		try {
			r = client.sendMsg(params);
			model = new BusinessFundsLogBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	@SuppressWarnings("rawtypes")
	public static boolean updateOrSave(Map<String, String[]> map) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			params.add(new BasicNameValuePair(k, v));
		}
		String r = null;
		int returnCode = -1;
		r = HttpUtil.httpPostEntity(GET_UPDATE, params);
		returnCode = new BusinessFundsLogBean().getReturnCode(r);
		if (returnCode == 0) {// 定义0为成功
			return true;
		}
		return false;
	}

	public static boolean del(String id) {
		HttpClient client = new HttpClient(GET_DEL);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r = null;
		int returnCode = -1;
		try {
			r = client.sendMsg(params);
			returnCode = new BusinessFundsLogBean().getReturnCode(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (returnCode == 0) {// 定义0为成功
			return true;
		}
		return false;
	}

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static List<BusinessFundsLogBean> getall() {
		HttpClient client = new HttpClient(GET_ALL);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		String r = null;
		List<BusinessFundsLogBean> list = null;
		try {
			r = client.sendMsg(params);
			list = new BusinessFundsLogBean().jackson2List(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
