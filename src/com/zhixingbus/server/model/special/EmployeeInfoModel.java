package com.zhixingbus.server.model.special;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *5.职工基本信息tb_employeeInfo
 * @author xiao
 *
 */
public class EmployeeInfoModel extends Model<EmployeeInfoModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_employeeInfo";
	public int getId() {
		return get("id");
	}
	public void setId(int id) {
		set("id", id);
	}
	public int getpositionId() {
		return get("positionId");
	}
	public void setpositionId(int positionId) {
		set("positionId", positionId);
	}
	public String gettel() {
		return get("tel");
	}
	public void settel(String tel) {
		set("tel" , tel);
	}
	public String getaddr() {
		return get("addr");
	}
	public void setaddr(String addr) {
		set("addr" , addr);
	}
	public String gethomeAddr() {
		return get("homeAddr");
	}
	public void sethomeAddr(String homeAddr) {
		set("homeAddr" , homeAddr);
	}
	public String getheadImg() {
		return get("headImg");
	}
	public void setheadImg(String headImg) {
		set("headImg" , headImg);
	}
	public String getskill() {
		return get("skill");
	}
	public void setskill(String skill) {
		set("skill" , skill);
	}
	public String getmajor() {
		return get("major");
	}
	public void setmajor(String major) {
		set("major" , major);
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
			return "离职";
		}
		else{
			return "在职";
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
	public String getSexStr(){
		int sex=getSex();
		if(sex==1){
			return "男";
		}
		else{
			return "女";
		}
	}
	public void settype(int type) {
		set("type",type);
	}
	public int gettype(){
		return get("type");
	}
	public String gettypeStr(){
		int sex=gettype();
		if(sex==1){
			return "全职";
		}
		else{
			return "兼职";
		}
	}
	public Date getCreatTime() {
		return get("creatTime");
	}
	public Date getBorthday() {
		return get("borthday");
	}
	
	public Date getoutTime() {
		return get("outTime");
	}
	public void setoutTime(Date outTime) {
		set("outTime" ,outTime);
	}
	public Date getinTime() {
		return get("inTime");
	}
	public void setinTime(Date inTime) {
		set("inTime" ,inTime);
	}
	
	public static final EmployeeInfoModel dao = new EmployeeInfoModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<EmployeeInfoModel> getList(int pageNumber, int pageSize,String key,int sex) {
		String sele_sql="select a.*,b.*,b.id as eid ,c.name as postionName ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(EmployeeModel.tableName).append(" a LEFT JOIN ").append(tableName).append(" b on a.id=b.id");
		from_sql.append(" LEFT JOIN ").append(PositionModel.tableName).append(" c on c.id=b.positionId ");
		from_sql.append(" where 1=1  ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append("and  a.name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("a.creatTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static EmployeeInfoModel getModelById(Object id){
		return dao.findFirst("select *  from "+StudentModel.tableName+" a LEFT JOIN " + tableName + " b on a.id=b.id where a.id = ? " , id);
	}
	public static EmployeeInfoModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static List<EmployeeInfoModel> getList(String key) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select * from ").append(tableName).append(" a left join ").append(EmployeeModel.tableName);
		from_sql.append(" b on a.id=b.id ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where a.state=0 and b.name like '%"+key+"%'");
		}
		return dao.find(from_sql.toString());
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
	public static boolean update(int id,String addr,String school,String grade,String classes,String tel,String parentTel,String qq,String weixin,int state){
		EmployeeInfoModel model=EmployeeInfoModel.getById(id);
		if(model==null){//添加
			model=new EmployeeInfoModel();
			model.setId(id);
			model.setaddr(addr);
			model.settel(tel);
			model.setqq(qq);
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
			model.settel(tel);
			model.setqq(qq);
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
