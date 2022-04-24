<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3.JSP的指令page：error-page的使用</title>
</head>
<body>
	<!-- 
		JSP的page指令可以用于解决JSP中文乱码问题
		1.JSP程序存在有与Servlet程序完全相同的中文乱码问题
			a.输出响应正文时出现的中文乱码问题 
			b.读取浏览器传递的参数信息时出现的中文乱码问题
		2.JSP引擎将JSP页面翻译成Servlet源文件时也可能导致中文乱码问题 
			a.JSP引擎将JSP源文件翻译成的Servlet源文件默认采用UTF-8编码，
			     而JSP开发人员可以采用各种字符集编码来编写JSP源文件，因此，JSP引擎
			     将JSP源文件翻译成Servlet源文件时，需要进行字符编码转换。 
			b.如果JSP文件中没有说明它采用的字符集编码，JSP引擎将把它当作默认的ISO8859-1
			     字符集编码处理。
		3.如何解决JSP引擎翻译JSP页面时的中文乱码问题 
			a.通过page指令的contentType属性说明JSP源文件的字符集编码
			b.page指令的pageEncoding属性说明JSP源文件的字符集编码
	 -->
	 <!-- 
	 	可以利用errorPage指定错误页面的显示，需要在web.xml中配置相关的属性
	 	既可以配置错误的异常，也可以配置错误码
	 	page  errorPage="/error/error.jsp"%
	 	web.xml中配置：
	 	<error-page>
			<exception-type>异常类型</exception-type>
			<location>指定错误页面路径</location>
		</error-page>
   		<error-page>
   	   		<error-code>错误码代号</error-code>
   	   		<location>指定错误页面路径</location>
   		</error-page>
	 -->
	<%
	 	// 定义错误触发异常
	  	int x=10/0;
	  	out.write(x);
	%>
</body>
</html>