package com.zhixingbus.server.bean.pandora;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.RedPackService;
import com.zhixingbus.server.service.constants.RedpackStatus;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;
import com.zhixingbus.server.utils.json.Rpage;
/**
* 文 件 名 :com.zhixingbus.server.model.UserRedPackLogModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：2015年6月3日下午4:53:52
*<br>
* 修 改 人：xiao
*<br>
* 日   期：2015年7月1日 15:59:51
*<br>
* 描   述：用户红包日志
*<br>
* 版 本 号：v1.0
 */
public class UserRedPackLogBean extends Data<UserRedPackLogBean> {
	public static final String GET_LIST=RedPackService.HOST+"zeus/get_redpacklog_list";//获取红包活动列表
	public static final String GET_MODEL=RedPackService.HOST+"zeus/get_redpacklog_model";//获取红包活动列表

	private String id;
	private String userId;
	private String nickName;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 访问时间
	 * @return
	 */
	private String accessTime;
	/**
	 * 访问连接
	 * @return
	 */
	private String accessUri;
	/**
	 * 红包id
	 * @return
	 */
	private String redpackId;
	
	/**
	 * @return
	 */
	private int statusold;
	/**
	 * @return
	 */
	private int statusnew;
	/**
	 * url带的参数
	 * @return
	 */
	private String parameters;
	/**
	 * 客户端ip
	 * 
	 * @return
	 */
	private String remoteAddr;
	/**
	 * 返回结果信息
	 * 
	 * @return
	 */
	private String returnInfo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	public String getAccessUri() {
		return accessUri;
	}
	public void setAccessUri(String accessUri) {
		this.accessUri = accessUri;
	}
	public String getRedpackId() {
		return redpackId;
	}
	public void setRedpackId(String redpackId) {
		this.redpackId = redpackId;
	}
	
	public String getStatusold() {
		return RedpackStatus.getStatusDesc(statusold);
	}
	public void setStatusold(int statusold) {
		this.statusold = statusold;
	}
	public String getStatusnew() {
		return RedpackStatus.getStatusDesc(statusnew);
	}
	public void setStatusnew(int statusnew) {
		this.statusnew = statusnew;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Rpage<UserRedPackLogBean> getlist(int pageNumber,
			int pageSize,String searchKey,String searchValue) {
			HttpClient client=new HttpClient(GET_LIST);
			List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("pageSize",  String.valueOf(pageSize)));
			params.add(new BasicNameValuePair("searchKey", searchKey));
			params.add(new BasicNameValuePair("searchValue", searchValue));
			String r=null;
			Rpage<UserRedPackLogBean> page=null;
			try {
				r = client.sendMsg(params);
				page=new UserRedPackLogBean().jackson2page(r);
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
	public static UserRedPackBean getObject(String id) {
		HttpClient client=new HttpClient(GET_MODEL);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		String r=null;
		UserRedPackBean model=null;
		try {
			r = client.sendMsg(params);
			model=new UserRedPackBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

}
