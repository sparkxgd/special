package com.zhixingbus.server.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;
/**
 * 智行公交官网-访客记录表
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class VisitorModel extends Model<VisitorModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_visitor";
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public void setremoteIp(String remoteIp) {
		set("remoteIp", remoteIp);
	}
	public String getremoteIp() {
		return get("remoteIp");
	}
	public String getip() {
		return get("ip");
	}
	public void setip(String ip) {
		set("ip", ip);
	}
	public String getaddr() {
		return get("addr");
	}
	public void setaddr(String addr) {
		set("addr", addr);
	}
	public void setbrowser(String browser) {
		set("browser", browser);
	}
	public String getbrowser() {
		return get("browser");
	}
	public Date getvisitTime() {
		return get("visitTime");
	}
	public void setvisitTime(Date visitTime) {
		set("visitTime", visitTime);
	}
	public static final VisitorModel dao = new VisitorModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<VisitorModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and addr like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("visitTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static VisitorModel getById(String id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	
	public static boolean saveModel(String remoteIp,String ip,String addr,String browser){
		VisitorModel model=new VisitorModel();
		model.setId(Utils.createNewUUID());
		model.setremoteIp(remoteIp);
		model.setip(ip);
		model.setaddr(addr);
		model.setbrowser(browser);
		model.setvisitTime(new Date());
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public static boolean delModel(String id){
		return dao.deleteById(id);
	}
}
