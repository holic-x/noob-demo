<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1.使用el表达式获取数据：获取四大领域对象的数据</title>
</head>
<body>
	<!-- 常规获取数据的方式 -->
	常规获取数据：<%=request.getAttribute("username") %><br/>
	<!-- 通过el表达式获取数据 -->
	通过el表达式获取四大领域对象的数据<br/>
	用户名：${username }<br/>
	密码：${password }<br/>
	具体描述：${descr }<br/>
</body>
</html>