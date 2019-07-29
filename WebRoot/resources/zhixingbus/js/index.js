var nestedAccordion = new TINY.accordion.slider("nestedAccordion");
var map;
var mapdata;
var srcXY;// 起始坐标
var desXY;// 目的坐标
var srcLatitude;
var srcLongitude;
var desLatitude;
var desLongitude;
var citycode = 0855;
var adcode = 522600;
var gaode_autoSearch_start;
var gaode_autoSearch_end;
var roadinfos_jsonarray=[];//初始化打开的路线信息
var roadInfoArray=[];
var defaultCityXY = new AMap.LngLat(106.713478, 26.578343);
var tishi_dialog;
var error_dialog;
$(function() {
	initLeftUI();
	initMapUI();
});
/**
 * 初始化左侧界面
 */
function initLeftUI(){
	// 初始界面
	$(".roadquery").addClass('hidden');
	$('ul#filter a').click(function() {
		$(this).css('outline', 'none');
		$('ul#filter .current').removeClass('current');
		$(this).parent().addClass('current');
		var filterid = $(this).attr('id');
		if (filterid == 'queryr') {
			$(".changequery").addClass('hidden');
			$(".roadquery").removeClass('hidden');
		} else {
			$(".roadquery").addClass('hidden');
			$(".changequery").removeClass('hidden');
		}
	});
    $( "#changeCity_dialog" ).dialog({
        modal: true,
        autoOpen:false,
        buttons: {
          Ok: function() {
            $( this ).dialog( "close" );
          }
        }
      });
    error_dialog=$( "#error_dialog" ).dialog({
        modal: true,
        autoOpen:false,
        buttons: {
          Ok: function() {
            $( this ).dialog( "close" );
          }
        }
      });
    tishi_dialog=$( "#tishi_dialog" ).dialog({
        modal: true,
        autoOpen:false,
        buttons: {
          Ok: function() {
            $( this ).dialog( "close" );
          }
        }
      });
	//绑定查询路线文本框
	bindquery_roads();
	$('.main_right').css("display", "none");
	$('.main_right_init_pic').css("display", "block");
	$('#initpage1').css("display", "block");
	$('#initpage2').css("display", "block"); 
	$('.roadquery_infos').css("display", "none");
	$('.query_scheme_info').css("display", "none");
}
/**
 *  绑定查询路线文本框
 */
function bindquery_roads(){
	// 绑定查询路线文本框
	$("#query_roads").autocomplete({
	source : function(request, response) {
		$.ajax({
			url : "/cloud/queryRoadLineList",
			dataType : "json",
			data : {
				adcode : adcode,
				term : request.term
			},
			success : function(data) {
				response($.map(data.data, function(item) {
					return {
						label : item.cityName+"	"+item.name,
						value : item.name,
						data : item
					}
				}));
			}
		});
	},
	select: function( event, ui ) {
		var name=ui.item.data.name;
		var id=ui.item.data.id;
		$('#select_road_id').val(id);
		$(this).val(name);
       return false;
    },
	minLength : 1
});
}
/**
 * 交换地点按钮
 */
function changposition(){
	var tem=$('#start_point').val();
	var tem_id=$('#start_point_id').val();
	
	var end_point=$('#end_point').val();
	var end_point_id=$('#end_point_id').val();
	
	$('#start_point').val(end_point);
	$('#start_point_id').val(end_point_id);
	
	$('#end_point').val(tem);
	$('#end_point_id').val(tem_id);
	
}
/**
 * 高德地图搜索数据结果
 * 
 * @returns {String}
 */
function autocomplete_CallBack_start(o) {
	console.log("start"+o.poi.location);
	$("#start_point_id").val(o.poi.location);
	srcXY = o.poi.location;
}
/**
 * 高德地图搜索数据结果
 * 
 * @returns {String}
 */
function autocomplete_CallBack_end(o) {
	console.log("end"+o.poi.location);
	$("#end_point_id").val(o.poi.location);
	desXY = o.poi.location;
}
/**
 * 换城市
 */
function changeCity(){
	 $( "#changeCity_dialog" ).dialog('open');
	//citycode = 0855;
	//adcode = 522600;
}
/**
 * 换向
 */
