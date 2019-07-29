package com.zhixingbus.server.model.special;

import java.math.BigDecimal;
import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *17.学生缴费信息tb_studentPay
 * @author xiao
 *
 */
public class StudentPayModel extends Model<StudentPayModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_studentPay";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public int getoperator() {
		return get("operator");
	}
	public void setoperator(int operator) {
		set("operator", operator);
	}
	public int getmodel() {
		return get("model");
	}
	public void setmodel(int model) {
		set("model", model);
	}
	/**
	 * 1现金，2刷卡，3微信，4支付宝
	 * @return
	 */
	public String getmodelStr(){
		int key=getmodel();
		String s="";
		switch (key) {
		case 1:
			s="现金";
			break;
		case 2:
			s="刷卡";
			break;
		case 3:
			s="微信";
			break;
		case 4:
			s="支付宝";
			break;
		default:
			s="现金";
			break;
		}
		return s;
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark", remark);
	}
	public Date getpayTime() {
		return get("payTime");
	}
	public void setpayTime(Date payTime) {
		set("payTime" ,payTime);
	}
	public BigDecimal gettotal() {
		return get("total");
	}
	public void settotal(BigDecimal total) {
		set("total" ,total);
	}
	public String getpayer() {
		return get("payer");
	}
	public void setpayer(String payer) {
		set("payer", payer);
	}
	public String getpayerTel() {
		return get("payerTel");
	}
	public void setpayerTel(String payerTel) {
		set("payerTel", payerTel);
	}
	public static final StudentPayModel dao = new StudentPayModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentPayModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select  a.*,c.`name` as operatorName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" c on c.id=a.operator");
		
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  remark like '%"+key+"%'");
		}
		from_sql.append(" order by ").append(" a.payTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static StudentPayModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
}
