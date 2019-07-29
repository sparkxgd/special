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
 *18.学生报名信息tb_studentRegistration
 * @author xiao
 *
 */
public class StudentRegistrationModel extends Model<StudentRegistrationModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_studentRegistration";
	public int regNumber;
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public int getstudentId() {
		return get("studentId");
	}
	public void setstudentId(int studentId) {
		set("studentId", studentId);
	}
	public Date getregTime() {
		return get("regTime");
	}
	public void setregTime(Date regTime) {
		set("regTime", regTime);
	}
	public int getsubjectId() {
		return get("subjectId");
	}
	public void setsubjectId(int subjectId) {
		set("subjectId", subjectId);
	}
	public int getregNumber() {
		return regNumber;
	}
	public void setregNumber(int regNumber) {
		this.regNumber=regNumber;;
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
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark", remark);
	}

	public int getyouxiaoNumber() {
		return get("youxiaoNumber");
	}
	public void setyouxiaoNumber(int youxiaoNumber) {
		set("youxiaoNumber", youxiaoNumber);
	}
	public int getfinishNumber() {
		return get("finishNumber");
	}
	public void setfinishNumber(int finishNumber) {
		set("finishNumber", finishNumber);
	}
	//报名交费状态
	public String getStateStr() {
		int you=getyouxiaoNumber();
		int finish=getfinishNumber();
		String s="<span style='color: red'>已欠费</span>";
		if(you>=finish){
			if(you-finish<4){
				if(you==0){
					s="<span style='color: #8E388E'>未交费</span>";
				}else {
					s="<span style='color: blue'>提醒交费</span>";
				}
			}else {
				s="<span style='color: green'>正常</span>";
			}
		}
		return s;
	}
	public int getstuClassId(){
		return get("stuClassId");
	}
	//报名上课状态
	public String getstuClassStr() {
		Object regid=get("stuClassId");
		String s="<span style='color: red'>未排课</span>";
		if(regid!=null){
			int you=getyouxiaoNumber();
			int finish=getfinishNumber();
			if(you==finish&&you!=0){
				s="<span style='color: blue'>结束课程</span>";
			}else {
				s="<span style='color: green'>已排课</span>";
			}
			
		}
		return s;
	}
	public int getavaild(){
		return get("availd");
	}
	public String getavaildStr(){
		int availd=getavaild();
		if(availd==1){
			return "<span style='color: green'>有效</span>";
		}
		else{
			return "<span style='color: red'>无效</span>";
		}
	}
	public void setavaild(int availd)
	{
		set("availd",availd);
	}
	public static final StudentRegistrationModel dao = new StudentRegistrationModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentRegistrationModel> getList(int pageNumber, int pageSize,String key,int type) {
		String sele_sql="select a.*,b.`name` as studentName ,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName,f.id as stuClassId," +
				" (SELECT SUM(mustCost) FROM tb_regorder where studentRegId=a.id) as mustCost," +
				" (SELECT SUM(regNumber) FROM tb_regorder where studentRegId=a.id) as regNum," +
				"(SELECT SUM(actualCost) FROM tb_regorder where studentRegId=a.id) as actualCost ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		from_sql.append(" LEFT JOIN  ").append(StudentClassModel.tableName).append(" f on f.studentRegId=a.id");
		from_sql.append(" where 1=1");
			switch (type) {
			case 1://未交费
				from_sql.append(" and a.youxiaoNumber=0 and a.finishNumber=0 ");
				break;
			case 2://提醒交费
				from_sql.append(" and a.youxiaoNumber<a.finishNumber+4 and a.youxiaoNumber<>0");
				break;
			case 3://已欠费
				from_sql.append(" and a.youxiaoNumber<a.finishNumber ");
				break;
			default:
				break;
			}
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and  b.`name` like '%"+key+"%' ");
		}
		from_sql.append(" order by ").append("a.regTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static StudentRegistrationModel getTotalModel(String key,int type){
		StringBuffer sql=new StringBuffer();
		sql.append("select SUM(b.mustCost) as mustCost,SUM(b.actualCost) as actualCost from tb_studentRegistration a LEFT JOIN tb_regorder b on b.studentRegId=a.id ");
		sql.append(" LEFT JOIN ").append(StudentModel.tableName).append(" c on a.studentId=c.id ");
		sql.append(" where 1=1 ");
		switch (type) {
		case 1://未交费
			sql.append(" and a.youxiaoNumber=0 and a.finishNumber=0 ");
			break;
		case 2://提醒交费
			sql.append(" and a.youxiaoNumber<a.finishNumber+4 and a.youxiaoNumber<>0");
			break;
		case 3://已欠费
			sql.append(" and a.youxiaoNumber<a.finishNumber ");
			break;
		default:
			break;
		}
	if(!StringUtil.isBlankOrEmpty(key)){
		sql.append("and  c.`name` like '%"+key+"%'");
	}
		return dao.findFirst(sql.toString());
	}
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentRegistrationModel> studentRegInfos(int pageNumber, int pageSize,int studentId,String key) {
		String sele_sql="select a.*,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName,f.id as stuClassId," +
				" (SELECT SUM(mustCost) FROM tb_regorder where studentRegId=a.id) as mustCost," +
				" (SELECT SUM(regNumber) FROM tb_regorder where studentRegId=a.id) as regNum," +
				"(SELECT SUM(actualCost) FROM tb_regorder where studentRegId=a.id) as actualCost ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		from_sql.append(" LEFT JOIN  ").append(StudentClassModel.tableName).append(" f on f.studentRegId=a.id");
		
		
		from_sql.append(" where a.studentId=?");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.`name` like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.regTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),studentId);
	} 
