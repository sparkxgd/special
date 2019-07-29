package com.zhixingbus.server.controller;

import com.zhixingbus.server.model.DownloadModel;
import com.zhixingbus.server.model.SuggestModel;
import com.zhixingbus.server.model.VisitorModel;
import com.zhixingbus.server.service.www.WebService;

/**
 * 产品官网web页面控制
 * @author xiao
 *2015年12月16日 15:49:18
 */
public class WebController extends BaseController {
	/**
	 * 加载首页
	 */
	public void index() {
		WebService.index(this);
	}
	/**
	 * 详情页面
	 */
	public void detail(){
		WebService.detail(this);
	}
	/**
	 * 详情列表页面
	 */
	public void detailList(){
		WebService.detailList(this);
	}
	/**
	 * 新闻详情页面
	 */
	public void see(){
		WebService.see(this);
	}
	/**
	 * 发表评论
	 */
	public void comment(){
		WebService.comment(this);
	}
	/**
	 * 发表评论点赞
	 */
	public void clicklike(){
		WebService.clicklike(this);
	}
	/**
	 * 记录访客
	 */
	public void visitor(){
		String remoteIp=getPara("remoteIp");
		String ip=getRequest().getRemoteAddr();
		String addr=getPara("addr");
		String browser=getPara("browser");
		VisitorModel.saveModel(remoteIp, ip, addr, browser);
		renderJson();
	}
	/**
	 * 意见反馈
	 */
	public void suggest(){
		String remoteIp=getPara("remoteIp");
		String addr=getPara("addr");
		String content=getPara("content");
		String title=getPara("title");
		String email=getPara("email");
		String name=getPara("name");
		SuggestModel.saveModel( name, email, title, content, remoteIp, addr);
		renderJson();
	}
	/**
	 * 实时公交
	 */
	public void zhixingbus() {
		render("zhixingbus.html");	
	}
	/**
	 * 下载
	 */
	public void download(){
		int t=getParaToInt("t",1);//默认是android
		String remoteIp=getPara("remoteIp");
		String ip=getRequest().getRemoteHost();
		String addr=getPara("addr");
		String browser=getPara("browser");
		if(t==1){//android
			DownloadModel.saveModel(remoteIp, ip, addr, browser,"android");
			renderFile("ZhiXingBus.apk");
		}else if(t==2){//ios
			DownloadModel.saveModel(remoteIp, ip, addr, browser,"ios");
			redirect("http://itunes.apple.com/us/app/id975077001");
		}else{
			renderFile("ZhiXingBus.apk");
		}
	}
}