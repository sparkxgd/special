package com.zhixingbus.server.controller;

import java.util.List;

import com.zhixingbus.server.redis.RedisBean;
import com.zhixingbus.server.redis.RedisManager;
import com.zhixingbus.server.utils.JSONUtil;

/**
 * redis
 * @author xiao
 *
 */
public class RedisController extends BaseController {
	public void index() {
		renderText("感谢你使用贵州智行公交实时公交！");
	}
	/**
	 * 进入redis页面
	 */
	public void redis() {
		List<RedisBean> redisList=RedisManager.redisList;
		setAttr("list", JSONUtil.object2JacksonString(redisList));
		render("files/redis/redis.html");	
	}
	/**
	 * 查询
	 */
	public void query() {
		/**
		 * weixincache
		 * token_key
		 */
		String host=getPara("host");
		String key=getPara("key");
		String cacheName=getPara("cacheName");
//		RedisKit.getKeys(cacheName,host);
//		RedisKit.get(cacheName, key, host);
		renderJson("","");
	}
}
