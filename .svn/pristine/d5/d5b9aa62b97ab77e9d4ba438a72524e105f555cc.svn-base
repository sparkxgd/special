package com.zhixingbus.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.bean.AreaCityBean;
import com.zhixingbus.server.config.ServiceConfig;
import com.zhixingbus.server.utils.http.HttpClient;

public class AreaCityService {
	public static List<AreaCityBean> query_open_city() {

		HttpClient client = new HttpClient("http://" + ServiceConfig.PoseidonServiceHost + "/zeus/query_open_city");
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
}
