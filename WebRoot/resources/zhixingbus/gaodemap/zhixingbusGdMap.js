
var stationMarkers=[];//显示站点的集合，用来清楚显示
var roadsLines=[];//显示路线的集合，用来清楚显示
var buses=[];//显示车辆的集合，用来清楚显示
var busTips=[];//显示车辆信息的集合，用来清楚显示
//自定义点图标
var sicon = new AMap.Icon({
	image: "./resources/zhixingbus/images/map/bus_station_icon.png",
    size: new AMap.Size(46,66),
    imageSize:new AMap.Size(16,30)
});
//选中站点点图标
var selecticon = new AMap.Icon({
	   image: "./resources/zhixingbus/images/map/bus_station_selected.png",
    size: new AMap.Size(46,66),
    imageSize:new AMap.Size(26,40)
});
//起点图标
var starticon = new AMap.Icon({
	   image: "./resources/zhixingbus/images/map/icon_st.png",
    size: new AMap.Size(46,66),
    imageSize:new AMap.Size(26,31)
});
//末点图标
var endicon = new AMap.Icon({
	   image: "./resources/zhixingbus/images/map/icon_en.png",
    size: new AMap.Size(46,66),
    imageSize:new AMap.Size(26,31)
});
//公交车正向图标
var busicon_z= new AMap.Icon({
	   image: "./resources/zhixingbus/images/map/forward_bus_icon.png",
    size: new AMap.Size(39,23),
    imageSize:new AMap.Size(29,13)
});
//公交车反向图标
var busicon_f= new AMap.Icon({
	   image: "./resources/zhixingbus/images/map/reverse_bus_icon.png",
    size: new AMap.Size(39,23),
    imageSize:new AMap.Size(29,13)
});
var infoWindow = new AMap.InfoWindow({
	offset:new AMap.Pixel(0,-30),
	isCustom:true,
	showShadow:true
	
});
/**
 * 画路线
 */
function drawRoadsLine(linePointArray,zIndex,lineColor,map){
	var lineArr=new Array();//创建线覆盖物节点坐标数组  
	var lng;
	var lat;
	for(var i=0;i<linePointArray.length;i++){
		lng=linePointArray[i].attrs.longitude;
		lat=linePointArray[i].attrs.latitude;
		lineArr.push(new AMap.LngLat(lng,lat));   
	}
	//绘制乘车的路线
   var pl= new AMap.Polyline({
        map:map,
        path:lineArr,
        strokeColor:lineColor,//线颜色
        strokeOpacity:0.8,//线透明度
        strokeWeight:6,//线宽
        zIndex:zIndex,
        geodesic:true
    });
   roadsLines.push(pl);
   map.setFitView();
}
/**
 * 画站点
 */
function drawStation(stationInfo) {
	for (var i = 0; i < stationInfo.length; i++) {
		var lng = stationInfo[i].attrs.longitude;
		var lat = stationInfo[i].attrs.latitude;
		var sorderNo = stationInfo[i].attrs.sorderNo;
		var name = stationInfo[i].attrs.stationName;
		var sorderNo = stationInfo[i].attrs.sorderNo;
		var id = stationInfo[i].attrs.stationId;
		if (sorderNo != null) {
			var type = -1;
			if (i == 0) {
				type = 0;// 第一个点为起点
			} else if (i == stationInfo.length - 1) {
				type = 1;// 最后一个点为终点
			}
			var smarker = drawStationPoint(lng, lat, name, id, sorderNo, type);
			stationMarkers.push(smarker);
		}
	}
}
/**
 * 显示车辆
 */
function showBuses(busData,name){
	for(var i=0;i<busData.length;i++){
		//显示公交车的位置
		var y=busData[i].attrs.longitude;
		var x=busData[i].attrs.latitude;
		var busno=busData[i].attrs.busno;
		var direction=busData[i].attrs.direction;
		var angle=busData[i].attrs.angle;
		var temangle=(180.0/3.1415926)*angle*(-1);//console.log("车牌号："+busno+"，angle:"+angle+",a:"+temangle);
		var roadno=name.match(/\d/g).join("");
		drawbus(y,x,busno,direction,temangle,roadno);
	}
}
/**
 * 绘制车辆
 */
