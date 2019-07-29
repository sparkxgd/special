package com.zhixingbus.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhixingbus.server.bean.pandora.BusinessFundsBean;
import com.zhixingbus.server.bean.pandora.BusinessFundsLogBean;
import com.zhixingbus.server.bean.pandora.BusinessInfoBean;
import com.zhixingbus.server.bean.pandora.BusinessRedPackActivityBean;
import com.zhixingbus.server.bean.pandora.PromotionBean;
import com.zhixingbus.server.bean.pandora.RedpackActivityLogBean;
import com.zhixingbus.server.bean.pandora.RedpackPayorderBean;
import com.zhixingbus.server.bean.pandora.UserRedPackBean;
import com.zhixingbus.server.bean.pandora.UserRedPackLogBean;
import com.zhixingbus.server.bean.pandora.WeixinLogBean;
import com.zhixingbus.server.bean.poseidon.AreaCityBean;
import com.zhixingbus.server.bean.poseidon.UserInfoBean;
import com.zhixingbus.server.config.ServiceConfig;
import com.zhixingbus.server.controller.BaseController;
import com.zhixingbus.server.service.constants.ActivityType;
import com.zhixingbus.server.service.constants.PromotionStatus;
import com.zhixingbus.server.service.constants.RedpackActivityStatus;
import com.zhixingbus.server.utils.DateUtils;
import com.zhixingbus.server.utils.RedpackUtil;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.json.Rpage;

/**
 * 
 * 文 件 名 :com.zhixingbus.server.weixin.RedPackService.java <br>
 * 创 建 人：xiao <br>
 * 日 期：2015年6月2日16:38:05 <br>
 * 修 改 人：xiao <br>
 * 日 期：2015年7月9日 16:11:38 <br>
 * 描 述：红包后台代理管理 <br>
 * 版 本 号：v1.0
 */
public class RedPackService {
	public static final String HOST = "http://" + ServiceConfig.RedPackServiceHost + "/";
	private static List<AreaCityBean> cityopenlist = new ArrayList<AreaCityBean>();

	/**
	 * 获取商家红包活动列表
	 * 
	 * @param c
	 */
	public static void queryBusinessRedpackActivitys(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey","-1");
		String searchValue = c.getPara("searchValue","");
		String activitTtype = c.getPara("activitTtype");
		Rpage<BusinessRedPackActivityBean> list = BusinessRedPackActivityBean.getlist(page, 10, searchKey, searchValue, activitTtype);
		if (cityopenlist==null||cityopenlist.size() < 1) {
			cityopenlist = AreaCityBean.queryOpenCity();
		}
		for (int i = 0; i < list.getList().size(); i++) {
			for (AreaCityBean city : cityopenlist) {
				if (city.getId().equals(list.getList().get(i).getCityId())) {
					list.getList().get(i).setCityId(city.getName());
					break;
				}
			}
		}
		c.setAttr("activitTtype", activitTtype);
		c.setAttr("searchKey", searchKey);
		c.setAttr("searchValue", searchValue);
		c.setAttr("list", list);
		c.render("files/red_pack/business_redpack_activity_list.html");
	}

	/**
	 * 查询商家红包活动详情
	 * 
	 * @param c
	 */
	public static void businessRedpackActivitysDtails(BaseController c) {
		String id = c.getPara("id");
		int page = c.getParaToInt("page", 1);
		int page2 = c.getParaToInt("page2", 1);
		BusinessRedPackActivityBean object = BusinessRedPackActivityBean.getObject(id);
		Rpage<UserRedPackBean> list = UserRedPackBean.getlist(page, 10, id, ActivityType.BUSINESS.getCode());
		StringBuffer sb = new StringBuffer();
		for (UserRedPackBean r : list.getList()) {
			sb.append(r.getUserid());
			sb.append("|");
		}
		List<UserInfoBean> userList = UserInfoBean.getUserInfos(sb.toString());
		for (int i = 0; i < list.getList().size(); i++) {
			for (UserInfoBean u : userList) {
				if (StringUtil.isBlankOrEmpty(list.getList().get(i).getUserid()) || !list.getList().get(i).getUserid().equals(u.getUserId())) {
					continue;
				} else {
					list.getList().get(i).setNickName(u.getNickname());
				}
			}
		}
		if (cityopenlist.size() < 1) {
			cityopenlist = AreaCityBean.queryOpenCity();
		}
		for (AreaCityBean city : cityopenlist) {
			if (city.getId().equals(object.getCityId())) {
				object.setCityId(city.getName());
				break;
			}
		}
		
		Rpage<RedpackActivityLogBean> loglist = RedpackActivityLogBean.getlist(page2, 10, id);

		c.setAttr("list", list);
		c.setAttr("loglist", loglist);
		c.setAttr("object", object);
		c.render("files/red_pack/business_redpack_activity_details.html");
	}

