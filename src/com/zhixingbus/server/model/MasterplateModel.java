package com.zhixingbus.server.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;
/**
 * 智行公交官网-首页模版表
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class MasterplateModel extends Model<MasterplateModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_masterplate";
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public void settempName(String tempName) {
		set("tempName", tempName);
	}
	public String gettempName() {
		return get("tempName");
	}
	public String getcode() {
		return get("code");
	}
	public void setcode(String code) {
		set("code", code);
	}
	public String getcss() {
		return get("css");
	}
	public void setcss(String css) {
		set("css" , css);
	}
	public Date getcreateTime() {
		return get("createTime");
	}
	public void setcreateTime(Date createTime) {
		set("createTime", createTime);
	}


	public static final MasterplateModel dao = new MasterplateModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MasterplateModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where tempName like '%"+key+"%'");
		}
		from_sql.append(" order by createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MasterplateModel> getList(int pageNumber, int pageSize,String key,String title) {
		String sele_sql="select a.*,b.title ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" as a left join  ").append(ArticleModel.tableName).append(" as b");
		from_sql.append(" on a.articleId=b.id where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(title)){
			from_sql.append(" and b.title like '%"+title+"%'");
		}
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and a.content like '%"+key+"%'");
		}
		from_sql.append(" order by a.createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MasterplateModel> getList(int pageNumber, int pageSize) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		from_sql.append(" order by createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static MasterplateModel getById(String id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	public static List<MasterplateModel> getList(){
		return dao.find("select * from " + tableName);
	}
	public static boolean saveModel(String name,String code,String css){
		MasterplateModel model=new MasterplateModel();
		model.setId(Utils.createNewUUID());
		model.setcode(code);
		model.settempName(name);
		model.setcreateTime(new Date());
		model.setcss(css);
		
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MasterplateModel> getListByArticleId(int pageNumber, int pageSize,String articleId) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		from_sql.append(" where articleId='").append(articleId).append("'");
		from_sql.append(" order by createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 获取热平文章
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MasterplateModel> getNewComments(int pageNumber, int pageSize) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		from_sql.append(" order by createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 更新	
	 * @return
	 */
	public static boolean updateModel(String id,String tempName,String code,String css){
		MasterplateModel model=getById(id);
		if(model==null){
			return false;
		}
		model.setcode(code);
		model.settempName(tempName);
		model.setcss(css);
		model.setcreateTime(new Date());
		try {
			model.update();
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
