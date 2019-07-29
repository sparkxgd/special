package com.zhixingbus.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhixingbus.server.bean.apollo.RoadDataBean;
import com.zhixingbus.server.bean.poseidon.AreaCityBean;
import com.zhixingbus.server.json.JsonModel;
import com.zhixingbus.server.service.ApolloService;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.http.HttpUtil;

/**
 * 云上公交
 * 微信，网页版实时公交
 * @author xiao
 *
 */
public class CloudBusController extends BaseController {
	public static JSONArray CITY_LIST=new JSONArray();
	public void index() {
		renderText("感谢你使用贵州智行公交实时公交！");
	}
	/**
	 * 查询路线信息列表
	 */
	public void searchBusRoadList() {
		String cityId=getPara("cityId");
		setAttr("cityId", cityId);
		String r =AreaCityBean.getAllcity();
		setAttr("city", r);
		render("files/weixinzhixingbus/search_bus_road2.html");	
	}
	/**
	 * 查询路线信息列表
	 */
	public void searchBusRoadLists() {
		String cityId=getPara("cityId");
		setAttr("cityId", cityId);
		String r =AreaCityBean.getAllcity();
		setAttr("city", r);
		render("files/weixinzhixingbus/search_bus_road2.html");	
	}
	/**
	 * 查询路线信息
	 */
	public void busRoad() {
		String cityId=getPara("cityId");
		String roadId=getPara("roadId");
		int direction=getParaToInt("direction",0);
		String cityName = getPara("cityName");
		String userId = getPara("userId","admin");
		if(StringUtil.isBlankOrEmpty(roadId)){
			
		}
		String road=RoadDataBean.lineSearchs(roadId,cityId,cityName,userId);
		setAttr("direction", direction);
		setAttr("data", road);
		render("files/weixinzhixingbus/bus_road_map2.html");	
	}
	/**
	 * 根据id查询路线信息
	 * xiao
	 * 2015年10月9日 09:42:35
	 */
	public void lineSearch(){
		ApolloService.lineSearch(this);
	}
	/**
	 * 根据citycode查询智行公交的城市信息
	 * xiao
	 * 2015年10月10日 14:48:11
	 */
	public void citySearch(){
		String citycode=getPara("citycode","");
		List<AreaCityBean> list= AreaCityBean.queryOpenCity();
		AreaCityBean citybean=new AreaCityBean();
		for(AreaCityBean city:list){
			if(citycode.contains(city.getAdcode())){
				citybean=city;
				break;
			}
		}
		renderJson(citybean);
	}
	/**
	 * 路线列表
	 */
	public void lineListSearch(){
		ApolloService.lineListSearch(this);
	}
	/**
	 * 公交车信息
	 */
	public void busSearch(){
		ApolloService.busSearch(this);
	}
	/**
	 * 获取城市列表
	 */
	public void getAllcity(){
		String r =AreaCityBean.getAllcity();
		renderJson(r);
	}
	public void queryScheme(){
		render("files/weixinzhixingbus/query_scheme.html");	
	}
	/**
	 * 查询乘车方案
	 */
	public void query_bus_scheme() {
		ApolloService.query_bus_scheme(this);
	}
	/**
	 * 查询乘车方案
	 */
	public void query_bus_scheme_by_adcode() {
		String adcode = getPara("adcode","5201");
		String cityId=getCityId(adcode);
		ApolloService.query_bus_scheme_by_adcode(this,cityId);
	}
	/**
	 * 公交车信息
	 */
	public void queryBusStationRT(){
		String adcode = getPara("adcode","5201");
		String cityId=getCityId(adcode);
		ApolloService.queryBusStationRT(this,cityId);
	}
	/**
	 * 路线列表
	 */
	public void queryRoadLineList(){
		String adcode = getPara("adcode","5201");
		String cityId=getCityId(adcode);
		ApolloService.queryRoadLineList(this,cityId);
	}
	/**
	 * 凯里公交首页_测试
	 */
	public void index_kaili_test() {
		render("files/cloud/kaili/index_kaili_test.html");	
	}
	/**
	 * 凯里公交首页
	 */
	public void index_kaili() {
		render("files/cloud/kaili/index_kaili.html");	
	}
	/**
	 * 获取远程ip 和
	 * 端口
	 */
	public void query_ip_port(){
		String ip=getRequest().getRemoteAddr();
		int port=getRequest().getRemotePort();
		if(ip.contains("192.168")){
			String r=HttpUtil.httpGetEntity("http://pv.sohu.com/cityjson?ie=utf-8");
			String j=r.substring(r.indexOf('=')+1, r.length()-1);
			JSONObject jb=new JSONObject(j);
			ip=jb.get("cip").toString();
		}
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("ip", ip);
		m.put("port", port);
		renderJson(new JsonModel().data(m));
	}
	/**
	 * 查询路线信息
	 */
	public void queryRoadInfo() {
		String roadId=getPara("roadId");
		String adcode = getPara("adcode","5201");
		String cityId=getCityId(adcode);
		String userId = getPara("userId","kaili_web");
		String road=RoadDataBean.lineSearchs(roadId,cityId,"",userId);//System.out.println(road);
		renderJson(road);	
	}
	private String getCityId(String adcode){
		if(CITY_LIST==null||CITY_LIST.length()<1){
			CITY_LIST=AreaCityBean.getListCity();
		}
		String code=adcode.substring(0, 4);
		String cityId="279";
		for(int i=0;i<CITY_LIST.length();i++){
			JSONObject o=CITY_LIST.getJSONObject(i);
			if(o.get("adcode").equals(code)){
				cityId=o.get("id").toString();
				break;
			}
		}
		return cityId;
	}
	
}
