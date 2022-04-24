<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>2.JSP的脚本表达式、脚本片段、注释</title>
</head>
<body>
	<!-- JSP的显示注释方式：客户端可以通过查看源代码进行查阅 -->
	<%-- JSP的隐式注释方式：客户端无法通过查看源代码进行查阅 --%>
	<%
		for(int i=0;i<10;i++){
	%>
	当前的i值为<%= i %><br/><!-- JSP的脚本表达式等价于out.write(...); -->
	<%
		}
	%>
	<!-- 
		JSP的脚本片段：用于在JSP页面中编写多行java代码
		单个脚本片断中的Java语句可以是不完整的，但是多个脚本
		片断组合后的结果必须是完整的Java语句
	 -->
</body>
</html>