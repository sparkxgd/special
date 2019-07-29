function postloadinfo() {
		var nav = navigator;
		//获取本机ip
		var ip = returnCitySN.cip;
		var cname=returnCitySN.cname;
		var user_Agent = nav.userAgent;
		var referer=nav.Referer;
		$.ajax({
			url : "visitor",
			type : 'post',
			data : {
				browser : user_Agent,
				addr:cname,
				remoteIp : ip,
				referer:referer
			},
			dataType : 'json'
		});
}
function suggest(){
	//获取本机ip
	var ip = returnCitySN.cip;
	var cname=returnCitySN.cname;
	var content=$("#content").val();
	var title=$("#title").val();
	var email=$("#email").val();
	var name=$("#name").val();
	if(title&&content){
		$.ajax({
			url : "../suggest",
			type : 'post',
			data : {
				addr:cname,
				remoteIp : ip,
				content:content,
				title:title,
				email:email,
				name:name
		
			},
			dataType : 'json',
			complete : function() {
				alert("非常感谢您的宝贵意见，感谢你的支持！");
				$("#content").val('');
				$("#title").val('');
			}
		});
	}else{
		alert("请填写你的建议标题和内容，感谢你的支持！");
		return;
	}
	
}
function dd(type) {
	var nav = navigator;
	// 获取本机ip
	var ip = returnCitySN.cip;
	var cname = returnCitySN.cname;
	var user_Agent = nav.userAgent;
	$.ajax({
		url : "/download",
		type : 'post',
		data : {
			browser : user_Agent,
			addr : cname,
			remoteIp : ip,
			t : type
		},
		dataType : 'jsonp',
		success : function(data) {
			if (data) {
				// window.href = data;
				window.open(data);
			}
		},
		complete : function(data) {
			window.open(data.responseText);
		}
});
}