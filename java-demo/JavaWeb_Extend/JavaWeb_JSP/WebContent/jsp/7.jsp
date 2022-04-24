<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>7.PageContext对象：存取数据</title>
</head>
<body>
	<!-- 
		pageContext对象是JSP技术中最重要的一个对象，它代表JSP页面的运行环境，
		这个对象不仅封装了对其它8大隐式对象的引用，它自身还是一个域对象，可以用来保存数据。
		并且，这个对象还封装了web开发中经常涉及到的一些常用操作，例如引入和跳转其它资源、
		检索其它域对象中的属性等。
		示例：将pageContext作为域对象存取数据，可以将数据存储到自身域对象，也可以将
			数据存储到其他的域对象
	-->
	<% 
		// pageContext存储数据到自身域对象
		pageContext.setAttribute("data", "pageContext存储数据到自身域对象"+"<br/>");
		// 从pageContext与对象中获取数据
		String data = (String)pageContext.getAttribute("data");
		// 输出数据到页面中
		out.write(data);
		
		// pageContext存储数据到其他域对象中
		pageContext.setAttribute("info", "pageContext存储数据到其他的域对象", PageContext.SESSION_SCOPE);
		// 从指定的域对象中获取数据
		String info = (String)session.getAttribute("info");
		// 输出数据到页面中
		out.write(info);
	%>
</body>
</html>