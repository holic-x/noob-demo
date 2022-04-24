<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>8.四大域对象</title>
</head>
<body>
	<!-- 
		PAGE_SCOPE、REQUEST_SCOPE、SESSION_SCOPE、APPLICATION_SCOPE
		PageContext      只针对当前页有效
		Request          一次请求有效
		Session          一个ip地址从头到尾都有效
		Application      所有的页面都有效
	-->
	<!-- 
		案例分析：设置四大作用域保存相关数据，跳转页面测试相关数据是否存在
	-->
	<% 
		// 设置四大作用域 保存数据
		pageContext.setAttribute("pageContextContent", "pageContext作用域测试...");
		request.setAttribute("requestContent", "request作用域测试...");
		session.setAttribute("sessionContent", "session作用域测试...");
		application.setAttribute("applicationContent", "application作用域测试...");
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
	<!-- 提供链接跳转页面进行测试 --> 
	<a href="9.jsp">跳转页面到9.jsp</a>
</body>
</html>