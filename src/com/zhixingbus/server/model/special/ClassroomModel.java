package com.zhixingbus.server.model.special;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 12.教室信息tb_classroom
 * @author xiao
 *
 */
public class ClassroomModel extends Model<ClassroomModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_classroom";
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
	public int getcampusId(){
		return get("campusId");
	}
	public void setcampusId(int campusId)
	{
		set("campusId",campusId);
	}
	public int getseats(){
		return get("seats");
	}
	public void setseats(int seats)
	{
		set("seats",seats);
	}
	public Date getupdateTime() {
		return get("updateTime");
	}
	public void setupdateTime(Date updateTime) {
		set("updateTime" ,updateTime);
	}
	public int getstate(){
		return get("state");
	}
	public String getstateStr(){
		int state=getstate();
		if(state==1){
			return "弃用";
		}
		else{
			return "可用";
		}
	}
	public void setstate(int state)
	{
		set("state",state);
	}
	public static final ClassroomModel dao = new ClassroomModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassroomModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("updateTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static ClassroomModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static List<ClassroomModel> getList(String key) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select *  from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		return dao.find(from_sql.toString());
	}
	/**
	 * 保存
	 * @return
	 */
	public static boolean save(String name,int seats,int campusId){
		ClassroomModel model=new ClassroomModel();
		model.setName(name);
		model.setcampusId(campusId);
		model.setseats(seats);
		model.setupdateTime(new Date());
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新
	 * @return
	 */
	public static boolean update(int id,String name,int seats,int campusId,int state){
		ClassroomModel model=ClassroomModel.getById(id);
		if(model==null){
			return false;
		}
		model.setName(name);
		model.setcampusId(campusId);
		model.setseats(seats);
		model.setstate(state);
		model.setupdateTime(new Date());
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
