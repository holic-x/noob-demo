<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Index</title>

    <c:if test="${requestScope.username!=null}">${requestScope.username} <a href="${pageContext.request.contextPath }/index/logout">退出登录</a> </c:if>

</head>
<body>
<div class="container">
   hello SpringMVC
</div>
</body>
</html>