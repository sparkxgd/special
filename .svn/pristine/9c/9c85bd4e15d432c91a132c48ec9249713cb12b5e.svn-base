package com.zhixingbus.server.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;

public class AdminModel extends Model<AdminModel> {
	public static final AdminModel dao = new AdminModel();
	private static final long serialVersionUID = 1L;
	private static final String tableName = "tb_admin_x";
	public int getId() {
		return get("id");
	}
	public int getType() {
		return get("type");
	}
	public void setType(int type){
		set("type", type);
	}
	public String getTypeStr(){
		int ty=getType();
		if(ty==1){
			return "超级管理员";
		}else{
			return "普通管理员";
		}
	}
	public String getPassword() {
		return get("password");
	}

	public String getName() {
		return get("name");
	}

	public AdminModel setPassword(String password) {
		set("password", password);
		return this;
	}

	public AdminModel setName(String name) {
		set("name", name);
		return this;
	}
	/**
	 * 查询显示
	 * @return
	 */
	public static List<AdminModel> getList() {
		StringBuffer sql=new StringBuffer("select * ");
		sql.append("from ").append(tableName);
		return dao.find(sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<AdminModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static AdminModel getById(int id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static AdminModel findByName(String name){
		return dao.findFirst("select * from " + tableName + " where name = ? " , name);
	}
	/**
	 * 保持
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
	public static boolean saveModel(String name,String password,int type){
		AdminModel model=new AdminModel();
		model.setName(name);
		model.setPassword(password);
		model.setType(type);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean updateModel(int id,String password,int type){
		AdminModel model=AdminModel.getById(id);
		if(model==null)
			return false;
		model.setPassword(password);
		model.setType(type);
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
	public static boolean delModel(int id){
		return dao.deleteById(id);
	}
	
}
