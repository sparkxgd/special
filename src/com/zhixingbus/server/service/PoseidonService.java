package com.zhixingbus.server.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.zhixingbus.server.bean.poseidon.AreaCityBean;
import com.zhixingbus.server.bean.poseidon.BankrollDetailBean;
import com.zhixingbus.server.bean.poseidon.CustomerServiceBean;
import com.zhixingbus.server.bean.poseidon.ErrorCorrectionBean;
import com.zhixingbus.server.bean.poseidon.SysMsgBean;
import com.zhixingbus.server.bean.poseidon.UserBean;
import com.zhixingbus.server.bean.poseidon.UserSuggestBean;
import com.zhixingbus.server.bean.poseidon.WeixinPayOrderBean;
import com.zhixingbus.server.config.ServiceConfig;
import com.zhixingbus.server.controller.BaseController;
import com.zhixingbus.server.json.JsonModel;
import com.zhixingbus.server.utils.ConstUtils;
import com.zhixingbus.server.utils.MD5Util;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;
import com.zhixingbus.server.utils.http.HttpUtil;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;

public class PoseidonService {
	public static final String HOST = "http://"
			+ ServiceConfig.PoseidonServiceHost + "/";

	public static void getErrorList(BaseController c) {
		int pageNum = c.getParaToInt("page", 1);
		int pageSize = c.getParaToInt("pageSize", 20);
		int status = c.getParaToInt("status", 0);
		String cityName = c.getPara("cityName");
		if (cityName == null) {
			cityName = "";
		}
		String url = HOST + "zeus/query_error_list?" + "pageNum=" + pageNum
				+ "&pageSize=" + pageSize + "&status=" + status + "&cityName="
				+ cityName;
		String json = HttpUtil.httpGetEntity(url);
		// c.renderJson(json);
		Data<ErrorCorrectionBean> error = new Data<ErrorCorrectionBean>();
		Rpage<ErrorCorrectionBean> page = error.jackson2page(json);
		c.setAttr("userPage", page);
		c.setAttr("cityName", cityName);
		c.setAttr("status", status);
		c.render("files/custs/error_road.html");
		// return null;
	}

	public static void errorOperator(BaseController c) {
		int op = c.getParaToInt("op", 0);// 1表示编辑，2表示删除
		String id = c.getPara("id");
		JsonModel jsonmodel = new JsonModel();
		if (StringUtil.isBlankOrEmpty(id)) {
			jsonmodel.setStatus(1);
			c.renderJson(jsonmodel);
		}
		if (op == 0) {
			jsonmodel.setStatus(1);
			c.renderJson(jsonmodel);
		}
		String url = HOST + "zeus/error_op?id=" + id + "&op=" + op;
		String json = HttpUtil.httpGetEntity(url);
		if (json != null) {
			JSONObject jsonobject = new JSONObject(json).getJSONObject("data");
			int returnCode = -1;
			if (jsonobject != null) {
				returnCode = jsonobject.getInt("returnCode");
			}
			jsonmodel.setStatus(returnCode);
		}
		c.renderJson(jsonmodel);
	}

	/**
	 * 获取用户列表
	 * 
	 * @param c
	 */
	public static void queryUserList(BaseController c) {
		int pageNum = c.getParaToInt("page", 1);
		int pageSize = c.getParaToInt("pageSize", 20);
		String searchType = c.getPara("searchType", "");
		String searchKey = c.getPara("searchKey", "");
		if ("QQ".equals(searchKey)) {
			searchKey = "2";
		} else if ("微博".equals(searchKey.trim())) {
			searchKey = "4";
		} else if (("微信".equals(searchKey.trim()))) {
			searchKey = "3";
		} else if ("手机".equals(searchKey.trim())) {
			searchKey = "1";
		}
		String url = HOST + "zeus/query_user_list?" + "page=" + pageNum
				+ "&pageSize=" + pageSize + "&searchType=" + searchType
				+ "&searchKey=" + searchKey;
		String json = HttpUtil.httpGetEntity(url);
		Data<UserBean> error = new Data<UserBean>();
		Rpage<UserBean> page = error.jackson2page(json);
		c.setAttr("userPage", page);
		c.setAttr("searchType", searchType);
		c.setAttr("searchKey", searchKey);
		c.render("files/user/users.html");
	}

