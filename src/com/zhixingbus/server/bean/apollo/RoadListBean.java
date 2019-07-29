package com.zhixingbus.server.bean.apollo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.zhixingbus.server.service.ApolloService;
import com.zhixingbus.server.utils.JSONUtil;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.http.HttpClient;

/**
 * 路线信息
 * @author xiao
 *
 */
public class RoadListBean {
	private String endtime;
	private String priceStr;
	private String status;
	private String cityId;
	private String originatingStation;
	private String begintime;
	private String cityName;
	private String terminalStation;
	private String stationCount;
	private String type;
	private String id;
	private String distance;
	private String price;
	private String name;
	private String endtime1;
	private String begintime1;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getPriceStr() {
		return priceStr;
	}
	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getOriginatingStation() {
		return originatingStation;
	}
	public void setOriginatingStation(String originatingStation) {
		this.originatingStation = originatingStation;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTerminalStation() {
		return terminalStation;
	}
	public void setTerminalStation(String terminalStation) {
		this.terminalStation = terminalStation;
	}
	public String getStationCount() {
		return stationCount;
	}
	public void setStationCount(String stationCount) {
		this.stationCount = stationCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEndtime1() {
		return endtime1;
	}
	public void setEndtime1(String endtime1) {
		this.endtime1 = endtime1;
	}
	public String getBegintime1() {
		return begintime1;
	}
	public void setBegintime1(String begintime1) {
		this.begintime1 = begintime1;
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static List<RoadListBean> lineListSearch(String cityId,String keyword) {
		String url=ApolloService.HOST+cityId+"/api/query_roads";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		params.add(new BasicNameValuePair("roadName",keyword));
		String r = null;
		List<RoadListBean> list = new ArrayList<RoadListBean>();
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
	private static List<RoadListBean> jsckson2List(String r) throws JSONException{
		List<RoadListBean> list = new ArrayList<RoadListBean>();
		if(StringUtil.isBlankOrEmpty(r)){
			return list;
		}else if(r.contains("<html><head><title>")){
			return list;
		}
		JSONObject rjson=new JSONObject(r);
		JSONArray data=rjson.getJSONArray("data");
		list=JSONUtil.jackson2List(data.toString(),List.class,RoadListBean.class);
		return list;
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static String lineListSearchs(String cityId,String keyword) {
		String url=ApolloService.HOST+cityId+"/api/query_roads";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		params.add(new BasicNameValuePair("roadName",keyword));
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
