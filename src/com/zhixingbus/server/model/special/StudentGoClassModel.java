package com.zhixingbus.server.model.special;

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
 *16.学生上课信息tb_studentGoClass
 * @author xiao
 *
 */
public class StudentGoClassModel extends Model<StudentGoClassModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_studentGoClass";
	public int getId() {
		return get("id");
	}
	public void setId(int id) {
		set("id", id);
	}
	public int getstudentId() {
		return get("studentId");
	}
	public void setstudentId(int studentId) {
		set("studentId", studentId);
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
	public int getavaild(){
		return get("availd");
	}
	public String getavaildStr(){
		int availd=getavaild();
		if(availd==1){
			return "<span style='color: green'>有效</span>";
		}else{
			return "<span style='color: red'>无效</span>";
	}
	}
	public void setavaild(int availd)
	{
		set("availd",availd);
	}
	public int getlogin(){
		return get("login");
	}
	public String getloginStr(){
		int m=getlogin();
		if(m==1){
			return "<span style='color: green'>已签到</span>";
		}
		else{
			return "<span style='color: red'>未签到</span>";
		}
	}
	public void setlogin(int login)
	{
		set("login",login);
	}
	public String getClassRcordId() {
		return get("classRcordId");
	}
	public void setClassRcordId(String classRcordId) {
		set("classRcordId", classRcordId);
	}
	public int gettype(){
		return get("type");
	}
	public String gettypeStr(){
		int type=gettype();
		if(type==1){
			return "正常上课";
		}
		else{
			return "补课";
		}
	}
	
	public static final StudentGoClassModel dao = new StudentGoClassModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentGoClassModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.*,b.`name` as studentName ,c.`name` as className,d.`name` as classroom,e.`name` as employeeName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=a.classInfoId");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" d on a.classroomId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  remark like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentGoClassModel> getList_two(int pageNumber, int pageSize,String key,String key2) {
		String sele_sql="select a.*,f.gotime,f.overTime,f.no,f.type,b.`name` as studentName ,c.`name` as className,d.`name` as classroom,e.`name` as employeeName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassRcordModel.tableName).append(" f on f.id=a.classRcordId");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=f.classInfoId");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" d on f.classroomId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=f.employeeId");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.`name` like '%"+key+"%' " );
		}
		if(!StringUtil.isBlankOrEmpty(key2)){
			from_sql.append("and  c.`name` like '%"+key2+"%' " );
		}
		from_sql.append(" order by ").append("a.id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentGoClassModel> getList_three(int pageNumber, int pageSize,String classRcordId,int no,String key) {
		String sele_sql="select a.*,f.gotime,f.overTime,f.no,f.type,b.`name` as studentName ,c.`name` as className,d.`name` as classroom,e.`name` as employeeName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassRcordModel.tableName).append(" f on f.id=a.classRcordId");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=f.classInfoId");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" d on f.classroomId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=f.employeeId");
		from_sql.append(" where a.classRcordId=? and  f.no =?");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.`name` like '%"+key+"%' " );
		}
		from_sql.append(" order by ").append("a.id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),classRcordId,no);
	} 
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentGoClassModel> studentGoClassInfos(int pageNumber, int pageSize,String classInfoId,String studentId,String searchKey) {
		String sele_sql="select a.*,f.gotime,f.overTime,f.no,f.type,d.`name` as classroom,e.`name` as employeeName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(ClassRcordModel.tableName).append(" f on f.id=a.classRcordId");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" d on f.classroomId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=f.employeeId");
		from_sql.append(" where f.classInfoId=? and a.studentId=?");
		from_sql.append(" order by ").append("a.id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),classInfoId,studentId);
	} 
	public static StudentGoClassModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static StudentGoClassModel getModel(Object id){
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select a.*,f.gotime,f.overTime,f.no,f.type,b.`name` as studentName ,c.`name` as className,d.`name` as classroom,e.`name` as employeeName ");;
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassRcordModel.tableName).append(" f on f.id=a.classRcordId");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=f.classInfoId");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" d on f.classroomId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=f.employeeId");
		from_sql.append(" where a.id=? ");
		return dao.findFirst(from_sql.toString(), id);
	}
	/**
	 * 保存
	 * @return
	 */
	public static boolean save(int classInfoId,int classroomId,int studentId,int employeeId,Date gotime,String remark){
		StudentGoClassModel model=new StudentGoClassModel();
		model.setstudentId(studentId);
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
	public static boolean update(int id,int classInfoId,int classroomId,int studentId,int employeeId,Date gotime,String remark){
		StudentGoClassModel model=StudentGoClassModel.getById(id);
		if(model==null){
			return false;
		}
		model.setstudentId(studentId);
		model.setremark(remark);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean save(StudentGoClassModel m){
		try {
			m.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	@Before(Tx.class)
	public static boolean save(final List<StudentGoClassModel> list,final ClassRcordModel rcord){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				rcord.save();
				for(StudentGoClassModel m:list){
					m.save();
				}
				return true;
			}
			});
		return succeed;
	}
	@Before(Tx.class)
	public static boolean studentSignUpdate(final int id, final int login,final int availd) {
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				StudentGoClassModel sign=StudentGoClassModel.getById(id);
				if(sign==null){
					return false;
				}
				sign.setlogin(login);
				sign.setavaild(availd);
				sign.update();

				return true;
			}
		});
		return succeed;
	}
}
