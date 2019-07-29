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
 * 学生信息
 * @author xiao
 *
 */
public class StudentModel extends Model<StudentModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_student";
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
		set("name" , name);
	}
	public int getSex(){
		return get("sex");
	}
	public String getSexStr(){
		int sex=getSex();
		if(sex==1){
			return "男";
		}
		else{
			return "女";
		}
	}
	public void setSex(int sex)
	{
		set("sex",sex);
	}
	
	public Date getCreatTime() {
		return get("creatTime");
	}
	public void setCreatTime(Date creatTime) {
		set("creatTime" ,creatTime);
	}
	public Date getBorthday() {
		return get("borthday");
	}
	public void setBorthday(Date borthday) {
		set("borthday" ,borthday);
	}
	public int getstate(){
		return get("state");
	}
	public String getstateStr(){
		int state=getstate();
		if(state==1){
			return "在校";
		}
		else{
			return "离校";
		}
	}
	public int getMaxid(){
		Object maxid=get("Maxid");
		if(maxid==null){
			return 0;
		}
		return get("Maxid");
	}
	public static final StudentModel dao = new StudentModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentModel> getList(int pageNumber, int pageSize,String key,int sex) {
		String sele_sql="select a.*,b.tel,b.parentTel,b.addr,b.school,b.grade,b.class,b.qq,b.weixin,b.state,b.updateTime,c.name as gradeName,d.name as schoolName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentInfoModel.tableName).append(" b ").append(" on a.id=b.id ");
		from_sql.append(" LEFT JOIN ").append(GradeModel.tableName).append(" c on c.id=b.grade ");
		from_sql.append(" LEFT JOIN ").append(SchoolModel.tableName).append(" d on d.id=b.school ");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.creatTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}  
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentModel> studentComprehensiveInfos(int pageNumber, int pageSize,String key,String tel) {
		String sele_sql="select a.*,b.tel,b.parentTel,b.state," +
		"(SELECT COUNT(g.id) FROM tb_studentclass g where g.studentId=a.id) as stuClassNum ,"+
		"(SELECT COUNT(c.id) FROM tb_audition c where c.student=a.id) as stuAudNum ,"+
		"(SELECT COUNT(h.id) FROM tb_regorderdetail h LEFT JOIN tb_regorder i on h.regorderId=i.id LEFT JOIN tb_studentregistration j on i.studentRegId=j.id where j.studentId=a.id) as orderNum ,"+
		"(SELECT COUNT(d.id) FROM tb_regorder d LEFT JOIN tb_studentregistration e on d.studentRegId=e.id where e.studentId=a.id) as stuRegNum ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentInfoModel.tableName).append(" b ").append(" on a.id=b.id ");
		from_sql.append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%' ");
		}
		if(!StringUtil.isBlankOrEmpty(tel)){
			from_sql.append(" and  b.tel like '%"+tel+"%' or b.parentTel like '%"+tel+"%'");
		}
		from_sql.append(" order by ").append("a.creatTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static StudentModel getModelById(int id) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select a.*,b.tel,b.parentTel,b.addr,b.school,b.grade,b.class as className,b.qq,b.weixin,b.state,b.updateTime,c.name as gradeName,d.name as schoolName ");
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentInfoModel.tableName).append(" b ").append(" on a.id=b.id ");
		from_sql.append(" LEFT JOIN ").append(GradeModel.tableName).append(" c on c.id=b.grade ");
		from_sql.append(" LEFT JOIN ").append(SchoolModel.tableName).append(" d on d.id=b.school ");
		from_sql.append(" where a.id=? ");
		return dao.findFirst(from_sql.toString(),id);
	} 
	public static StudentModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static int getMaxId(){
		StudentModel m=StudentModel.dao.findFirst("select MAX(id) as Maxid  from " + tableName+"");
		int id=m.getMaxid();
		id=id+1;
		return id;
	}

	public static List<StudentModel> getList(String key) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select *  from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		return dao.find(from_sql.toString());
	}
	public static boolean exitSameName(Object name,int sex){
		StudentModel m=dao.findFirst("select *  from " + tableName + " where name = ? and sex=?" , name,sex);
		if(m==null){
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * 保存
	 * @param name
	 * @param sex
	 * @param borthday
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final StudentModel student, final StudentInfoModel info){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						student.save();
						info.save();
						return true;
					}
					});
				return succeed;
	}
	/**
	 * 更新
	 * @param id
	 * @param title
	 * @param type
	 * @param summary
	 * @param content
	 * @param author
	 * @param level
	 * @return
	 */
	public static boolean update(int id,String name,int sex,Date borthday,String addr,int school,int grade,String classes,String tel,String parentTel,String qq,String weixin,String remark,int state){
		StudentModel model=StudentModel.getById(id);
		if(model==null){
			return false;
		}
		model.setName(name);
		model.setSex(sex);
		model.setBorthday(borthday);
		try {
			model.update();
			StudentInfoModel.update(id, addr, school, grade, classes, tel, parentTel, qq, weixin, state);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
