<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>脏话过滤测试</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/FilterExample3Servlet" method="post">
	<textarea name="content" rows="5" cols="60">
	</textarea>
	<button type="submit">测试</button>
	</form>
</body>
</html>
