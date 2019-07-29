package com.zhixingbus.server.controller;

import com.zhixingbus.server.service.FileService;

public class UploadFileController extends BaseController {

	/**
	 * 上传apk文件
	 */
	public void uploadApk(){
		FileService.uploadFileApk(this);
	}
	/**
	 * 上传新闻内容文件
	 */
	public void uploadNewsFile(){
		FileService.uploadNewsFile(this);
	}
	/**
	 * 上传视频文件
	 */
	public void uploadMedia(){
		FileService.uploadFileMedia(this);
	}
	/**
	 * 上传Swf文件
	 */
	public void uploadSwf(){
		FileService.uploadFileApk(this);
	}
	/**
	 * 上传zip,rar,txt文件
	 */
	public void uploadOther(){
		FileService.uploadFileApk(this);
	}
	/**
	 * 上传模版图片文件
	 */
	public void uploadMasterplateImg(){
		FileService.uploadMasterplateImg(this);
	}
	/**
	 * 上传Img文件（新闻缩略图）
	 * @param c
	 */
	public void uploadFileThumbnail()
	{
		FileService.uploadFileThumbnail(this);
	}
}
