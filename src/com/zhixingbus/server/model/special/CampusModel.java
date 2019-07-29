package com.zhixingbus.server.model.special;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 12.校区信息tb_campus
 * @author xiao
 *
 */
public class CampusModel extends Model<CampusModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_campus";
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
	public String getaddr() {
		return get("addr");
	}
	public void setaddr(String addr) {
		set("addr" , addr);
	}
	public int getemployeeId(){
		return get("employeeId");
	}
	public void setemployeeId(int employeeId)
	{
		set("employeeId",employeeId);
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
			return "未使用";
		}
		else{
			return "使用";
		}
	}
	public void setstate(int state)
	{
		set("state",state);
	}
	public static final CampusModel dao = new CampusModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<CampusModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.*,b.`name` as mangerName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" b on a.employeeId=b.id");
		
		
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("updateTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static CampusModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static List<CampusModel> getList(String key) {
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
	public static boolean save(String name,int seats,int employeeId,String addr){
		CampusModel model=new CampusModel();
		model.setName(name);
		model.setemployeeId(employeeId);
		model.setaddr(addr);
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
	public static boolean update(int id,String name,int seats,int employeeId,String addr,int state){
		CampusModel model=CampusModel.getById(id);
		if(model==null){
			return false;
		}
		model.setName(name);
		model.setemployeeId(employeeId);
		model.setaddr(addr);
		model.setseats(seats);
		model.setupdateTime(new Date());
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
