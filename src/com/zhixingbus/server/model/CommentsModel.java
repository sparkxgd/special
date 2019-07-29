package com.zhixingbus.server.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;
/**
 * 智行公交官网-评论表
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class CommentsModel extends Model<CommentsModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_comments";
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public void setArticleId(String articleId) {
		set("articleId", articleId);
	}
	public String getArticleId() {
		return get("articleId");
	}
	public String getcityName() {
		return get("cityName");
	}
	public void setcityName(String cityName) {
		set("cityName", cityName);
	}
	public String getcontent() {
		return get("content");
	}
	public void setcontent(String content) {
		set("content" , content);
	}
	public String getip() {
		return get("ip");
	}
	public void setip(String ip) {
		set("ip", ip);
	}
	public Date getCreateTime() {
		return get("createTime");
	}
	public void setCreateTime(Date createTime) {
		set("createTime" ,createTime);
	}
	public int getagreeNum() {
		return get("agreeNum");
	}
	public void setagreeNum(int agreeNum) {
		set("agreeNum", agreeNum);
	}
	public int getdisapprovalNum() {
		return get("disapprovalNum");
	}
	public void setdisapprovalNum(int disapprovalNum) {
		set("disapprovalNum", disapprovalNum);
	}


	public static final CommentsModel dao = new CommentsModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<CommentsModel> getList(int pageNumber, int pageSize,String key) {
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
	public static Page<CommentsModel> getList(int pageNumber, int pageSize,String key,String title) {
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
	public static Page<CommentsModel> getList(int pageNumber, int pageSize) {
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
	public static CommentsModel getById(String id){
		return dao.findFirst("select * from " + tableName + " where id = ? " , id);
	}
	
	public static boolean saveModel(String articleId,String content,String ip,String cityName){
		CommentsModel model=new CommentsModel();
		model.setId(Utils.createNewUUID());
		model.setip(ip);
		model.setcityName(cityName);
		model.setcontent(content);
		model.setArticleId(articleId);
		model.setCreateTime(new Date());
		model.setagreeNum(0);
		model.setdisapprovalNum(0);
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
	public static Page<CommentsModel> getListByArticleId(int pageNumber, int pageSize,String articleId) {
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
	public static Page<CommentsModel> getNewComments(int pageNumber, int pageSize) {
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
