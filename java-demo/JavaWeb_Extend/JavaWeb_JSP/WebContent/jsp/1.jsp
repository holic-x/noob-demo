<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1.jsp的基本使用：获取当前系统时间</title>
</head>
<body>
	<pre>
		1.JSP全称是Java Server Pages,jsp与servelet技术一样,
		     均是SUN公司定义的用于开发动态Web资源的技术
		2.JSP这门技术的最大的特点在于,写jsp就像在写html,
		    但它相比html而言,html只能为用户提供静态数据,
		    而Jsp技术允许在页面中嵌套java代码,为用户提供动态数据
	</pre>
	<%
		// 编写java代码
		Date date = new Date();
		out.write("当前系统时间" + date.toLocaleString());
	%>
</body>

</html>