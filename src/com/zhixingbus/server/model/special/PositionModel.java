package com.zhixingbus.server.model.special;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *4.职位信息tb_position
 * @author xiao
 *
 */
public class PositionModel extends Model<PositionModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_position";
	public int getId() {
		return get("id");
	}
	public void setId(int id) {
		set("id", id);
	}
	public String getName() {
		return get("name");
	}
	public void setName(String name) {
		set("name" , name);
	}
	public static final PositionModel dao = new PositionModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<PositionModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static PositionModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static List<PositionModel> getList(String key) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select *  from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		return dao.find(from_sql.toString());
	}
	/**
	 * 
	 * @param name
	 * @param sex
	 * @param borthday
	 * @return
	 */
	public static boolean save(String name){
		PositionModel model=new PositionModel();
		model.setName(name);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新
	 * @param id
	 * @param name
	 * @return
	 */
	public static boolean update(int id,String name){
		PositionModel model=PositionModel.getById(id);
		if(model==null){
			return false;
		}
		model.setName(name);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