	/**
	 * 查询红包详情
	 * 
	 * @param c
	 */
	public static void redpackDtails(BaseController c) {
		String id = c.getPara("id");
		int page = c.getParaToInt("page", 1);
		int page2 = c.getParaToInt("page2", 1);
		UserRedPackBean object = UserRedPackBean.getObject(id);
		Rpage<UserRedPackLogBean> list = UserRedPackLogBean.getlist(page, 10, id, id);
		StringBuffer sb = new StringBuffer();
		for (UserRedPackLogBean r : list.getList()) {
			sb.append(r.getUserId());
			sb.append("|");
		}
		sb.append(object.getUserid());
		List<UserInfoBean> userList = UserInfoBean.getUserInfos(sb.toString());
		for (int i = 0; i < list.getList().size(); i++) {
			for (UserInfoBean u : userList) {
				if (StringUtil.isBlankOrEmpty(object.getNickName())&&!StringUtil.isBlankOrEmpty(list.getList().get(i).getUserId())&&list.getList().get(i).getUserId().equals(object.getUserid())) {
					object.setNickName(u.getNickname());
				}
				if (StringUtil.isBlankOrEmpty(list.getList().get(i).getUserId()) || !list.getList().get(i).getUserId().equals(u.getUserId())) {
					continue;
				} else {
					list.getList().get(i).setNickName(u.getNickname());
				}
			}
		}
		
		Rpage<RedpackPayorderBean> payOrderList = RedpackPayorderBean.getlist(page2, 10, id);
		c.setAttr("page2List", payOrderList);
		c.setAttr("list", list);
		c.setAttr("object", object);
		c.render("files/red_pack/redpack_details.html");
	}

	/**
	 * 删除红包活动
	 * 
	 * @param c
	 */
	public static void delRedpackActivity(BaseController c) {
		String id = c.getPara("id");
		String activitTtype = c.getPara("activitTtype");
		if (BusinessRedPackActivityBean.del(id)) {
			c.renderAlert("删除成功！", "/redpack/query_business_redpack_activitys?activitTtype=" + activitTtype);
		} else {
			c.renderAlert("删除失败！", "/redpack/query_business_redpack_activitys?activitTtype=" + activitTtype);
		}
	}

	/**
	 * 删除红包
	 * 
	 * @param c
	 */
	public static void delRedpack(BaseController c) {
		String id = c.getPara("id");
		if (UserRedPackBean.del(id)) {
			c.renderAlert("删除成功！", "/redpack/query_redpacks");
		} else {
			c.renderAlert("删除失败！", "/redpack/query_redpacks");
		}
	}

	/**
	 * 打开添加红包活动页面
	 * 
	 * @param c
	 */
	public static void openEditRedpackActivity(BaseController c) {
		String id = c.getPara("id");
		String isedit = c.getPara("isedit");
		BusinessRedPackActivityBean object = BusinessRedPackActivityBean.getObject(id);
		if (cityopenlist.size() < 1) {
			cityopenlist = AreaCityBean.queryOpenCity();
		}
		List<BusinessInfoBean> blist = BusinessInfoBean.getall();
		List<PromotionBean> plist = PromotionBean.getall();
		c.setAttr("cityopenlist", cityopenlist);
		c.setAttr("blist", blist);
		c.setAttr("plist", plist);
		c.setAttr("object", object);
		c.setAttr("isedit", isedit);// 0：表示添加界面，1：表示编辑页面
		c.render("files/red_pack/activity_edit.html");
	}

