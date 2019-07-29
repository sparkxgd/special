package com.zhixingbus.server.controller;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.service.constants.PromotionStatus;


public class RedpackController extends BaseController {
	
	public void index() {
		renderText("欢迎来到贵州智行公交管理系统！");
	}
	/**
	 * 获取商家红包活动列表
	 * xiao
	 * 2015年8月20日 10:44:22
	 */
	public void query_business_redpack_activitys(){
		RedPackService.queryBusinessRedpackActivitys(this);
	}
	/**
	 * 查询商家红包活动详情
	 */
	public void business_redpack_activitys_details(){
		RedPackService.businessRedpackActivitysDtails(this);
	}
	/**
	 * 查询红包活动详情
	 */
	public void redpack_details(){
		RedPackService.redpackDtails(this);
	}
	/**
	 * 打开添加或编辑红包活动页面
	 * xiao
	 * 2015年7月17日 13:49:47
	 */
	public void open_edit_redpack_activity(){
		RedPackService.openEditRedpackActivity(this);
	}
	/**
	 * 打开添加红包活动页面
	 * xiao
	 * 2015年7月17日 13:49:47
	 */
	public void save_business_redpack_activity(){
		RedPackService.saveRedpackActivity(this);
	}
	/**
	 * 更新商家红包活动状态
	 */
	public void update_business_redpack_activity_status()
	{
		RedPackService.updateBusinessRedpackActivityStatus(this);
	}
	/**
	 * 查询红包列表
	 */
	public void query_redpacks(){
		RedPackService.queryRedpacks(this);
	}
	/**
	 * 查询红包列表
	 */
	public void query_businessinfos(){
		RedPackService.queryBusinessinfos(this);
	}
	/**
	 * 打开添加或编辑商家信息页面
	 * xiao
	 * 2015年7月17日 13:49:47
	 */
	public void open_edit_businessinfo(){
		RedPackService.openEditBusinessinfo(this);
	}
	/**
	 * 打开添加红包活动页面
	 * xiao
	 * 2015年7月17日 13:49:47
	 */
	public void save_businessinfo(){
		RedPackService.saveBusinessinfo(this);
	}
	/**
	 * 查询商家资金信息列表
	 */
	public void query_businessfundses(){
		RedPackService.queryBusinessFundses(this);
	}
	/**
	 * 打开商家充值页面
	 * xiao
	 * 2015年8月19日 11:35:23
	 */
	public void open_business_funds_recharge(){
		RedPackService.openBusinessFundsRecharge(this);
	}
	/**
	 * 保存充值金额
	 * xiao
	 * 2015年8月19日 15:17:33
	 */
	public void save_business_funds_recharge(){
		RedPackService.saveBusinessFundsRecharge(this);
	}
	/**
	 * 查询商家资金详情
	 */
	public void businessfunds_details(){
		RedPackService.businessfundsDtails(this);
	}
	/**
	 * 查询促销活动列表
	 */
	public void query_promotions(){
		RedPackService.queryPromotionos(this);
	}
	/**
	 * 打开添加或编辑促销活动信息页面
	 * xiao
	 * 2015年7月17日 13:49:47
	 */
	public void open_edit_promotion(){
		RedPackService.openEditPromotion(this);
	}
	/**
	 * 打开添加促销活动页面
	 * xiao
	 * 2015年7月17日 13:49:47
	 */
	public void save_promotion(){
		RedPackService.savePromotion(this);
	}
	/**
	 * 开始促销活动
	 */
	public void start_promotion()
	{
		RedPackService.update_promotion_status(this,PromotionStatus.PROCEEDING);
	}
	/**
	 * 关闭促销活动
	 */
	public void close_promotion()
	{
		RedPackService.update_promotion_status(this,PromotionStatus.CLOSED);
	}
	/**
	 * 查询微信交互日志信息列表
	 */
	public void query_weixinlog(){
		RedPackService.queryWeixinLogs(this);
	}
	/**
	 * 查询商家资金详情
	 */
	public void weixinlog_details(){
		RedPackService.weixinlogDtails(this);
	}
	/**
	 *检查红包数据
	 */
	public void check_redpack(){
		RedPackService.check_redpack(this);
	}
}
