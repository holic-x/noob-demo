<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>6.利用out对象实现文件的下载</title>
</head>
<body>
	<!--
		文件下载的实现步骤 
		a.获取文件的真实路径和名称
		b.设置消息头
		c.利用response对象获取OutputStream实现文件下载
		此处实现文件下载有一个隐藏的小问题，虽然能够实现文件下载，但是却由于
		JspWriter对象out与用户自定义的OutputStream对象sout存在字符流
		与字节流写入的冲突，会导致后台代码报错，为了解决问题，可以打开相应的
		源码文件查看将jsp转化为相应的servlet的代码，删除掉除了<% %>之外
		所有的out输出的数据即可
		即利用out对象实现文件的下载,在一个java类中不能使用字节流和字符流
		同时向一个页面输出数据否则会报错
	 -->
	<%
		// 获取文件的真实路径和名称
		String path = request.getRealPath("/img/1.jpg");
		String filename = path.substring(path.lastIndexOf("\\")+1);
		FileInputStream fis = new FileInputStream(path);
		// 设置消息头
		response.setHeader("content-disposition", "attachment;filename="+filename);
		// 通过OutputStream字节流实现文件下载
		OutputStream sout = response.getOutputStream();
		int hasRead=0;
		byte[] buffer = new byte[1024];
		while((hasRead=fis.read(buffer))!=-1){
			sout.write(buffer, 0, hasRead);
		}
	%>

</body>
</html>