	/**
	 * 保存商家红包活动
	 * 
	 * @param c
	 */
	public static void saveRedpackActivity(BaseController c) {
		String totalAmount = c.getPara("totalAmount");
		String redPackNum = c.getPara("redPackNum");
		String winningWord = c.getPara("winningWord");
		String content = c.getPara("content");
		String cityId = c.getPara("cityId");
		String businessId = c.getPara("businessId");
		String availableMoney = c.getPara("availableMoney");
		/* 检查数据的正确性 */
		if (StringUtil.isBlankOrEmpty(totalAmount)) {
			c.renderAlertAndGoBack("红包总金额不能为空！");
			return;
		}
		if (StringUtil.isBlankOrEmpty(redPackNum)) {
			c.renderAlertAndGoBack("红包总数不能为空！");
			return;
		}
		if (StringUtil.isBlankOrEmpty(winningWord)) {
			c.renderAlertAndGoBack("广告辞不能为空！");
			return;
		}
		if (StringUtil.isBlankOrEmpty(content)) {
			c.renderAlertAndGoBack("内容不能为空！");
			return;
		}
		if (StringUtil.isBlankOrEmpty(cityId)) {
			c.renderAlertAndGoBack("城市不能为空！");
			return;
		}
		if (StringUtil.isBlankOrEmpty(businessId)||"-1".equals(businessId)) {
			c.renderAlertAndGoBack("请选择商家！");
			return;
		}
		/* 验证红包数 */
		if (!RedpackUtil.isInteger(totalAmount)) {
			c.renderAlertAndGoBack("红包总金额必须是整数！");
			return;
		}
		if (!RedpackUtil.isInteger(redPackNum)) {
			c.renderAlertAndGoBack("红包数必须是整数！");
			return;
		}
		/* 红包数必须大于0 */
		if (Integer.valueOf(redPackNum) < 1) {
			c.renderAlertAndGoBack("红包数必须大于零！");
			return;
		}
		/* 余额不足，请充值 */
		if (Double.valueOf(totalAmount) > Double.valueOf(availableMoney)) {
			c.renderAlertAndGoBack("余额不足，请充值");
			return;
		}
		/* 分配的红包必须大于等于1 */
		BigDecimal total = new BigDecimal(totalAmount);
		BigDecimal amount = new BigDecimal(redPackNum);
		double values = total.doubleValue() / amount.doubleValue();
		if (values < 1) {
			c.renderAlertAndGoBack("分配的红包必须大于等于1（分配的红包=红包总金额/红包数）");
			return;
		}
		if (BusinessRedPackActivityBean.updateOrSave(c.getParaMap())) {
			c.renderAlert("保存失败！", "/redpack/query_business_redpack_activitys?activitTtype=1");
		} else {
			c.renderAlert("保存成功！", "/redpack/query_business_redpack_activitys?activitTtype=1");
		}
	}

	/**
	 * 更新红包活动状态
	 * 
	 * @param c
	 */
	public static void updateBusinessRedpackActivityStatus(BaseController c) {
		String id = c.getPara("id");
		String status = c.getPara("status");
		String activitTtype = c.getPara("activitTtype");
		/* 检查数据的正确性 */
		if (StringUtil.isBlankOrEmpty(id)) {
			c.renderAlert("操作失败！", "/redpack/query_business_redpack_activitys?activitTtype=" + activitTtype);
			return;
		}
		if(!RedpackActivityStatus.isExit(status)){
			c.renderAlert("操作失败！", "/redpack/query_business_redpack_activitys?activitTtype=" + activitTtype);
			return;
		}
		if (!BusinessRedPackActivityBean.updateStatus(c.getParaMap())) {
			c.renderAlert("操作失败！", "/redpack/query_business_redpack_activitys?activitTtype=" + activitTtype);
			return;
		}
		c.renderAlert("操作成功！", "/redpack/query_business_redpack_activitys?activitTtype=" + activitTtype);
	}

	/**
	 * 查询红包列表
	 * 
	 * @param c
	 */
	public static void queryRedpacks(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey","-1");
		String searchValue = c.getPara("searchValue","");
		if("company".equals(searchKey)){
			/*先模糊查找用户基本信息*/
			/*根据查找到的用户去找对应的红包*/
		}
		Rpage<UserRedPackBean> list = UserRedPackBean.getlist(page, 10, searchKey, searchValue);
		StringBuffer sb = new StringBuffer();
		for (UserRedPackBean r : list.getList()) {
			sb.append(r.getUserid());
			sb.append("|");
		}
		List<UserInfoBean> userList = UserInfoBean.getUserInfos(sb.toString());
		for (int i = 0; i < list.getList().size(); i++) {
			for (UserInfoBean u : userList) {
				if (StringUtil.isBlankOrEmpty(list.getList().get(i).getUserid()) || !list.getList().get(i).getUserid().equals(u.getUserId())) {
					continue;
				} else {
					list.getList().get(i).setNickName(u.getNickname());
				}
			}
		}
		c.setAttr("list", list);
		c.setAttr("searchValue", searchValue);
		c.setAttr("searchKey", searchKey);
		c.render("files/red_pack/red_pack_list.html");
	}

	/**
	 * 查询商家信息列表
	 * 
	 * @param c
	 */
	public static void queryBusinessinfos(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey");
		Rpage<BusinessInfoBean> list = BusinessInfoBean.getlist(page, 10, searchKey, searchKey);
		c.setAttr("list", list);
		c.render("files/red_pack/businessinfo_list.html");
	}

