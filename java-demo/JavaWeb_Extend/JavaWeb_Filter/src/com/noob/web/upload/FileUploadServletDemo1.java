package com.noob.web.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *	文件上传测试
 *	完成文件上传操作需要借助Apache提供的两个jar包完成
 *	步骤：
 *	a.导入commons-fileupload.jar、commons-io.jar
 *	b.编写jsp界面用于提供文件上传
 *	c.将相关数据提交到指定的servlet用于处理文件上传
 */
@WebServlet("/FileUploadServletDemo1")
public class FileUploadServletDemo1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 处理文件上传：
		 * 1.获取解析工厂：
		 * 	DiskFileItemFactory df = new DiskFileItemFactory();
		 * 2.通过解析工厂获取解析器
		 * 	ServletFileUpload sfu = new ServletFileUpload(df);
		 * 3.判断是否为上传表单，如果是则进行解析，若不是则直接return
		 * 4.通过解析器解析request请求，获取每一个FileItem进行解析
		 *   根据不同的FileItem进行不同的操作即可，上传的文件可以根据需求保存到
		 *   任意位置
		 */
		// a.获取解析工厂
		DiskFileItemFactory df = new DiskFileItemFactory();
		// b.根据解析工厂获取解析器
		ServletFileUpload sfu = new ServletFileUpload(df);
		// c.判断是否为上传表单，如果不是则直接return
		if(!sfu.isMultipartContent(request)) {
			return;
		}
		// d.如果是上传表单则用解析器解析request请求
		try {
			List<FileItem> list = sfu.parseRequest(request);
			// 依次进行遍历，根据不同的FileItem类型进行不同的操作
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()) {
					/**
					 *  如果是普通的上传项,获取相关的值进行打印
					 *  getFieldName():获取输入项的名称
					 *  getString():得到上传项的值
					 */
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					System.out.println("key:"+name+"--value:"+value);
				}else {
					/**
					 * 如果这个项是上传的文件，则通过io流进行读写操作
					 * getInputStream():获取输入流
					 * getName():获取文件完整路径信息
					 * 如果只需要获取文件名称，进行截取即可
					 */
					InputStream fis = fileItem.getInputStream();
					String filename = fileItem.getName();
					// 通过截取获取文件的名称
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					// 利用io流知识进行读写操作
					String path="e:\\upload";
					FileOutputStream fos = new FileOutputStream(path+"\\"+filename);
					byte[] buffer = new byte[1024];
					int hasRead=0;
					while((hasRead=fis.read(buffer))!=-1) {
						fos.write(buffer, 0, hasRead);
					}
					// 关闭文件流
					fis.close();
					fos.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
