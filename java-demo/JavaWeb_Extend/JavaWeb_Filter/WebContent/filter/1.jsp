<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全站乱码测试</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/FilterExample1Servlet" method="post">
		<input type="text" name="city" value="城市"/>
		<button type="submit">测试</button>
	</form>
</body>
</html>