	/**
	 * 打开添加红包活动页面
	 * 
	 * @param c
	 */
	public static void openEditBusinessinfo(BaseController c) {
		String id = c.getPara("id");
		String isedit = c.getPara("isedit");
		BusinessInfoBean object = BusinessInfoBean.getObject(id);
		c.setAttr("object", object);
		c.setAttr("isedit", isedit);// 0：表示添加界面，1：表示编辑页面
		c.render("files/red_pack/businessinfo_edit.html");
	}

	/**
	 * 保存商家信息
	 * 
	 * @param c
	 */
	public static void saveBusinessinfo(BaseController c) {
		String abbreviation = c.getPara("abbreviation");
		String company = c.getPara("company");
		if (StringUtil.isBlankOrEmpty(company)) {
			c.renderAlertAndGoBack("公司名称不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(abbreviation)) {
			c.renderAlertAndGoBack("公司简称不能为空！请重新填写");
			return;
		}
		if (BusinessInfoBean.updateOrSave(c.getParaMap())) {
			c.renderAlert("保存成功！", "/redpack/query_businessinfos");
		} else {
			c.renderAlert("保存失败！", "/redpack/query_businessinfos");
		}
	}

	/**
	 * 查询商家资金信息列表
	 * 
	 * @param c
	 */
	public static void queryBusinessFundses(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey","-1");
		String searchValue = c.getPara("searchValue","");
		Rpage<BusinessFundsBean> list = BusinessFundsBean.getlist(page, 10, searchKey, searchValue);
		c.setAttr("list", list);
		c.setAttr("searchKey", searchKey);
		c.setAttr("searchValue", searchValue);
		c.render("files/red_pack/business_funds_list.html");
	}

	/**
	 * 打开添加活动页面
	 * 
	 * @param c
	 */
	public static void openEditBusinessfunds(BaseController c) {
		String id = c.getPara("id");
		String isedit = c.getPara("isedit");
		BusinessFundsBean object = BusinessFundsBean.getObject(id);
		c.setAttr("object", object);
		c.setAttr("isedit", isedit);// 0：表示添加界面，1：表示编辑页面
		c.render("files/red_pack/businessinfo_edit.html");
	}

	/**
	 * 查询红包活动详情
	 * 
	 * @param c
	 */
	public static void businessfundsDtails(BaseController c) {
		String id = c.getPara("id");
		int page = c.getParaToInt("page", 1);
		BusinessFundsBean object = BusinessFundsBean.getObject(id);
		Rpage<BusinessFundsLogBean> list = BusinessFundsLogBean.getlist(page, 10, id);
		c.setAttr("list", list);
		c.setAttr("object", object);
		c.render("files/red_pack/business_funds_details.html");
	}

	/**
	 * 打开商家充值页面
	 * 
	 * @param c
	 */
	public static void openBusinessFundsRecharge(BaseController c) {
		String id = c.getPara("id");
		String isedit = c.getPara("isedit");
		BusinessFundsBean object = BusinessFundsBean.getObject(id);
		c.setAttr("object", object);
		c.setAttr("isedit", isedit);// 0：表示添加界面，1：表示编辑页面
		c.render("files/red_pack/business_funds_recharge.html");
	}

	/**
	 * 保存充值金额
	 * 
	 * @param c
	 */
	public static void saveBusinessFundsRecharge(BaseController c) {
		String changeMoney = c.getPara("changeMoney");
		String remark = c.getPara("remark");
		String id = c.getPara("id");
		if (StringUtil.isBlankOrEmpty(changeMoney)) {
			c.renderAlertAndGoBack("充值金额不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(remark)) {
			c.renderAlertAndGoBack("备注不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(id)) {
			c.renderAlertAndGoBack("数据出错（id）");
			return;
		}
		if (!RedpackUtil.isInteger(changeMoney)) {
			c.renderAlertAndGoBack("充值金额必须为整数！请重新填写");
			return;
		}
		if (BusinessFundsLogBean.updateOrSave(c.getParaMap())) {
			c.renderAlert("充值成功！", "/redpack/query_businessfundses");
		} else {
			c.renderAlert("充值失败！", "/redpack/query_businessfundses");
		}
	}

	/**
	 * 查询促销活动信息列表
	 * 
	 * @param c
	 */
	public static void queryPromotionos(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey","-1");
		String searchValue = c.getPara("searchValue","");
		Rpage<PromotionBean> list = PromotionBean.getlist(page, 10, searchKey, searchValue);
		c.setAttr("list", list);
		c.setAttr("searchKey", searchKey);
		c.setAttr("searchValue", searchValue);
		c.render("files/red_pack/promotion_list.html");
	}

	/**
	 * 打开添加促销活动页面
	 * 
	 * @param c
	 */
	public static void openEditPromotion(BaseController c) {
		String id = c.getPara("id");
		String isedit = c.getPara("isedit");
		PromotionBean object = null;
		if ("0".equals(isedit)) {
			object = PromotionBean.getObject(id);
		}
		c.setAttr("object", object);
		c.setAttr("isedit", isedit);// 0：表示添加界面，1：表示编辑页面
		c.render("files/red_pack/promotion_edit.html");
	}

	/**
	 * 保存促销活动信息
	 * 
	 * @param c
	 */
	public static void savePromotion(BaseController c) {
		final String content = c.getPara("content");
		final String endTime = c.getPara("endTime");
		final String startTime = c.getPara("startTime");
		final String title = c.getPara("title");
		final String url = c.getPara("url");
		final String image = c.getPara("image");
		if (StringUtil.isBlankOrEmpty(image)) {
			c.renderAlertAndGoBack("宣传图片不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(title)) {
			c.renderAlertAndGoBack("公司简称不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(startTime)) {
			c.renderAlertAndGoBack("开始时间不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(endTime)) {
			c.renderAlertAndGoBack("结束时间不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(content)) {
			c.renderAlertAndGoBack("活动内容不能为空！请重新填写");
			return;
		}
		if (StringUtil.isBlankOrEmpty(url)) {
			c.renderAlertAndGoBack("活动说明不能为空！请重新填写");
			return;
		}

		if (PromotionBean.updateOrSave(c.getParaMap())) {
			c.renderAlert("保存成功！", "/redpack/query_promotions");
		} else {
			c.renderAlert("保存失败！", "/redpack/query_promotions");
		}
	}

	/**
	 * 更新活动状态
	 * 
	 * @param c
	 */
	public static void update_promotion_status(BaseController c, PromotionStatus status) {
		String id = c.getPara("id");
		String searchKey = c.getPara("searchKey","-1");
		String searchValue = c.getPara("searchValue","");
		/* 检查数据的正确性 */
		if (StringUtil.isBlankOrEmpty(id)) {
			c.renderAlert("操作失败！", "/redpack/query_promotions?searchKey="+searchKey+"&searchValue="+searchValue);
			return;
		}
		if (!PromotionBean.updateStatus(c.getParaMap(), status)) {
			c.renderAlert("操作失败！", "/redpack/query_promotions?searchKey="+searchKey+"&searchValue="+searchValue);
			return;
		}
		c.renderAlert("操作成功！", "/redpack/query_promotions?searchKey="+searchKey+"&searchValue="+searchValue);
	}
	/**
	 * 查询微信交互日志信息列表
	 * 
	 * @param c
	 */
	public static void queryWeixinLogs(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String searchKey = c.getPara("searchKey","");
		String searchValue = c.getPara("searchValue","");
		Rpage<WeixinLogBean> list = WeixinLogBean.getlist(page, 10, searchKey, searchValue);
		c.setAttr("list", list);
		c.setAttr("searchKey", searchKey);
		c.setAttr("searchValue", searchValue);
		c.render("files/red_pack/weixinlog_list.html");
	}
	/**
	 * 查询红包活动详情
	 * 
	 * @param c
	 */
	public static void weixinlogDtails(BaseController c) {
		String id = c.getPara("id");
		WeixinLogBean object = WeixinLogBean.getObject(id);
		c.setAttr("object", object);
		c.render("files/red_pack/weixinlog_details.html");
	}
	/**
	 * 查询微信交互日志信息列表
	 * 
	 * @param c
	 */
	public static void check_redpack(BaseController c) {
		int page = c.getParaToInt("page", 1);
		String startTime = c.getPara("startTime","");
		String endTime = c.getPara("endTime","");
		Rpage<PromotionBean> list = PromotionBean.checkRedpack(page, 10, startTime, endTime);
		c.setAttr("list", list);
		if(StringUtil.isBlankOrEmpty(startTime)){
			//默认是今天的
			startTime=DateUtils.date2y_m_dString(new Date());
		}
		if(StringUtil.isBlankOrEmpty(endTime)){
			//默认是当前的
			endTime=DateUtils.date2String(new Date());
		}
		c.setAttr("endTime", endTime);
		c.setAttr("startTime", startTime);
		c.setAttr("userRedpackCount", 100);
		c.render("files/red_pack/redpack_report.html");
	}
}
