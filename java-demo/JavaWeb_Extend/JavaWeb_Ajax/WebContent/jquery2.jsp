<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1.jquery结合Ajax的使用</title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.min.js"></script>
<body>
	<div id="test"></div>
	<input type="button" value="测试服务器" id="btn" />
	<script type="text/javascript">
		/**
		 * AjaxJqueryServlet需要处理客户端请求并响应数据发送到客户端
		 */
		// 点击按钮触发事件
		$("#btn").click(function() {
			// 定义参数
			var param = {
				p1 : "hello",
				p2 : "ajax and jquery i am post"
			};
			// 利用jquery的ajax实现简化操作：可以通过调用明确的post或get方法
			/**
			 * $.post("url",param,function(returnData){
			 * 		响应服务器返回的数据...
			 * })
			 */
			$.post("AjaxJqueryServlet", param, function(returnData) {
				$("#test").html(returnData);
			});
		});
	</script>
</body>
</html>