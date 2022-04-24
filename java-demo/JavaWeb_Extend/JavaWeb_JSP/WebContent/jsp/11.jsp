<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>11.JSP标签的使用</title>
</head>
<body>
	<!-- 
		jsp:include标签是动态引入，对比include指令，两个最终实现 
		将jsp页面中的内容合并，结合下列例子分析，区别在于jsp:include
		标签涉及到的2个jsp文件会被翻译成2个相应的servlet，这两个servlet
		的内容在执行的时候会进行合并，而include指令则是静态引入，其涉及到
		的两个jsp文件会被合并成1个servlet文件，其内容是在源文件级别进行
		合并。
	-->
	<jsp:include page="/public/head.jsp"/>
	<p>我是11.jsp中定义的内容</p>
	<jsp:include page="/public/footer.jsp"/>
	
	<!-- jsp:forward实现页面转发、jsp:param实现参数传递 -->
	<%--
		<jsp:forward page="12.jsp">
			<jsp:param value="noob" name="username"/>
		</jsp:forward>
	--%>
</body>
</html>