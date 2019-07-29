package com.zhixingbus.server.service;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhixingbus.server.bean.apollo.BusinfoDataBean;
import com.zhixingbus.server.bean.apollo.RoadDataBean;
import com.zhixingbus.server.bean.apollo.RoadListBean;
import com.zhixingbus.server.config.ServiceConfig;
import com.zhixingbus.server.controller.BaseController;
import com.zhixingbus.server.utils.StringUtil;



/**
 * 
 * 文 件 名 :com.zhixingbus.server.controller.weixin.ApolloService.java <br>
 * 创 建 人：xiao <br>
 * 日 期：2015年10月9日 09:45:04 <br>
 * 修 改 人：xiao <br>
 * 日 期：2015年10月9日 09:45:08 <br>
 * 描 述：apollo交互信息处理
 * 版 本 号：v1.0
 */
public class ApolloService {
	public static final String HOST = "http://" + ServiceConfig.apolloServiceHost + "/";
	public static final String app_token="007";
	/**
	 *  根据id查询路线信息
	 * @param c
	 */
	public static void lineSearch(BaseController c){
		String roadId = c.getPara("roadId");
		
		String cityName = c.getPara("cityName");
		String cityId = c.getPara("cityId");
		
		String userId = c.getPara("userId");
		if(StringUtil.isBlankOrEmpty(roadId)){
			
		}
		String road=RoadDataBean.lineSearchs(roadId,cityId,cityName,userId);
		c.renderJson(road);
	}
	/**
	 *  查询路线信息列表
	 * @param c
	 */
	public static void lineListSearch(BaseController c){
		String keyword = c.getPara("keyword");
		String cityId = c.getPara("cityId");
 		String roads=RoadListBean.lineListSearchs(cityId, keyword);
		c.renderJson(roads);
	}
	/**
	 * 公交车查询
	 * @param c
	 */
	public static void busSearch(BaseController c){
		String roadId = c.getPara("roadId");
		String cityId = c.getPara("cityId");
		String direction = c.getPara("direction");
		String userId = c.getPara("userId","");
		String stationId = c.getPara("stationId","");
		String bus=BusinfoDataBean.busSearchs(cityId,roadId, direction,stationId,userId);
		c.renderJson(bus);
	}
	/**
	 * 公交车查询
	 * @param c
	 */
	public static void queryBusStationRT(BaseController c,String cityId){
		String roadId = c.getPara("roadId");
		String direction = c.getPara("direction");
		String userId = c.getPara("userId","");
		String stationId = c.getPara("stationId","");
		String bus=BusinfoDataBean.busSearchs(cityId,roadId, direction,stationId,userId);
		c.renderJson(bus);
	}
	/**
	 * 查询乘车方案
	 */
	public static void query_bus_scheme(BaseController c) {
		// 源点经纬度
		String srcLatitudeStr = c.getPara("srcLatitude");
		String srcLongitudeStr = c.getPara("srcLongitude");
		// 目的地的经纬度
		String desLatitudeStr = c.getPara("desLatitude");
		String desLongitudeStr = c.getPara("desLongitude");
		String cityIdStr = c.getPara("cityId");
		String r =RoadDataBean.query_bus_scheme(srcLatitudeStr, srcLongitudeStr, desLatitudeStr, desLongitudeStr,cityIdStr);
		c.renderJson(r);
	}
	/**
	 * 查询乘车方案
	 */
	public static void query_bus_scheme_by_adcode(BaseController c ,String cityId) {
		// 源点经纬度
		String srcLatitudeStr = c.getPara("srcLatitude");
		String srcLongitudeStr = c.getPara("srcLongitude");
		// 目的地的经纬度
		String desLatitudeStr = c.getPara("desLatitude");
		String desLongitudeStr = c.getPara("desLongitude");
	
		String r =RoadDataBean.query_bus_scheme(srcLatitudeStr, srcLongitudeStr, desLatitudeStr, desLongitudeStr,cityId);//System.out.println(r);
		c.renderJson(r);
	}
	/**
	 *  查询路线信息列表
	 * @param c
	 */
	public static void queryRoadLineList(BaseController c,String cityId){
		String keyword = c.getPara("term");
 		String roads=RoadListBean.lineListSearchs(cityId, keyword);
		c.renderJson(roads);
	}
}
