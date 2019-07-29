package com.zhixingbus.server.utils.json;

import java.util.ArrayList;
import java.util.List;


public class ML<T>{
	private List<T> rList;
	private T rModel;
	private Rpage<T> rPage;
	private int returnCode;
	public ML(){
		
	}
	public List<T> getrList() {
		if(rList==null)
			return new ArrayList<T>();
		else
			return rList;
	}
	public void setrList(List<T> rList) {
		this.rList = rList;
	}
	public T getrModel() {
		return rModel;
	}
	public void setrModel(T rModel) {
		this.rModel = rModel;
	}
	public Rpage<T> getrPage() {
		return rPage;
	}
	public void setrPage(Rpage<T> page) {
		this.rPage = page;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	
}
