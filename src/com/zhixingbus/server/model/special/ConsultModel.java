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
 * 23.学生咨询登记表tb_consult
 * @author xiao
 *
 */
public class ConsultModel extends Model<ConsultModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_consult";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public Date gettime() {
		return get("time");
	}
	public void settime(Date time) {
		set("time" , time);
	}
	public int getcampus() {
		return get("campus");
	}
	public void setcampus(int campus) {
		set("campus" , campus);
	}
	public String getadvocate() {
		return get("advocate");
	}
	public void setadvocate(String advocate) {
		set("advocate" , advocate);
	}
	public String gettel() {
		return get("tel");
	}
	public void settel(String tel) {
		set("tel" , tel);
	}
	public int getreceiverType() {
		return get("receiverType");
	}
	public void setreceiverType(int receiverType) {
		set("receiverType" , receiverType);
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
	public int getreceiver() {
		return get("receiver");
	}
	public void setreceiver(int receiver) {
		set("receiver" , receiver);
	}
	public int getchannel() {
		return get("channel");
	}
	public void setchannel(int channel) {
		set("channel" , channel);
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
		}else if(state==5){
			s="到校咨询";
		}else{
			s="其他";
		}
		return s;
	}
	public int getresponsible() {
		return get("responsible");
	}
	public void setresponsible(int responsible) {
		set("responsible" , responsible);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" , remark);
	}
	public static final ConsultModel dao = new ConsultModel();
	
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ConsultModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("id desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	public static ConsultModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static List<ConsultModel> getList(String key) {
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select *  from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where name like '%"+key+"%'");
		}
		return dao.find(from_sql.toString());
	}

	/**
	 * 保存咨询信息
	 * @param consult
	 * @param list
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final ConsultModel consult,final List<ConsultDetailModel> list){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				consult.save();
				for(ConsultDetailModel m:list){
					m.save();
				}
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
	public static boolean update(final ConsultModel consult,final List<ConsultDetailModel> list){
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				consult.update();
				for(ConsultDetailModel m:list){
					m.update();
				}
				return true;
			}
			});
		return succeed;
	}
}
