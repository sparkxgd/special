/**
 * 初始化显示查询的方案信息列表
 */
function initSchemeInfos(schemes){
	var li_info = [];
	roadinfos_jsonarray=[];
	for (var i = 0; i < schemes.length; i++) {
		var scheme, busDistance, stations, describe, toOri, cost, costTime;
		scheme = schemes[i].scheme;
		busDistance = schemes[i].busDistance;
		stations = schemes[i].stations;
		describe = schemes[i].describe;
		toOri = schemes[i].toOri;
		cost = schemes[i].cost;
		allWalk = schemes[i].allWalk;
		costTime = schemes[i].costTime;
		costTime = parseInt(costTime / 60);
		li_info.push(createSchemeInfos(i, scheme, busDistance, stations,
				describe, toOri, cost, costTime, allWalk));
	}
	$('#nested').html(li_info.join(""));
	nestedAccordion.init("nested", "h3", -1, -1, "acc-selected");
}
/**
 * 显示乘车方案信息
 * 
 * @returns {String}
 */
function createSchemeInfos(div_i, scheme, busDistance, stations, describe,
		toOri, cost, costTime, allWalk) {
	var info = [];
	var div_id = "acc_" + div_i;
	var roadId;
	var div_dir;
	if (scheme.length > 0) {
		roadId = scheme[0].roadId;
		div_dir = scheme[0].dir;
	}
	info.push("<li><h3>");
	info.push("<span class='schemename'>");
	info.push(describe);
	info.push("</span>");
	info.push("<span class='scheme_buxing'>");
	info.push("步行" + allWalk + "米");
	info.push("</span>");
	info.push("<span class='scheme_price'>");
	info.push(+cost + "元");
	info.push("</span>");
	info.push("<span class='scheme_time'>");
	info.push(+costTime + "分钟");
	info.push("</span>");
	info.push("<span class='details_lable'></span></h3>");

	for (var i = 0; i < scheme.length; i++) {
		var roadName, start_stationName, end_stationName, start_stationId, end_stationId, stations, type;
		start_stationId = scheme[i].originStationId;
		end_stationId = scheme[i].terminalStationId;
		stations = scheme[i].stations;
		roadId = scheme[i].roadId;
		roadName = scheme[i].roadName;
		type = scheme[i].type;
		// 找到起始站
		for (var j = 0; j < scheme[i].points.length; j++) {
			if (scheme[i].points[j].stationId == start_stationId) {
				start_stationName = scheme[i].points[j].stationName;
				break;
			}
		}
		for (var j = 0; j < scheme[i].points.length; j++) {
			if (scheme[i].points[j].stationId == end_stationId) {
				end_stationName = scheme[i].points[j].stationName;
				break;
			}
		}
		info.push(createSchemeInfo(i, scheme.length, roadName, roadId,
				start_stationName, end_stationName, stations, div_dir, div_id,
				start_stationId, end_stationId, type));
	}
	info.push("<div class='acc-content' id='" + div_id + "'></div>");
	info.push("</div></li>");
	return info.join("");
}
/**
 * 显示乘车方案信息
 * 
 * @returns {String}
 */
