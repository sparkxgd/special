package com.zhixingbus.server.utils.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Data<T> extends JsonData{
	private ObjectMapper objectMapper;
	private ML<T> data;

	public Data() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public ML<T> getData() {
		return data;
	}
	
	public void setData(ML<T> data) {
		this.data = data;
	}
	/**
	 * json数据转化为对象
	 * @author xiao
	 * @date 2015年8月14日 11:36:49
	 *
	 * @param jsonString
	 * @param destClass
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public  Data<T> jackson2ServiceJson(String jsonString) {
		try {
			if(!verificationData(jsonString))
			{
				return null;
			}
			return objectMapper.readValue(jsonString, getClass());
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * json数据转化为对象
	 * @author xiao
	 * @date 2015年8月14日 11:36:49
	 *
	 * @param jsonString
	 * @param destClass
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public  T jackson2Object(String jsonString) {
		try {
			if(!verificationData(jsonString))
			{
				return null;
			}
			return (T) objectMapper.readValue(jsonString, getClass()).getData().getrModel();
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * json数据转化为对象数组
	 * @author xiao
	 * @date 2015年8月14日 11:36:44
	 * @param jsonString
	 * @param destClass
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public  List<T> jackson2List(String jsonString) {
		try {
			if(!verificationData(jsonString))
			{
				return new ArrayList<T>();
			}
			ML<T> m=objectMapper.readValue(jsonString, getClass()).getData();
			if(m==null)
			{
				return new ArrayList<T>();
			}
			return m.getrList();
		}  catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<T>();
		}
	}
	/**
	 * json数据转化为对象数组
	 * @author xiao
	 * @date 2015年8月14日 11:36:44
	 * @param jsonString
	 * @param destClass
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public Rpage<T> jackson2page(String jsonString) {
		try {
			if(!verificationData(jsonString))
			{
				return new Rpage<T>();
			}
			ML<T> ml=objectMapper.readValue(jsonString, getClass()).getData();
			if(ml==null)
			{
				return new Rpage<T>();
			}
			return ml.getrPage();
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * json数据转化为对象数组
	 * @author xiao
	 * @date 2015年8月14日 11:36:44
	 * @param jsonString
	 * @param destClass
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public  int getReturnCode(String jsonString) {
		try {
			if(!verificationData(jsonString))
			{
				return -1;
			}
			ML<T> ml=objectMapper.readValue(jsonString, getClass()).getData();
			if(ml==null)
			{
				return -1;
			}
			return ml.getReturnCode();
		}  catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
