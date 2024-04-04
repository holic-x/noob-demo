<%@page import="com.noob.web.session.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎您，
	<%
		// 获取当前传入的session数据
		User user = (User)session.getAttribute("user");
		if(user!=null){
			out.write(user.getUsername());
		}
	%>
	<br/>
	<a href="userLogin.html">登录</a>
</body>
</html>