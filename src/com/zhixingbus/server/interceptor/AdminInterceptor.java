package com.zhixingbus.server.interceptor;

import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.zhixingbus.server.config.Constans;
import com.zhixingbus.server.utils.CookieUtils;

/**
 * 后台数据管理拦截器
 * @author xiao
 * 2015年12月22日 09:38:34
 *
 */
public class AdminInterceptor extends BaseInterceptor {
	public void intercept(ActionInvocation ai) {
		Controller c = ai.getController();
		//检查cookie是否有效
		String cookie = c.getCookie(Constans.cookieName);
		if (!CookieUtils.validateUserLoginCookie(cookie)) {// 判断cookie是否有效
			c.redirect("/admin/login");
			return;
		}
		ai.invoke();
		
	}

}
