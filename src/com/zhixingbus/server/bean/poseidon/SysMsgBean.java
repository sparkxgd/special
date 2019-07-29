package com.zhixingbus.server.bean.poseidon;

public class SysMsgBean {
	private String adCode;
	private int abortmsg;
	private int page;
	private String url;
	private String startTime;
	private String endTime;
	private int phoneType;
	private int execute;
	private String title;
	private String tag;
	private int sendType;
    private int id;
    
	public int getAbortmsg() {
		return abortmsg;
	}
	public void setAbortmsg(int abortmsg) {
		this.abortmsg = abortmsg;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	public int getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getExecute() {
		return execute;
	}
	public void setExecute(int execute) {
		this.execute = execute;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
