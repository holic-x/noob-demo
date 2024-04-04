<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1.JSP中JavaBean的使用</title>
</head>
<body>
	<!-- 
		自定义一个领域对象model：Student
		其具有如下属性：id、username、password、birthday
	-->
	<!-- 
		a.jsp:useBean标签,其属性如下
		id属性用于指定JavaBean实例对象的引用名称和其存储在域范围中的名称
		class属性用于指定JavaBean的完整类名（即必须带有包名）
		scope属性用于指定JavaBean实例对象所存储的域范围，其默认值是page
		             其取值只能是page、request、session、application四者之一
	-->
	<jsp:useBean id="stu" class="com.noob.web.model.Student"></jsp:useBean>
	<% System.out.println("用户名："+stu.getUsername()); %>
	
	<!-- 
		b.jsp:setProperty标签,其用于设置和访问JavaBean对象的属性
			name属性用于指定JavaBean对象的名称
			property属性用于指定JavaBean实例对象的属性名
			value属性用于指定JavaBean对象的某个属性的值，value的值可以是字符串，
				  也可以是表达式。为字符串时，该值会自动转化为JavaBean属性相应的类型，
				  如果value的值是一个表达式，那么该表达式的计算结果必须与所要设置的
				 JavaBean属性的类型一致。
			param属性用于将JavaBean实例对象的某个属性值设置为一个请求参数值，该属性值
				  同样会自动转换成要设置的JavaBean属性的类型
	-->
	<!-- 方式1：设置属性值的普通方式 -->
	<%-- 
	<jsp:setProperty property="id" name="stu" value="001"/>
	<jsp:setProperty property="username" name="stu" value="hhh"/>
	<jsp:setProperty property="password" name="stu" value="wkaka"/>
	<jsp:setProperty property="birthday" name="stu" value="<%=new Date() %>"/>
	--%>
	
	<!-- 
		方式2：通过地址栏参数传入 
		http://localhost:8080/JavaWeb_JSP/jsp2/1.jsp?id=hhh&username=baba
	-->
	<%-- 
	<jsp:setProperty property="id" name="stu" param="id"/>
	<jsp:setProperty property="username" name="stu" param="username"/>
	--%>
	
	<!-- 方式3：所有的参数值都通过参数传递 -->
	<jsp:setProperty property="*" name="stu"/>
	
	<!-- 获取相应的值进行测试 -->
	<!--  
		c.jsp:getProperty标签,用于读取JavaBean对象的属性，也就是调用JavaBean对象
			的getter方法，然后将读取的属性值转换成字符串后插入进输出的响应正文中
		name属性用于指定JavaBean实例对象的名称，其值应与jsp:useBean标签的id属性值相同
		  property属性用于指定JavaBean实例对象的属性名
		如果一个JavaBean实例对象的某个属性的值为null,则使用jsp:getProperty
		标签输出该属性的结果将是一个内容为“null”的字符串。
	-->
	用户id:<jsp:getProperty property="id" name="stu"/><br/>
	用户名:<jsp:getProperty property="username" name="stu"/><br/>
	密码:<jsp:getProperty property="password" name="stu"/><br/>
	生日:<jsp:getProperty property="birthday" name="stu"/><br/>
</body>
</html>