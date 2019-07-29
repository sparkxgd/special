package com.zhixingbus.server.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;

public class CooperationModel extends Model<CooperationModel> {
	public static final CooperationModel dao = new CooperationModel();
	private static final long serialVersionUID = 1L;
	private static final String tableName = "tb_cooperation";
	public String getId() {
		return get("id");
	}
	public void setId(String id){
		set("id", id);
	}
	public String getname() {
		return get("name");
	}
	public void setname(String name){
		set("name", name);
	}
	public String getwebsite() {
		return get("website");
	}
	public void setwebsite(String website){
		set("website", website);
	}
	/**
	 * 查询显示
	 * @return
	 */
	public static List<CooperationModel> getList() {
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
	public static Page<CooperationModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and name like '%"+key+"%'");
		}
		from_sql.append(" order by id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static CooperationModel getById(Object id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	/**
	 * 保持
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
	public static boolean saveModel(String name,String website){
		CooperationModel model=new CooperationModel();
		model.setId(Utils.createNewUUID());
		model.setname(name);
		model.setwebsite(website);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean updateModel(String id,String name,String website){
		CooperationModel model=CooperationModel.getById(id);
		if(model==null)
			return false;
		model.setname(name);
		model.setwebsite(website);
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