	public static void queryUserDetail(BaseController c) {
		String userId = c.getPara("userId", "");
		String url = HOST + "zeus/query_user_info?userId=" + userId;
		String json = HttpUtil.httpGetEntity(url);
		JSONObject data = new JSONObject(json).getJSONObject("data");
		c.setAttr("data", data);
		c.render("files/user/user_edit.html");
	}

	public static void querySuggestion(BaseController c) {
		int page = c.getParaToInt("page", 1);
		int status = c.getParaToInt("status", 0);// 0表示未回复，1表示回复，2表示全部
		String url = HOST + "zeus/query_suggestion?page=" + page + "&status="
				+ status;
		String json = HttpUtil.httpGetEntity(url);
		Data<UserSuggestBean> error = new Data<UserSuggestBean>();
		Rpage<UserSuggestBean> rpage = error.jackson2page(json);
		c.setAttr("queryReply", rpage);
		if (status == 0) {
			c.render("files/custs/username_feedback.html");
		} else if (status == 2) {
			c.render("files/custs/user_all_reply.html");
		} else if (status == 1) {
			c.render("files/custs/user_already_reply.html");
		}
	}

	public static void queryReply(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String userId = c.getPara("userId").trim();
		String msgId = c.getPara("msgId");
		if (StringUtil.isBlankOrEmpty(userId)) {
			c.renderJson(JsonModel.forError("suerid id is null"));
			return;
		}
		String url = HOST + "zeus/user_reply?page=" + page + "&userId="
				+ userId;
		String json = HttpUtil.httpGetEntity(url);
		Data<UserSuggestBean> error = new Data<UserSuggestBean>();
		List<UserSuggestBean> list = error.jackson2List(json);
		c.setAttr("querySuggest", list);
		c.setAttr("userId", userId);
		c.setAttr("msgId", msgId);
		// c.renderJson(json);
		c.render("files/custs/user_reply.html");
	}

