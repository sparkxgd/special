package com.zhixingbus.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpClientUtils {
	/**
	 * 初始化日志引擎
	 */
	private final Logger logger = LoggerFactory.getLogger(SftpClientUtils.class);

	/** Sftp */
	ChannelSftp sftp = null;
	Session sshSession = null;
	/** 主机 */
	public static final String host = "123.57.50.170";
	public static final String HEAD_PHOTO = "headphoto";
	public static final String UPLOAD = "upload";
	public static final String DOWNLOAD = "download";
	/** 端口 */
	private int port = 22;
	/** 用户名 */
	private String username = "root";
	/** 密码 */
	private String password = "Hunter015";

	/**
	 * 构造函数
	 *
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * 
	 */
	public SftpClientUtils() {
	}

	public static void main(String[] args) {
		SftpClientUtils sftpClientUtils = new SftpClientUtils();
		try {
			sftpClientUtils.connect();

			String uploadPath = "www" + File.separator + "huntersun-info" + File.separator + "upload";
			sftpClientUtils.upload(uploadPath, "e:" + File.separator + "1.txt");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sftpClientUtils.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 连接sftp服务器
	 * 
	 * @throws Exception
	 */
	public void connect() throws Exception {

		JSch jsch = new JSch();
		sshSession = jsch.getSession(this.username, this.host, this.port);
		System.out.println(SftpClientUtils.class + "Session created.");

		sshSession.setPassword(password);
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		sshSession.connect(20000);

		Channel channel = sshSession.openChannel("sftp");
		channel.connect();
		this.sftp = (ChannelSftp) channel;
		System.out.println(SftpClientUtils.class + " Connected to " + this.host + ".");
	}

	/**
	 * Disconnect with server
	 * 
	 * @throws Exception
	 */
	public void disconnect() throws Exception {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
			} else if (this.sftp.isClosed()) {
				logger.debug(SftpClientUtils.class + " sftp is closed already");
			}
		}

		if (sshSession != null) {
			sshSession.disconnect();
		}
	}

	/**
	 * 上传单个文件
	 *
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * 
	 * @throws Exception
	 */
	public void upload(String directory, String uploadFile) throws Exception {

		if (HEAD_PHOTO.equals(directory)) {
			this.cdToHeadphoto();
		} else if (UPLOAD.equals(directory)) {
			this.cdToUpload();
		} else if (DOWNLOAD.equals(directory)) {
			this.cdToDownload();
		}

		File file = new File(uploadFile);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			this.sftp.put(fis, file.getName());
		} catch (Exception e) {
		} finally {
			try {
				sftp.quit();
			} catch (Exception e) {
			}

			try {
				fis.close();
			} catch (Exception e) {
			}
		}
	}

	public void cdToDownload() throws SftpException {
		this.sftp.cd("..");
		this.sftp.cd("www");
		this.sftp.cd("www.zhixingbus.com");
		this.sftp.cd("download");
	}

	public void cdToUpload() throws SftpException {
		this.sftp.cd("..");
		this.sftp.cd("www");
		this.sftp.cd("www.zhixingbus.com");
		this.sftp.cd("upload");
	}

	public void cdToHeadphoto() throws SftpException {
		this.cdToUpload();
		this.sftp.cd("headphoto");
	}

	/**
	 * 上传目录下全部文件
	 *
	 * @param directory
	 *            上传的目录
	 * 
	 * @throws Exception
	 */
	public void uploadByDirectory(String directory) throws Exception {

		String uploadFile = "";
		List<String> uploadFileList = this.listFiles(directory);
		Iterator<String> it = uploadFileList.iterator();

		while (it.hasNext()) {
			uploadFile = it.next().toString();
			this.upload(directory, uploadFile);
		}
	}

	/**
	 * 下载单个文件
	 *
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveDirectory
	 *            存在本地的路径
	 * 
	 * @throws Exception
	 */
	public void download(String directory, String downloadFile, String saveDirectory) throws Exception {
		String saveFile = saveDirectory + "//" + downloadFile;

		this.sftp.cd(directory);
		File file = new File(saveFile);
		this.sftp.get(downloadFile, new FileOutputStream(file));
	}

	/**
	 * 下载目录下全部文件
	 *
	 * @param directory
	 *            下载目录
	 * 
	 * @param saveDirectory
	 *            存在本地的路径
	 * 
	 * @throws Exception
	 */
	public void downloadByDirectory(String directory, String saveDirectory) throws Exception {
		String downloadFile = "";
		List<String> downloadFileList = this.listFiles(directory);
		Iterator<String> it = downloadFileList.iterator();

		while (it.hasNext()) {
			downloadFile = it.next().toString();
			if (downloadFile.toString().indexOf(".") < 0) {
				continue;
			}
			this.download(directory, downloadFile, saveDirectory);
		}
	}

	/**
	 * 删除文件
	 *
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * 
	 * @throws Exception
	 */
	public void delete(String directory, String deleteFile) throws Exception {
		this.sftp.cd(directory);
		this.sftp.rm(deleteFile);
	}

	public void delete(String deleteFile) throws Exception {
		this.sftp.rm(deleteFile);
	}

	/**
	 * 列出目录下的文件
	 *
	 * @param directory
	 *            要列出的目录
	 * 
	 * @return list 文件名列表
	 *
	 * @throws Exception
	 */
	public List<String> listFiles(String directory) throws Exception {

		Vector fileList;
		List<String> fileNameList = new ArrayList<String>();

		fileList = this.sftp.ls(directory);
		Iterator it = fileList.iterator();

		while (it.hasNext()) {
			String fileName = ((LsEntry) it.next()).getFilename();
			if (".".equals(fileName) || "..".equals(fileName)) {
				continue;
			}
			fileNameList.add(fileName);

		}

		return fileNameList;
	}

	/**
	 * 更改文件名
	 *
	 * @param directory
	 *            文件所在目录
	 * @param oldFileNm
	 *            原文件名
	 * @param newFileNm
	 *            新文件名
	 * 
	 * @throws Exception
	 */
	public void rename(String directory, String oldFileNm, String newFileNm) throws Exception {
		this.sftp.cd(directory);
		this.sftp.rename(oldFileNm, newFileNm);
	}

	public void cd(String directory) throws Exception {
		this.sftp.cd(directory);
	}

	public InputStream get(String directory) throws Exception {
		InputStream streatm = this.sftp.get(directory);
		return streatm;
	}

}

