package com.zhixingbus.server.bean;
/**
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl

 * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent"}

 * {"statusCode":"300", "message":"操作失败"}

 * {"statusCode":"301", "message":"会话超时"}

 * @author xiao
 *
 */
public class DwzResult{
	private String statusCode;
	private String message;
	private String navTabId;
	private String rel;
	private String callbackType;
	private String forwardUrl;
	public DwzResult(){
		
	}
	public DwzResult(String statusCode,String message,String navTabId,String rel,String callbackType,String forwardUrl){
		this.statusCode=statusCode;
		this.message=message;
		this.navTabId=navTabId;
		this.rel=rel;
		this.callbackType=callbackType;
		this.forwardUrl=forwardUrl;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
}