function createSchemeInfo(i, length, roadName, roadId, start_stationName,
		end_stationName, stations, div_dir, div_id, start_stationId,
		end_stationId, type) {
	var info = [];
	var end_tip_info;
	if (i == 0) {
		info.push("<div class='acc-section'>");
		info.push("<ul class='content_title'>");
		info.push("<li>");
		info.push("<div class='mod_lable'>");
		if (type == 1) {// 步行
			info.push(start_stationName + "开始走");
			end_tip_info = "到" + end_stationName + "停";
			info.push("</div>");
			info.push("<div class='mod_lable'><span>");
			info.push(roadName);
			info.push(stations + "站");
			info.push("</span><div class='mod_road_icon'></div></div>");
		} else {// 乘车
			info.push(start_stationName + "上车");
			end_tip_info = end_stationName + "下车";
			info.push("</div>");
			info.push("<div class='mod_road' onclick=findBusStationInfoRT_bt(\'" + roadId
					+ "\',\'" + div_dir + "\',\'" + div_id + "\',\'"
					+ start_stationId + "\',\'" + end_stationId + "\',\'"
					+ start_stationName + "\',2,this)><span>");
			info.push(roadName);
			info.push("乘坐" + stations + "站");
			info
					.push("</span><div class='mod_road_icon mod_road_icon_down'></div></div>");
			var roadinf={roadId:roadId,div_dir:div_dir,div_id:div_id,start_stationId:start_stationId,end_stationId:end_stationId,start_stationName:start_stationName};
			roadinfos_jsonarray.push(roadinf);
		}
		info.push("<div class='mod_lable'>");
		info.push(end_tip_info);
		info.push("</div>");
		if (i == (length - 1)) {
			info.push("</li>");
		}
	} else if (i == 1) {
		info.push("<div class='mod_lable'>");
		if (type == 1) {// 步行
			info.push(start_stationName + "开始走");
			end_tip_info = "到" + end_stationName + "停";
			info.push("</div>");
			info.push("<div class='mod_lable'><span>");
			info.push(roadName);
			info.push(stations + "站");
			info.push("</span><div class='mod_road_icon'></div></div>");
		} else {// 乘车
			info.push(start_stationName + "上车");
			end_tip_info = end_stationName + "下车";
			info.push("</div>");
			info.push("<div class='mod_road' onclick=findBusStationInfoRT_bt(\'" + roadId
					+ "\',\'" + div_dir + "\',\'" + div_id + "\',\'"
					+ start_stationId + "\',\'" + end_stationId + "\',\'"
					+ start_stationName + "\',2,this)><span>");
			info.push(roadName);
			info.push("乘坐" + stations + "站");
			info
					.push("</span><div class='mod_road_icon mod_road_icon_up'></div></div>");
		}
		info.push("<div class='mod_lable'>");
		info.push(end_tip_info);
		info.push("</div>");
		if (i == (length - 1)) {
			info.push("</li>");
		}
	} else if (i == 2) {
		info.push("<li>");
		info.push("<div class='mod_lable'>");
		if (type == 1) {// 步行
			info.push(start_stationName + "开始走");
			end_tip_info = "到" + end_stationName + "停";
			info.push("</div>");
			info.push("<div class='mod_lable'><span>");
			info.push(roadName);
			info.push(stations + "站");
			info.push("</span><div class='mod_road_icon'></div></div>");
		} else {// 乘车
			info.push(start_stationName + "上车");
			end_tip_info = end_stationName + "下车";
			info.push("</div>");
			info.push("<div class='mod_road' onclick=findBusStationInfoRT_bt(\'" + roadId
					+ "\',\'" + div_dir + "\',\'" + div_id + "\',\'"
					+ start_stationId + "\',\'" + end_stationId + "\',\'"
					+ start_stationName + "\',2,this)><span>");
			info.push(roadName);
			info.push("乘坐" + stations + "站");
			info
					.push("</span><div class='mod_road_icon mod_road_icon_up'></div></div>");
		}
		info.push("<div class='mod_lable'>");
		info.push(end_tip_info);
		info.push("</div>");
		if (i == (length - 1)) {
			info.push("</li>");
		}
	} else if (i == 3) {
		info.push("<div class='mod_lable'>");
		if (type == 1) {// 步行
			info.push(start_stationName + "开始走");
			end_tip_info = "到" + end_stationName + "停";
			info.push("</div>");
			info.push("<div class='mod_lable'><span>");
			info.push("【" + roadName + "】");
			info.push(stations + "站");
			info.push("</span><div class='mod_road_icon'></div></div>");
		} else {// 乘车
			info.push(start_stationName + "上车");
			end_tip_info = end_stationName + "下车";
			info.push("</div>");
			info.push("<div class='mod_road' onclick=findBusStationInfoRT_bt(\'" + roadId
					+ "\',\'" + div_dir + "\',\'" + div_id + "\',\'"
					+ start_stationId + "\',\'" + end_stationId + "\',\'"
					+ start_stationName + "\','2',this)><span>");
			info.push(roadName);
			info.push("乘坐" + stations + "站");
			info
					.push("</span><div class='mod_road_icon mod_road_icon_up'></div></div>");
		}
		info.push("<div class='mod_lable'>");
		info.push(end_tip_info);
		info.push("</div>");
		if (i == (length - 1)) {
			info.push("</li>");
		}
	}
	if (i == (length - 1)) {
		info.push("</ul>");
	}
	return info.join("");
}
function showRoadInfo(roadInfo, dir, div_id, start_stationId, end_stationId,
		roadId, start_stationName) {
	var info = [];

	if (roadInfo == null || roadInfo.status != 0 || roadInfo.data.length < 1) {
		return false;
	}
	var roadName, priceStr, RT_name, stationInfo;
	roadName = roadInfo.data.rModel.attrs.name;
	priceStr = roadInfo.data.rModel.attrs.priceStr;
	RT_name = "距【" + start_stationName + "】站 --站，约--分钟";

	if (dir == 0) {
		stationInfo = roadInfo.data.rModel.forwardStationList;
	} else {
		stationInfo = roadInfo.data.rModel.backStationList;
	}

	info.push(createRoadTitle(roadName, RT_name, div_id, roadId, dir,
			start_stationName, start_stationId));
	info.push("<div class='station_info'><ul>");
	info.push(createStationInfo(stationInfo, start_stationId, end_stationId,
			div_id, roadId));
	info.push("</ul></div>");
	var div = '#' + div_id;
	$('#' + div_id).html(info.join(""));
}
/**
 * 显示乘车方案信息
 * 
 * @returns {String}
 */
