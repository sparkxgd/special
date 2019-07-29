package com.zhixingbus.server.utils.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.utils.http.HttpClient;
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
public class TestJsonModel extends Data<TestJsonModel>{
	private String id;
	private String title;
	private String content;
	private String userId;
	private String createTime;
	private String startTime;
	private String overdueTime;
	private int status;
	private String winningWord;
	private BigDecimal totalAmount;
	private BigDecimal frozenAmount;
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

	public TestJsonModel(){
		
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getWinningWord() {
		return winningWord;
	}

	public void setWinningWord(String winningWord) {
		this.winningWord = winningWord;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
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

	public int getType() {
		return type;
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
	
	public static void main(String[] args) {
//		HttpClient client=new HttpClient("http://192.168.1.41:8081/zeus/get_activity_list");
		HttpClient client=new HttpClient("http://192.168.1.41:8081/zeus/get_activity_model");
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", "eb9c07c9e88946c498998626dcd27591"));
		params.add(new BasicNameValuePair("pageNumber", "1"));
		params.add(new BasicNameValuePair("pageSize",  "10"));
		params.add(new BasicNameValuePair("searchKey", "ss"));
		String r=null;
//		Rpage<XRedPackActivityModel> model=null;
		try {
			r = client.sendMsg(params);
//			model=new Data<XRedPackActivityModel>().jackson2page(r);
			TestJsonModel model=new Data<TestJsonModel>().jackson2Object(r);
			System.out.println(model);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