function change_dir(){
	var curr_dir=$('#formId').find('.dir').val();
	var roadId=$('#select_road_id').val();
	var dir;
	if(curr_dir==0){
		dir=1;
	}else{
		dir=0;
	}
	$('#formId').find('.dir').val(dir);
	queryRoadByStationId(roadId,dir);
	
}

/**
 * 点击站点，查看实时公交信息
 */
function busStationInfo(stationId, formId, div_id, stationName) {
	var roadid = $('#' + formId).find('.roadId').val();
	var dir = $('#' + formId).find('.dir').val();
	$('#' + formId).find('.stationName').val(stationName);
	$('#' + formId).find('.stationId').val(stationId);
	queryBusStationRT(roadid, dir, stationId, stationName, div_id);
}
/**
 * 点击刷新按钮，查看实时公交信息
 */
function freshBusStationInfo(formId, div_id) {
	var roadid = $('#' + formId).find('.roadId').val();
	var dir = $('#' + formId).find('.dir').val();
	var stationName = $('#' + formId).find('.stationName').val();
	var stationId = $('#' + formId).find('.stationId').val();
	queryBusStationRT(roadid, dir, stationId, stationName, div_id);
}
/**
 * 查询路线详情按钮
 */
function queryRoadById_bt(){
	var roadId=$('#select_road_id').val();
	if(roadId){
		var dir=0;//默认正方向
		$('#formId').find('.roadId').val(roadId);
		$('#formId').find('.dir').val(dir);
		queryRoadByStationId(roadId,dir);
		
		$('#initpage2').css("display", "none"); 
		$('.roadquery_infos').css("display", "block"); 
		$('.main_right').css("display", "block");
		$('.main_right_init_pic').css("display", "none");
	}else{
		error_dialog.dialog('open');
	}
}

/**
 * 初始化地图界面
 * 
 */
function initMapUI(){
	initGDMap();
	//初始化地图版面head信息
	initMapHeadInfo();
}
/**
 * 初始化高德地图
 */
function initGDMap(){
	// 初始化地图
	map = new AMap.Map('map', {
		zoom : 13,
		center : defaultCityXY,
	});
	// 加载输入提示插件
	map.plugin([ "AMap.Autocomplete" ], function() {
		var autoOptions = {
			city : citycode,
			input : "start_point"

		// 城市，默认全国
		};
		var autoOptions2 = {
			city : citycode,
			input : "end_point"
		// 城市，默认全国
		};
		gaode_autoSearch_start = new AMap.Autocomplete(autoOptions);//初始化地点搜索--起始位置
		gaode_autoSearch_end = new AMap.Autocomplete(autoOptions2);//初始化地点搜索--终点位置
	});
	AMap.event.addListener(gaode_autoSearch_start, "select",
			autocomplete_CallBack_start);
	AMap.event.addListener(gaode_autoSearch_start, "choose",
			autocomplete_CallBack_start);
	AMap.event.addListener(gaode_autoSearch_end, "select",
			autocomplete_CallBack_end);
	AMap.event.addListener(gaode_autoSearch_end, "choose",
			autocomplete_CallBack_end);
	
	initMapHeadInfo();
}
/**
 * 更新地图数据显示
 * 地图只负责显示，不参与数据处理
 * 负责路线和路线上的站牌显示，同时更新head车辆信息
 */
function refreshMapRoadsLine(roadPoint,stationInfo,roadLineInfo){
	clear();
	//更新地图 数据显示（画路线）
	if(roadPoint){
		drawRoadsLine(roadPoint,50,'#030303',map);
		//更新地图 数据显示（画站点）
		drawStation(roadPoint);
	}
	//更新路线信息
	if(roadLineInfo){
		refreshMapHeadInfoByRoad(roadLineInfo);
	}
}
/**
 * 显示路线信息
 */
function showRoadLine(dir){
	var line=b_r_d.data.rModel.forwardRoadPointList;//路线点集合
	if(dir!=0){
		if(b_r_d.data.rModel.backRoadPointList.length>0){//说明不是是环线
			line=b_r_d.data.rModel.backRoadPointList;
		}
	}
	//画路线
	drawRoadLine(line);
	//画站点
	drawRoadStation(line,dir);	
}
/**
 * 初始化公交车到点信息
 * 
 */
