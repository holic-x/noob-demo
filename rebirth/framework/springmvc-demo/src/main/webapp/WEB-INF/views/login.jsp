<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
</head>
<body>
<font color="red">${requestScope.message }</font><br/><br/>
<h3>登录页面</h3>
<form action="${pageContext.request.contextPath }/index/login" method="post">
    账号：<input type="text" name="username"/><br/><br/>
    密码：<input type="password" name="password"/><br/><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
