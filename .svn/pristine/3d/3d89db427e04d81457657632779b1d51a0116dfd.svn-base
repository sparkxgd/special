package com.zhixingbus.server.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.constants.ArticleLevel;
import com.zhixingbus.server.constants.ArticleState;
import com.zhixingbus.server.utils.StringUtil;
/**
 * 智行公交官网文章表
 * @author xiao
 *
 */
public class ArticleModel extends Model<ArticleModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tb_article_x";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getTitle() {
		return get("title");
	}
	public void setTitle(String title) {
		set("title", title);
	}
	public String getSummary() {
		return get("summary");
	}
	public void setSummary(String summary) {
		set("summary", summary);
	}
	public Date getCreateTime() {
		return get("createTime");
	}
	public void setCreateTime(Date createTime) {
		set("createTime" ,createTime);
	}
	public Date getPublishTime() {
		return get("publishTime");
	}
	public void setPublishTime(Date publishTime) {
		set("publishTime" ,publishTime);
	}
	public String getcontent() {
		return get("content");
	}
	public void setcontent(String content) {
		set("content" , content);
	}
	public String getauthor() {
		return get("author");
	}
	public void setauthor(String author) {
		set("author" , author);
	}
	public String getremark() {
		return get("remark");
	}
	public void setremark(String remark) {
		set("remark" , remark);
	}
	public int getcommentsNun() {
		return get("commentsNun");
	}
	public void setcommentsNun(int commentsNun) {
		set("commentsNun", commentsNun);
	}
	public int getreadNum() {
		return get("readNum");
	}
	public void setreadNum(int readNum) {
		set("readNum", readNum);
	}
	public int getisComment() {
		return get("isComment");
	}
	public void setisComment(int isComment) {
		set("isComment", isComment);
	}
	public String getisCommentStr(){
		if(getisComment()==1){
			return "允许";
		}else{
			return "不允许";
		}
		
	}
	
	/**
	 * 状态0：未发表。1：已发表。2：已经下架
	 * @param state
	 */
	public void setstate(int state) {
		set("state" , state);
	}
	public int getstate() {
		return get("state");
	}
	public String getStateStr() {
		return ArticleState.getStatusDesc(getstate());
	}
	public int gettype() {
		return get("type");
	}
	public void settype(int type) {
		set("type" , type);
	}
	public int getlevel(){
		return get("level");
	}
	public void setlevel(int level) {
		set("level" , level);
	}
	public String getLevelstr(){
		return ArticleLevel.getStatusDesc(getlevel());
	}
	public String getCommentsNunStr() {
		long commentsNun=getcommentsNun();
		String commentsNunStr="";
		if(commentsNun<10000){
			commentsNunStr = String.valueOf(commentsNun);
		}else{
			double sub=commentsNun/10000;
			commentsNunStr = sub+"万+";
		}
		return commentsNunStr;
	}
	public String getReadNumStr() {
		long readNum=getreadNum();
		String readNumStr="";
		if(readNum<10000){
			readNumStr = String.valueOf(readNum);
		}else{
			double sub=readNum/10000;
			readNumStr = sub+"万+";
		}
		return readNumStr;
	}
	public String getName() {
		return get("name");
	}
	public void setName(String name) {
		set("name" , name);
	}
	public String getDec() {
		return get("dec");
	}
	public void setDec(String dec) {
		set("dec" , dec);
	}
	public String getheadImg() {
		return get("headImg");
	}
	public void setheadImg(String headImg) {
		set("headImg" , headImg);
	}
	public String getTypeString() {
		String name=getName();
		if(name.length()<2){
			return "公交";
		}
		return name.substring(0, 2);
	}
	public String getTypeString2() {
		String name=getName();
		if(name.length()<4){
			return "资讯";
		}
		return name.substring(2, 4);
	}


	public static final ArticleModel dao = new ArticleModel();
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ArticleModel> getList(int pageNumber, int pageSize,String key,int type) {
		String sele_sql="select a.*, t.name,t.enName";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" as a LEFT JOIN ").append(MenuModel.tableName).append(" as t ").append(" ON ");
		from_sql.append("a.type = ").append("t.id ").append(" where 1=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and a.title like '%"+key+"%'");
		}
		if(type!=0){
			from_sql.append(" and ").append("a.type=").append(type);
		}
		from_sql.append(" order by ").append("a.createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	} 
	/**
	 * 分页查询显示
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ArticleModel> getWebArticleList(int pageNumber, int pageSize,String key,int type) {
		String sele_sql="select a.*, t.name,t.dec ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" as a LEFT JOIN ").append(ArticleTypeModel.tableName).append(" as t ").append(" ON ");
		from_sql.append("a.type = ").append("t.id ").append(" where a.state=1 ");
		if(!StringUtil.isBlankOrEmpty(key)){
			from_sql.append(" and a.title like '%"+key+"%'");
		}
		if(type!=0){
			from_sql.append(" and ").append("a.type=").append(type);
		}
		from_sql.append(" order by ").append("a.createTime desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	
	public static ArticleModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	public static ArticleModel getByType(Object type){
		return dao.findFirst("select *  from " + tableName + " where type = ? " , type);
	}
	public static boolean saveNews(String id,String title,int type,String summary,String content,String author,int level,int isComment,String headImg){
		ArticleModel model=new ArticleModel();
		model.setId(id);
		model.setTitle(title);
		model.setSummary(summary);
		model.setauthor(author);
		model.setcontent(content);
		model.settype(type);
		model.setCreateTime(new Date());
		model.setreadNum(0);
		model.setcommentsNun(0);
		model.setlevel(level);
		model.setstate(0);
		model.setisComment(isComment);
		model.setheadImg(headImg);
		try {
			model.save();
		} catch (Exception e) {
			return false;
		}
		return true;
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
	public static boolean updateNews(String id,String title,int type,String summary,String content,String author,int level,int isComment,String headImg){
		ArticleModel model=ArticleModel.getById(id);
		if(model==null){
			return false;
		}
		if(model.getstate()==1){//正在发布就不能更新，必须先下架在更新
			return false;
		}
		model.setTitle(title);
		model.setSummary(summary);
		model.setauthor(author);
		model.setcontent(content);
		model.settype(type);
		model.setlevel(level);
		model.setstate(0);
		model.setisComment(isComment);
		model.setheadImg(headImg);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新阅读量
	 * @return
	 */
	public static boolean addseeNum(ArticleModel model){
		if(model==null){
			return false;
		}
		int readNum=model.getreadNum();
		model.setreadNum(readNum+1);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 获取重要通知信息
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ArticleModel> getImportant(int pageNumber, int pageSize,Object type) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append(" from ").append(tableName);
		from_sql.append(" where state=? and type=?");
		from_sql.append(" order by level desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),1,type);
	}
	/**
	 * 获取重要通知信息
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ArticleModel> gettopnews(int pageNumber, int pageSize) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append(" from ").append(tableName);
		from_sql.append(" where state=?");
		from_sql.append(" order by level desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString(),1);
	}
	/**
	 * 获取首页展示的信息
	 * @param iswrapper
	 * @return
	 */
	public static List<ArticleModel> getWrapper(List<ConfigModel> wrapperlist) {
		StringBuffer articIds=new StringBuffer();
		for(ConfigModel m:wrapperlist){
			articIds.append("'").append(m.getValue()).append("'");
			articIds.append(",");
		}
		String articIdStr=articIds.substring(0, articIds.length()-1);
		StringBuffer from_sql=new StringBuffer("select *");
		from_sql.append(" from ").append(tableName);
		from_sql.append(" where state=? and id in (").append(articIdStr).append(")");
		return dao.find(from_sql.toString(),1);
	}
	/**
	 * 获取热度文章
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ArticleModel> getHeatRead(int pageNumber, int pageSize) {
		String sele_sql="select a.* ,t.name,t.dec ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" as a LEFT JOIN ").append(ArticleTypeModel.tableName).append(" as t ").append(" ON ");
		from_sql.append("a.type = ").append("t.id ");
		from_sql.append(" where a.state=1 ");
		from_sql.append(" order by a.readNum desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 获取热平文章
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<ArticleModel> getHeatComments(int pageNumber, int pageSize) {
		String sele_sql="select a.*, t.name,t.dec ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName).append(" as a LEFT JOIN ").append(ArticleTypeModel.tableName).append(" as t ").append(" ON ");
		from_sql.append("a.type = ").append("t.id ");
		from_sql.append(" where a.state=1 ");
		from_sql.append(" order by a.commentsNun desc");
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}
	/**
	 * 更新状态
	 * @param state
	 * @param id
	 * @param remark
	 * @return
	 */
	public static boolean updateState(int state,String id,String remark){
		ArticleModel m=getById(id);
		int rstate=m.getstate();
		if(rstate==0){//可以修改，可以发布，可以下架
			m.setstate(state);
			if(state==1){
				m.setremark(remark+"发布"+"|");
				m.setPublishTime(new Date());
			}else{
				m.setremark(remark+"操作"+"|");
			}
			m.update();
			return true;
		}else if(rstate==1){//可以下降
			m.setstate(state);
			if(state==2){
				m.setremark(remark+"下降"+"|");
				m.update();
				return true;
			}
			return false;
		}else{
			return false;
		}
	}
	/**
	 * 新闻评论控制
	 * @param state
	 * @param id
	 * @param remark
	 * @return
	 */
	public static boolean controlNewsComments(int isComments,String id){
		String sql="update "+tableName+" set isComment=? where id=?";
		int i=Db.update(sql,isComments,id);
		if(i!=1){
			return false;
		}
		return true;
	}
}