function drawbus(y,x,busno,direction,angle,roadno){
	var busicon;
	if(direction==0){
		busicon=busicon_z;
	}else{
		busicon=busicon_f;
	}
	  //绘制车辆
	  var busmarker=new AMap.Marker({
	      map:map,
	      position:new AMap.LngLat(y,x), //基点位置
	      icon:busicon, 
	      title:busno,
	      angle:angle,
	      autoRotation:true,
	      clickable:true,
	      offset: new AMap.Pixel(0, -10),//相对于基点的位置
	  });
	  buses.push(busmarker);
	  var contenth= "<div class='zxb_gd_map_businfo'><span>"+roadno+"</span></div>";
	  //绘制车辆信息点
	  var busmarkerbiao=new AMap.Marker({
		  map:map,
	      position:new AMap.LngLat(y,x), //基点位置
	      angle:angle,
	      content:contenth,
	      offset: new AMap.Pixel(0, -43),//相对于基点的位置
	      clickable:true
	  });
	  busTips.push(busmarkerbiao);
}
/**
 * 画点（绘制站牌）
 */
function drawStationPoint(lng, lat, name, id, sorderNo, type) {
	var smarker = new AMap.Marker({
		map : map,
		position : new AMap.LngLat(lng, lat), // 基点位置
		icon : starticon, // 复杂图标
		title : name,
		clickable : true,
		extData : id
	});
	   var info = [];
	    info.push("<div style=\"position:relative;width:100px;height:40px;line-height:40px;background:#50A9FD;color:#FFFFFF;font-family:Times New Roman;text-align:center;border:1px solid #FFFFFF;border-radius:10px;\">"); +
	    info.push(name);
	    info.push("<div style=\"position:absolute;width: 0px;height:0px;line-height: 0px;border-width: 20px 9px 0;border-style: solid dashed dashed dashed;border-left-color: transparent;border-right-color: transparent;color: #FFFFFF;bottom: -20px;right: 27%;\" ></div>");
	    info.push("<div style=\"position:absolute;width: 0px;height:0px;line-height: 0px;border-width: 20px 9px 0;border-style: solid dashed dashed dashed;border-left-color: transparent;border-right-color: transparent;color:#50A9FD;bottom: -18px;right: 27%;\" ></div></div>");
	    smarker.content = info.join("");
	AMap.event.addListener(smarker, 'click', markerClick);
	switch (type) {
	case 0:// 画起点
		smarker.setIcon(starticon);
		break;
	case 1:// 画终点
		smarker.setIcon(endicon);
		break;
	default:
		var contenth = "<div class='zxb_gd_map_station'><span>" + sorderNo + "</span></div>";
		smarker.setContent(contenth);
		smarker.setOffset(new AMap.Pixel(0, -15));
		AMap.event.addListener(smarker, 'mouseover', function(e) {
			e.target.setIcon(selecticon);
		});
		AMap.event.addListener(smarker, 'mouseout', function(e) {
			e.target.setIcon(sicon);
		});
		break;
	}
	return smarker;
}
function markerClick(e){
    infoWindow.setContent(e.target.content);
    infoWindow.open(map, e.target.getPosition());
}
/**
 * 清除显示的公交车
 */
function clearBuses(){
	for(var i=0;i<buses.length;i++){
		buses[i].setMap(null);
	}
	buses.splice(0,buses.length);
	for(var i=0;i<busTips.length;i++){
		busTips[i].setMap(null);
	}
	busTips.splice(0,busTips.length);
}
/**
 * 清除显示的路线和站票
 */
function clearStationLine(){
	for(var i=0;i<stationMarkers.length;i++){
		stationMarkers[i].setMap(null);
	}
	stationMarkers.splice(0,stationMarkers.length);
	for(var i=0;i<roadsLines.length;i++){
		roadsLines[i].setMap(null);
	}
	roadsLines.splice(0,roadsLines.length);
}
function clear(){
	map.clearMap();
}