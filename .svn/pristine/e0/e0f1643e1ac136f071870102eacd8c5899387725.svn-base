package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;
/**
* 文 件 名 :com.zhixingbus.server.model.UserRedPackModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年7月1日 15:59:51
*<br>
* 描   述：用户红包
*<br>
* 版 本 号：v1.0
 */
public class UserRedPackBean extends Data<UserRedPackBean> {
	public static final String GET_LIST=RedPackService.HOST+"zeus/get_redpack_list";//获取红包活动列表
	public static final String GET_MODEL=RedPackService.HOST+"zeus/get_redpack_model";//获取红包活动列表
	public static final String GET_BY_FATHERID_LIST=RedPackService.HOST+"zeus/get_redpack_fatherid_list";//获取所有列表
	public static final String DEL_REDPACK=RedPackService.HOST+"zeus/get_redpack_list";//获取红包活动列表
	private String id;
	private String userid;
	private String nickName;
	private double redPackAmount;
	private String createTime;
	private String createEquipment;
	private String openTime;
	private String openEquipment;
	private String activityId;
	private String overdueTime;
	private String payOrderId;
	private int status;//状态 0:未领取 1：已领取 2：已过期 3：已删除
	private String message;// 留言	拿到钱后，给发红包的人留言
	private String updateTime;
	private String remark;
	private int orderNo;
	private String title;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getRedPackAmount() {
		return new BigDecimal(redPackAmount/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	public void setRedPackAmount(int redPackAmount) {
		this.redPackAmount = redPackAmount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateEquipment() {
		return createEquipment;
	}
	public void setCreateEquipment(String createEquipment) {
		this.createEquipment = createEquipment;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getOpenEquipment() {
		return openEquipment;
	}
	public void setOpenEquipment(String openEquipment) {
		this.openEquipment = openEquipment;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(String overdueTime) {
		this.overdueTime = overdueTime;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 红包删除
	 * 2015年8月6日 14:00:28
	 * 1、找出未打开的
	 * @param redPackid
	 * @param status
	 * @return
	 */
	public static boolean del(String id) {
		HttpClient client=new HttpClient(GET_LIST);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new UserRedPackBean().getReturnCode(r);
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
	public static UserRedPackBean getObject(String id) {
		HttpClient client=new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		UserRedPackBean model=null;
		try {
			r = client.sendMsg(params);
			model=new UserRedPackBean().jackson2Object(r);
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
	public static Rpage<UserRedPackBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue) {
			HttpClient client=new HttpClient(GET_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			String r=null;
			Rpage<UserRedPackBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new UserRedPackBean().jackson2page(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
	/**
	 * 根据活动id，查找红包列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<UserRedPackBean> getlist(int pageNumber,
			int pageSize,String fatherId,int type) {
			HttpClient client=new HttpClient(GET_BY_FATHERID_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("fatherId", fatherId));
			params.add(new BasicNameValuePair("type", String.valueOf(type)));
			String r=null;
			Rpage<UserRedPackBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new UserRedPackBean().jackson2page(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
}
