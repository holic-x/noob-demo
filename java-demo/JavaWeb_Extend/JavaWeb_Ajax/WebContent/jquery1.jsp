<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1.jquery结合Ajax的使用</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.2.min.js" ></script>
	<body>
		<div id="test"></div>
		<input type="button" value="测试服务器" id="btn"/>
		<script type="text/javascript">
			/**
			 * AjaxJqueryServlet需要处理客户端请求并响应数据发送到客户端
			 */
			// 点击按钮触发事件
			$("#btn").click(function(){
				var param = {p1:"hello",p2:"ajax and jquery"};
				// 利用jquery的ajax实现简化操作
				$.ajax({
					type:"POST", // 请求方式
					url:"AjaxJqueryServlet", // 请求地址
					data:param, //向服务器传递的数据
					success:function(returnData){
						/**
						 * success成功：由客户端传递数据到服务器，并且服务器响应完成后
						 * 传递数据到客户端，全部正确执行
						 * 等价于Ajax实现案例中步骤4的两步判断
						 * if(XMLHttpReq.readyState==4){
						 * 		if(XMLHttpReq.status==200){
						 * 			......处理数据
						 * 		}
						 * }
						 * returnData是将服务器传递的数据自动封装
						 * eg:var xmlDoc = XMLHttpReq.responseXML;
						 */
						// 将数据在页面进行显示
						$("#test").html(returnData);
					}
				});
			});
		</script>
	</body>
</html>