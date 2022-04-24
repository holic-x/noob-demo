package com.noob.web.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
 * 文件上传测试2：实现多文件的上传，此外文件上传还有常见的几个小问题需要进行处理 
 * a.文件同名问题： 通过UUID解决，对文件名称进行重新编码即可
 * b.上传文件中文乱码问题： 
 * 		通过设置消息头进行解决： 如果是设置了过滤器则不需要特意指定也能解决中文乱码问题
 * 		但如果是直接从表单中获取数据并输出到控制台出现中文乱码，则需要对获取的数据进行
 * 		再次封装后再进行输出便能解决乱码问题(默认是ISO-8859-1编码)
 * 		eg:String value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
 * c.自定义保存文件夹 
 * 		随着文件删除数目的增加，普通的存储规则已经无法满足需求，一般情况下需要通过
 * 		指定的规则对上传文件进行存储，可以以日期进行分类，也可以用户名进行分类，亦可 
 * 		通过传入的指定的文件名称的hashcode进行分类
 */
@WebServlet("/FileUploadServletDemo2")
public class FileUploadServletDemo2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// a.获取解析工厂
		DiskFileItemFactory df = new DiskFileItemFactory();
		// b.根据解析工厂获取解析器
		ServletFileUpload sfu = new ServletFileUpload(df);
		// c.判断是否为上传表单，如果不是则直接return
		if (!sfu.isMultipartContent(request)) {
			return;
		}
		// d.如果是上传表单则用解析器解析request请求
		try {
			List<FileItem> list = sfu.parseRequest(request);
			// 依次进行遍历，根据不同的FileItem类型进行不同的操作
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					/**
					 * 如果是普通的上传项,获取相关的值进行打印 getFieldName():获取输入项的名称 getString():得到上传项的值
					 */
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					// 直接获取从表单中读取的数据，存在中文乱码问题，则重新封装该数据再进行输出即可
					value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println("key:" + name + "--value:" + value);
				} else {
					/**
					 * 如果这个项是上传的文件，则通过io流进行读写操作 getInputStream():获取输入流 getName():获取文件完整路径信息
					 * 如果只需要获取文件名称，进行截取即可
					 */
					InputStream fis = fileItem.getInputStream();
					String filename = fileItem.getName();
					// 通过截取获取文件的名称
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 利用io流知识进行读写操作
					String path = "e:\\upload";
					/**
					 * 1.普通方式：
					 * FileOutputStream fos = new FileOutputStream(path + "\\" + filename);
					 */
					/**
					 * 2.通过自定义规则的文件名称进行保存（参考时间）
					 * FileOutputStream fos = new FileOutputStream(getPath(path) + "\\" + getFilename(filename));
					 */
					/**
					 * 3.通过自定义规则的文件名称进行保存（参考filename的hashcode）
					 * FileOutputStream fos = new FileOutputStream(getPath(path,filename) + "\\" + getFilename(filename));
					 */
					FileOutputStream fos = new FileOutputStream(getPath(path,filename) + "\\" + getFilename(filename));
					byte[] buffer = new byte[1024];
					int hasRead = 0;
					while ((hasRead = fis.read(buffer)) != -1) {
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
	
	// 根据UUID获取文件名称
	public String getFilename(String filename) {
		return UUID.randomUUID().toString()+"_"+filename;
	}
	
	// 根据自定义规则获取文件保存路径(参考时间)
	public String getPath(String path) {
		// 按照时间规则保存上传数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String savePath = sdf.format(new Date());
		String dir = path + "/" + savePath;
		File file =new File(dir);
		// 如果指定目录不存在则创建目录即可
		if(!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}
	
	// 根据自定义规则获取文件保存路径(参考filename的hashcode)
	public String getPath(String path,String filename) {
		int hashcode = filename.hashCode();
		int dir1 =hashcode & 0xf ;  // 得到0-15之间的数字
	    int dir2=(hashcode & 0xf0) >> 4 ; // 得到0-15之间的数字
	    // 将目录路径进行拼接
	    String dir=path+"/"+dir1+"/"+dir2;
		File file =new File(dir);
		// 如果指定目录不存在则创建目录即可
		if(!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