function initMapHeadInfo(){
	var name="--";
	var endStationName="--";
	var startTime="--";
	var endTime="--";
	var price="--";
	var toSationName="--";
	var sationNum="--";
	var m="--";
	var s="--";
	showMapHeadInfo(name,endStationName,startTime,endTime,price,toSationName,sationNum,m,s);
}
/**
 * 更新地图板块数据显示
 */
function refreshMapShowData(sationNum,m,s,toSationName,busData,div_id,roadId,dir){
	var endStationName,startTime,endTime,price,name;
	for(var i=0;i<roadInfoArray.length;i++){
		if(roadInfoArray[i].roadId==roadId&&roadInfoArray[i].dir==dir){
			var roadInfo=roadInfoArray[i].roadinfo;
			name=roadInfo.data.rModel.attrs.name;
			endStationName=roadInfo.data.rModel.attrs.terminalStation;
			if(dir==1){
				endStationName=roadInfo.data.rModel.attrs.originatingStation;
			}
			startTime=roadInfo.data.rModel.attrs.begintime;
			endTime=roadInfo.data.rModel.attrs.endtime;
			price=roadInfo.data.rModel.attrs.priceStr;
			break;
		}
	}
	if(div_id){
		showMapHeadInfo(name,endStationName,startTime,endTime,price,toSationName,sationNum,m,s);
	}
	
	//更新地图head数据显示
	refreshMapHeadInfoRT(sationNum,m,s,toSationName,div_id);
	//更新地图上的数据显示(车辆位置显示)
	refreshMapBusInfo(busData,name);
	
}
/**
 * 显示某条路线信息
 * 每次更改路线的时候调用
 */
function refreshMapHeadInfoByRoad(attrs){
	var name=attrs.name;
	var endStationName=attrs.terminalStation;
	var startTime=attrs.begintime;
	var endTime=attrs.endtime;
	var price=attrs.priceStr;
	var toSationName="--";
	var sationNum="--";
	var m="--";
	var s="--";
	showMapHeadInfo(name,endStationName,startTime,endTime,price,toSationName,sationNum,m,s);
}
/**
 * 显示某条路线信息
 * 每次更改路线的时候调用
 */
function refreshMapHeadInfoRT(sationNum,m,s,toSationName,div_id){
	showMapHeadInfo(null,null,null,null,null,toSationName,sationNum,m,s);
}
/**
 * 更新路线地图上的公交车位置显示
 * @param busData
 */
function refreshMapBusInfo(busData,name){
	clearBuses();
	showBuses(busData,name);
}
/**
 * 查询换乘方案
 */
function query_scheme(o) {
	if(!srcXY||!desXY){
		error_dialog.dialog('open');
		return
	}
	$('.main_right').css("display", "block");
	$('.main_right_init_pic').css("display", "none");
	$('#initpage1').css("display", "none"); 
	$('.query_scheme_info').css("display", "block");
	$(o).attr('disabled',"true");//添加disabled属性
	// 异步查询数据
	$.ajax({
		type : "post",
		data : {
			srcLatitude : srcXY.lat,
			srcLongitude : srcXY.lng,
			desLatitude : desXY.lat,
			desLongitude : desXY.lng,
			adcode : adcode
		},
		url : "/cloud/query_bus_scheme_by_adcode",
		dataType : "json",
		success : function(data) {
			query_bus_scheme_Callback(data);
		},
		error : function() {
			error_dialog.dialog("open");
			$(o).removeAttr("disabled"); //移除disabled属性 
		},
		complete : function() {
			$(o).removeAttr("disabled"); //移除disabled属性 
			//默认打开查询第一个站点的实时公交信息
			for(var i=0;i<roadinfos_jsonarray.length;i++){
				var roadId=roadinfos_jsonarray[i].roadId;
				var div_dir=roadinfos_jsonarray[i].div_dir;
				var div_id=roadinfos_jsonarray[i].div_id;
				var start_stationId=roadinfos_jsonarray[i].start_stationId;
				var end_stationId=roadinfos_jsonarray[i].end_stationId;
				var start_stationName=roadinfos_jsonarray[i].start_stationName;
				getRoadInfo(roadId,div_dir,div_id,start_stationId,end_stationId,start_stationName,0);
			}
		}
	});

}
/**
 * 
 * @param data
 * @returns
 */
