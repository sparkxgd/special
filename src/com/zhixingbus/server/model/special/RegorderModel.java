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
import com.zhixingbus.server.utils.Utils;
/**
 *19.学生报名订单tb_regorder
 * @author xiao
 *
 */
public class RegorderModel extends Model<RegorderModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_regorder";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getstudentRegId() {
		return get("studentRegId");
	}
	public void setstudentRegId(String studentRegId) {
		set("studentRegId", studentRegId);
	}
	public String getstudentPayId() {
		return get("studentPayId");
	}
	public void setstudentPayId(int studentPayId) {
		set("studentPayId", studentPayId);
	}
	public Date getupdateTime() {
		return get("updateTime");
	}
	public void setupdateTime(Date updateTime) {
		set("updateTime", updateTime);
	}
	public Date getcreateTime() {
		return get("createTime");
	}
	public void setcreateTime(Date createTime) {
		set("createTime", createTime);
	}
	public int getregNumber() {
		return get("regNumber");
	}
	public void setregNumber(int regNumber) {
		set("regNumber", regNumber);
	}
	public int getType() {
		return get("type");
	}
	public void setType(int type) {
		set("type", type);
	}
	public BigDecimal gettotal() {
		return get("total");
	}
	public void settotal(BigDecimal total) {
		set("total" ,total);
	}
	public BigDecimal getmustCost() {
		return get("mustCost");
	}
	public void setmustCost(BigDecimal mustCost) {
		set("mustCost" ,mustCost);
	}
	public BigDecimal getactualCost() {
		return get("actualCost");
	}
	public void setactualCost(BigDecimal actualCost) {
		set("actualCost" ,actualCost);
	}
	public BigDecimal getuncollected() {
		return get("uncollected");
	}
	public void setuncollected(BigDecimal uncollected) {
		set("uncollected" ,uncollected);
	}
	public BigDecimal getdiscountCost() {
		return get("discountCost");
	}
	public void setdiscountCost(BigDecimal discountCost) {
		set("discountCost" ,discountCost);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark", remark);
	}
	public int getyouxiaoNumber() {
		return get("youxiaoNumber");
	}
	public int getfinishNumber() {
		return get("finishNumber");
	}
	//报名上课状态
	public String getStateStr() {
		int you=getyouxiaoNumber();
		int finish=getfinishNumber();
		String s="异常";
		if(you>=finish){
			if(you-finish<4){
				s="正常(提醒交费)";
			}
			s="正常(提醒交费)";
		}
		return s;
	}
	
	public static final RegorderModel dao = new RegorderModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<RegorderModel> getList(int pageNumber, int pageSize,String studentRegId,String studentId,String key) {
		String sele_sql="select f.*,a.regTime,b.`name` as studentName ,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName  ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" f LEFT JOIN ").append(StudentRegistrationModel.tableName);
		from_sql.append(" a ON f.studentRegId=a.id LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		
		
		from_sql.append(" where f.state=0 ");
		
		if(!StringUtil.isBlankOrEmpty(studentRegId)){
			from_sql.append(" and a.id='").append(studentRegId).append("'");
		}
		if(!StringUtil.isBlankOrEmpty(studentId)){
			from_sql.append(" and a.studentId='").append(studentId).append("'");
		}
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.`name` like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("f.createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static RegorderModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static RegorderModel getModelById(Object id){
		StringBuffer sql=new StringBuffer();
		sql.append("select f.*,a.regTime,b.`name` as studentName ,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName  ");
		
		sql.append("from ").append(tableName).append(" f LEFT JOIN ").append(StudentRegistrationModel.tableName);
		sql.append(" a ON f.studentRegId=a.id LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		sql.append(" where f.id=? ");
		
		return dao.findFirst(sql.toString() , id);
	}
	
	public static List<RegorderModel> getList(String orderIds[]){
		StringBuffer sql=new StringBuffer();
		StringBuffer orders=new StringBuffer();
		for(int i=0;i<orderIds.length;i++){
			orders.append("'");
			orders.append(orderIds[i]);
			orders.append("',");
		}
		if(orders.length()>0){
			orders.replace(orders.length()-1, orders.length(), "");
		}else {
			orders.append("''");
		}
		sql.append("select f.*,a.regTime,b.`name` as studentName ,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName  ");
		sql.append("from ").append(tableName).append(" f LEFT JOIN ").append(StudentRegistrationModel.tableName);
		sql.append(" a ON f.studentRegId=a.id LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		sql.append(" where  f.id in ("+orders.toString()+")");
		
		return  dao.find(sql.toString());
	}
	@Before(Tx.class)
	public static boolean save(final List<RegorderModel> list){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				for(RegorderModel m:list){
					m.save();
				}
				return true;
			}
			});
		return succeed;
	}
	/**
	 * 保存
	 * @return
	 */
	public static boolean save(int classTypeId,int gradeId,int studentPayId,int studentId,int subjectId,BigDecimal total,BigDecimal actualCost,BigDecimal mustCost,BigDecimal uncollected,BigDecimal discountCost,int regNumber, Date regTime,String remark){
		RegorderModel model=new RegorderModel();
		model.setactualCost(actualCost);
		model.setmustCost(mustCost);
		model.setuncollected(uncollected);
		model.setdiscountCost(discountCost);
		model.settotal(total);
		model.setregNumber(regNumber);
		model.setremark(remark);
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
	public static boolean update(int id,int classTypeId,int gradeId,int studentPayId,int studentId,int subjectId,BigDecimal total,BigDecimal actualCost,BigDecimal mustCost,BigDecimal uncollected,BigDecimal discountCost,int regNumber, Date regTime,String remark){
		RegorderModel model=RegorderModel.getById(id);
		if(model==null){
			return false;
		}
		model.setactualCost(actualCost);
		model.setmustCost(mustCost);
		model.setuncollected(uncollected);
		model.setdiscountCost(discountCost);
		model.settotal(total);
		model.setregNumber(regNumber);
		model.setremark(remark);
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
	public static boolean rgorderDiscount(String id,BigDecimal discountCost,int regNumber,String remark){
		RegorderModel model=RegorderModel.getById(id);
		if(model==null){
			return false;
		}
		//查找报名的年级和类型
		StudentRegistrationModel regModel=StudentRegistrationModel.getById(model.getstudentRegId());
		if(regModel==null){
			return false;
		}
		//查找价格
		RegistrationFeeModel feeModel=RegistrationFeeModel.getObject(regModel.getclassTypeId(), regModel.getgradeId());
		if(feeModel==null){
			return false;
		}
		
		BigDecimal price=feeModel.getprice();
		BigDecimal finalTotal=new BigDecimal(price.doubleValue()*regNumber);
		
		BigDecimal total=model.gettotal();
		int reg=model.getregNumber();
		String rem=model.getremark();
		
		BigDecimal finalmust=new BigDecimal(total.doubleValue()*regNumber/reg-discountCost.doubleValue());
		
		BigDecimal actualCost=model.getactualCost();
		
		//有效课时=（实交金额+优惠金额）/价格
		
		BigDecimal youxiao=new BigDecimal((actualCost.doubleValue()+discountCost.doubleValue())/price.doubleValue());
		
		regModel.setyouxiaoNumber(youxiao.intValue());
		
		model.settotal(finalTotal);
		model.setmustCost(finalmust);
		model.setdiscountCost(discountCost);
		model.setregNumber(regNumber);
		model.setremark(rem+"|"+remark);
		model.setupdateTime(new Date());
		try {
			regModel.update();
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
	public static boolean update(String id,BigDecimal actualCost,int regNumber,String remark){
		RegorderModel model=RegorderModel.getById(id);
		if(model==null){
			return false;
		}
//		//查找报名的年级和类型
//		StudentRegistrationModel regModel=StudentRegistrationModel.getById(model.getstudentRegId());
//		if(regModel==null){
//			return false;
//		}
//		//查找价格
//		RegistrationFeeModel feeModel=RegistrationFeeModel.getObject(regModel.getclassTypeId(), regModel.getgradeId());
//		if(feeModel==null){
//			return false;
//		}
//		
//		BigDecimal price=feeModel.getprice();
//		BigDecimal finalTotal=new BigDecimal(price.doubleValue()*regNumber);
//		
//		BigDecimal total=model.gettotal();
//		int reg=model.getregNumber();
//		String rem=model.getremark();
//		
//		BigDecimal finalmust=new BigDecimal(total.doubleValue()*regNumber/reg-discountCost.doubleValue());
//		
//		BigDecimal actualCost=model.getactualCost();
//		
//		//有效课时=（实交金额+优惠金额）/价格
//		
//		BigDecimal youxiao=new BigDecimal((actualCost.doubleValue()+discountCost.doubleValue())/price.doubleValue());
//		
//		regModel.setyouxiaoNumber(youxiao.intValue());
//		
//		model.settotal(finalTotal);
//		model.setmustCost(finalmust);
//		model.setdiscountCost(discountCost);
//		model.setregNumber(regNumber);
//		model.setremark(rem+"|"+remark);
//		model.setupdateTime(new Date());
//		try {
//			regModel.update();
//			model.update();
//		} catch (Exception e) {
//			return false;
//		}
		return true;
	}
	/**
	 * 学生续报
	 * @param list
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final int classType,final int grade,final int regNumber,final String studentRegId,final String remark){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
					//查找一下，报名价格
					RegistrationFeeModel feeModel=RegistrationFeeModel.getObject(classType,grade);
					if(feeModel==null)
						return false;
					//计算产品总价
					BigDecimal price=feeModel.getprice();
					double tal=price.doubleValue()*regNumber;
					BigDecimal total=new BigDecimal(tal);
					//生成报名订单,其他参数，数据库有默认值
					RegorderModel s=new RegorderModel();
					s.setId(Utils.createNumUUID());
					s.settotal(total);
					s.setmustCost(total);
					s.setstudentRegId(studentRegId);
					s.setregNumber(regNumber);
					s.setremark(remark);
					s.setType(1);//0新增，1续报，2新生
					s.setcreateTime(new Date());
					s.setupdateTime(new Date());
					s.save();
				return true;
			}
			});
		return succeed;
	}
	/**
	 * 退费
	 * @return
	 */
	public static boolean exitCost(String id,BigDecimal exitCost,String remark){
		RegorderModel m=RegorderModel.getById(id);
		if(m==null){
			return false;
		}
		double vaild_cost = m.getactualCost().doubleValue()
				- m.getmustCost().doubleValue();
		if (exitCost.doubleValue()>vaild_cost) {
			return false;
		}
//		//查找报名的年级和类型
//		StudentRegistrationModel regModel=StudentRegistrationModel.getById(model.getstudentRegId());
//		if(regModel==null){
//			return false;
//		}
//		//查找价格
//		RegistrationFeeModel feeModel=RegistrationFeeModel.getObject(regModel.getclassTypeId(), regModel.getgradeId());
//		if(feeModel==null){
//			return false;
//		}
//		
//		BigDecimal price=feeModel.getprice();
//		BigDecimal finalTotal=new BigDecimal(price.doubleValue()*regNumber);
//		
//		BigDecimal total=model.gettotal();
//		int reg=model.getregNumber();
//		String rem=model.getremark();
//		
//		BigDecimal finalmust=new BigDecimal(total.doubleValue()*regNumber/reg-discountCost.doubleValue());
//		
//		BigDecimal actualCost=model.getactualCost();
//		
//		//有效课时=（实交金额+优惠金额）/价格
//		
//		BigDecimal youxiao=new BigDecimal((actualCost.doubleValue()+discountCost.doubleValue())/price.doubleValue());
//		
//		regModel.setyouxiaoNumber(youxiao.intValue());
//		
//		model.settotal(finalTotal);
//		model.setmustCost(finalmust);
//		model.setdiscountCost(discountCost);
//		model.setregNumber(regNumber);
//		model.setremark(rem+"|"+remark);
//		model.setupdateTime(new Date());
//		try {
//			regModel.update();
//			model.update();
//		} catch (Exception e) {
//			return false;
//		}
		return true;
	}
}
