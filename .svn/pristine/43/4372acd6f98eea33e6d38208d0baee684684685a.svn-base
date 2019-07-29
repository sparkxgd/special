package com.zhixingbus.server.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;
/**
 * 智行公交官网-意见表
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class SuggestModel extends Model<SuggestModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_suggest";
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public void setname(String name) {
		set("name", name);
	}
	public String getname() {
		return get("name");
	}
	public String getip() {
		return get("ip");
	}
	public void setip(String ip) {
		set("ip", ip);
	}
	public String gettitle() {
		return get("title");
	}
	public void settitle(String title) {
		set("title", title);
	}
	public String getcontent() {
		return get("content");
	}
	public void setcontent(String content) {
		set("content", content);
	}
	public String getaddress() {
		return get("address");
	}
	public void setaddress(String address) {
		set("address", address);
	}
	public void setemail(String email) {
		set("email", email);
	}
	public String getemail() {
		return get("email");
	}
	public Date getcreateTime() {
		return get("createTime");
	}
	public void setcreateTime(Date createTime) {
		set("createTime", createTime);
	}
	public static final SuggestModel dao = new SuggestModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<SuggestModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and title like '%"+key+"%'");
		}
		from_sql.append(" order by ").append("createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static SuggestModel getById(String id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	
	public static boolean saveModel(String name,String email,String title,String content,String ip,String address){
		SuggestModel model=new SuggestModel();
		model.setId(Utils.createNewUUID());
		model.setemail(email);
		model.setip(ip);
		model.setaddress(address);
		model.setcontent(content);
		model.settitle(title);
		model.setname(name);
		model.setcreateTime(new Date());
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean delModel(String id){
		return dao.deleteById(id);
	}
}
