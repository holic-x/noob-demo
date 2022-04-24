<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax案例1：用户名验证</title>
</head>
	
<script type="text/javascript">
 	 
	var XMLHttpReq;

	function createXMLHttpRequest() {
		//针对不同的浏览器创建XMLHttpRequest对象
		if (window.XMLHttpRequest) { //火狐等浏览器
			XMLHttpReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) {//IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
	}

	function back() {
		if (XMLHttpReq.readyState == 4) {
			if (XMLHttpReq.status == 200) {
				//得到服务器相应的数据  是xml数据格式的类型
				var xmlDoc = XMLHttpReq.responseXML;
				var info = document.getElementById("info");
				var flag = xmlDoc.getElementsByTagName("res")[0].firstChild.nodeValue;
				if (flag == 1) {
					var error = "该邮箱已经被注册,请重新输入..";
					info.innerHTML = error;
				} else {
					info.innerHTML = "恭喜你，此邮箱可以使用!!";
				}
			}
		}
	}

	function checkUserName() {
		var name = document.getElementById("username").value;
		// a.创建XMLHTTPRequest对象
		createXMLHttpRequest();
		// b.打开链接
		var url = "CheckUserNameServlet?username=" + name;
		XMLHttpReq.open("GET", url, true);
		// c.发送数据
		XMLHttpReq.send(null);
		// d.接收服务器的响应数据
		XMLHttpReq.onreadystatechange = back;
	}
</script>
<body>
	 <form action="reg">
	          用户名: <input type="text" name="username" id="username" onblur="checkUserName()" />
	          <span id="info"></span><br/>
	 	 密码:<input type="text" name="pwd"><br/>
	 	<input type="submit" value="注册">
	 </form>
</body>
</html>