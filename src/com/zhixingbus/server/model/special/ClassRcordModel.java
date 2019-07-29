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
 *21.班级上课记录tb_classRcord
 * @author xiao
 *
 */
public class ClassRcordModel extends Model<ClassRcordModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_classRcord";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public int getNo() {
		return get("no");
	}
	public void setNo(int no) {
		set("no", no);
	}
	public int getemployeeId() {
		return get("employeeId");
	}
	public void setemployeeId(int employeeId) {
		set("employeeId", employeeId);
	}
	public int getclassInfoId() {
		return get("classInfoId");
	}
	public void setclassInfoId(int classInfoId) {
		set("classInfoId", classInfoId);
	}
	public int getclassroomId() {
		return get("classroomId");
	}
	public void setclassroomId(int classroomId) {
		set("classroomId", classroomId);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark", remark);
	}
	public Date getgotime() {
		return get("gotime");
	}
	public void setgotime(Date gotime) {
		set("gotime" ,gotime);
	}
	public Date getoverTime() {
		return get("overTime");
	}
	public void setoverTime(Date overTime) {
		set("overTime" ,overTime);
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
	public void settype(int type)
	{
		set("type",type);
	}

	public int getMno(){
		Object maxNO=get("Maxno");
		if(maxNO==null){
			return 0;
		}
		return get("Maxno");
	}
	public static int getMaxNo(int classInfoId){
		ClassRcordModel m=ClassRcordModel.dao.findFirst("select MAX(no) as Maxno  from " + tableName+" where classInfoId=?",classInfoId);
		int id=m.getMno();
		id=id+1;
		return id;
	}
	
	public Integer gety(){
		return get("y");
	}
	public Integer getm(){
		return get("m");
	}
	public Long getval(){
		return get("val");
	}
	public static List<ClassRcordModel> getList() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT year(a.gotime)as 'y',month(a.gotime) as 'm',COUNT(*) as 'val' FROM ").append(tableName);
		sql.append(" a LEFT JOIN ").append(StudentGoClassModel.tableName).append(" b on a.id=b.classRcordId GROUP BY year(a.gotime),month(a.gotime),b.studentId");
		return dao.find(sql.toString());
	}
	
	public static final ClassRcordModel dao = new ClassRcordModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ClassRcordModel> getList(int pageNumber, int pageSize,String classInfoId) {
		String sele_sql="select a.*,c.`name` as className,d.`name` as classroom,e.`name` as employeeName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(ClassInfoModel.tableName).append(" c on c.id=a.classInfoId");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" d on a.classroomId=d.id");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=a.employeeId");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(classInfoId)){
			from_sql.append("and  a.classInfoId="+classInfoId+"");
		}
		from_sql.append(" order by ").append("a.gotime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	
	public static ClassRcordModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	/**
	 * 保存
	 * @return
	 */
	public static boolean save(int classInfoId,int classroomId,int employeeId,Date gotime,String remark){
		ClassRcordModel model=new ClassRcordModel();
		model.setclassInfoId(classInfoId);
		model.setclassroomId(classroomId);
		model.setremark(remark);
		model.setgotime(gotime);
		model.setemployeeId(employeeId);
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
	public static boolean update(int id,int classInfoId,int classroomId,int employeeId,Date gotime,String remark){
		ClassRcordModel model=ClassRcordModel.getById(id);
		if(model==null){
			return false;
		}
		model.setclassInfoId(classInfoId);
		model.setclassroomId(classroomId);
		model.setremark(remark);
		model.setgotime(gotime);
		model.setemployeeId(employeeId);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean save(ClassRcordModel m){
		try {
			m.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	@Before(Tx.class)
	public static boolean save(final List<ClassRcordModel> list){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				for(ClassRcordModel m:list){
					m.save();
				}
				return true;
			}
			});
		return succeed;
	}
	
}
