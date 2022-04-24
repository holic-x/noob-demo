<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传简单测试</title>
</head>
<body>	
	<!-- 文件上传jsp设计满足以下两个条件 
		a.提交的form表单必须提供enctype="multipart/form-data"属性
		b.上传组件为'file'类型：<input type="file" name="..." />
	-->
	<form action="${pageContext.request.contextPath }/FileUploadServletDemo1" method="post" enctype="multipart/form-data">
		上传用户：<input type="text" name="username"/><br/>
		上传文件：<input type="file" name="file" /><br/>
		<button type="submit">上传</button>
	</form>
</body>
</html>