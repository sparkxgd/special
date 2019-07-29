package com.zhixingbus.server.bean;

import com.zhixingbus.server.utils.json.Data;

public class AreaCityBean extends Data<AreaCityBean> {
	private String id;
	private String name;
	private String provinceId;
	private String citycode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

}
