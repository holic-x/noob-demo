<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>5.JSP的九大对象</title>
</head>
<body>
	<!-- 
		利用out对象和response对象向页面输出数据 
		a.JspWriter通过out缓冲区写带有缓冲的输出,只有缓冲区域满或者程序执行完毕
		     再输出到response缓冲区
		b.Response的缓冲区是直接输出到页面
		因此通过response对象输出的数据优先显示
		下述例子输出结果为：bbb、ddd、aaa、ccc
	-->
	<%
		out.write("aaa");
		response.getWriter().write("bbb");
		out.write("ccc");
		response.getWriter().write("ddd");
	%>
</body>
</html>