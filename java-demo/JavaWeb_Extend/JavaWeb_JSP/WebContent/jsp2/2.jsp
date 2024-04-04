<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>2.简易的网页计算器的实现</title>
</head>
	<!-- 导入bootstrap提供的相关的css、js文件，设定指定的编码集 -->
	<link rel="stylesheet" href="../css/bootstrap.min.css" />
	<script type="text/javascript" src="../js/jquery.min.js" charset="UTF-8" ></script>
	<script type="text/javascript" src="../js/bootstrap.min.js" charset="UTF-8" ></script>
	<body>
		<jsp:useBean id="calc" class="com.noob.web.model.Calculator"></jsp:useBean>
		<jsp:setProperty property="*" name="calc"/>
		<%
			// 调用cal()方法获取运算结果
			calc.cal();
		%>
		<!-- 显示运算结果 -->
		<div class="container">
			<h4 align="center">
				计算结果：
				<jsp:getProperty property="num1" name="calc"/>
				<jsp:getProperty property="operator" name="calc"/>
				<jsp:getProperty property="num2" name="calc"/>
				=
				<jsp:getProperty property="result" name="calc"/>
			</h4>
		</div>
		<div class="container">
			<form action="/JavaWeb_JSP/jsp2/2.jsp" method="post">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<td colspan="2" align="center">简易计算器</td>
					</tr>
					<tr>
						<td>参数1</td>
						<td>
							<input type="text" name="num1" />
						</td>
					</tr>
					<tr>
						<td>参数2</td>
						<td>
							<input type="text" name="num2" />
						</td>
					</tr>
					<tr>
						<td>运算符</td>
						<td>
							<select name="operator">
								<option value="+">+</option>
								<option value="-">-</option>
								<option value="*">*</option>
								<option value="/">/</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>