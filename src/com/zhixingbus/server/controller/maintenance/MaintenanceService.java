package com.zhixingbus.server.controller.maintenance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.apollo.server.model.BusModel;
import com.apollo.server.model.BusPositionModel;
import com.apollo.server.redis.RedisKit;
import com.zhixingbus.server.config.ServiceConfig;
import com.zhixingbus.server.controller.BaseController;
import com.zhixingbus.server.json.JsonModel;
import com.zhixingbus.server.utils.ConstUtils;
import com.zhixingbus.server.utils.http.HttpUtil;

public class MaintenanceService {
	public static Map<Integer, Integer>  noticeMap = new HashMap<Integer, Integer>();

	public static void queryRoadBuses(BaseController c) {
		int cityId = c.getParaToInt("cityId", 0);
		int roadId = c.getParaToInt("roadId", 0);
		if (cityId == 0 || roadId == 0) {
			c.renderJson(new JsonModel("arguments error!"));
			return;
		}
		long now = new Date(System.currentTimeMillis() - 3 * 60 * 1000).getTime();// 比当前时间小3分钟
		List<BusPositionModel> list0 = RedisKit.get(cityId + "roadBusPosition", roadId + ":" + 0);
		List<BusPositionModel> list1 = RedisKit.get(cityId + "roadBusPosition", roadId + ":" + 1);
		List<BusPositionModel> list = new ArrayList<BusPositionModel>();
		list.addAll(list0);
		list.addAll(list1);
		if (list != null) {
			for (BusPositionModel busPositionModel : list) {
				if (busPositionModel.getupdateTime().getTime() <= now) {
					continue;
				}
				// if (busPositionModel.getStatus() != 0) {
				// continue;
				// }
				String busId = busPositionModel.getBusId();
				BusModel bus = RedisKit.get(cityId + "bus", busId);
				if (bus != null) {
					System.out.println(bus.getBusno() + ":" + busPositionModel.getupdateTime());
				}
			}
		}
		c.renderText(list0.size() + list1.size() + "");
		System.out.println(list0.size() + list1.size());
	}

	public static void checkRoadGps(BaseController c) {
		String getOpenCityUrl = "http://betaposeidon.zhixingbus.com/zeus/query_open_city";
		String noticeUrl = "http://betaposeidon.zhixingbus.com/zeus/send_phone_msg?phones=" + ServiceConfig.phones;
		HttpResponse response = null;
		try {
			response = HttpUtil.httpGet(getOpenCityUrl);
			JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
			JSONObject data = json.getJSONObject("data");
			JSONArray rList = data.getJSONArray(ConstUtils.R_LIST);
			StringBuffer content = null;
			for (int i = 0; i < rList.length(); i++) {
				JSONObject city = rList.getJSONObject(i);
				int cityId = city.getInt("id");
				String cityName = city.getString("name");
				if (!checkCityRoadGps(cityId, cityName)) {
					//如果该城市的所有线路都没有公交车了
					Integer notice = noticeMap.get(cityId);
					if(notice == null || notice == 0){
						noticeMap.put(cityId, 1);
						if(content == null){
							content = new StringBuffer();
						}
						content.append(cityName);
						content.append("、");
					}
				}else{
					noticeMap.put(cityId, 0);
				}

			}
			
			if(content != null){
				content.append("没有车辆显示，请处理！");
				HttpUtil.httpGet(noticeUrl + "&content=" + content.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean checkCityRoadGps(int cityId, String cityName) {
		boolean hasGps = false;
		try {
			String getCityRoadsUrl = "http://betaapollo.zhixingbus.com/api/query_road_by_city_id/";
			HttpResponse responseRoad = HttpUtil.httpGet(getCityRoadsUrl + cityId + "?app_token=yj123&cityId=" + cityId);
			if (responseRoad.getStatusLine().getStatusCode() == 200) {
				long now = new Date(System.currentTimeMillis() - 5 * 60 * 1000).getTime();// 比当前时间小5分钟
				JSONArray rList = new JSONObject(EntityUtils.toString(responseRoad.getEntity())).getJSONObject("data").getJSONArray(ConstUtils.R_LIST);
				for (int i = 0; i < rList.length(); i++) {
					JSONObject road = rList.getJSONObject(i);
					int roadId = road.getInt("id");
					System.out.println(road.getString("name"));
					List<BusPositionModel> list0 = RedisKit.get(cityId + "roadBusPosition", roadId + ":" + 0);
					List<BusPositionModel> list1 = RedisKit.get(cityId + "roadBusPosition", roadId + ":" + 1);
					List<BusPositionModel> list = new ArrayList<BusPositionModel>();
					if(list0 != null){
						list.addAll(list0);
					}
					if(list1 != null){
						list.addAll(list1);
					}
					
					if (list != null) {
						for (BusPositionModel busPositionModel : list) {
							if (busPositionModel.getupdateTime().getTime() <= now) {
								continue;
							}
							if (busPositionModel.getStatus() != 0) {
								continue;
							}
							String busId = busPositionModel.getBusId();
							BusModel bus = RedisKit.get(cityId + "bus", busId);
							if (bus != null) {
								hasGps = true;
								return hasGps;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasGps;
	}

	public static void main(String[] args) {
		List<BusPositionModel> list = new ArrayList<BusPositionModel>();
		list.addAll(null);
	}
}
