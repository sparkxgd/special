package com.zhixingbus.server.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 智行公交官网-模版参数表
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class MasterplateParamModel extends Model<MasterplateParamModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_masterplate_param";
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public void setmasterplateId(String masterplateId) {
		set("masterplateId", masterplateId);
	}
	public String getmasterplateId() {
		return get("masterplateId");
	}
	public String getname() {
		return get("name");
	}
	public void setname(String name) {
		set("name", name);
	}
	public String getvalue() {
		return get("value");
	}
	public void setvalue(String value) {
		set("value" , value);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark", remark);
	}

	public static final MasterplateParamModel dao = new MasterplateParamModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MasterplateParamModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where content like '%"+key+"%'");
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
	public static Page<MasterplateParamModel> getList(int pageNumber, int pageSize,String key,String title) {
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
	public static Page<MasterplateParamModel> getList(int pageNumber, int pageSize) {
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
	public static MasterplateParamModel getById(String id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	
	public static boolean saveModel(String articleId,String content,String ip,String cityName){
		MasterplateParamModel model=new MasterplateParamModel();
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
	public static Page<MasterplateParamModel> getListByArticleId(int pageNumber, int pageSize,String articleId) {
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
	public static Page<MasterplateParamModel> getNewComments(int pageNumber, int pageSize) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		from_sql.append(" order by createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
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
