package com.zhixingbus.server.model.special;

import java.math.BigDecimal;
import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 13.报名费用信息tb_registrationFee
 * @author xiao
 *
 */
public class RegistrationFeeModel extends Model<RegistrationFeeModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_registrationFee";
	public int getId() {
		return get("id");
	}
	public void setId(int id) {
		set("id", id);
	}
	public int getclassTypeId() {
		return get("classTypeId");
	}
	public void setclassTypeId(int classTypeId) {
		set("classTypeId", classTypeId);
	}
	public int getgradeId() {
		return get("gradeId");
	}
	public void setgradeId(int gradeId) {
		set("gradeId", gradeId);
	}
	public BigDecimal getprice() {
		return get("price");
	}
	public void setprice(BigDecimal price) {
		set("price", price);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" , remark);
	}
	public Date getupdateTime() {
		return get("updateTime");
	}
	public void setupdateTime(Date updateTime) {
		set("updateTime" ,updateTime);
	}
	public static final RegistrationFeeModel dao = new RegistrationFeeModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<RegistrationFeeModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.*,d.`name` as classTypeName,e.`name` as gradeName  ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		
		
		from_sql.append(" where 1=1 ");

		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  remark like '%"+key+"%'");
		}
		from_sql.append(" order by ").append(" a.id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static RegistrationFeeModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static RegistrationFeeModel getModelById(Object id){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.*,d.`name` as classTypeName,e.`name` as gradeName  ");
		sql.append("from ").append(tableName).append(" a ");
		sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		sql.append(" where a.id=? ");
		return dao.findFirst(sql.toString(), id);
	}
	/**
	 * 保存
	 * @return
	 */
	public static boolean save(BigDecimal price,int classTypeId,int gradeId,String remark){
		
		RegistrationFeeModel model2=RegistrationFeeModel.getObject(classTypeId, gradeId);
		if(model2!=null){
			return false;
		}
		RegistrationFeeModel model=new RegistrationFeeModel();
		model.setclassTypeId(classTypeId);
		model.setgradeId(gradeId);
		model.setprice(price);
		model.setremark(remark);
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
	public static boolean update(int id,BigDecimal price,String remark){
		RegistrationFeeModel model=RegistrationFeeModel.getById(id);
		if(model==null){
			return false;
		}
		model.setprice(price);
		model.setremark(remark);
		model.setupdateTime(new Date());
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新
	 * @return
	 */
	public static boolean update(int id,BigDecimal price,int classTypeId,int gradeId,String remark){
		RegistrationFeeModel model=RegistrationFeeModel.getById(id);
		if(model==null){
			return false;
		}
//		model.setclassTypeId(classTypeId);
//		model.setgradeId(gradeId);
		model.setprice(price);
		model.setremark(remark);
		model.setupdateTime(new Date());
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static RegistrationFeeModel getObject(int classTypeId,int gradeId){
		return dao.findFirst("select *  from " + tableName + " where classTypeId = ? and gradeId=?" , classTypeId,gradeId);
	}
}
