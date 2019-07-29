package com.zhixingbus.server.model.special;

import java.math.BigDecimal;
import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *tv_pay_detail学生交费报表
 * @author xiao
 *
 */
public class VPayDetailModel extends Model<VPayDetailModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tv_pay_detail";
	public String getId() {
		return get("id");
	}
	public String getregorderId() {
		return get("regorderId");
	}
	public String getpayId() {
		return get("payId");
	}
	public String getremark() {
		return get("remark");
	}
	public Date getcreateTime() {
		return get("createTime");
	}
	public BigDecimal gettotal() {
		return get("total");
	}
	public String getoperator() {
		return get("operator");
	}
	public String getclasstype() {
		return get("classtype");
	}
	public String getsubject() {
		return get("subject");
	}
	public String getgrade() {
		return get("grade");
	}
	public String getstudent() {
		return get("student");
	}
	public int getmodel() {
		return get("model");
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
	public String getpayerTel() {
		return get("payerTel");
	}
	public String getpayer() {
		return get("payer");
	}
	public static final VPayDetailModel dao = new VPayDetailModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<VPayDetailModel> getList(int pageNumber, int pageSize,String key,String startTime,String endTime) {
		String sele_sql="select  *  ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  student like '%"+key+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(startTime)){
			from_sql.append("and  createTime>'"+startTime+"' and createTime<'"+endTime+"'");
		}
		from_sql.append(" order by ").append("createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static double getTatol(String key,String startTime,String endTime) {
		StringBuffer from_sql=new StringBuffer("select SUM(total) as total ");
		from_sql.append("from ").append(tableName);
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  student like '%"+key+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(startTime)){
			from_sql.append("and  createTime>'"+startTime+"' and createTime<'"+endTime+"'");
		}
		VPayDetailModel m=dao.findFirst(from_sql.toString());
		double total=0;
		if(m!=null){
			total=m.gettotal().doubleValue();
		}
		return total;
	} 
	public static VPayDetailModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
}
