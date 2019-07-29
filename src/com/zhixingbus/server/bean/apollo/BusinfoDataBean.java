package com.zhixingbus.server.bean.apollo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhixingbus.server.service.ApolloService;
import com.zhixingbus.server.utils.JSONUtil;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.http.HttpClient;

public class BusinfoDataBean {
	private BusInfoBean attrs;
	
	public BusInfoBean getAttrs() {
		return attrs;
	}
	public void setAttrs(BusInfoBean attrs) {
		this.attrs = attrs;
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static List<BusInfoBean> busSearch(String cityId,String roadId,String direction,String stationId,String userId) {
		String url=ApolloService.HOST+cityId+"/api/query_bus_pos";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roadId", roadId);
		map.put("direction", direction);
		JSONObject jso=new JSONObject(map);
		JSONArray jsa=new JSONArray();
		jsa.put(jso);
		params.add(new BasicNameValuePair("json",jsa.toString()));
		params.add(new BasicNameValuePair("cityId",cityId));
		params.add(new BasicNameValuePair("stationId",stationId));
		params.add(new BasicNameValuePair("userId",userId));
		String r = null;
		List<BusInfoBean> list = new ArrayList<BusInfoBean>();
		try {
			r = client.sendMsg(params);
			list=jsckson2List(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	private static List<BusInfoBean> jsckson2List(String r) throws JSONException{
		List<BusInfoBean> list = new ArrayList<BusInfoBean>();
		if(StringUtil.isBlankOrEmpty(r)){
			return list;
		}
		JSONArray rjson=new JSONArray(r);
		JSONObject data=rjson.getJSONObject(0);
		JSONArray busPos=null;
		try {
			busPos = data.getJSONArray("busPos");
		} catch (Exception e) {
			return list;
		}
		list=JSONUtil.jackson2List(busPos.toString(),List.class,BusinfoDataBean.class);
		return list;
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static String busSearchs(String cityId,String roadId,String direction,String stationId,String userId) {
		String url=ApolloService.HOST+cityId+"/api/query_bus_pos";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roadId", roadId);
		map.put("direction", direction);
		map.put("check", 1);
		JSONObject jso=new JSONObject(map);
		JSONArray jsa=new JSONArray();
		jsa.put(jso);
		params.add(new BasicNameValuePair("json",jsa.toString()));
		params.add(new BasicNameValuePair("cityId",cityId));
		params.add(new BasicNameValuePair("stationId",stationId));
		params.add(new BasicNameValuePair("userId",userId));
//		params.add(new BasicNameValuePair("type","1"));
		String r = "";
		try {
			r = client.sendMsg(params);
			if(StringUtil.isBlankOrEmpty(r)){
				return r;
			}else if(r.contains("<html><head><title>")){
				return r;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
}
