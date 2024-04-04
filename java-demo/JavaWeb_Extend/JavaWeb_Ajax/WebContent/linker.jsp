<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax案例2：二级联动</title>
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
				var xSel = xmlDoc.getElementsByTagName('select');
				var select_root = document.getElementById('city');
				select_root.options.length = 0;
				// 循环由服务器传递的数据
				for (var i = 0; i < xSel.length; i++) {
					var xvalue = xSel[i].childNodes[0].firstChild.nodeValue;
					var xText = xSel[i].childNodes[1].firstChild.nodeValue;
					var option = new Option(xText, xvalue);
					select_root.add(option);
				}
			}
		}
	}

	function change_city() {
		var province = document.getElementById("province").value;
		var url = "LinkerServlet?id=" + province;
		createXMLHttpRequest();
		XMLHttpReq.open("GET", url, true);//2.打开连接
		XMLHttpReq.send(null);//3.发送数据
		XMLHttpReq.onreadystatechange = back;
	}
</script>

<body>
	<form action="" method="post" name="form1">
		<table border="1">
			<tr>
				<td colspan="2">二级联动测试</td>
			</tr>
			<tr>
				<td><select name="province" id="province"
					onchange="change_city()">
						<option value="0">请选择省份</option>
						<option value="1">浙江省</option>
						<option value="2">山东省</option>
						<option value="3">江苏省</option>
						<option value="4">福建省</option>
						<option value="5">甘肃省</option>
						<option value="6">广东省</option>
				</select></td>
				<td><select name="city" id="city">
						<option value="0">请选择区域</option>
				</select></td>
			</tr>
		</table>
	</form>
</body>
</html>