<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>12.转发的页面测试</title>
</head>
<body>
	获取的用户名：<%= request.getParameter("username")%>
	<!-- 
		request.getParameter(name属性):
		获取的是表单的数据、通过地址栏传递的参数、通过a标签传递的参数
		
		request.getAttribute(key):
		获取的是保存在领域对象中的数据
	-->
</body>
</html>