function createRoadTitle(roadName, RT_name, div_id, roadId, dir, stationName,
		stationId) {
	var info = [];
	var id = div_id + "_" + roadId;
	var formId = div_id + "_" + roadId + "_formId";// 当前路线id
	var curr_roadId_input_id = div_id + "_" + roadId + "_roadId";// 当前路线id
	var curr_stationId_input_id = div_id + "_" + roadId + "_stationId";// 当前选择站点id
	var curr_dir_input_id = div_id + "_" + roadId + "_dir";// 当前路线方向

	info.push("<div class='road_info_title' ><span>");
	info.push(roadName);
	info.push("</span><span id=\'" + id + "\'>");
	info.push(RT_name);
	info.push("</span><input type='button' onclick=freshBusStationInfo(\'"
			+ formId + "\',\'" + div_id + "\') value='刷新'>");
	info.push("<form id=\'" + formId + "\'>");
	info.push("<input type='hidden'  class='roadId' value=\'" + roadId + "\'>");
	info.push("<input type='hidden'  class='stationId' value=\'" + stationId
			+ "\'>");
	info.push("<input type='hidden'  class='stationName' value=\'"
			+ stationName + "\'>");
	info.push("<input type='hidden'  class='dir' value=\'" + dir + "\'><form>");
	info.push("</div>");

	return info.join("");
}
/**
 * 显示路线站点信息
 * 
 * @returns {String}
 */
function createStationInfo(stationInfo, start_stationId, end_stationId, div_id,
		roadId) {
	var info = [];
	for (var i = 0; i < stationInfo.length; i++) {
		var stationName = stationInfo[i].attrs.name;
		var stationId = stationInfo[i].attrs.id;
		var bus_coming_div_id = div_id + "_" + stationId;
		var formId = div_id + "_" + roadId + "_formId";// 当前路线id
		info.push("<li><div class='bus_coming_box' id=\'" + bus_coming_div_id
				+ "\'></div>");
		if (i == 0) {
			info
					.push("<div class='bus_position_line bus_position_line_start'></div>");
			info.push("<div class='li_icon li_icon_start'></div> ");
		} else if (i == stationInfo.length - 1) {
			info
					.push("<div class='bus_position_line bus_position_line_end'></div>");
			info.push("<div class='li_icon li_icon_end'></div> ");
		} else {
			info
					.push("<div class='bus_position_line bus_position_line_zhong'></div>");
			info.push("<div class='li_icon li_icon_zhong'></div> ");
		}

		if (start_stationId == stationId) {
			info.push("<a href='javascript:void(0)' onclick=busStationInfo(\'"
					+ stationId + "\',\'" + formId + "\',\'" + div_id + "\',\'"
					+ stationName + "\') class='up_sation_a'>" + stationName
					+ "</a>");
			info.push("<div class='arrow_icon up_bus_arrow'></div>");
		} else if (end_stationId == stationId) {
			info.push("<a href='javascript:void(0)' onclick=busStationInfo(\'"
					+ stationId + "\',\'" + formId + "\',\'" + div_id + "\',\'"
					+ stationName + "\') class='down_sation_a'>" + stationName
					+ "</a>");
			info.push("<div class='arrow_icon down_bus_arrow'></div>");
		} else {
			info.push("<a href='javascript:void(0)' onclick=busStationInfo(\'"
					+ stationId + "\',\'" + formId + "\',\'" + div_id + "\',\'"
					+ stationName + "\') class='sation_a'>" + stationName
					+ "</a>");
			info.push("<div class='arrow_icon'></div>");
		}

		info.push("</li>");
	}

	return info.join("");
}
/**
 * 显示公交车到站的实时位置
 */
