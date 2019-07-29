package com.zhixingbus.server.bean.ares;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.AresService;
import com.zhixingbus.server.utils.DateUtils;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;

/**
 * 文 件 名 :com.zhixingbus.server.model.WeixinUserInfoModel.java <br>
 * 创 建 人：xiao <br>
 * 日 期：2015年7月4日 14:44:54 <br>
 * 修 改 人：xiao <br>
 * 日 期：2015年7月4日 14:44:58 <br>
 * 描 述：微信用户信息 <br>
 * 版 本 号：v1.0
 */
public class WeixinUserInfoBean extends Data<WeixinUserInfoBean> {
	public static final String GET_LIST = AresService.HOST + "admin/get_weixinUserInfo_list";// 获取列表
	public static final String GET_MODEL = AresService.HOST + "admin/get_weixinUserInfo_model";// 获取详情

	/**
	 * 是否关注公众号0：未关注，1：关注
	 * 
	 * @return
	 */
	private String subscribe;
	/**
	 * 用户的唯一标识
	 * 
	 * @return
	 */
	private String openid;

	/**
	 * 用户昵称
	 * 
	 * @return
	 */
	private String nickname;

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 * 
	 * @return
	 */
	private String sex;

	/**
	 * 语句
	 * 
	 * @return
	 */
	private String language;

	/**
	 * 普通用户个人资料填写的城市
	 * 
	 * @return
	 */
	private String city;

	/**
	 * 用户个人资料填写的省份
	 * 
	 * @return
	 */
	private String province;

	/**
	 * 国家，如中国为CN
	 * 
	 * @return
	 */
	private String country;

	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。
	 * 若用户更换头像，原有头像URL将失效。
	 * 
	 * @return
	 */
	private String headimgurl;
	/**
	 * 关注时间
	 * 
	 * @return
	 */
	private long subscribetime;
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 * 
	 * @return
	 */
	private String unionid;
	/**
	 * 更新时间
	 * @return
	 */
	private String updatetime;
	public String getSubscribe() {
		if("1".equals(subscribe)){
			return "已关注";
		}
		return "未关注";
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		if("1".equals(sex))
		{
			return "男";
		}else if("2".equals(sex))
		{
			return "女";
		}
		return "未知";
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribetime() {
		return DateUtils.date2String(new Date(subscribetime*1000L));
	}
	public void setSubscribetime(long subscribetime) {
		this.subscribetime = subscribetime;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdateime(String updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<WeixinUserInfoBean> getlist(int pageNumber, int pageSize, String searchKey, String searchValue) {
		HttpClient client = new HttpClient(GET_LIST);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		params.add(new BasicNameValuePair("searchKey", searchKey));
		params.add(new BasicNameValuePair("searchValue", searchValue));
		String r = null;
		Rpage<WeixinUserInfoBean> page = null;
		try {
			r = client.sendMsg(params);
			page = new WeixinUserInfoBean().jackson2page(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static WeixinUserInfoBean getObject(String id) {
		HttpClient client = new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r = null;
		WeixinUserInfoBean model = null;
		try {
			r = client.sendMsg(params);
			model = new WeixinUserInfoBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
}
