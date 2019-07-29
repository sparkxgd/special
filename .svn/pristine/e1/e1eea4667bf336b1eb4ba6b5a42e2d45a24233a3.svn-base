package com.zhixingbus.server.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 智行公交官网-样式
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class MasterplatCssModel extends Model<MasterplatCssModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_masterplat_css";
	
	public int getId() {
		return get("id");
	}
	public void setName(String name) {
		set("name", name);
	}
	public String getName() {
		return get("name");
	}
	public String getDec() {
		return get("dec");
	}
	public void setDec(String dec) {
		set("dec", dec);
	}
	public static final MasterplatCssModel dao = new MasterplatCssModel();
	/**
	 * 查询显示
	 * @return
	 */
	public static List<MasterplatCssModel> getList() {
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
	public static Page<MasterplatCssModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and dec like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static MasterplatCssModel getById(int id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	
	public static boolean saveModel(String name,String dec){
		MasterplatCssModel model=new MasterplatCssModel();
		model.setName(name);
		model.setDec(dec);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean updateModel(int id,String name,String dec){
		MasterplatCssModel model=MasterplatCssModel.getById(id);
		if(model==null)
			return false;
		model.setName(name);
		model.setDec(dec);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
