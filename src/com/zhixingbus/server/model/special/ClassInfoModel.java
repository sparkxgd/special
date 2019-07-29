package com.zhixingbus.server.model.special;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *14.班级信息tb_classInfo
 * @author xiao
 *
 */
public class ClassInfoModel extends Model<ClassInfoModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_classInfo";
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
		set("name", name);
	}
	public int getemployeeId() {
		return get("employeeId");
	}
	public void setemployeeId(int employeeId) {
		set("employeeId", employeeId);
	}
	public int getsubjectId() {
		return get("subjectId");
	}
	public void setsubjectId(int subjectId) {
		set("subjectId", subjectId);
	}
	public int gettimeSlot() {
		return get("timeSlot");
	}
	public void settimeSlot(int timeSlot) {
		set("timeSlot", timeSlot);
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
	public Date getstartTime() {
		return get("startTime");
	}
	public void setstartTime(Date startTime) {
		set("startTime" ,startTime);
	}
	public Date getendTime() {
		return get("endTime");
	}
	public void setendTime(Date endTime) {
		set("endTime" ,endTime);
	}
	public String getsubjectName(){
		return get("subjectName");
	}
	public Long getstudentNum(){
		return get("studentNum");
	}
	public String getgradeName(){
		return get("gradeName");
	}
	public int getstate() {
		return get("state");
	}
	public void setstate(int state) {
		set("state", state);
	}
	public String getStateStr(){
		String s="";
		int t=getstate();
		if(t==0){
			s="<span style='color: green'>正常</span>";
		}else {
			s="<span style='color: red'>结束</span>";
		}
		return s;
	}
	public static final ClassInfoModel dao = new ClassInfoModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassInfoModel> getList(int pageNumber, int pageSize,String key,String key2) {
		String sele_sql="select a.*,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(key2)){
			from_sql.append("and  e.`name` like '%"+key2+"%'");
		}
		from_sql.append(" order by ").append("a.startTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassInfoModel> classComprehensiveInfos(int pageNumber, int pageSize,String key,String key2) {
		String sele_sql="select a.*,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName ," +
				"(SELECT COUNT(g.id) FROM tb_studentclass g where  g.state=0 and  g.classinfoId=a.id) as stuNo," +
				"(SELECT COUNT(h.id) FROM tb_classrcord h where h.classinfoId=a.id) as claNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(key2)){
			from_sql.append("and  e.name like '%"+key2+"%'");
		}
		from_sql.append(" order by ").append("a.startTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassInfoModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.*,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  remark like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("startTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassInfoModel> getList_two(int pageNumber, int pageSize,String key,String key2) {
		String sele_sql="select a.*,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName ," +
				"(SELECT COUNT(g.id) FROM tb_studentclass g where  g.state=0 and  g.classinfoId=a.id) as stuNo," +
				"(SELECT COUNT(h.id) FROM tb_classrcord h where h.classinfoId=a.id) as claNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(key2)){
			from_sql.append("and  e.name like '%"+key2+"%'");
		}
		from_sql.append(" order by ").append("a.startTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static ClassInfoModel getModel(int id) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select a.*,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName "); 
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where a.id=? ");
		return dao.findFirst(from_sql.toString(), id);
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassInfoModel> getList(int pageNumber, int pageSize,String key,int gradeId,int subjectId,int classTypeId) {
		String sele_sql="select a.*,b.`name` as gradeName,c.`name` as subjectName,d.`name` as classTypeName,e.`name` as employeeName,f.`name` as timeSlotName," +
		"(SELECT COUNT(*) FROM tb_studentclass g where  g.state=0 and  g.classinfoId=a.id) as stuNo ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(GradeModel.tableName).append(" b on a.gradeId=b.id");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" c on c.id=a.subjectId");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" d on a.classTypeId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" LEFT JOIN  ").append(TimeslotModel.tableName).append(" f on f.id=a.timeSlot");
		from_sql.append(" where a.state=0 and a.gradeId=? and a.subjectId=? and a.classTypeId=?");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.startTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),gradeId,subjectId,classTypeId);
	}
	public static ClassInfoModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static List<ClassInfoModel> getList() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT c.`name` as subjectName,COUNT(*) as studentNum from ").append(tableName);
		sql.append(" a LEFT JOIN ").append(StudentClassModel.tableName).append(" b on b.classinfoId=a.id LEFT JOIN ");
		sql.append(SubjectModel.tableName).append(" c on c.id=a.subjectId GROUP BY c.`name`");
		return dao.find(sql.toString());
	}
	public static List<ClassInfoModel> getGradeList() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT c.`name` as gradeName,COUNT(*) as studentNum from ").append(tableName);
		sql.append(" a LEFT JOIN ").append(StudentClassModel.tableName).append(" b on b.classinfoId=a.id LEFT JOIN ");
		sql.append(GradeModel.tableName).append(" c on c.id=a.gradeId GROUP BY c.`name`");
		return dao.find(sql.toString());
	}
	/**
	 * 保存
	 * @return
	 */
	public static boolean save(String name,int classTypeId,int gradeId,int employeeId,int subjectId,int timeSlot){
		ClassInfoModel model=new ClassInfoModel();
		model.setName(name);
		model.setclassTypeId(classTypeId);
		model.setgradeId(gradeId);
		model.setemployeeId(employeeId);
		model.setsubjectId(subjectId);
		model.settimeSlot(timeSlot);
		model.setstartTime(new Date());
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
	public static boolean update(int id ,int classTypeId,int gradeId,int employeeId,int subjectId,int timeSlot,Date endTime,Date startTime){
		ClassInfoModel model=ClassInfoModel.getById(id);
		if(model==null){
			return false;
		}
		model.setclassTypeId(classTypeId);
		model.setgradeId(gradeId);
		model.setemployeeId(employeeId);
		model.setsubjectId(subjectId);
		model.settimeSlot(timeSlot);
		model.setendTime(endTime);
		model.setstartTime(startTime);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 结束
	 * @return
	 */
	public static boolean endClass(int id){
		ClassInfoModel model=ClassInfoModel.getById(id);
		if(model==null){
			return false;
		}
		model.setendTime(new Date());
		model.setstate(1);//0：正常，1结束
		//结束一下学生的班级
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		StringBuffer sql=new StringBuffer();
		sql.append("update ");
		sql.append(StudentClassModel.tableName);
		sql.append(" set state=1,outTime=?");
		sql.append(" where classinfoId=?");
		int count=Db.update(sql.toString(),new Date(),id);
		if(count>0){
			return true;
		}
		return true;
	}
	public static boolean exitSameName(Object name){
		ClassInfoModel m=dao.findFirst("select *  from " + tableName + " where name = ? " , name);
		if(m==null){
			return false;
		}
		else {
			return true;
		}
	}
}
