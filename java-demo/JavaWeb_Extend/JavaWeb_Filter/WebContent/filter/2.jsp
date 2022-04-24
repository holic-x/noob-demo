<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>防盗链测试</title>
</head>
<body>
	<img alt="图片" src="${pageContext.request.contextPath }/img/1.jpg"/>
	<a href="javascript:window.open('../img/1.jpg')" onclick="window.open('${pageContext.request.contextPath }/img/1.jpg');return false">打开图片</a>
</body>
</html>