function query_bus_scheme_Callback(data) {
	//数据检查
	if(data.length<1||data.data==null||data.data.length<1){
		tishi_dialog.dialog("open");
		return;
	}
	var schemes = data.data;
	//显示查询的方案数据
	initSchemeInfos(schemes);
}
/**
 * 查询方案，点击站点信息获取公交车实时信息
 * @param roadId
 * @param dir
 * @param div_id
 * @param start_stationId
 * @param end_stationId
 * @param start_stationName
 * @param option
 * @param object
 */
function findBusStationInfoRT_bt(roadId, dir, div_id, start_stationId, end_stationId,start_stationName,option,object){
	var p_li=$(object).parent().parent().find('li').find('.mod_road').find('div');
	p_li.removeClass('mod_road_icon_down');
	p_li.addClass('mod_road_icon_up');
	$(object).find('.mod_road_icon').removeClass('mod_road_icon_up');
	$(object).find('.mod_road_icon').addClass('mod_road_icon_down');
	getRoadInfo(roadId, dir, div_id, start_stationId, end_stationId,start_stationName,option)
	
}
/**
 * 查询路线详情
 * @param roadId
 * @param dir
 * @param div_id
 * @param start_stationId
 * @param end_stationId
 * @param start_stationName
 */
function getRoadInfo(roadId, dir, div_id, start_stationId, end_stationId,
		start_stationName,option) {
	if(roadId==null||roadId==""){
		tishi_dialog.dialog('open');
		return;
	}
	// 异步查询数据
	$.ajax({
		type : "post",
		data : {
			roadId : roadId,
			adcode : adcode
		},
		url : "/cloud/queryRoadInfo",
		dataType : "json",
		success : function(data) {
			//显示路线详情
			getRoadInfo_CallBack(data, dir, div_id, start_stationId, end_stationId,
					roadId, start_stationName,option);
		},
		error : function() {
			error_dialog.dialog('open');
		},
		complete :	function(result) {
			//查公交车实时位置
			getRoadInfo_complete_CallBack(result,roadId, dir, start_stationId, start_stationName,div_id,option);
		}
	});

}
/**
 * 
 * @param data
 */
function getRoadInfo_CallBack(data, dir, div_id, start_stationId, end_stationId,
		roadId, start_stationName,option){
	if(data.data==null){
		tishi_dialog.dialog('open');
		return;
	}
	var isexit=false;
	for(var i=0;i<roadInfoArray.length;i++){
		if(roadInfoArray[i].roadId==roadId&&roadInfoArray[i].dir==dir){
			isexit=true;
			break;
		}
	}
	if(!isexit){
		var roadinfo={roadId:roadId,dir:dir,roadinfo:data};
		roadInfoArray.push(roadinfo);
	}

	switch (option) {
	case 0:
		//显示在方案里
		showRoadInfo(data, dir, div_id, start_stationId, end_stationId,
				roadId, start_stationName);
		break;
	case 1:
		//显示在地图
		queryRoadById_CallBack(data,roadId,dir);
		break;
	case 2:
		showRoadInfo(data, dir, div_id, start_stationId, end_stationId,
				roadId, start_stationName);
		scheme_refreshMapRoadsLine(data, dir);
		break;
	default:
		break;
	}
	
}
/**
 * 
 */
function getRoadInfo_complete_CallBack(result,roadId, dir, start_stationId, start_stationName,div_id,option){
	
		var data=result.responseJSON.data;
		var stationName;
		var stationId;
		if(data!=null){
			if(dir==0){
				stationName=data.rModel.forwardStationList[0].attrs.name;
				stationId=data.rModel.forwardStationList[0].attrs.id;
			}else{
				stationName=data.rModel.backStationList[0].attrs.name;
				stationId=data.rModel.backStationList[0].attrs.id;
			}
		}
		switch (option) {
		case 0:
			//显示在方案里
			// 获取公交到站的实时信息(默认是上车的站)
			queryBusStationRT(roadId, dir, start_stationId, start_stationName,
					div_id);
			break;
		case 1:
			//显示在地图
			// 获取公交到站的实时信息(默认是上车的站)
			queryBusStationRT(roadId, dir, stationId, stationName,"");
			break;
		case 2:
			// 获取公交到站的实时信息(默认是上车的站)
			queryBusStationRT(roadId, dir, start_stationId, start_stationName,
					div_id);
			queryBusStationRT(roadId, dir, stationId, stationName,"");
			break;
		default:
			break;
		}
		
}
/**
 * 路线查询面板查询按钮查询
 */
