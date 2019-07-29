package com.zhixingbus.server.model.special;

import java.sql.SQLException;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.zhixingbus.server.utils.StringUtil;
/**
25.学生试听记录表tb_audition
 * @author xiao
 *
 */
public class AuditionModel extends Model<AuditionModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_audition";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public int getsubject() {
		return get("subject");
	}
	public void setsubject(int subject) {
		set("subject" , subject);
	}
	public int getgrade() {
		return get("grade");
	}
	public void setgrade(int grade) {
		set("grade" , grade);
	}
	public int getstudent() {
		return get("student");
	}
	public void setstudent(int student) {
		set("student" , student);
	}
	public int getclassroom() {
		return get("classroom");
	}
	public void setclassroom(int classroom) {
		set("classroom" , classroom);
	}
	public int getteacher() {
		return get("teacher");
	}
	public void setteacher(int teacher) {
		set("teacher" , teacher);
	}
	public String getconsultDetailId() {
		return get("consultDetailId");
	}
	public void setconsultDetailId(String consultDetailId) {
		set("consultDetailId" , consultDetailId);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" , remark);
	}
	public Date gettime() {
		return get("time");
	}
	public void settime(Date time) {
		set("time" , time);
	}
	public int getresult(){
		return get("result");
	}
	public String getresultStr(){
		int result=getresult();
		String s="未知";
		switch (result) {
		case 0:
			s="<span style='color: green'>成功</span>";
			break;
		case 1:
			s="<span style='color: blue'>待定</span>";
			break;
		case 2:
			s="<span style='color: red'>失败</span>";
			break;
		case 3:
			s="取消";
			break;
		default:
			break;
		}
		return s;
	}
	public void setresult(int result)
	{
		set("result",result);
	}
	public static final AuditionModel dao = new AuditionModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<AuditionModel> getList(int pageNumber, int pageSize,String key,int result) {
		String sele_sql="select a.*,d.`name` as teacherName,c.`name` as studentName,f.`name` as subjectName,g.`name` as gradeName,i.`name` as classroomName";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(StudentModel.tableName).append(" c on c.id=a.student");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" d on d.id=a.teacher");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" f on f.id=a.subject");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" g on g.id=a.grade");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" i on i.id=a.classroom");
		from_sql.append(" where 1=1 ");
		switch (result) {
		case 0:
			from_sql.append(" and a.result=0 ");
			break;
		case 1:
			from_sql.append(" and a.result=1 ");
			break;
		case 2:
			from_sql.append(" and a.result=2 ");
			break;
		case 3:
			from_sql.append(" and a.result=3 ");
			break;
		default:
			break;
		}
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  c.`name`  like '%"+key+"%'");
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
	public static Page<AuditionModel> studentAuditionInfos(int pageNumber, int pageSize,int studentId,String key) {
		String sele_sql="select a.*,d.`name` as teacherName,c.`name` as studentName,f.`name` as subjectName,g.`name` as gradeName,i.`name` as classroomName";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(StudentModel.tableName).append(" c on c.id=a.student");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" d on d.id=a.teacher");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" f on f.id=a.subject");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" g on g.id=a.grade");
		from_sql.append(" LEFT JOIN  ").append(ClassroomModel.tableName).append(" i on i.id=a.classroom");
		from_sql.append(" where a.student=? ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  c.`name`  like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),studentId);
	}
	public static AuditionModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	/**
	 * 保存
	 * @param consult
	 * @param list
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final AuditionModel audition){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				if(audition.getconsultDetailId()!=null){//如果不等于空，说明是从咨询表里面点击过来的信息
					//更新一下咨询细表的状态为已处理
					ConsultDetailModel c=ConsultDetailModel.getById(audition.getconsultDetailId());
					if(c!=null){
						c.setstate(1);//0：未处理1已处理
						c.setupdateTime(new Date());
						c.update();
					}
				}
				audition.save();
				return true;
			}
			});
		return succeed;
	}
	/**
	 * 更新
	 * @param consult
	 * @param list
	 * @return
	 */
	@Before(Tx.class)
	public static boolean update(final AuditionModel audition){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				audition.update();
				return true;
			}
			});
		return succeed;
	}
	/**
	 * 更新试听结果
	 * @param consult
	 * @param list
	 * @return
	 */
	@Before(Tx.class)
	public static boolean update(final String id,final String remark ,final int result){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				AuditionModel a=AuditionModel.getById(id);
				String rem=a.getremark();
				a.setremark(remark+"|"+rem);
				a.setresult(result);
				a.update();
				return true;
			}
			});
		return succeed;
	}
}
