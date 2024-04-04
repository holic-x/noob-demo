<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.JSP的指令include：引入其它页面</title>
</head>
<body>
	<%@ include file="/public/head.jsp" %>
	<p>我是4.jsp中定义的内容</p>
	<%@ include file="/public/footer.jsp" %>
</body>
</html>