package com.zhixingbus.server.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.upload.UploadFile;
import com.zhixingbus.server.controller.BaseController;
/**
 * 文件管理
 * @author xiao
 *2015年12月30日 10:35:43
 */
public class FileService {
	public static final String APK_PATH="/download";
	public static final String IMG_PATH="/resources/images/www/news";
	public static final String NEWS_BASE_PATH="/resources/images/www/news";//新闻图片根目录
	public static final String THUMBNAIL_PATH="/thumbnail";//新闻说略图目录
	public static final String CONTENT_PATH="/content";//新闻图图文目录
	public static final String MEDIA_PATH="/resources/media/www/news";
	public static final String MASTERPLATES_PATH="/resources/images/www/masterplates";
	/**
	 * 上传apk文件
	 * @param c
	 */
	public static void uploadFileApk(BaseController c){
		String basepath = c.getRequest().getSession().getServletContext().getRealPath(APK_PATH);
		UploadFile file = c.getFile("ZhiXingBus",basepath);//,"/download"
		File oldFile =new File(basepath+"/ZhiXingBus.apk");
		if (oldFile.exists()) {
			oldFile.delete();
		}
		boolean b=file.getFile().renameTo(oldFile);
		if(b){
			c.renderText(file.getFileName()+"上传成功！"+file.getSaveDirectory());
		}else{
			c.renderText(file.getFileName()+"上传失败！");
		}
		
	}
	/**
	 * 上传Img文件（新闻缩略图）
	 * @param c
	 */
	public static void uploadFileThumbnail(BaseController c){
		String id=c.getPara("id");
		String savapath=NEWS_BASE_PATH+"/"+id+THUMBNAIL_PATH;
		String basepath = c.getRequest().getSession().getServletContext().getRealPath(savapath);
		UploadFile file = c.getFile("thumbnail",basepath);
		
		File oldFile =new File(basepath+"/"+id+"_"+file.getFileName());
		if (oldFile.exists()) {
			oldFile.delete();
		}
		file.getFile().renameTo(oldFile);
		String murl=savapath+"/"+oldFile.getName();
		c.renderText(murl);
	}
	/**
	 * 上传新闻内容文件
	 * @param c
	 */
	public static void uploadNewsFile(BaseController c){
		String id=c.getPara("id");
		String savapath=NEWS_BASE_PATH+"/"+id+CONTENT_PATH;
		String basepath = c.getRequest().getSession().getServletContext().getRealPath(savapath);
		List<UploadFile> files = c.getFiles(basepath);
		Map<String, String> map=new HashMap<String, String>();
		UploadFile file=files.get(0);
		File oldFile =new File(basepath+"/"+id+"_"+file.getFileName());
		if (oldFile.exists()) {
			oldFile.delete();
		}
		file.getFile().renameTo(oldFile);
		String murl=savapath+"/"+oldFile.getName();
		
		map.put("err", "");
		map.put("msg", murl);
		c.renderJson(map);
	}
	/**
	 * 上传Img文件（新闻图片）
	 * @param c
	 */
	public static void uploadFileMedia(BaseController c){
		String savapath=MEDIA_PATH;
		String basepath = c.getRequest().getSession().getServletContext().getRealPath(savapath);
		List<UploadFile> files = c.getFiles(basepath);
		Map<String, String> map=new HashMap<String, String>();
		map.put("err", "");
		map.put("msg", savapath+"/"+files.get(0).getFileName());
		c.renderJson(map);
	}
	/**
	 * 上传Img文件(模版图片)
	 * @param c
	 */
	public static void uploadMasterplateImg(BaseController c){
		String savapath=MASTERPLATES_PATH;
		String basepath = c.getRequest().getSession().getServletContext().getRealPath(savapath);
		List<UploadFile> files = c.getFiles(basepath);
		Map<String, String> map=new HashMap<String, String>();
		map.put("err", "");
		map.put("msg", savapath+"/"+files.get(0).getFileName());
		c.renderJson(map);
	}
}
