package com.zhixingbus.server.json;


public class JsonModel {
	
	private int status = 0;
	private ApiException exception;
	private Object data;
	private String message;
	
	public JsonModel() {
	}
	
	public JsonModel(String msg) {
		this.message = msg;
	}
	
	public JsonModel(Object data) {
		this.data = data;
	}
	
	public JsonModel(Object data,String msg) {
		this.data = data;
		this.message = msg;
	}
	
	public JsonModel(ApiException apiException) {
		this.exception = apiException;
		if(apiException!=null){
			status = apiException.getId();
		}
	}
	
	public JsonModel(ApiException apiException,String msg) {
		this.exception = apiException;
		this.message = msg;
		if(apiException!=null){
			status = apiException.getId();
		}
	}
	
	public int status() {
		return status;
	}
	public JsonModel status(int status) {
		this.status = status;
		return this;
	}
	public ApiException exception() {
		return exception;
	}
	public JsonModel exception(ApiException exception) {
		this.exception = exception;
		if(exception !=null ){
			status = exception.getId();
		}
		return this;
	}
	public Object data() {
		return data;
	}
	public JsonModel data(Object data) {
		this.data = data;
		return this;
	}
	
	public String message() {
		return message;
	}

	public JsonModel message(String message) {
		this.message = message;
		return this;
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static JsonModel forError(String text){
		return new JsonModel(new ApiException(text));
	}
	
	

}
