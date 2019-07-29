package com.zhixingbus.server.bean.poseidon;

public class UserSuggestBean {
	private String id;
	//private String showTime;
	//private String standardTime;
	private String updateTime;
	private String roomId;
	private int orderNo;
	private Integer cityId;
	private Integer clientVer;
	private String clientVerInfo;
	private int sysType;
	private String msg;
	private int msgType;
	private String guideRed;
	private String cityName;
	private int status;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getClientVer() {
		return clientVer;
	}

	public void setClientVer(Integer clientVer) {
		this.clientVer = clientVer;
	}

	public String getClientVerInfo() {
		return clientVerInfo;
	}

	public void setClientVerInfo(String clientVerInfo) {
		this.clientVerInfo = clientVerInfo;
	}

	public int getSysType() {
		return sysType;
	}

	public void setSysType(int sysType) {
		this.sysType = sysType;
	}

	public String getGuideRed() {
		return guideRed;
	}

	public void setGuideRed(String guideRed) {
		this.guideRed = guideRed;
	}

	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

}
