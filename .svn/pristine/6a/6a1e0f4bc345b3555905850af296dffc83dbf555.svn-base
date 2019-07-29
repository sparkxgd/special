package com.zhixingbus.server.service;

import com.zhixingbus.server.bean.ares.WeixinUserInfoBean;
import com.zhixingbus.server.config.ServiceConfig;
import com.zhixingbus.server.controller.BaseController;
import com.zhixingbus.server.utils.json.Rpage;



/**
 * 
 * 文 件 名 :com.zhixingbus.server.controller.weixin.WeiXinAdminUserService.java <br>
 * 创 建 人：xiao <br>
 * 日 期：20152015年7月6日下午2:33:40 <br>
 * 修 改 人：xiao <br>
 * 日 期：2015年7月13日 10:36:34 <br>
 * 描 述：微信关注好友后台管理类 
 * 版 本 号：v1.0
 */
public class AresService {
	public static final String HOST = "http://" + ServiceConfig.AresServiceHost + "/";
	/**
	 * 微信关注好友列表
	 * @param c
	 */
	public static void queryWeixinUsers(BaseController c){
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey");
		String searchValue = c.getPara("searchValue");
		Rpage<WeixinUserInfoBean> pageList = WeixinUserInfoBean.getlist(page, 10, searchKey, searchValue);
		c.setAttr("list", pageList);
		c.setAttr("searchKey", searchKey);
		c.setAttr("searchValue", searchValue);
		c.render("files/weixin/weixin_usreinfo_list.html");
	
	}
	/**
	 * 查询红包活动详情
	 * @param c
	 */
	public static void weixinUserDtails(BaseController c){
		String id=c.getPara("id");
		WeixinUserInfoBean object=WeixinUserInfoBean.getObject(id);
		c.setAttr("object", object);
		c.render("files/weixin/weixin_usreinfo_details.html");
	}
	/**
	 * 同步微信好友
	 * xiao
	 * 2015年7月8日 09:30:36
	 */
	public static void syncFriends(BaseController c){
		c.renderAlert("同步成功！", "/weixin/query_weixin_users");
	}
}
