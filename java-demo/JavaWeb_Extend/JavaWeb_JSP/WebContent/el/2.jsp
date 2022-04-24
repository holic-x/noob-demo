<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>2.使用el表达式获取数据：获取javaBean的属性</title>
</head>
<body>
	获取到的Person对象<br />
	<!-- 可以获取javaBean的属性 -->	
	得到的person:${person }<br />
	id:${person.id }<br />
	username:${person.username }<br />
	password:${person.password }<br />
	sex:${person.sex }<br />
	descr:${person.descr }<br />
	address:${person.address }<br />
	<!-- el表达式可以通过“.”继续获取相关的属性或对象 -->
	具体的address：<br />
	country:${person.address.country }<br />
	city:${person.address.city }<br />
	street:${person.address.city }<br />
</body>
</html>