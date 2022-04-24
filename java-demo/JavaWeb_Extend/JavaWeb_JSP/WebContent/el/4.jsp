<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.el表达式执行运算示例</title>
</head>
	<body>
		<!-- el表达式可以直接执行相应的运算，得到相应的数据进行显示 -->
		一天有24*3600=${24*3600 }秒<hr/>
		<!-- 
			el表达式结合jstl用以判断数据是否为空 
			c:if test="${expression}"
			test中指定的内容如果返回true则执行相应标签中的内容,
			可以通过empty(object)判断对象是否为空
		-->
		empty判断对象是否为空测试<br/>
		<c:if test="${person!=null }">
			当前用户：${person.username }
		</c:if>
		<c:if test="${empty(person) }">
			抱歉！当前没有数据显示......
		</c:if>
		<hr/>
		<!-- 
			可以通过二元运算符判断传入的对象是否为空
			${(expression)?statement1:statement2}
			如果expression返回的值为true则执行statement1,否则执行statement2
			可以根据servlet传递的参数进行数据回显
		-->
		二元运算符测试<br/>
		${(person!=null)?person.username:"当前没有数据显示哦！！！" }
		<!-- 根据servlet传递的参数进行数据回显 -->
		<input type="radio" name="gender" value="男" ${(person.sex=='男')?'checked':'' }/>男
		<input type="radio" name="gender" value="女" ${(person.sex=='女')?'checked':''}/>女
	</body>
</html>