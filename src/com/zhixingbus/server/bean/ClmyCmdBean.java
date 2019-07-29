package com.zhixingbus.server.bean;

import com.zhixingbus.server.utils.json.Data;

public class ClmyCmdBean extends Data<ClmyCmdBean> {
	private String id;
	private String submitIp;
	private String receiveId;
	private String cityId;
	private String delayTime;
	private int orderNo;
	private int sendtime;
	private int repeat;
	private int status;
	private int disMin;
	private int disGap;
	private double beanMin;
	private double beanMax;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubmitIp() {
		return submitIp;
	}

	public void setSubmitIp(String submitIp) {
		this.submitIp = submitIp;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getSendtime() {
		return sendtime;
	}

	public void setSendtime(int sendtime) {
		this.sendtime = sendtime;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getBeanMin() {
		return beanMin;
	}

	public void setBeanMin(double beanMin) {
		this.beanMin = beanMin;
	}

	public double getBeanMax() {
		return beanMax;
	}

	public void setBeanMax(double beanMax) {
		this.beanMax = beanMax;
	}

	public int getDisMin() {
		return disMin;
	}

	public void setDisMin(int disMin) {
		this.disMin = disMin;
	}

	public int getDisGap() {
		return disGap;
	}

	public void setDisGap(int disGap) {
		this.disGap = disGap;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}
}
