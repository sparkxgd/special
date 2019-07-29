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
 * 2.学生基本信息
 * @author xiao
 *
 */
public class StudentInfoModel extends Model<StudentInfoModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_studentInfo";
	public int getId() {
		return get("id");
	}
	public void setId(int id) {
		set("id", id);
	}
	public String gettel() {
		return get("tel");
	}
	public void settel(String tel) {
		set("tel" , tel);
	}
	public String getParentTel() {
		return get("parentTel");
	}
	public void setParentTel(String parentTel) {
		set("parentTel" , parentTel);
	}
	public String getaddr() {
		return get("addr");
	}
	public void setaddr(String addr) {
		set("addr" , addr);
	}
	public int getschool() {
		return get("school");
	}
	public void setschool(int school) {
		set("school" , school);
	}
	public int getgrade() {
		return get("grade");
	}
	public void setgrade(int grade) {
		set("grade" , grade);
	}
	public String getclass() {
		return get("class");
	}
	public void setclass(String classes) {
		set("class" , classes);
	}
	public String getqq() {
		return get("qq");
	}
	public void setqq(String qq) {
		set("qq" , qq);
	}
	public String getweixin() {
		return get("weixin");
	}
	public void setweixin(String weixin) {
		set("weixin" , weixin);
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
	public void setstate(int state)
	{
		set("state",state);
	}
	
	public Date getupdateTime() {
		return get("updateTime");
	}
	public void setupdateTime(Date updateTime) {
		set("updateTime" ,updateTime);
	}
	public String getName() {
		return get("name");
	}
	public int getSex(){
		return get("sex");
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" ,remark);
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
	public Date getCreatTime() {
		return get("creatTime");
	}
	public Date getBorthday() {
		return get("borthday");
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
	public static final StudentInfoModel dao = new StudentInfoModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StudentInfoModel> getList(int pageNumber, int pageSize,String key,int sex) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(StudentModel.tableName).append(" b ");
		from_sql.append(" on a.id=b.id where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("b.creatTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static StudentInfoModel getModelById(Object id){
		StringBuffer sql=new StringBuffer();
		sql.append("select a.*,b.name,b.sex,b.creatTime,b.borthday,c.name as gradeName,d.name as schoolName  from ").append(tableName).append(" a ");
		sql.append(" LEFT JOIN ").append(StudentModel.tableName).append(" b on a.id=b.id ");
		sql.append(" LEFT JOIN ").append(GradeModel.tableName).append(" c on c.id=a.grade ");
		sql.append(" LEFT JOIN ").append(SchoolModel.tableName).append(" d on d.id=a.school ");
		sql.append(" where a.id = ? ");
		return dao.findFirst(sql.toString() , id);
	}
	public static List<StudentInfoModel> getList() {
		StringBuffer sql=new StringBuffer();
		sql.append("select year(updateTime)as 'y',month(updateTime) as 'm',COUNT(*) as 'val' from ").append(tableName).append(" group by year(updateTime),month(updateTime)");
		return dao.find(sql.toString());
	}
	public static StudentInfoModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	@Before(Tx.class)
	public static boolean save(final StudentInfoModel info){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						info.save();
						return true;
					}
					});
				return succeed;
	}
	
	public static boolean update(int id,String addr,int school,int grade,String classes,String tel,String parentTel,String qq,String weixin,int state){
		StudentInfoModel model=StudentInfoModel.getById(id);
		if(model==null){//添加
			model=new StudentInfoModel();
			model.setId(id);
			model.setaddr(addr);
			model.setgrade(grade);
			model.settel(tel);
			model.setParentTel(parentTel);
			model.setqq(qq);
			model.setschool(school);
			model.setclass(classes);
			model.setstate(1);
			model.setweixin(weixin);
			model.setupdateTime(new Date());
			try {
				model.save();
			} catch (Exception e) {
				return false;
			}
		}else {//更新
			model.setaddr(addr);
			model.setgrade(grade);
			model.settel(tel);
			model.setParentTel(parentTel);
			model.setqq(qq);
			model.setschool(school);
			model.setclass(classes);
			model.setstate(state);
			model.setweixin(weixin);
			model.setupdateTime(new Date());
			try {
				model.update();
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
