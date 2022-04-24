<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>9.四大作用域测试</title>
</head>
<body>
	<%
		// 获取四大作用作用域的数据
		String pageContextContent = (String)pageContext.getAttribute("pageContextContent");
		String requestContent = (String)request.getAttribute("requestContent");
		String sessionContent = (String)session.getAttribute("sessionContent");
		String applicationContent = (String)application.getAttribute("applicationContent");
		// 向页面中输出数据
		out.write(pageContextContent+"<br/>");
		out.write(requestContent+"<br/>");
		out.write(sessionContent+"<br/>");
		out.write(applicationContent+"<br/>");
	%>
</body>
</html>