package com.zhixingbus.server.bean.poseidon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zhixingbus.server.service.PoseidonService;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;

public class AreaCityBean extends Data<AreaCityBean> {
	public static final String query_open_city=PoseidonService.HOST+"zeus/query_open_city";//获取所有列表
	public static final String query_all_city=PoseidonService.HOST+"api/query_citys";//获取所有列表

	private String id;
	private String adcode;
	private String citycode;
	private String center;
	private String name;
	private String provinceId;
	private String isShow;
	private String isOpen;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static List<AreaCityBean> queryOpenCity() {
		HttpClient client = new HttpClient(query_open_city);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		String r = null;
		List<AreaCityBean> list = null;
		try {
			r = client.sendMsg(params);
			list = new AreaCityBean().jackson2List(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static String getAllcity() {
		HttpClient client = new HttpClient(query_all_city);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token","007"));
		String r = null;
		try {
			r = client.sendMsg(params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static JSONArray getListCity() {
		HttpClient client = new HttpClient(query_all_city);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token","007"));
		String r = null;
		JSONArray list = new JSONArray();
		try {
			r = client.sendMsg(params);
			if(StringUtil.isBlankOrEmpty(r)||r.contains("<html><head>"))
			{
				return list;
			}
			JSONObject o=new JSONObject(r);
			list=o.getJSONArray("data");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
