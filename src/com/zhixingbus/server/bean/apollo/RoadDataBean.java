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
import com.zhixingbus.server.utils.http.HttpUtil;

public class RoadDataBean {
	public static final String query_road=ApolloService.HOST+"/286/api/query_road_point_by_id";//获取所有列表
	private RoadBean attrs;

	public RoadBean getAttrs() {
		return attrs;
	}
	public void setAttrs(RoadBean attrs) {
		this.attrs = attrs;
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static List<RoadBean> lineSearch(String id,String cityId,String cityName,String userId) {
		String url=ApolloService.HOST+"/"+cityId+"/api/query_road_point_by_id";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		params.add(new BasicNameValuePair("id",id));
		params.add(new BasicNameValuePair("cityId",cityId));
		params.add(new BasicNameValuePair("cityName",cityName));
		params.add(new BasicNameValuePair("userId",userId));
		String r = null;
		List<RoadBean> list = new ArrayList<RoadBean>();
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
	private static List<RoadBean> jsckson2List(String r) throws JSONException{
		List<RoadBean> list = new ArrayList<RoadBean>();
		if(StringUtil.isBlankOrEmpty(r)){
			return list;
		}
		JSONObject rjson=new JSONObject(r);
		JSONObject data=rjson.getJSONObject("data");
		JSONObject rModel=data.getJSONObject("rModel");
		JSONArray forwardRoadPointList=rModel.getJSONArray("forwardRoadPointList");
		list=JSONUtil.jackson2List(forwardRoadPointList.toString(),List.class,RoadDataBean.class);
		return list;
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static String lineSearchs(String id,String cityId,String cityName,String userId) {
		String url=ApolloService.HOST+"/"+cityId+"/api/query_road_point_by_id";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		params.add(new BasicNameValuePair("id",id));
		params.add(new BasicNameValuePair("cityId",cityId));
		params.add(new BasicNameValuePair("cityName",cityName));
		params.add(new BasicNameValuePair("userId",userId));
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
	
	/**
	 * /**
	 * 根据城市ID和线路ID获取线路信息
	 * @param cityId
	 * @param roadId
	 * @return
	 */
	public static JSONObject getRoadInfoById(int cityId, int roadId) {
		String url = ApolloService.HOST + cityId+"/api/query_road_by_id?app_token=APP_TOKEN&";
		String res = HttpUtil.httpGetEntity(url + "id=" + roadId);
		try {
			JSONObject json = new JSONObject(res);
			return json;
		} catch (Exception e) {
			throw new RuntimeException("http请求返回的数据Json转换异常");
		}
	}
	/**
	 * 根据id查询路线信息
	 * @return
	 */
	public static String query_bus_scheme(String srcLatitudeStr,String srcLongitudeStr,String desLatitudeStr,String desLongitudeStr,String cityIdStr) {
		String url=ApolloService.HOST+cityIdStr+"/api/query_bus_scheme";
		HttpClient client = new HttpClient(url);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("app_token",ApolloService.app_token));
		params.add(new BasicNameValuePair("srcLatitude",srcLatitudeStr));
		params.add(new BasicNameValuePair("srcLongitude",srcLongitudeStr));
		params.add(new BasicNameValuePair("desLatitude",desLatitudeStr));
		params.add(new BasicNameValuePair("desLongitude",desLongitudeStr));
		params.add(new BasicNameValuePair("cityId",cityIdStr));
		String r = "";
		try {
			r = client.sendMsg(params);
			if(StringUtil.isBlankOrEmpty(r)){
				return r;
			}else if(r.contains("<html><head><title>")){
				return "";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
}
