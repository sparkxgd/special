package com.zhixingbus.server.bean.poseidon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.zhixingbus.server.service.PoseidonService;
import com.zhixingbus.server.utils.http.HttpClient;
import com.zhixingbus.server.utils.json.Data;

/**
 * 
* 文 件 名 :com.pandora.server.model.poseidonUserInfoModel.java
*<br>
* 创 建 人：xiao
*<br>
* 日    期：20152015年8月14日上午11:57:38
*<br>
* 修 改 人：xiao
*<br>
* 日   期：20152015年8月14日上午11:57:38
*<br>
* 描   述：用户信息
*<br>
* 版 本 号：v1.0
 */
public class UserInfoBean extends Data<UserInfoBean>{
	private static final String GET_USER_INFO=PoseidonService.HOST+"red/user_info";//获取用户信息
	private static final String GET_USER_INFOS=PoseidonService.HOST+"red/user_infos";//获取用户信息列表
	public static final int SEX_DEFULT = -1;//未填写
	public static final int SEX_M = 0;//男
	public static final int SEX_F = 1;//女
	public static final int ISUS = 1;//非第三方用户名
    public static final int NOTUS = 0;//第三方用户名
	
    private String username;
    private String password;
    private String userId;
    private String nickname;
    private int sex;
    private String date;
    private int status;
    private String headPhoto;
    private String savePath;
    private String url;
    private String birthday;
    private int version;
    private String registerTime;
    public UserInfoBean(){
    	
    }
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * 获取用户信息
	 * @param userId
	 */
	public static UserInfoBean getUserInfo(String userId){
		HttpClient client=new HttpClient(GET_USER_INFO);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String r=null;
		UserInfoBean model=null;
		try {
			r = client.sendMsg(params);
			model=new UserInfoBean().jackson2Object(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 获取用户信息列表
	 * @param userId
	 */
	public static List<UserInfoBean> getUserInfos(String userId){
		if(StringUtil.isAllBlank(userId))
		{
			return new ArrayList<UserInfoBean>();
		}
		HttpClient client=new HttpClient(GET_USER_INFOS);
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String r=null;
		List<UserInfoBean> list=null;
		try {
			r = client.sendMsg(params);
			list=new UserInfoBean().jackson2List(r);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