function queryRoadByStationId(roadId,dir){
	getRoadInfo(roadId,dir,null,null,null,null,1);
}
/**
 * 查询公交车到站的实时信息
 * 
 * @param roadid
 * @param dir
 * @param stationId
 */
function queryBusStationRT(roadid, dir, stationId, start_stationName, div_id) {
	// 异步查询数据
	$.ajax({
				type : "post",
				data : {
					roadId : roadid,
					adcode : adcode,
					direction : dir,
					stationId : stationId
				},
				url : "/cloud/queryBusStationRT",
				dataType : "json",
				success : function(data) {
					queryBusStationRT_Callback(data, start_stationName, roadid,
							div_id,dir);
				},
				error : function() {
					busSearch_error();
				},
				complete : function() {
				}
			});
}
/**
 * 查询成功后数据处理
 * 
 * @param data
 */
function queryBusStationRT_Callback(data, start_stationName, roadid, div_id,dir) {
	//条件控制
	if (data == null || data.length < 1) {
		return;
	}
	//更新公交车已经公交路线的实时数据
	refreshShowData(data, start_stationName, roadid, div_id,dir);
}
/**
 * 更新系统的显示数据
 */
function refreshShowData(d, toSationName, roadid, div_id,dir){
	
	var bus_info = d[0].busPos;
	if (bus_info == null) {
		return;
	}
	var sationNum = d[0].sc;
	var ts = d[0].ts;
	var m=getMinute(ts);
	var s=getSeconds(ts);
	if(sationNum==-1){
		sationNum="--";
	}
	//更新地图数据
	refreshMapShowData(sationNum,m,s,toSationName,bus_info,div_id,roadid,dir);
	//更新左侧信息
	showBusToStationInfo(sationNum, m,s, toSationName, roadid, div_id);
	showBusToStationRT(div_id, bus_info);
}

/**
 * 路线详情数据解析
 * @param data
 */
function queryRoadById_CallBack(roadInfo,roadid,dir){
	//判断数据的合法信息
	if(roadInfo==null||roadInfo.length<1||roadInfo.data==null){
		return;
	}
	//显示文本信息
	showBusRoadInfo(roadInfo,dir);
	//显示公交车路线站点信息
	var stationInfo;
	var roadPoint;
	var attrs=roadInfo.data.rModel.attrs;
	var station;
	if(roadInfo.data.rModel.backRoadPointList.length>1){//说明不是环线
		if (dir == 0) {
			stationInfo = roadInfo.data.rModel.forwardStationList;
			roadPoint= roadInfo.data.rModel.forwardRoadPointList;
		} else {
			station=attrs.originatingStation;
			attrs.originatingStation=attrs.terminalStation;
			attrs.terminalStation=station;
			stationInfo = roadInfo.data.rModel.backStationList;
			roadPoint= roadInfo.data.rModel.backRoadPointList;
		}
	}else{
		stationInfo = roadInfo.data.rModel.forwardStationList;
		roadPoint= roadInfo.data.rModel.forwardRoadPointList;
	}
	
	createStationInfo_query_road(stationInfo,roadid);
	refreshMapRoadsLine(roadPoint,stationInfo,attrs);
}
/**
 * 
 * @param roadInfo
 * @param roadid
 * @param dir
 */
function scheme_refreshMapRoadsLine(roadInfo,dir){
	//判断数据的合法信息
	if(roadInfo==null||roadInfo.length<1||roadInfo.data==null){
		return;
	}
	//显示公交车路线站点信息
	var stationInfo;
	var roadPoint;
	var attrs=roadInfo.data.rModel.attrs;
	var station;
	if (dir == 0) {
		stationInfo = roadInfo.data.rModel.forwardStationList;
		roadPoint= roadInfo.data.rModel.forwardRoadPointList;
	} else {
		station=attrs.originatingStation;
		attrs.originatingStation=attrs.terminalStation;
		attrs.terminalStation=station;
		stationInfo = roadInfo.data.rModel.backStationList;
		roadPoint= roadInfo.data.rModel.backRoadPointList;
	}
	refreshMapRoadsLine(roadPoint,stationInfo,attrs);
}