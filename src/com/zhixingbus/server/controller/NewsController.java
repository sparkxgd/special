package com.zhixingbus.server.controller;

import com.jfinal.aop.ClearInterceptor;

public class NewsController extends BaseController {

	/**
	 * 首页
	 */
	@ClearInterceptor
	public void index() {
		renderText("功能正在升级，敬请等候。。。。。");
	}
	
	/**
	 * 编辑文件
	 */
//		public void ueditor(){
////			render(view);
//			render("ueditor/index.html");
//		}
		
	/**
	 *  存储文件	
	 */
//		public void upload_news_article(){
////			FileService.uploadNewsArticle(this);
//			
//			String newsTitle = getPara("newsTitle");
//			String newsAbstract = getPara("newsAbstract");
//			String thumbnail = getPara("thumbnail");
//			String newsContent = getPara("newsContent");
//			
//
//			NewsWechatModel news =  new NewsWechatModel();
//			
//			news.setArticleId(UUID.randomUUID().toString());
//			news.setCreateTime(DateUtils.date2StringSlash(new Date(System.currentTimeMillis())));
//			news.setNewsAbstract(newsAbstract);
//			news.setNewsContent(newsContent);
//			news.setThumbnail(thumbnail);
//			
////			news.setNewsTitle(newsTitle);
//			news.save();
//			renderAlert("保存成功！", "/news/upload_news_show");
//		}
//		
//		
//		/**
//		 * 选择图片
//		 */
//		public void news_picture(){
//			String str = this.getRequest().getSession().getServletContext().getRealPath("/");
//			 File file = new File(str + "/WebRoot/upload/head/upload_test_picture");
//			
//			List<String> pic = new ArrayList<String>();
//		        File [] files = file.listFiles();
//		        for (int i = 0; i < files.length; i++){
//		            File file1 = files[i];
//		            file1.getName();   //根据后缀判断
//		            System.out.println("文件名："+file1.getName());
//		            pic.add(file1.getName());
//		        }
////		        renderJson(pic);
//		        setAttr("pic", pic);
//		        render("WEB-INF/html/files/news/news_show_pic.html");
//		}
//	
//	/**
//	 * 编辑文章
//	 */
//	public void news_edit(){
//		String articleId = getPara("articleId");
//		NewsWechatModel article =  NewsWechatModel.getById(articleId);
//		setAttr("article", article);
//		render("ueditor/index.html");
////		renderAlert("编辑成功！", "/admin/upload_news_show");
//	}
//	
//	public void news_doEdit() {
//		String articleId = getPara("articleId");
//		String newsTitle = getPara("newsTitle");
//		String newsAbstract = getPara("newsAbstract");
//		String thumbnail = getPara("thumbnail");
//		String newsContent = getPara("newsContent");
//		
//		//#############################################
//		if (StringUtil.isBlankOrEmpty(thumbnail)) {
//			renderAlertAndGoBack("账号不能为空！");
//			return;
//		}
////		CompressUtils cpUtils = new CompressUtils();
//		HttpServletRequest request = getRequest();
//		String basepath = request.getSession().getServletContext().getRealPath("/");
//	//缩略图入口	
//		System.out.println(basepath+"path:"+thumbnail);
//		String smallString = CompressUtils.thumbnail(thumbnail,basepath);
//		System.out.println("路径输出："+smallString);
//		
//		//###########################################
//		
//		if(articleId == null || articleId.length() <= 0){
//			
//			NewsWechatModel news =  new NewsWechatModel();
//			news.setArticleId(UUID.randomUUID().toString());
//			news.setCreateTime(DateUtils.date2StringSlash(new Date(System.currentTimeMillis())));
//			news.setNewsAbstract(newsAbstract);
//			news.setNewsContent(newsContent);
//			news.setThumbnail(smallString);
////			news.setThumbnail(thumbnail);
//			news.setNewsTitle(newsTitle);
//			news.setStatus(0);
//			news.save();
//			renderAlert("保存成功！", "/news/upload_news_show");
//		}else{
//			NewsWechatModel news =  NewsWechatModel.getById(articleId);
//			news.setNewsAbstract(newsAbstract);
//			news.setNewsContent(newsContent);
//			news.setThumbnail(thumbnail);
//			news.setNewsTitle(newsTitle);
//			news.update();
//			renderAlert("更改成功！", "/news/upload_news_show");
//		}
//	}
//	
//
//	
//	
//	
//	
//	
//	 /**
//	   *   资讯文章
//	   */
//		
//		public void upload_news_show(){
////			FileService.uploadNewsShow(this);
//			int page = getParaToInt("page", 1);
//			Page<NewsWechatModel> newsList = NewsWechatModel.findNewsByWechat(page, 10);
//			setAttr("newsList", newsList);
//			render("WEB-INF/html/files/news/news_show.html");
//		}
//		
//	/**
//	 * 展现新闻资讯
//	 */
//		@ClearInterceptor
//	public void news_client_content(){
////		FileService.newsClients(this);
//		String articleId = getPara("articleId");
//		
//		NewsWechatModel showClient = NewsWechatModel.findNewsArticle(articleId);
//		setAttr("showClient", showClient);
//		render("/WEB-INF/html/files/news/news_clients.html");
//		
//	}
//		/**
//		 * 客服端资讯展现
//		 */
//		@ClearInterceptor
//		public void news_phone_clients_list(){
//			List<NewsWechatModel> newsList = NewsWechatModel.findNewsPhoneClientsByWechat();
//			setAttr("newsList", newsList);
//			render("/WEB-INF/html/files/news/news_title_list.html");
//		}
//		
//		public void news_clients_list(){
//			List<NewsWechatModel> newsList = NewsWechatModel.findNewsClientsByWechat();
//			setAttr("newsList", newsList);
//			render("/WEB-INF/html/files/news/news_title_list.html");
//		}
//		
//		/**
//		 * 删除文章
//		 */
//		
//		public void news_delete(){
//			String articleId = getPara("articleId");
//			NewsWechatModel.deleteAllByArticleId(articleId);
//			renderAlert("删除成功！", "/news/upload_news_show");
//		}
//	
//	/**
//	 * 是否执行文章
//	 */
//	public void news_operate(){
//		String articleId = getPara("articleId");
//		int status = getParaToInt("status");
//		NewsWechatModel news =  NewsWechatModel.getById(articleId);
//		news.setStatus(status);
//		news.update();
//		renderAlert("更改成功！", "/news/upload_news_show");
//	}

}
