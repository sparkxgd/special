package com.zhixingbus.server.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 菜单表
 * @author xiao
 * 2015年12月22日 21:32:09
 *
 */
public class MenuModel extends Model<MenuModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_menu";
	
	public int getId() {
		return get("id");
	}
	public void setId(int id) {
		set("id", id);
	}
	public int gettreeId() {
		return get("treeId");
	}
	public void settreeId(int treeId) {
		set("treeId", treeId);
	}
	public void setname(String name) {
		set("name", name);
	}
	public String getname() {
		return get("name");
	}
	public String getpage() {
		return get("page");
	}
	public void setpage(String page) {
		set("page", page);
	}
	public int getposition() {
		return get("position");
	}
	public void setposition(int position) {
		set("position", position);
	}
	/**
	 * 是否显示，0：显示，1，不显示
	 * @return
	 */
	public int getisShow() {
		return get("isShow");
	}
	public void setisShow(int isShow) {
		set("isShow", isShow);
	}
	public String getisShowStr() {
		if(getisShow()==0){
			return "显示";
		}else if(getisShow()==1){
			return "不显示";
		}else{
			return get("isShow")+"";
		}
	}
	public String getenName() {
		return get("enName");
	}
	public void setenName(String enName) {
		set("enName" , enName);
	}
	public String getTreeEnName() {
		return get("treeEnName");
	}
	public String getTreeName() {
		return get("treeName");
	}
	
	public static final MenuModel dao = new MenuModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MenuModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select a.*,b.name as name1 ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" as a LEFT JOIN ").append(tableName).append(" as b ").append(" on b.id=a.treeId ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" where a.name like '%"+key+"%'");
		}
		from_sql.append(" order by a.treeId asc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<MenuModel> getList(int pageNumber, int pageSize,String key,String title) {
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
	public static Page<MenuModel> getList(int pageNumber, int pageSize) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		from_sql.append(" order by createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 显示菜单
	 * @return
	 */
	public static List<MenuModel> getShowMenuList() {
		StringBuffer sql=new StringBuffer("select * ");
		sql.append("from ").append(tableName);
		sql.append(" where isShow=? ");
		sql.append(" order by position asc");
		return dao.find(sql.toString(),0);

	}
	/**
	 * 显示菜单
	 * @return
	 */
	public static List<MenuModel> getSelectMenuList() {
		StringBuffer sql=new StringBuffer("select * ");
		sql.append("from ").append(tableName);
		sql.append(" where treeId=? ");
		sql.append(" order by position asc");
		return dao.find(sql.toString(),-1);

	}
	/**
	 * 显示二级菜单
	 * @return
	 */
	public static List<MenuModel> getTwoMenuList(int treeId) {
		StringBuffer sql=new StringBuffer("select * ");
		sql.append("from ").append(tableName);
		sql.append(" where treeId=? ");
		sql.append(" order by position asc");
		return dao.find(sql.toString(),treeId);

	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static MenuModel getById(Object id){
		StringBuffer sql=new StringBuffer("select a.*,b.name as treeName,b.enName as treeEnName ");
		sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(tableName).append(" b ").append(" on a.treeId=b.id ");
		sql.append(" where a.id=?");
		return dao.findFirst(sql.toString(), id);
	}
	
	public static boolean saveModel(String name,String enName,String page,int position ,int treeId,int isShow){
		MenuModel model=new MenuModel();
		model.setname(name);
		model.setenName(enName);
		model.setpage(page);
		model.setposition(position);
		model.settreeId(treeId);
		model.setisShow(isShow);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean updateModel(int id,String name,String enName,String page,int position ,int treeId,int isShow){
		MenuModel model=getById(id);
		model.setname(name);
		model.setenName(enName);
		model.setpage(page);
		model.setposition(position);
		model.settreeId(treeId);
		model.setisShow(isShow);
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
