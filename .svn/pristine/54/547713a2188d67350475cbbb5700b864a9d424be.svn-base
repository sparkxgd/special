package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.service.constants.CounsumptionType;
import com.zhixingbus.server.service.constants.RedpackActivityStatus;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;

/**
 * 文 件 名 :com.zhixingbus.server.model.RedpackActivityLogModel.java <br>
 * 创 建 人：xiao <br>
 * 日 期：2015年6月3日下午4:53:52 <br>
 * 修 改 人：xiao <br>
 * 日 期：2015年8月19日 10:50:26 <br>
 * 描 述：日志信息 <br>
 * 版 本 号：v1.0
 */
public class RedpackActivityLogBean extends Data<RedpackActivityLogBean> {
	public static final String GET_BY_FATHERID_LIST = RedPackService.HOST + "zeus/get_activitylog_fatherid_list";// 获取所有列表
	/**
	 * 主键
	 * @return
	 */
	private String id;
	/**
	 * 红包活动id
	 * @return
	 */
	private String activityId;
	/**
	 * 红包id
	 * @return
	 */
	private String redpackId;
	/**
	 *原始冻结金额
	 * @return
	 */
	private double oldFrozenAmount;
	/**
	 * 原始状态
	 * @return
	 */
	private int oldStatus;
	/**
	 * 更新后状态
	 * @return
	 */
	private int newStatus;
	/**
	 * 消费类型，0：入账，1：出账
	 * @return
	 */
	private int consumptionType;
	/**
	 * 变化金额
	 * @return
	 */
	private double changeMoney;
	/**
	 * 更新时间
	 * @return
	 */
	private String updateTime;
	/**
	 * 说明
	 * @return
	 */
	private String remark;

	
	
	
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getRedpackId() {
		return redpackId;
	}

	public void setRedpackId(String redpackId) {
		this.redpackId = redpackId;
	}

	public double getOldFrozenAmount() {
		return new BigDecimal(oldFrozenAmount/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	public void setOldFrozenAmount(double oldFrozenAmount) {
		this.oldFrozenAmount = oldFrozenAmount;
	}

	public String getOldStatus() {
		return RedpackActivityStatus.getStatusDesc(oldStatus);
	}

	public void setOldStatus(int oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getNewStatus() {
		return RedpackActivityStatus.getStatusDesc(newStatus);
	}

	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}

	public void setChangeMoney(double changeMoney) {
		this.changeMoney = changeMoney;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getConsumptionType() {
		return CounsumptionType.getDesc(consumptionType);
	}

	public void setConsumptionType(int consumptionType) {
		this.consumptionType = consumptionType;
	}

	public double getChangeMoney() {
		return new BigDecimal(changeMoney/100).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();

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
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<RedpackActivityLogBean> getlist(int pageNumber, int pageSize, String fatherId) {
		HttpClient client = new HttpClient(GET_BY_FATHERID_LIST);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		params.add(new BasicNameValuePair("fatherId", fatherId));
		String r = null;
		Rpage<RedpackActivityLogBean> page = null;
		try {
			r = client.sendMsg(params);
			page = new RedpackActivityLogBean().jackson2page(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
}
