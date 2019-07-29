package com.zhixingbus.server.model.special;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.zhixingbus.server.utils.StringUtil;
/**
 *20.学生报名订单交费明细tb_regorderdetail
 * @author xiao
 *
 */
public class RegorderdetailModel extends Model<RegorderdetailModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_regorderdetail";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getregorderId() {
		return get("regorderId");
	}
	public void setregorderId(String regorderId) {
		set("regorderId", regorderId);
	}
	public String getpayId() {
		return get("payId");
	}
	public void setpayId(String payId) {
		set("payId", payId);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark", remark);
	}
	public Date getcreateTime() {
		return get("createTime");
	}
	public void setcreateTime(Date createTime) {
		set("createTime" ,createTime);
	}
	public BigDecimal gettotal() {
		return get("total");
	}
	public void settotal(BigDecimal total) {
		set("total" ,total);
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
	public static final RegorderdetailModel dao = new RegorderdetailModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<RegorderdetailModel> getList(int pageNumber, int pageSize,String regorderId,String key) {
		String sele_sql="select a.*,b.model,c.`name` as operator ,b.payer ,b.payerTel ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ");
		from_sql.append(StudentPayModel.tableName).append(" b on a.payId=b.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" c on b.operator=c.id");
		
		from_sql.append(" where  a.regorderId=? ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.payer like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),regorderId);
	} 
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<RegorderdetailModel> studentPayOrderInfos(int pageNumber, int pageSize,int studentId,String key) {
		String sele_sql="select a.*,b.model,c.`name` as operator ,b.payer ,d.`name` as subjectName,e.`name` as gradeName,f.`name` as classTypeName,b.payerTel ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ");
		from_sql.append(StudentPayModel.tableName).append(" b on a.payId=b.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" c on b.operator=c.id");
		from_sql.append(" LEFT JOIN  ").append(RegorderModel.tableName).append(" g on a.regorderId=g.id");
		from_sql.append(" LEFT JOIN  ").append(StudentRegistrationModel.tableName).append(" h on g.studentRegId=h.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" d on h.subjectId=d.id");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on h.gradeId=e.id");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" f on h.classTypeId=f.id");
		from_sql.append(" where  h.studentId=? ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.payer like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),studentId);
	}
	public static RegorderdetailModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	
	public static RegorderdetailModel getModelById(Object id){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.*,b.model,c.`name` as operator ,b.payer ,b.payerTel ");
		sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentPayModel.tableName).append(" b on a.payId=b.id");
		sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" c on b.operator=c.id");
		sql.append(" where  a.id=? ");
		return dao.findFirst(sql.toString() , id);
	}
	
	
	
	@Before(Tx.class)
	public static boolean save(final List<RegorderdetailModel> list,final StudentPayModel paymodel){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				paymodel.save();//外键的关系，必须要先保存缴费单，在保存明细
				for(RegorderdetailModel m:list){
					m.save();
				}
				return true;
			}
			});
		return succeed;
	}
	@Before(Tx.class)
	public static boolean regorderDetailUpdate(final String id,final BigDecimal actualCost,final String remark){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				RegorderdetailModel m=RegorderdetailModel.getById(id);
				if(m==null){
					return false;
				}
				StudentPayModel sp=StudentPayModel.getById(m.getpayId());
				if(sp==null){
					return false;
				}
				BigDecimal old_total=m.gettotal();
				//交费单总额=原来缴费单总额-（修改缴费单详情金额-原来交费单详情金额）
				BigDecimal finaltotal=new BigDecimal(sp.gettotal().doubleValue()-(actualCost.doubleValue()-old_total.doubleValue()));
				sp.settotal(finaltotal);
				m.settotal(actualCost);
				m.setremark(remark);
				m.update();
				sp.update();
				return true;
			}
			});
		return succeed;
	}
}
