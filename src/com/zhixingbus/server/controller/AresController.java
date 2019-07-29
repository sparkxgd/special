package com.zhixingbus.server.controller;

import com.zhixingbus.server.service.AresService;



public class AresController extends BaseController {
	
	public void index() {
		renderText("欢迎来到贵州智行公交管理系统！");
	}
	/*******************微信服务号******************************************/
	/**
	 *  微信关注好友列表
	 */
	public void query_weixin_users(){
		AresService.queryWeixinUsers(this);
	}
	/**
	 *  微信关注好友列表
	 */
	public void del_weixin_user(){
//		AresService.delWeixinUser(this);
	}
	/**
	 * 查询详情
	 */
	public void weixin_user_details(){
		AresService.weixinUserDtails(this);
	}
	/**
	 * 同步微信好友
	 * xiao
	 * 2015年7月8日 09:30:36
	 */
	public void sync_friends(){
		AresService.syncFriends(this);
	}
	/**
	 * 获取微信自定义菜单
	 * xiao
	 * 2015年7月13日 17:17:02
	 */
	public void get_defined_menus_one(){
//		WeiXinAdminMenuService.get_defined_menus_one(this);
		renderText("本功能正在开发中，敬请等待。。。。。。");
		}
	/**
	 * 获取微信自定义菜单
	 * xiao
	 * 2015年7月13日 17:17:02
	 */
	public void get_defined_menus_two(){
//		WeiXinAdminMenuService.get_defined_menus_two(this);
	}
	/**
	 * 打开自定菜单编辑页面
	 * xiao
	 * 2015年7月13日 17:17:02
	 */
	public void open_defined_menu(){
//		WeiXinAdminMenuService.open_defined_menu(this);
	}
	/**
	 * 保存自定菜单
	 * xiao
	 * 2015年7月14日 15:18:21
	 */
	public void save_defined_menu(){
//		WeiXinAdminMenuService.save_defined_menu(this);
	}
	/**
	 * 到微信服务器下载自定义菜单
	 * xiao
	 * 2015年7月13日 17:17:02
	 */
	public void load_defined_menus(){
//		WeiXinAdminMenuService.load_defined_menus(this);
	}
	/**
	 * 更新微信服务器的菜单
	 * xiao
	 * 2015年7月13日 17:17:02
	 */
	public void update_defined_menus_to_weixin(){
//		WeiXinAdminMenuService.update_defined_menus_to_weixin(this);
	}
}
