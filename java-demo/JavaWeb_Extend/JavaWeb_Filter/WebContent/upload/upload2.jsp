<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多文件上传简单测试</title>
</head>
	<body>	
	<!-- 文件上传jsp设计满足以下两个条件 
		a.提交的form表单必须提供enctype="multipart/form-data"属性
		b.上传组件为'file'类型：<input type="file" name="..." />
	-->
	<form action="${pageContext.request.contextPath }/FileUploadServletDemo2" method="post" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<td>上传用户</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>上传文件</td>
					<td id="upload">
						<input type="file" name="file" />
						<button type="button" onclick="addMore()">上传更多</button>
					</td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit">上传</button></td>
				</tr>
			</table>
			<script type="text/javascript">
				function addMore(){
					// 获取当前的父标签
					var td = document.getElementById("upload");
					// 定义要新建的标签
					var br = document.createElement("br");
					var input = document.createElement("input");
					input.type="file";
					input.name="file";
					var button = document.createElement("input");
					button.type="button";
					button.value="remove";
					button.onclick=function removeElement(){
						// 移除
						td.removeChild(br);
						td.removeChild(input);
						td.removeChild(button);
					}
					// 将标签进行添加
					td.appendChild(br);
					td.appendChild(input)
					td.appendChild(button);
				}
			</script>
		</form>
	</body>
</html>