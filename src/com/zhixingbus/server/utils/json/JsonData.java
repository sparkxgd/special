package com.zhixingbus.server.utils.json;




public class JsonData{
	private int status;
	private ApiException exception;
	private String message;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ApiException getException() {
		return exception;
	}
	public void setException(ApiException exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 检查收到的数据是否有效
	 * @param jsonString
	 * @return
	 */
	protected boolean verificationData(String jsonString){
//		if(StringUtil.isBlankOrEmpty(jsonString))
//		{
//			return false;
//		}else if(jsonString.contains("<html><head>")){
//			return false;
//		}
		return true;
	}
}