function showBusToStationRT(div_id, bus_info) {
	if(div_id){
		// 获取所有的站点车辆，将期不显示
		$('#' + div_id).find('.bus_coming_box').removeClass('bus_coming');
		for (var i = 0; i < bus_info.length; i++) {
			// 显示公交车的位置
			var stationId = bus_info[i].attrs.stationId;
			var bus_coming_div_id = div_id + "_" + stationId;
			// 显示有车的
			$('#' + bus_coming_div_id).addClass('bus_coming');
		}
		
	}else{
		$('#roadquery_infos_station_info').find('.bus_coming_box').removeClass('bus_coming');
		for (var i = 0; i < bus_info.length; i++) {
			// 显示公交车的位置
			var stationId = bus_info[i].attrs.stationId;
			var bus_coming_div_id = div_id + "_" + stationId;
			// 显示有车的
			$('#roadquery_infos_station_info').find('#'+bus_coming_div_id).addClass('bus_coming');
		}
	}
}
/**
 * 显示到站时间
 */
function showBusToStationInfo(st_num, f,s ,start_stationName, roadid, div_id) {

	var s_s_n = start_stationName;
	if (s_s_n == null) {
		s_s_n = "--";
	}
	var div="<span>距【" + s_s_n + "】</span><span >" + st_num + "站|约" + f + "分"	+ s + "秒</span>";
	if(div_id!=""){
		var id = div_id + "_" + roadid;
		$('#' + id).html(div);
	}else{
		$('#road_info_rt').html(div);
	}
	
}
/**
 * 显示路线站点信息
 * 
 * @returns {String}
 */
function createStationInfo_query_road(stationInfo,roadId) {
	var info = [];
	info.push("<ul class='businfo_ul'>");
	for (var i = 0; i < stationInfo.length; i++) {
		var stationName = stationInfo[i].attrs.name;
		var stationId = stationInfo[i].attrs.id;
		var formId = "formId";// 当前路线id
		var bus_coming_div_id ="_" + stationId;
		info.push("<li><div class='bus_coming_box' id=\'"+bus_coming_div_id+"\'></div>");
		if (i == 0) {
			info
					.push("<div class='bus_position_line bus_position_line_start'></div>");
			info.push("<div class='li_icon li_icon_start'></div> ");
		} else if (i == stationInfo.length - 1) {
			info
					.push("<div class='bus_position_line bus_position_line_end'></div>");
			info.push("<div class='li_icon li_icon_end'></div> ");
		} else {
			info
					.push("<div class='bus_position_line bus_position_line_zhong'></div>");
			info.push("<div class='li_icon li_icon_zhong'></div> ");
		}

			info.push("<a href='javascript:void(0)' onclick=busStationInfo(\'"
					+ stationId + "\',\'" + formId + "\','',\'"
					+ stationName + "\') class='up_sation_a'>" + stationName
					+ "</a>");
			info.push("<div class='arrow_icon'></div>");
		info.push("</li>");
	}
	info.push("</ul>");
	$('#roadquery_infos_station_info').html(info.join(""));
}

/**
 * 显示公交路线详细信息
 * @param roadInfo
 */
function showBusRoadInfo(roadInfo,dir){
	var info=[];
	var name=roadInfo.data.rModel.attrs.name;
	var start_stationName=roadInfo.data.rModel.attrs.originatingStation;
	var end_stationName=roadInfo.data.rModel.attrs.terminalStation;
	if(dir==1){
		start_stationName=roadInfo.data.rModel.attrs.terminalStation;
		end_stationName=roadInfo.data.rModel.attrs.originatingStation;
	}
	var begintime=roadInfo.data.rModel.attrs.begintime;
	var endtime=roadInfo.data.rModel.attrs.endtime;
	var rt="--";
	var price=roadInfo.data.rModel.attrs.priceStr;
	var company=roadInfo.data.rModel.attrs.cityName;
	var updateTime=currentTime();
	
	info.push("<div class='txt road_info_txt_t_title'>");
	info.push(name+"公交车路线");
	info.push("</div>");
	info.push("<div class='txt road_info_txt_t_to'>");
	info.push("<div class='from'>");
	info.push(start_stationName);
	info.push("</div><div class='to_icon'></div><div class='to'>");
	info.push(end_stationName);
	info.push("</div></div>");
	info.push("<div class='txt'>");
	info.push("运营时间："+begintime+"--"+endtime);
	info.push("</div>");
	info.push("<div class='txt'>");
	info.push("发车间隔："+rt);
	info.push("</div>");
	info.push("<div class='txt'>");
	info.push("票价信息："+price);
	info.push("</div>");
	info.push("<div class='txt'>");
	info.push("汽车公司："+company);
	info.push("</div>");
	info.push("<div class='txt'>");
	info.push("更新时间："+updateTime);
	info.push("</div>");
	$('.road_info_txt_t').html(info.join(""));
}