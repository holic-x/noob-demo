package com.noob.web.response;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;

import sun.misc.BASE64Encoder;

/**
 * 案例2：通过response对象完成文件的下载
 */
@WebServlet("/ResponseDemo2")
public class ResponseDemo2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 定义encodeDownloadFileName方法针对不同的浏览器进行编码
	 */
	private String encodeDownloadFileName(String filename,String agent) throws IOException {
		if(agent.contains("Firefox")) {
			// 火狐浏览器
			filename = "?UTF-8?B?"+new BASE64Encoder().encode(filename.getBytes("UTF-8"))+"?=";
		}else {
			// 针对其他不同的浏览器
			filename = URLEncoder.encode(filename,"UTF-8");
		}
		return filename;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 *  a.获取要下载的文件的真实路径信息（发布路径下的目录）
		 *  D:\dev\soft\tomcat\apache-tomcat-8.5.23\webapps\JavaWeb\img\...
		 */
		String path = this.getServletContext().getRealPath("img/1.jpg");
		// b.根据真实路径信息完成文件的读写
		String filename = path.substring(path.lastIndexOf("\\")+1);
		// c.根据不同的浏览器获取下载文件的名称
		filename = encodeDownloadFileName(filename, request.getHeader("user-agent"));
		// d.利用response设置输出消息头
		response.setHeader("content-disposition", "attachment;filename="+filename);
		// e.实现文件下载
		InputStream in = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		int hasRead=0;
		byte[] buffer = new byte[1024];
		while((hasRead=in.read(buffer))!=-1) {
			out.write(buffer,0,hasRead);
		}
		in.close();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
