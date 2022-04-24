<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3.获取数据案例3：通过el表达式获取数组、list、map集合的数据</title>
</head>
	<body>
		<!-- 
			普通获取 ：
			如果仅仅只是普通获取数组、list集合数据则可通过下标进行获取
			${array }获取的是数组的地址
			${list }获取的是整个列表所有数据
			通过指定的下标可以获取对应的数据，进一步获取相关属性
		-->
		获取数组数据：<br/>
		${array }<br/>
		${array[0] }<br/>
		${array[0].username }<br/>
		获取列表数据：<br/>
		${list }<br/>
		${list[1] }<br/>
		${list[1].username }<br/>
	
		<!-- 
			此处使用el表达式结合jstl实现循环遍历
			需要导入相应的jstl.jar,并在页面中引入该文件方能正常使用 
			@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
		-->
		<!-- 此处只简单显示id、用户名、密码 -->
		<table border="1">
			<tr>
				<td colspan="3" align="center">迭代数组</td>
			</tr>
			<tr>
				<td>id</td>
				<td>用户名</td>
				<td>密码</td>
			</tr>
			<!-- c:forEach循环迭代：var是迭代后的变量名 items是要进行迭代的数据 -->
			<c:forEach var="person" items="${array }" >
				<tr>
					<td>${person.id }</td>
					<td>${person.username }</td>
					<td>${person.password }</td>
				</tr>
			</c:forEach>
		</table><hr/>
		<!-- 迭代list集合 -->
		<table border="1">
			<tr>
				<td colspan="3" align="center">迭代list集合</td>
			</tr>
			<tr>
				<td>id</td>
				<td>用户名</td>
				<td>密码</td>
			</tr>
			<!-- c:forEach循环迭代：var是迭代后的变量名 items是要进行迭代的数据 -->
			<c:forEach var="person" items="${list }" >
				<tr>
					<td>${person.id }</td>
					<td>${person.username }</td>
					<td>${person.password }</td>
				</tr>
			</c:forEach>
		</table><hr/>
		<!-- 迭代map集合 -->
		<table border="1">
			<tr>
				<td colspan="3" align="center">迭代map集合</td>
			</tr>
			<tr>
				<td>用户1</td>
				<td>用户名</td>
				<td>密码</td>
			</tr>
			<tr>
				<td>${map.person1 }</td>
				<td>${map.person1.username }</td>
				<td>${map.person1.password }</td>
			</tr>
		</table><hr/>
	</body>
</html>