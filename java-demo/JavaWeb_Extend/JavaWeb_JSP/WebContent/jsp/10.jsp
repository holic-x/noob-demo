<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>10.Application对象：完成简单的网页计数器</title>
</head>
<body>
	<!-- 
		利用application对象完成一个简单的计数器
		a.判断当前是否为第一次访问，若为第一次则设置属性
		b.如果不是第一次访问，则执行计数操作
		c.显示计数信息到页面
	-->
	<% 
		if(application.getAttribute("counter")==null){
			// 第一次访问：设置计数器
			application.setAttribute("counter", 1);
		}else{
			// 获取当前的计数，执行+1操作
			int count = (int)application.getAttribute("counter");
			count ++;
			application.setAttribute("counter", count);
		}
		// 显示数据到当前页面 
		out.write("您是当前访问该页面的第"+application.getAttribute("counter")+"位访客");
	%>
</body>
</html>