//	/**
//	 * 分页查询显示
//	 * @param pageNumber
//	 * @param pageSize
//	 * @param key
//	 * @return
//	 */
//	public static Page<StudentRegistrationModel> getList(int pageNumber, int pageSize,String key) {
//		String sele_sql="select a.*,b.`name` as studentName ,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName,f.id as stuClassId  ";
//		StringBuffer from_sql=new StringBuffer();
//		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
//		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
//		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
//		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
//		from_sql.append(" LEFT JOIN  ").append(StudentClassModel.tableName).append(" f on f.studentRegId=a.id");
//		
//		
//		from_sql.append(" where 1=1 ");
//		if(!StringUtil.isBlankOrEmpty(key)){
//			from_sql.append("and  remark like '%"+key+"%'");
//		}
//		from_sql.append(" order by ").append("a.regTime desc");
//		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
//	}
	public static StudentRegistrationModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static StudentRegistrationModel getModel(Object id) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select a.*,b.`name` as studentName ,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as gradeName ");
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" e on e.id=a.gradeId");
		from_sql.append(" where a.id=? ");
		return dao.findFirst(from_sql.toString(),id);
	}
	public static StudentRegistrationModel getModel(Object studentId,Object subjectId,Object classTypeId,Object gradeId) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select * ");
		from_sql.append("from ").append(tableName);
		from_sql.append(" where availd=1 and studentId=? and subjectId=? and classTypeId=? and gradeId=?");
		return dao.findFirst(from_sql.toString(),studentId,subjectId,classTypeId,gradeId);
	}
	
	
	@Before(Tx.class)
	public static boolean save(final List<StudentRegistrationModel> list){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				for(StudentRegistrationModel m:list){
					//查找一下，报名价格
					RegistrationFeeModel feeModel=RegistrationFeeModel.getObject(m.getclassTypeId(),m.getgradeId());
					if(feeModel==null)
						return false;
					//计算产品总价
					int regNumber=m.getregNumber();
					BigDecimal price=feeModel.getprice();
					double tal=price.doubleValue()*regNumber;
					BigDecimal total=new BigDecimal(tal);
					//生成报名的第一张订单,其他参数，数据库有默认值
					RegorderModel s=new RegorderModel();
					s.setId(Utils.createNumUUID());
					s.settotal(total);
					s.setmustCost(total);
					s.setstudentRegId(m.getId());
					s.setregNumber(regNumber);
					s.setremark(m.getremark());
					s.setType(0);//0新增，1续报，2新生(这里还要判断一下？)
					s.setcreateTime(new Date());
					s.setupdateTime(new Date());
					m.save();//因为外键的关系，先保存报名表，在保存订单表
					s.save();
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
		StudentRegistrationModel model=new StudentRegistrationModel();
		model.setclassTypeId(classTypeId);
		model.setgradeId(gradeId);
		model.setstudentId(studentId);
		model.setsubjectId(subjectId);
		model.setregNumber(regNumber);
		model.setregTime(regTime);
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
		StudentRegistrationModel model=StudentRegistrationModel.getById(id);
		if(model==null){
			return false;
		}
		model.setclassTypeId(classTypeId);
		model.setgradeId(gradeId);
		model.setstudentId(studentId);
		model.setsubjectId(subjectId);
		model.setregNumber(regNumber);
		model.setregTime(regTime);
		model.setremark(remark);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public static boolean delModel(String id){
		StudentRegistrationModel model=StudentRegistrationModel.getById(id);
		if(model==null){
			return false;
		}
		model.setavaild(0);//1有效，0无效
		
		model.update();
		
		StringBuffer sql=new StringBuffer();
		sql.append("update ");
		sql.append(RegorderModel.tableName);
		sql.append(" set state=1,updateTime=?");
		sql.append(" where studentRegId=?");
		int count=Db.update(sql.toString(),new Date(),id);

		if(count>0){
			return true;
		}
		return false;
	}
}
