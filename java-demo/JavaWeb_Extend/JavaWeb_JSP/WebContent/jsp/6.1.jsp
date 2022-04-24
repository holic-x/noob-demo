<%@page import="java.io.OutputStream"%><%@page import="java.io.FileInputStream"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	// application就是ServletContext对象
	String path= application.getRealPath("/img/1.jpg");
	//得到文件的名字
	String filename=path.substring(path.lastIndexOf("\\")+1);// 4.jpg
	response.setHeader("content-disposition", "attachment;filename="+filename);
	FileInputStream fis =new FileInputStream(path);
	int hasRead=0;
	byte [] buffer =new byte[1024];
	OutputStream  sout=response.getOutputStream();
	while((hasRead=fis.read(buffer))!=-1){
		sout.write(buffer,0,hasRead);
	}
 %>