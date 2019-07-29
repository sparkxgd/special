package com.zhixingbus.server.controller;

import com.jfinal.core.Controller;

public abstract class BaseController extends Controller {
	
	public void renderAlert(String alertText){
		renderHtml("<script language=\"javascript\">alert('"+alertText+"');</script>");
	}
	
	public void renderAlertAndGoBack(String alertText){
		renderHtml("<script language=\"javascript\">alert('"+alertText+"');location='javascript:history.go(-1)';</script>");
	}
	
	public void renderAlert(String alertText,String goToUrl){
		renderHtml("<script language=\"javascript\">alert('"+alertText+"');location='"+goToUrl+"';</script>");
	}
	

}
