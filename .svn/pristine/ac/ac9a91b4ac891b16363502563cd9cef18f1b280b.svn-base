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
import com.zhixingbus.server.service.constants.ActivityType;
import com.zhixingbus.server.service.constants.RedpackActivityStatus;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;
/**
* 文 件 名 :com.zhixingbus.server.model.XRedPackActivityModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年7月1日 16:28:51
*<br>
* 描   述：红包活动
*<br>
* 版 本 号：v1.0
 */
public class BusinessRedPackActivityBean extends Data<BusinessRedPackActivityBean>{
	public static final String GET_ACTIVITY_LIST=RedPackService.HOST+"zeus/get_business_activity_list";//获取红包活动列表
	public static final String GET_ACTIVITY_MODEL=RedPackService.HOST+"zeus/get_business_activity_model";//获取红包活动列表
	public static final String GET_UPDATE=RedPackService.HOST+"zeus/business_activity_update";//保存或更新
	public static final String GET_UPDATE_STATUS=RedPackService.HOST+"zeus/business_activity_update_status";//更新状态

	private String id;
	private String title;
	private String content;
	private String userId;
	private String createTime;
	private String startTime;
	private String overdueTime;
	private String winningWord;
	private double totalAmount;
	private double frozenAmount;
	private int redPackNum;
	private String longitude;
	private String latitude;
	private String cityId;
	private int type;
	private String payOrderId;
	private String friendId;
	private String updateTime;
	private String orderNo;
	private String remark;
	private int sendRedpackNum;
	private String company;
	private String abbreviation;
	private String promotionTitle;
	
	public String getPromotionTitle() {
		return promotionTitle;
	}
	public void setPromotionTitle(String promotionTitle) {
		this.promotionTitle = promotionTitle;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public String getStatusName() {
		return RedpackActivityStatus.getStatusDesc(getStatus());
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(String overdueTime) {
		this.overdueTime = overdueTime;
	}

	public String getWinningWord() {
		return winningWord;
	}
	public void setWinningWord(String winningWord) {
		this.winningWord = winningWord;
	}
	public double getTotalAmount() {
		return new BigDecimal(totalAmount/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getFrozenAmount() {
		return new BigDecimal(frozenAmount/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	public void setFrozenAmount(int frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public int getRedPackNum() {
		return redPackNum;
	}
	public void setRedPackNum(int redPackNum) {
		this.redPackNum = redPackNum;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getType() {
		return ActivityType.getTypeDesc(type);
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSendRedpackNum() {
		return sendRedpackNum;
	}
	public void setSendRedpackNum(int sendRedpackNum) {
		this.sendRedpackNum = sendRedpackNum;
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
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<BusinessRedPackActivityBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue,String activitTtype) {
			HttpClient client=new HttpClient(GET_ACTIVITY_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			params.add(new BasicNameValuePair("activitTtype", activitTtype));
			String r=null;
			Rpage<BusinessRedPackActivityBean> page=new Rpage<BusinessRedPackActivityBean>();
			try {
				r = client.sendMsg(params);
				page=new BusinessRedPackActivityBean().jackson2page(r);
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
	public static BusinessRedPackActivityBean getObject(String id) {
		HttpClient client=new HttpClient(GET_ACTIVITY_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		BusinessRedPackActivityBean model=null;
		try {
			r = client.sendMsg(params);
			model=new BusinessRedPackActivityBean().jackson2Object(r);
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
			returnCode=new BusinessRedPackActivityBean().getReturnCode(r);
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
		HttpClient client=new HttpClient(GET_UPDATE_STATUS);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new BusinessRedPackActivityBean().getReturnCode(r);
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
	@SuppressWarnings("rawtypes")
	public static boolean updateStatus(Map<String, String[]> map) {
		HttpClient client=new HttpClient(GET_UPDATE_STATUS);
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
			returnCode=new BusinessRedPackActivityBean().getReturnCode(r);
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
}
