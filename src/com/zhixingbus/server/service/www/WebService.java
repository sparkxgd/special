package com.zhixingbus.server.service.www;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.controller.BaseController;
import com.zhixingbus.server.model.ArticleModel;
import com.zhixingbus.server.model.ArticleTypeModel;
import com.zhixingbus.server.model.CommentsModel;
import com.zhixingbus.server.model.ConfigModel;
import com.zhixingbus.server.model.CooperationModel;
import com.zhixingbus.server.model.MenuModel;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.WebUtil;



/**
 * 
 * 创 建 人：xiao <br>
 * 日 期：2015年12月22日 14:09:29 <br>
 * 修 改 人：xiao <br>
 * 描 述：产品官网页面显示管理
 * 版 本 号：v1.0
 */
public class WebService {
	/**
	 * 新闻首页
	 * @param c
	 */
	public static void index(BaseController c) {
		int menuid=c.getParaToInt("id",-1);
		// 加载首页菜单和显示内容数据
		List<MenuModel> menus = MenuModel.getShowMenuList();
		StringBuffer menu = new StringBuffer();
		/**
		 * 加载菜单
		 */
		WebUtil.createIndexMenu(menu, menus,menuid);

		/**
		 * 加载板块
		 */
		StringBuffer I_about = new StringBuffer();
		ConfigModel tuwen=ConfigModel.getTuwen();
		String tuwenId="5";//默认
		if(tuwen!=null){
			tuwenId=tuwen.getValue();
		}
		//找出文章
		ArticleModel art=ArticleModel.getById(tuwenId);//暂时，由配置文件获取
		MenuModel model=null;
		if(art!=null){
			model=MenuModel.getById(art.gettype());
		}
		WebUtil.createTeletext(I_about,model,art);
		//新闻列表
		StringBuffer I_news = new StringBuffer();
		ConfigModel listType=ConfigModel.getListInfo();
		String type="7";//默认为7
		if(listType!=null){
			type=listType.getValue();
		}
		
		MenuModel typeMode=MenuModel.getById(type);//暂时(新闻资讯)，由配置文件获取
		Page<ArticleModel> p=ArticleModel.getImportant(1, 5,type);
		WebUtil.createListShow(I_news,p.getList(),typeMode);
		//查找wrapper的内容
//		List<ConfigModel> wrapperlist=ConfigModel.getwrapperInfo();
//		List<ArticleModel> wrapperModel=ArticleModel.getWrapper(wrapperlist);
		List<ArticleModel> wrapperModel=ArticleModel.gettopnews(1,5).getList();
		StringBuffer wrapper=new StringBuffer();
		WebUtil.createWrapper(wrapper, wrapperModel);
		
		//合作伙伴
		List<CooperationModel> coop=CooperationModel.getList();
		StringBuffer coopstr=new StringBuffer();
		WebUtil.createCooperation(coopstr, coop);
		
		
		c.setAttr("I_about", I_about.toString());
		
		c.setAttr("I_news", I_news.toString());
		c.setAttr("coop", coopstr.toString());
		c.setAttr("menu", menu.toString());
		c.setAttr("wrapper", wrapper.toString());
		
		c.render("index.html");
	}
	/**
	 * 点击菜单，查看
	 * @param c
	 */
	public static void detail(BaseController c){
		int treeId = c.getParaToInt("id",-1);
		MenuModel mm=MenuModel.getById(treeId);
		if(mm==null){
			return;
		}
		String menuName=mm.getname();
		ArticleModel artic=null;
		//先判断该菜单是一级还是二级菜单
		if(mm.gettreeId()!=-1){
			//说明是二级菜单
			treeId=mm.gettreeId();
			menuName=mm.getname();
			//显示内容
			artic=ArticleModel.getByType(mm.getId());
		}else{//如果是一级
			//先查查它是否有文章，如果没有，就找出他二级的一篇文章
			artic=ArticleModel.getByType(treeId);
			if(artic==null){
				List<MenuModel> fms=MenuModel.getTwoMenuList(treeId);
				if(fms.size()>0){
					artic=ArticleModel.getByType(fms.get(0).getId());//
					menuName=fms.get(0).getname();
				}
			}
			
		}
		//找出二级菜单
		List<MenuModel> mlist=MenuModel.getTwoMenuList(treeId);
		//左侧菜单显示
		StringBuffer leftMenus=new StringBuffer();
		WebUtil.createDetailLeftMenu(leftMenus,mlist,mm);
		
		//显示内容
		String content="暂无数据！";
		if(artic!=null){
			content=artic.getcontent();
		}
		//记录阅读数
		ArticleModel.addseeNum(artic);
		// 加载首页菜单
		List<MenuModel> menus = MenuModel.getShowMenuList();
		StringBuffer menu = new StringBuffer();
		WebUtil.createIndexMenu(menu, menus,treeId);
		//合作伙伴
		List<CooperationModel> coop = CooperationModel.getList();
		StringBuffer coopstr = new StringBuffer();
		WebUtil.createCooperation(coopstr, coop);
		
		c.setAttr("coop", coopstr.toString());
		c.setAttr("menu", menu.toString());
		c.setAttr("leftMenus", leftMenus);
		c.setAttr("menuName", menuName);
		c.setAttr("content", content);
		c.render("detail.html");
	}
	/**
	 * 查看文章
	 * @param c
	 */
	public static void detailList(BaseController c){
		int treeId = c.getParaToInt("id",-1);
		int pageNumber = c.getParaToInt("pageNumber", 1);
		int pageSize = c.getParaToInt("pageSize", 5);
		String searchKey = c.getPara("searchKey", "");
		MenuModel mm=MenuModel.getById(treeId);
		if(mm==null){
			return;
		}
		String menuName=mm.getTreeName();
		//先判断该菜单是一级还是二级菜单
		if(mm.gettreeId()!=-1){
			//说明是二级菜单
			treeId=mm.gettreeId();
			menuName=mm.getname();
		}
		//找出二级菜单
		List<MenuModel> mlist=MenuModel.getTwoMenuList(treeId);
		//左侧菜单显示
		StringBuffer leftMenus=new StringBuffer();
		WebUtil.createDetailLeftMenu(leftMenus,mlist,mm);
		
		//显示内容
		Page<ArticleModel> artics=ArticleModel.getList(pageNumber, pageSize, searchKey, treeId);
		StringBuffer content=new StringBuffer();
		WebUtil.createListContent(content,artics,treeId,searchKey);
		// 加载首页菜单
		List<MenuModel> menus = MenuModel.getShowMenuList();
		StringBuffer menu = new StringBuffer();
		// 加载菜单
		WebUtil.createIndexMenu(menu, menus,treeId);
		
		//合作伙伴
		List<CooperationModel> coop = CooperationModel.getList();
		StringBuffer coopstr = new StringBuffer();
		WebUtil.createCooperation(coopstr, coop);

		c.setAttr("coop", coopstr.toString());
		c.setAttr("menu", menu.toString());
		c.setAttr("leftMenus", leftMenus);
		c.setAttr("menuName", menuName);
		c.setAttr("content", content);
		c.render("detail.html");
	}
	/**
	 * c
	 * @param c
	 */
	public static void see(BaseController c){

		int treeId = c.getParaToInt("type",-1);
		String id=c.getPara("id");
		MenuModel mm=MenuModel.getById(treeId);
		if(mm==null){
			return;
		}
		String menuName=mm.getTreeName();
		//先判断该菜单是一级还是二级菜单
		if(mm.gettreeId()!=-1){
			//说明是二级菜单
			treeId=mm.gettreeId();
			menuName=mm.getname();
		}
		//找出二级菜单
		List<MenuModel> mlist=MenuModel.getTwoMenuList(treeId);
		//左侧菜单显示
		StringBuffer leftMenus=new StringBuffer();
		WebUtil.createDetailLeftMenu(leftMenus,mlist,mm);
		
		//显示内容
		ArticleModel artic=ArticleModel.getById(id);
		String content="暂无数据！";
		if(artic!=null){
			content=artic.getcontent();
		}
		//记录阅读数
		ArticleModel.addseeNum(artic);
		// 加载首页菜单
		List<MenuModel> menus = MenuModel.getShowMenuList();
		StringBuffer menu = new StringBuffer();
		// 加载菜单
		WebUtil.createIndexMenu(menu, menus,treeId);
		
		c.setAttr("menu", menu.toString());
		c.setAttr("leftMenus", leftMenus);
		c.setAttr("menuName", menuName);
		c.setAttr("content", content);
		c.render("detail.html");
	
	}
	/**
	 * 发表评论
	 * @param c
	 */
	public static void comment(BaseController c){
		String articleId = c.getPara("articleId");
		String content=c.getPara("content");
		String ip=c.getPara("ip","0.0.0.0");
		String cityName=c.getPara("cityName","**");
		Page<CommentsModel> page=null;
		if(StringUtil.isBlankOrEmpty(articleId)||StringUtil.isBlankOrEmpty(content)){
			c.renderJson(page);
			return;
		}
		if(CommentsModel.saveModel(articleId,content,ip,cityName)){
			page=CommentsModel.getListByArticleId(1, 10, articleId);
		}
		c.renderJson(page);
	}
	/**
	 * 发表评论点赞
	 */
	public static void clicklike(BaseController c){
		int type = c.getParaToInt("type");
		String id=c.getPara("id");
		CommentsModel m=CommentsModel.getById(id);
		if(m==null){
			c.renderJson(m);
			return;
		}
		switch (type) {
		case 1://赞同
			int agreeNum=m.getagreeNum();
			m.setagreeNum(agreeNum+1);
			try {
				m.update();
			} catch (Exception e) {
				
			}
			break;
		case 2://不赞同
			int disapprovalNum=m.getdisapprovalNum();
			m.setdisapprovalNum(disapprovalNum+1);
			try {
				m.update();
			} catch (Exception e) {
				
			}
			break;
		default:
			break;
		}
		c.renderJson(m);
	}
	/**
	 * 加载菜单
	 * @param sb
	 */
	private static void createMenu(StringBuffer sb,List<ArticleTypeModel> list,int type){
		sb.append("<ul>");
		sb.append("<li><a href='/'>主页</a></li>");
		if(type==0){
			sb.append("<li><a class='active' href='/news'>最新资讯</a></li>");
		}else{
			sb.append("<li><a href='/news'>最新资讯</a></li>");
		}
		for(ArticleTypeModel m:list){
			String clas="class='active'";
			if(m.getId()==type){
				sb.append("<li><a "+clas+" href='/news?type="+m.getId()+"'>"+m.getDec()+"</a></li>");
			}else{
				sb.append("<li><a  href='/news?type="+m.getId()+"'>"+m.getDec()+"</a></li>");
			}
		}
		sb.append("</ul>");
		
	}
	/**
	 * 创建页面右侧信息栏目
	 */
	private static void createRight_column(StringBuffer sb,Page<ArticleModel> pi,Page<ArticleModel> pr,Page<ArticleModel> pc){
		if(pi!=null){
			sb.append("<div class='item'>");
			sb.append("<header>");
			sb.append("<h2 class='with-line'>");
			sb.append("重要通知");
			sb.append("</h2>");
			sb.append("</header>");
			sb.append("<ul>");
			for(ArticleModel m:pi.getList()){
				sb.append("<li><a href='see?id="+m.getId()+"&type="+m.gettype()+"'>"+m.getTitle()+"</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");
		}
		if(pr!=null){
			sb.append("<div class='item'>");
			sb.append("<header>");
			sb.append("<h2 class='with-line'>");
			sb.append("热读文章");
			sb.append("</h2>");
			sb.append("</header>");
			sb.append("<ul>");
			for(ArticleModel m:pr.getList()){
				sb.append("<li><a href='see?id="+m.getId()+"&type="+m.gettype()+"'>"+m.getTitle()+"</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");
		}
		if(pc!=null){
			sb.append("<div class='item'>");
			sb.append("<header>");
			sb.append("<h2 class='with-line'>");
			sb.append("热评文章");
			sb.append("</h2>");
			sb.append("</header>");
			sb.append("<ul>");
			for(ArticleModel m:pc.getList()){
				sb.append("<li><a href='see?id="+m.getId()+"&type="+m.gettype()+"'>"+m.getTitle()+"</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");
		}
	}
	/**
	 * 加载翻页
	 * @param sb
	 */
	private static void createPagenation(StringBuffer sb,Page<ArticleModel> p,String searchKey){
	
		sb.append("<ul>");
		if(p.getTotalPage()<=10){
			for(int i=1;i<=p.getTotalPage();i++){
				if(p.getPageNumber()==i){
					sb.append("<li><a class='pagenation_cru_a' href='/news?pageNumber="+i+"&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>"+i+"</a></li>");
				}else{
					sb.append("<li><a href='/news?pageNumber="+i+"&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>"+i+"</a></li>");
				}
			}
		}else{
			
			int tolp=p.getPageNumber()+5;
			if(tolp>p.getTotalPage()){
				tolp=p.getTotalPage();
			}
			int lowp=p.getPageNumber()-5;
			if(lowp<0){
				if(10<p.getTotalPage()){
					tolp=10;
				}
				for(int i=p.getPageNumber();i<=tolp;i++){
					sb.append("<li><a href='/news?pageNumber="+i+"&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>"+i+"</a></li>");
				}
			}else{
				sb.append("<li><a href='/news?pageNumber='1'&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>首页</a></li>");
				for(int i=lowp;i<=tolp-1;i++){
					if(p.getPageNumber()==i){
						sb.append("<li><a class='pagenation_cru_a' href='/news?pageNumber="+i+"&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>"+i+"</a></li>");
					}else{
						sb.append("<li><a href='/news?pageNumber="+i+"&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>"+i+"</a></li>");

					}
				}
			}
			sb.append("<li><span>. . . . . . . . . .</span></li>");
			sb.append("<li><a href='/news?pageNumber="+p.getTotalPage()+"&pageSize="+p.getPageSize()+"&searchKey="+searchKey+"'>末页</a></li>");
		}
		sb.append("</ul>");
	}
}
