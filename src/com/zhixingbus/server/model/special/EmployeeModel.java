package com.zhixingbus.server.model.special;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *3.职工信息tb_employee
 * @author xiao
 *
 */
public class EmployeeModel extends Model<EmployeeModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_employee";
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
	public int getSex(){
		return get("sex");
	}
	public String getSexStr(){
		int sex=getSex();
		if(sex==1){
			return "男";
		}
		else{
			return "女";
		}
	}
	public void setSex(int sex)
	{
		set("sex",sex);
	}
	
	public Date getCreatTime() {
		return get("creatTime");
	}
	public void setCreatTime(Date creatTime) {
		set("creatTime" ,creatTime);
	}
	public Date getBorthday() {
		return get("borthday");
	}
	public void setBorthday(Date borthday) {
		set("borthday" ,borthday);
	}
	public String getNativePlace() {
		return get("nativePlace");
	}
	public void setNativePlace(String nativePlace) {
		set("nativePlace" , nativePlace);
	}
	public String getIdentification() {
		return get("identification");
	}
	public void setIdentification(String identification) {
		set("identification" , identification);
	}
	public int getMaxid(){
		Object maxid=get("Maxid");
		if(maxid==null){
			return 0;
		}
		return get("Maxid");
	}
	public static final EmployeeModel dao = new EmployeeModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<EmployeeModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static EmployeeModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static int getMaxId(){
		EmployeeModel m=EmployeeModel.dao.findFirst("select MAX(id) as Maxid  from " + tableName+"");
		int id=m.getMaxid();
		id=id+1;
		return id;
	}
	/**
	 * 保存
	 * @param name
	 * @param sex
	 * @param borthday
	 * @return
	 */
	public static boolean save(String name,int sex,Date borthday,String identification,String nativePlace){
		EmployeeModel model=new EmployeeModel();
		EmployeeInfoModel info=new EmployeeInfoModel();
		
		int id=EmployeeModel.getMaxId();
		
		info.setId(id);
		model.setId(id);
		model.setName(name);
		model.setSex(sex);
		model.setCreatTime(new Date());
		model.setBorthday(borthday);
		model.setIdentification(identification);
		model.setNativePlace(nativePlace);
		try {
			model.save();
			info.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新
	 * @param id
	 * @param title
	 * @param type
	 * @param summary
	 * @param content
	 * @param author
	 * @param level
	 * @return
	 */
	public static boolean update(int id,String name,int sex,Date borthday,String identification,String nativePlace){
		EmployeeModel model=EmployeeModel.getById(id);
		if(model==null){
			return false;
		}
		model.setName(name);
		model.setSex(sex);
		model.setBorthday(borthday);
		model.setIdentification(identification);
		model.setNativePlace(nativePlace);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 保存
	 * @param name
	 * @param sex
	 * @param borthday
	 * @return
	 */
	public static boolean save(EmployeeModel em,EmployeeInfoModel i){
		try {
			em.save();
			i.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
