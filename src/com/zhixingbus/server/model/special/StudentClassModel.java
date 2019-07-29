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
 * 15.学生班级信息tb_studentClass
 * 
 * @author xiao
 * 
 */
public class StudentClassModel extends Model<StudentClassModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_studentClass";
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
	public int getclassinfoId() {
		return get("classinfoId");
	}
	public void setclassinfoId(int classinfoId) {
		set("classinfoId", classinfoId);
	}
	public Date getintTime() {
		return get("intTime");
	}
	public void setintTime(Date intTime) {
		set("intTime" ,intTime);
	}
	public Date getoutTime() {
		return get("outTime");
	}
	public void setoutTime(Date outTime) {
		set("outTime" ,outTime);
	}
	public String getremark(){
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" ,remark);
	}
	public String getstudentRegId(){
		return get("studentRegId");
	}
	public void setstudentRegId(String studentRegId) {
		set("studentRegId" ,studentRegId);
	}
	public int getState(){
		return get("state");
	}
	public String getStateStr(){
		int state=getState();
		if(state==0){
			return "<span style='color: green'>在读</span>";
		}else{
			return "<span style='color: red'>已经退出</span>";
		}
	}
	public void setState(int state)
	{
		set("state",state);
	}
	
	public static final StudentClassModel dao = new StudentClassModel();
	
	/**
	 * 分页查询显示
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentClassModel> studentClassInfos(int pageNumber, int pageSize,int studentId,String key,String key2) {
		String sele_sql="select i.*,a.name,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName ," +
		"(SELECT COUNT(g.id) FROM tb_studentclass g where  g.state=0 and  g.classinfoId=a.id) as stuNo," +
		"(SELECT COUNT(y.id) from tb_studentgoclass y LEFT JOIN tb_classrcord x on y.classRcordId=x.id where x.classInfoId=a.id and y.studentId=i.studentId and y.availd=1) as classNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" i LEFT JOIN ");
		from_sql.append(ClassInfoModel.tableName).append(" a  on a.id=i.classinfoId ");
		from_sql.append("LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where i.studentId=? ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(key2)){
			from_sql.append("and  e.`name` like '%"+key2+"%'");
		}
		from_sql.append(" order by ").append("a.startTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),studentId);
	} 
	/**
	 * 分页查询显示
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentClassModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.* ,b.`name` as studentName ,c.`name` as classInfoName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=a.classinfoId");
		
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  remark like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 分页查询显示
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentClassModel> getList_two(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.* ,b.`name` as studentName ,c.`name` as classInfoName ," +
				"(SELECT COUNT(d.id) from tb_studentgoclass d LEFT JOIN tb_classrcord e on d.classRcordId=e.id where e.classInfoId=c.id and d.studentId=a.studentId and d.availd=1) as classNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=a.classinfoId");
		
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.`name` like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static Page<StudentClassModel> getList_three(int pageNumber, int pageSize,String classId) {
		String sele_sql="select a.* ,b.`name` as studentName ,c.`name` as classInfoName ," +
				"(SELECT COUNT(d.id) from tb_studentgoclass d LEFT JOIN tb_classrcord e on d.classRcordId=e.id where e.classInfoId=c.id and d.studentId=b.id) as classNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=a.classinfoId");
		
		from_sql.append(" where c.id=? and a.state=0 ");
		from_sql.append(" order by ").append("a.intTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),classId);
	}
	public static Page<StudentClassModel> classStudents(int pageNumber, int pageSize,int classId) {
		String sele_sql="select a.* ,b.`name` as studentName ,c.`name` as classInfoName ,f.tel,f.parentTel," +
		" (SELECT SUM(mustCost) FROM tb_regorder where studentRegId=a.studentRegId) as mustCost," +
		" (SELECT SUM(regNumber) FROM tb_regorder where studentRegId=a.studentRegId) as regNum," +
		"(SELECT SUM(actualCost) FROM tb_regorder where studentRegId=a.studentRegId) as actualCost ,"+
				"(SELECT COUNT(d.id) from tb_studentgoclass d LEFT JOIN tb_classrcord e on d.classRcordId=e.id where e.classInfoId=c.id and d.studentId=b.id) as classNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=a.classinfoId");
		from_sql.append(" LEFT JOIN  ").append(StudentInfoModel.tableName).append(" f on f.id=b.id");
		from_sql.append(" LEFT JOIN  ").append(StudentRegistrationModel.tableName).append(" g on g.id=a.studentRegId");
		
		from_sql.append(" where c.id=? and a.state=0 ");
		from_sql.append(" order by ").append("a.intTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),classId);
	}
	public static Page<StudentClassModel> classStudentInfos(int pageNumber, int pageSize,String classId) {
		String sele_sql="select a.* ,b.`name` as studentName ," +
				"(SELECT COUNT(d.id) from tb_studentgoclass d LEFT JOIN tb_classrcord e on d.classRcordId=e.id where e.classInfoId=a.classinfoId and d.studentId=b.id) as classNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		from_sql.append(" where a.classinfoId=? and a.state=0 ");
		from_sql.append(" order by ").append("a.intTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),classId);
	}
	
	public static StudentClassModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static StudentClassModel getModelId(Object id){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.*,b.name as className,c.name as studentName  from ").append(tableName).append(" a ");
		sql.append(" LEFT JOIN ").append(ClassInfoModel.tableName).append(" b on a.classinfoId=b.id ");
		sql.append(" LEFT JOIN ").append(StudentModel.tableName).append(" c on a.studentId=c.id ");
		sql.append(" where a.id=?");
		return dao.findFirst(sql.toString() , id);
	}
	public static StudentClassModel getById(Object id,int state){
		return dao.findFirst("select *  from " + tableName + " where id = ? and state=?" , id,state);
	}
	public static List<StudentClassModel> getList(int classinfoId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* ,b.`name` as studentName ");
		sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b on a.studentId=b.id");
		sql.append(" where a.classinfoId=? and state=0");
		sql.append(" order by ").append("a.intTime desc");
		return dao.find(sql.toString(),classinfoId);
	} 
	public static StudentClassModel getModel(int studentId,int classinfoId,int state){
		return dao.findFirst("select *  from " + tableName + " where studentId = ? and classinfoId=? and state=? " , studentId,classinfoId,state);
	}
	/**
	 * 根据科目，年级，类型，找在读的班级0：在读，1离开
	 * 
	 * @param studentId
	 * @param subjectId
	 * @param gradeId
	 * @param classTypeId
	 * @return
	 */
	public static StudentClassModel getModel(Object studentId,Object subjectId,Object gradeId,Object classTypeId){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* ");
		sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(ClassInfoModel.tableName).append(" b on a.classinfoId=b.id");
		sql.append(" where a.state=0 and a.studentId=? and b.subjectId=? and gradeId=? and classTypeId=?");
		return dao.findFirst(sql.toString(),studentId,subjectId,gradeId,classTypeId);
	}
	/**
	 * 根据报名id，找在读的班级0：在读，1离开
	 * 
	 * @param studentId
	 * @param subjectId
	 * @param gradeId
	 * @param classTypeId
	 * @return
	 */
	public static StudentClassModel getModelByRegId(Object studentReg){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.* ");
		sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(ClassInfoModel.tableName).append(" b on a.classinfoId=b.id");
		sql.append(" where a.state=0 and a.studentRegId=?");
		return dao.findFirst(sql.toString(),studentReg);
	}
	/**
	 * 保存
	 * 
	 * @return
	 */
	public static boolean save(int studentId,int classinfoId,String studentRegId){
		StudentClassModel model=new StudentClassModel();
		model.setstudentId(studentId);
		model.setclassinfoId(classinfoId);
		model.setintTime(new Date());
		model.setstudentRegId(studentRegId);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新
	 * 
	 * @return
	 */
	public static boolean update(int id,int studentId,int classinfoId,Date intTime,Date outTime,String remark){
		StudentClassModel model=StudentClassModel.getById(id);
		if(model==null){
			return false;
		}
		model.setstudentId(studentId);
		model.setclassinfoId(classinfoId);
		model.setintTime(intTime);
		model.setoutTime(outTime);
		model.setremark(remark);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean outClass(int id){
		StudentClassModel model=StudentClassModel.getById(id,0);// 0：在读，1离开
		if(model==null){
			return false;
		}
		model.setoutTime(new Date());
		model.setState(1);// 0：在读，1离开
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	@Before(Tx.class)
	public static boolean changeClass(final int student_id,final StudentClassModel old_class,final int new_class_id){
		boolean succeed = Db.tx(new IAtom() {
		@Override
		public boolean run() throws SQLException {
			old_class.setoutTime(new Date());
			old_class.setState(1);// 0：在读，1离开
			
			StudentClassModel model=new StudentClassModel();
			model.setstudentId(student_id);
			model.setclassinfoId(new_class_id);
			model.setintTime(new Date());
			model.setstudentRegId(old_class.getstudentRegId());
			model.setState(0);
			try {
				old_class.update();
				model.save();
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		});
		return succeed;
	}
}
