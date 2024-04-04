<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载测试</title>
</head>
<body>
	<c:forEach var="mm" items="${map }">
		<c:url value="DownLoadServlet" var="downurl">
			<c:param name="filename" value="${mm.key }"></c:param>
		</c:url>
		${mm.value } <a href="${downurl }">下载</a>
		<br />
	</c:forEach>
</body>
</html>