	public static void replySuggest(BaseController c) {
	/*	String reply = c.getPara("reply");
		String userId = c.getPara("userId");
		String msgId = c.getPara("msgId");*/
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String, String[]> map = c.getParaMap();
		@SuppressWarnings("rawtypes")
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			params.add(new BasicNameValuePair(k, v));
		}
		String uri = HOST + "zeus/reply_suggest";
		HttpUtil.httpPostEntity(uri, params);
		c.renderText("");
	}

	public static void custsRegister(BaseController c) {
		String username = c.getPara("username");
		String password = c.getPara("password");
		String repassword = c.getPara("repassword");
		if (StringUtil.isBlankOrEmpty(username)
				|| StringUtil.isBlankOrEmpty(password)
				|| StringUtil.isBlankOrEmpty(repassword)) {
			c.setAttr("returnCode", 3);
			c.render("files/custs/custs_register.html");
			return;
		} else {
			if (repassword.equals(password)) {
				password = MD5Util.string2MD5(password);
				String url = HOST + "custs/customer_service_register?username="
						+ username + "&password=" + password;
				String res = HttpUtil.httpGetEntity(url);
				JSONObject jsonobj = new JSONObject(res);
				if (jsonobj != null && jsonobj.getJSONObject("data") != null) {
					int returnCode = jsonobj.getJSONObject("data").getInt(
							ConstUtils.RETURN_CODE);
					if (returnCode != 0) {
						c.setAttr("returnCode", 1);
					} else {
						c.setAttr("returnCode", 0);
					}
				}
			} else {
				c.setAttr("returnCode", 2);
			}
			c.render("files/custs/custs_register.html");
		}
	}

	public static void queryCusts(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String url = HOST + "custs/customer_service_list?pageNumber=" + page;
		String json = HttpUtil.httpGetEntity(url);
		Data<CustomerServiceBean> bean = new Data<CustomerServiceBean>();
		Rpage<CustomerServiceBean> rpage = bean.jackson2page(json);
		c.setAttr("custsPage", rpage);
		c.render("files/custs/custs_list.html");
	}

	/**
	 * 查询客服详情
	 * @param c
	 */
	public static void queryCustsDetail(BaseController c) {
		String id = c.getPara("id");
		String url = HOST + "custs/query_custs_detail?id=" + id;
		String json = HttpUtil.httpGetEntity(url);
		JSONObject jsonObj = new JSONObject(json);
		String cityName = null;
		if (jsonObj != null && jsonObj.getJSONObject("data") != null) {
			cityName = jsonObj.getJSONObject("data").getString("cityName");
		}
		c.setAttr("cityName", cityName);
		c.setAttr("csId", id);
		c.render("files/custs/custs_detail.html");
	}

	public static void searchCity(BaseController c) {
		String id = c.getPara("csId");
		String cityName = c.getPara("cityname");
		String lastCityName = c.getPara("lastCityName");
		String url = HOST + "zeus/query_city?cityName=" + cityName;
		String json = HttpUtil.httpGetEntity(url);
		List<AreaCityBean> list = new AreaCityBean().jackson2List(json);
		c.setAttr("cityName", lastCityName);
		c.setAttr("cityList", list);
		c.setAttr("csId", id);
		// c.renderJson("fdshajkfhasdjk");
		c.render("files/custs/custs_detail.html");
	}

	public static void addCustsCity(BaseController c) {
		int cityId = c.getParaToInt("cityId");
		String csId = c.getPara("csId");
		if (StringUtil.isBlankOrEmpty(csId)) {
			csId = "";
		}
		String url = HOST + "custs/add_custs_city?cityId=" + cityId + "&csId="
				+ csId;
		String json = HttpUtil.httpGetEntity(url);
		c.renderJson(json);
	}

	/**
	 * 获取系统消息列表
	 * 
	 * @param c
	 */
	public static void systemPrompt(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String url = HOST + "zeus/system_prompt?page=" + page;
		String json = HttpUtil.httpGetEntity(url);
		Data<SysMsgBean> bean = new Data<SysMsgBean>();
		Rpage<SysMsgBean> rpage = bean.jackson2page(json);
		c.setAttr("sysMsgs", rpage);
		c.render("files/system_prompt.html");

	}

	public static void systemEdit(BaseController c, int returnCode) {
		// TODO Auto-generated method stub
		String id = c.getPara("id", "");
		String see = c.getPara("see");
		if (see != null)
			c.setAttr("see", see);
		else
			c.setAttr("see", "1");

		String url = HOST + "zeus/system_edit?id=" + id;

		String json = HttpUtil.httpGetEntity(url);
		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj != null && jsonObj.getJSONObject("data") != null) {
			Object obj = jsonObj.getJSONObject("data").get("sysmsg");
			if (obj != null && !obj.equals(null)) {
				JSONObject sysmsg = jsonObj.getJSONObject("data")
						.getJSONObject("sysmsg");
				String adcode = sysmsg.getString("adCode");
				c.setAttr("smm", sysmsg);
				c.setAttr("city", adcode);
			} else {
				c.setAttr("smm", "smm");
			}

			jsonObj.remove("sysmsg");
		}
		List<AreaCityBean> list = new AreaCityBean().jackson2List(jsonObj
				.toString());
		c.setAttr("cityopenlist", list);
		c.setAttr("returnCode", returnCode);
		c.render("files/system_edit.html");
	}

	public static void systemSave(BaseController c) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String, String[]> map = c.getParaMap();
		@SuppressWarnings("rawtypes")
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			params.add(new BasicNameValuePair(k, v));
		}
		String uri = HOST + "zeus/system_save";
		String json = HttpUtil.httpPostEntity(uri, params);

		JSONObject jsonObj = new JSONObject(json);
		if (jsonObj != null && jsonObj.getJSONObject("data") != null) {
			int returnCode = jsonObj.getJSONObject("data").getInt("returnCode");
			systemEdit(c, returnCode);
		} else {
			c.setAttr("returnCode", 1);
		}
	}
	
	public static void systemDelete(BaseController c){
		String id = c.getPara("id","");
		String url = HOST + "zeus/system_delete?id="+id;
		String json = HttpUtil.httpGetEntity(url);
		JSONObject jsonObj = new JSONObject(json);
		if(jsonObj!=null&&jsonObj.getJSONObject("data")!=null){
			int returnCode = jsonObj.getJSONObject("data").getInt("returnCode");
			if(returnCode==0){
				c.renderAlert("删除成功");
			}else{
				c.renderAlert("删除失败");
			}
		}
	}
	
	public static void sendSysMsg(BaseController c){
		String id=c.getPara("id");
		String execute=c.getPara("execute");
		String url = HOST + "zeus/send_sys_msg?id="+id+"&execute="+execute;
		String json = HttpUtil.httpGetEntity(url);
		JSONObject jsonObj = new JSONObject(json);
		if(jsonObj!=null&&jsonObj.getJSONObject("data")!=null){
			int returnCode = jsonObj.getJSONObject("data").getInt("returnCode");
			if(returnCode==0){
				c.renderAlert("执行成功");
			}else{
				c.renderAlert("执行失败");
			}
		}
	}
	
	public static void query_bankroll_detail(BaseController c){
		String userId = c.getPara("userId");
		int pageSize = c.getParaToInt("pageSize",20);
		int pageNumber = c.getParaToInt("page",1);
		String url = HOST+"zeus/query_bankroll_detail?userId="+userId+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
		String json = HttpUtil.httpGetEntity(url);
		Data<BankrollDetailBean> error = new Data<BankrollDetailBean>();
		Rpage<BankrollDetailBean> page = error.jackson2page(json);
		c.setAttr("userPage", page);
		c.setAttr("userId", userId);
		c.render("files/user/bankroll_detail.html");
	}
	
	public static void queryWeixinpayLog(BaseController c){
		String searchKey=c.getPara("searchKey");
		int pageSize = c.getParaToInt("pageSize",20);
		int pageNumber = c.getParaToInt("page",1);
		String url = HOST+"zeus/query_pay_log?username="+searchKey+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
		String json = HttpUtil.httpGetEntity(url);
		Data<WeixinPayOrderBean> error = new Data<WeixinPayOrderBean>();
		Rpage<WeixinPayOrderBean> page = error.jackson2page(json);
		c.setAttr("userPage", page);
		c.setAttr("searchKey", searchKey);
		c.render("files/user/weixinpay.html");
	}
	
	public static void queryUserStatistics(BaseController c){}
	

	
	
	
	/**
	 * 存储文件
	 */
	public static boolean uploadNewsArticle(String accountId,int cityId,String newsTitle, String newsAbstract, String newsContent)
	{
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("accountId",accountId));
		params.add(new BasicNameValuePair("cityId",""+cityId));
		params.add(new BasicNameValuePair("newsTitle",newsTitle));
		params.add(new BasicNameValuePair("newsAbstract",newsAbstract));
		params.add(new BasicNameValuePair("newsContent",newsContent));
		params.add(new BasicNameValuePair("articleId",Utils.createNewUUID()));

		return Boolean.parseBoolean(HttpUtil.httpPostEntity(HOST+"zeus/upload_news_article", params));
	}
	
	
	
	public static boolean newsDoEdit(String accountId,int cityId,String articleId,String newsTitle,String newsAbstract,String newsContent,String basepath)
	{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("accountId",accountId));
		params.add(new BasicNameValuePair("cityId",""+cityId));
		params.add(new BasicNameValuePair("articleId",articleId));
		params.add(new BasicNameValuePair("newsTitle",newsTitle));
		params.add(new BasicNameValuePair("newsAbstract",newsAbstract));
		params.add(new BasicNameValuePair("newsContent",newsContent));
		params.add(new BasicNameValuePair("basepath",basepath));
		
		return Boolean.parseBoolean(HttpUtil.httpPostEntity(HOST+"zeus/news_doEdit", params));
	}
	
	/**
	 * 删除文章
	 */

	public static boolean newsDelete(String articleId)
	{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("articleId",articleId));
		
		return  Boolean.parseBoolean(HttpUtil.httpPostEntity(HOST+"zeus/news_delete", params));
	}
	
	/**
	 * 是否执行文章
	 */
	public static boolean newsOperate(String articleId,int status)
	{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("articleId",articleId));
		params.add(new BasicNameValuePair("status",""+status));
		
		return  Boolean.parseBoolean(HttpUtil.httpPostEntity(HOST+"zeus/news_operate", params));
	}


	/**
	 * 保存下载数据
	 * xiao
	 * 2015年11月27日 14:00:07
	 */
	public static void save_advertisment(List<NameValuePair> params){
		String url=HOST+"user/user_advertisment";
		try {
			HttpUtil.httpPostEntity(url, params);
			System.out.println("save_advertisment:"+url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存下载数据
	 * xiao
	 * 2015年11月27日 14:00:07
	 */
	public static void uploadcomarison(List<NameValuePair> params){
		String url=HOST+"user/user_advertisment";
		
		try {
			HttpUtil.httpPostEntity(url, params);
			System.out.println("uploadcomarison:"+url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