// class DownloadApplyPersonServlet extends HttpServlet {
//
// /** 初始化日志引擎 * */
// private final Logger logger =
// LoggerFactory.getLogger(DownloadApplyPersonServlet.class);
//
// public void doGet(HttpServletRequest request, HttpServletResponse response)
// throws IOException, ServletException {
// doPost(request, response);
// }
//
// // 在doPost()方法中，当servlet收到浏览器发出的Post请求后，实现文件下载
// public void doPost(HttpServletRequest request, HttpServletResponse response)
// throws IOException, ServletException {
// logger.info("进入下载文件开始..........");
// String host = "";// 主机地址
// String port = "";// 主机端口
// String username = "";// 服务器用户名
// String password = "";// 服务器密码
// String planPath = "";// 文件所在服务器路径
// BufferedInputStream bis = null;
// BufferedOutputStream bos = null;
// OutputStream fos = null;
//
// String fileName = "KJ_CUST_KBYJ";// KJ_CUST_KBYJ20140326.txt
// SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
// String currentDate = formatter.format(new Date());
// String downloadFile = fileName + currentDate + ".zip";
//
// PrintWriter out = null;
// SftpClientUtils sftp = new SftpClientUtils(host, Integer.parseInt(port),
// username, password);
// try {
// sftp.connect();
// String filename = "";
// // String[] strs=planUrl.split("/");
// String filePath = planPath;
// // 列出目录下的文件
// List<String> listFiles = sftp.listFiles(filePath);
// boolean isExists = listFiles.contains(downloadFile);
// if (isExists) {
// sftp.cd(filePath);
// if (sftp.get(downloadFile) != null) {
// bis = new BufferedInputStream(sftp.get(downloadFile));
// }
// filename = downloadFile;
// fos = response.getOutputStream();
// bos = new BufferedOutputStream(fos);
// response.setCharacterEncoding("UTF-8");
// response.setContentType("application/x-msdownload;charset=utf-8");
// final String agent = request.getHeader("User-Agent");
// String attachment = "attachment;fileName=";
// String outputFilename = null;
//
// if (agent.indexOf("Firefox") > 0) {
// attachment = "attachment;fileName*=";
// outputFilename = "=?UTF-8?B?" + (new
// String(Base64.encodeBase64(filename.getBytes("UTF-8")))) + "?=";
// ;
// } else {
// if (agent.indexOf("MSIE") != -1) {
// outputFilename = new String(filename.getBytes("gbk"), "iso8859-1");
// } else {
// outputFilename = new String(filename.getBytes("UTF-8"), "iso8859-1");
// }
// }
// response.setHeader("Content-Disposition", attachment + outputFilename);
// int bytesRead = 0;
// // 输入流进行先读，然后用输出流去写，下面用的是缓冲输入输出流
// byte[] buffer = new byte[8192];
// while ((bytesRead = bis.read(buffer)) != -1) {
// bos.write(buffer, 0, bytesRead);
// }
// bos.flush();
// logger.info("文件下载成功");
// } else {
// response.setCharacterEncoding("utf-8");
// response.setContentType("text/html; charset=UTF-8");
// out = response.getWriter();
// out.println("<html >" + "<body>" + "没有找到你要下载的文件" + "</body>" + "</html>");
// }
// } catch (Exception e) {
// response.setCharacterEncoding("utf-8");
// response.setContentType("text/html; charset=UTF-8");
// out = response.getWriter();
// out.println("<html >" + "<body>" + "没有找到你要下载的文件" + "</body>" + "</html>");
// } finally {
// try {
// sftp.disconnect();
// logger.info("SFTP连接已断开");
// } catch (Exception e) {
// e.printStackTrace();
// }
//
// if (out != null) {
// out.close();
// }
// logger.info("out已关闭");
// if (bis != null) {
// bis.close();
// }
// logger.info("bis已关闭");
// if (bos != null) {
// bos.close();
// }
// logger.info("bos已关闭");
// }
// }
// }
