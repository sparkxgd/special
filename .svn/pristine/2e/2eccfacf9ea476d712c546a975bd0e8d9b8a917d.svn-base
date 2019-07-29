package com.zhixingbus.server.controller;

import com.zhixingbus.server.utils.Utils;


public class CommonController extends BaseController {
	String key="zhixingbus";
	public void index1() {
		String ss="<li><a class='page-scroll' href='#contact' >意见/建议</a></li>";
		setAttr("ss", ss);
		render("external/www/index.html");	
	}
	public void index() {
		render("index.html");	
	}
	public void storage() {
		String value=Utils.createNewUUID();
		setAttr("value", value);
		setCookie(key, value, 3600);
		render("files/map.html");	
	}
	
	public void getdata() {
		setAttr("value", getCookie(key));
		render("files/map0.html");	
	}
	/**
	 * 查询路线信息列表
	 */
	public void searchBusRoadList() {
		String cityId=getPara("cityId");
		setAttr("cityId", cityId);
		render("files/weixinzhixingbus/search_bus_road.html");	
	}
	/**
	 * 查询路线信息
	 */
	public void busRoad() {
		String cityId=getPara("cityId");
		String roadId=getPara("roadId");
		int direction=getParaToInt("direction",0);
		setAttr("roadId", roadId);
		setAttr("cityId", cityId);
		setAttr("direction", direction);
		render("files/weixinzhixingbus/bus_road_map.html");	
	}
}
