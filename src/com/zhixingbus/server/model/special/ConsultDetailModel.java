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
24.学生咨询详细信息表tb_consult_detail
 * @author xiao
 *
 */
public class ConsultDetailModel extends Model<ConsultDetailModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_consult_detail";
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
	public int getclassType() {
		return get("classType");
	}
	public void setclassType(int classType) {
		set("classType" , classType);
	}
	public int getschool() {
		return get("school");
	}
	public void setschool(int school) {
		set("school" , school);
	}
	public String getscore() {
		return get("score");
	}
	public void setscore(String score) {
		set("score" , score);
	}
	public String getconsultId() {
		return get("consultId");
	}
	public void setconsultId(String consultId) {
		set("consultId" , consultId);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" , remark);
	}
	public Date getupdateTime() {
		return get("updateTime");
	}
	public void setupdateTime(Date updateTime) {
		set("updateTime" , updateTime);
	}
	public int getstate(){
		return get("state");
	}
	public String getstateStr(){
		int state=getstate();
		if(state==1){//0：未处理1已处理
			return "已处理";
		}
		else{
			return "未处理";
		}
	}
	public void setstate(int state)
	{
		set("state",state);
	}
	public int getreceiverType() {
		return get("receiverType");
	}
	public String getreceiverTypeStr(){
		int state=getreceiverType();
		if(state==0){//0学生1家长2朋友
			return "学生";
		}else if(state==1){
			return "家长";
		}else{
			return "朋友";
		}
	}
	public int getchannel() {
		return get("channel");
	}
	public String getchannelStr(){
		int state=getchannel();
		String s="";
		if(state==0){//0网络1传单2朋友介绍3招生队4其他
			s="网络";
		}else if(state==1){
			s="传单";
		}else if(state==2){
			s="朋友介绍";
		}else if(state==3){
			s="招生队";
		}else{
			s="其他";
		}
		return s;
	}
	public static final ConsultDetailModel dao = new ConsultDetailModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ConsultDetailModel> getList(int pageNumber, int pageSize,String key,int state) {
		String sele_sql="select a.*,b.time,b.advocate,b.tel,b.receiverType,b.channel,c.`name` as campusName,d.`name` as receiverName,f.`name` as subjectName,h.`name` as classTypeName,g.`name` as gradeName,e.`name` as responsibleName,i.`name` as schoolName";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a ");
		from_sql.append(" LEFT JOIN  ").append(ConsultModel.tableName).append(" b on a.consultId=b.id");
		from_sql.append(" LEFT JOIN  ").append(CampusModel.tableName).append(" c on c.id=b.campus");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" d on d.id=b.receiver");
		from_sql.append(" LEFT JOIN  ").append(EmployeeModel.tableName).append(" e on e.id=b.responsible");
		from_sql.append(" LEFT JOIN  ").append(SubjectModel.tableName).append(" f on f.id=a.subject");
		from_sql.append(" LEFT JOIN  ").append(GradeModel.tableName).append(" g on g.id=a.grade");
		from_sql.append(" LEFT JOIN  ").append(ClassTypeModel.tableName).append(" h on h.id=a.classType");
		from_sql.append(" LEFT JOIN  ").append(SchoolModel.tableName).append(" i on i.id=a.school");
		from_sql.append(" where 1=1 ");
		switch (state) {
		case 0:
			from_sql.append(" and a.state=0 ");
			break;
		case 1:
			from_sql.append(" and a.state=1 ");
			break;
		default:
			break;
		}
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  b.advocate like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static ConsultDetailModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	@Before(Tx.class)
	public static boolean update(final String id,final int state,final String remark){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				ConsultDetailModel c=ConsultDetailModel.getById(id);
				if(c==null){
					return false;
				}
				c.setstate(state);
				c.setremark(remark);
				c.setupdateTime(new Date());
				c.update();
				return true;
			}
			});
		return succeed;
	}
}
