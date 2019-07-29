package com.zhixingbus.server.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;

public class ConfigModel extends Model<ConfigModel> {
	public static final ConfigModel dao = new ConfigModel();
	private static final long serialVersionUID = 1L;
	private static final String tableName = "tb_config";
	public String getId() {
		return get("id");
	}
	public void setId(String id){
		set("id", id);
	}
	public String getKey() {
		return get("key");
	}
	public void setKey(String key){
		set("key", key);
	}
	public String getValue() {
		return get("value");
	}
	public void setValue(String value){
		set("value", value);
	}
	public String getName() {
		return get("name");
	}
	public void setName(String name){
		set("name", name);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark){
		set("remark", remark);
	}
	public Date getUpdateTime() {
		return get("updateTime");
	}
	public void setUpdateTime(Date updateTime){
		set("updateTime", updateTime);
	}
	/**
	 * 查询显示
	 * @return
	 */
	public static List<ConfigModel> getList() {
		StringBuffer sql=new StringBuffer("select * ");
		sql.append("from ").append(tableName);
		return dao.find(sql.toString());
	}
	/**
	 * 查询key不重复的信息
	 * @return
	 */
	public static List<ConfigModel> getNotRepeat() {
		StringBuffer sql=new StringBuffer("select * ");
		sql.append("from ").append(tableName).append(" GROUP BY  `key`");
		return dao.find(sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ConfigModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and `key` like '%"+key+"%'");
		}
		from_sql.append(" order by id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static ConfigModel getById(Object id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	/**
	 * 根据`key`查找
	 * @param id
	 * @return
	 */
	public static ConfigModel getByKey(Object key){
		return dao.findFirst("select * from " + tableName + " where `key` = ? " , key);
	}
	/**
	 * 找到图文配置
	 * @return
	 */
	public static ConfigModel getTuwen(){
		return dao.findFirst("select * from " + tableName + " where `key` = ? " , "tuwen");
	}
	/**
	 * 找到列表配置
	 * @return
	 */
	public static ConfigModel getListInfo(){
		return dao.findFirst("select * from " + tableName + " where `key` = ? " , "list");
	}
	/**
	 * 找到图片轮换配置
	 * @return
	 */
	public static List<ConfigModel> getwrapperInfo(){
		return dao.find("select * from " + tableName + " where `key` = ? " , "wrapper");
	}
	/**
	 * 保持
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
	public static boolean saveModel(String key,String value,String name,String remark){
		ConfigModel model=new ConfigModel();
		model.setId(Utils.createNewUUID());
		model.setKey(key);
		model.setValue(value);
		model.setName(name);
		model.setRemark(remark);
		model.setUpdateTime(new Date());
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean updateModel(String id,String value,String remark){
		ConfigModel model=ConfigModel.getById(id);
		if(model==null)
			return false;
		model.setValue(value);
		model.setRemark(remark);
		model.setUpdateTime(new Date());
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public static boolean delModel(String id){
		return dao.deleteById(id);
	}
	
}
