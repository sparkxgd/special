package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.service.constants.PromotionStatus;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;
/**
* 文 件 名 :com.zhixingbus.server.model.PromotionModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年8月28日 20:26:41
*<br>
* 描   述：促销活动
*<br>
* 版 本 号：v1.0
 */
public class PromotionBean extends Data<PromotionBean> {
	public static final String GET_LIST=RedPackService.HOST+"zeus/get_promotion_list";//获取列表
	public static final String GET_MODEL=RedPackService.HOST+"zeus/get_promotion_model";//获取详情
	public static final String GET_UPDATE=RedPackService.HOST+"zeus/promotion_update";//保存或更新
	public static final String GET_DEL=RedPackService.HOST+"zeus/promotion_del";//删除
	public static final String GET_ALL=RedPackService.HOST+"zeus/get_promotion_all";//获取所有列表
	public static final String UPDATE_STATUS=RedPackService.HOST+"zeus/promotion_update_status";//更新状态
	public static final String check_redpack=RedPackService.HOST+"zeus/check_redpack";//检查红包数据

	/**
	 * 主键
	 * @return
	 */
	private String id;
	/**
	 *  活动标题
	 * @return
	 */
	private String title;
	/**
	 * 活动内容
	 * @return
	 */
	private String content;
	/**
	 *创建时间
	 * @return
	 */
	private String createTime;
	/**
	 * 开始时间
	 * @return
	 */
	private String startTime;
	/**
	 * 结束时间
	 * @return
	 */
	private String endTime;
	/**
	 * 图片
	 * @return
	 */
	private String image;
	/**
	 * 活动说明
	 * @return
	 */
	private String url;
	/**
	 * 更新时间
	 * @return
	 */
	private String updateTime;
	/**
	 * 状态
	 * @return
	 */
	private int status;
	/**
	 * 状态
	 * @return
	 */
	private String statusName;
	/**
	 * 参与商家数
	 * @return
	 */
	private int businessCount;
	/**
	 * 参与红包活动的用户
	 * @return
	 */
	private int userCount;
	/**
	 * 产生的红包数量
	 * @return
	 */
	private int redPackCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusName() {
		statusName= PromotionStatus.getStatusDesc(status);
		return statusName;
	}
	public int getBusinessCount() {
		return businessCount;
	}
	public void setBusinessCount(int businessCount) {
		this.businessCount = businessCount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getRedPackCount() {
		return redPackCount;
	}
	public void setRedPackCount(int redPackCount) {
		this.redPackCount = redPackCount;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<PromotionBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue) {
			HttpClient client=new HttpClient(GET_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			String r=null;
			Rpage<PromotionBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new PromotionBean().jackson2page(r);
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
	public static PromotionBean getObject(String id) {
		HttpClient client=new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		PromotionBean model=null;
		try {
			r = client.sendMsg(params);
			model=new PromotionBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	@SuppressWarnings("rawtypes")
	public static boolean updateOrSave(Map<String, String[]> map) {
		HttpClient client=new HttpClient(GET_UPDATE);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			params.add(new BasicNameValuePair(k, v));
		}
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new PromotionBean().getReturnCode(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(returnCode==0){//定义0为成功
			return true;
		}
		return false;
	}
	public static boolean del(String id) {
		HttpClient client=new HttpClient(GET_DEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new PromotionBean().getReturnCode(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(returnCode==0){//定义0为成功
			return true;
		}
		return false;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static List<PromotionBean> getall() {
			HttpClient client=new HttpClient(GET_ALL);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			String r=null;
			List<PromotionBean> list=null;
			try {
				r = client.sendMsg(params);
				list=new PromotionBean().jackson2List(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
	}
	@SuppressWarnings("rawtypes")
	public static boolean updateStatus(Map<String, String[]> map,PromotionStatus status) {
		HttpClient client=new HttpClient(UPDATE_STATUS);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) map.get(k))[0];
			params.add(new BasicNameValuePair(k, v));
		}
		params.add(new BasicNameValuePair("status", String.valueOf(status.getCode())));
		String r=null;
		int returnCode=-1;
		try {
			r = client.sendMsg(params);
			returnCode=new BusinessRedPackActivityBean().getReturnCode(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(returnCode==0){//定义0为成功
			return true;
		}
		return false;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<PromotionBean> checkRedpack(int pageNumber,
			int pageSize,String starTime,String endTime) {
			HttpClient client=new HttpClient(check_redpack);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("startTime", starTime));
			params.add(new BasicNameValuePair("endTime", endTime));
			String r=null;
			Rpage<PromotionBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new PromotionBean().jackson2page(r);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return page;
	}
}
