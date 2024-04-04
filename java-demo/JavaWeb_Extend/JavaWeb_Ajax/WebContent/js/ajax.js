/**
 *  Ajax的工作原理：Ajax的核心即JavaScript与XMLHttpRequst
 *  该对象在浏览器中是一种支持异步请求的计数
 *  Ajax是基于JavaScript的操作，数据通过js传到服务器，需要考虑如下问题：
 *  1.如何把用户请求的数据发送到服务器
 *  2.服务器接收到如何处理
 *  3.服务器处理完成后返回的数据格式是什么？
 *  	xml、json数据 -- 客户端如何解析这种数据格式
 *  4.如何把解析的数据通过JavaScript显示到客户端
 */
/**
 * 利用Ajax完成与服务器进行交互的步骤 开发步骤： 
 * a.创建XMLHttpRequest对象 
 * b.打开与服务器的链接 
 * c.发送数据
 * d.接收服务器的响应数据
 */

function createXMLHttpRequest(){
	 var XMLHttpReq;
	 
	 //针对不同的浏览器创建XMLHttpRequest对象
	 if(window.XMLHttpRequest){ //火狐等浏览器
		 XMLHttpReq =new XMLHttpRequest();
	 }else if(window.ActiveXObject){//IE浏览器
		 try{
		  XMLHttpReq =new  ActiveXObject("Msxml2.XMLHTTP");
		 }catch(e){
			 XMLHttpReq =new ActiveXObject("Microsoft.XMLHTTP");
		 }
	 }
	
	 return  XMLHttpReq;
}

// 在页面加载的时候这个函数自动执行
window.onload=function(){
	document.getElementById("test").onclick=function(){
		// 测试：alert("hello ajax...");
		// a.创建XMLHttpRequest对象
		var xmlReq=createXMLHttpRequest();
		
		// b.打开与服务器的链接
		/**
		 * 参数1：请求方式
		 * 参数2：请求路径
		 * 参数3：是否是同步的
		 * xmlReq.open("GET","AjaxServlet",true);
		 */
		xmlReq.open("GET","AjaxServlet",true);
		
		// c.发送数据
		/**
		 * 如果请求方式是get,则不需要向服务器传递数据：send(null)
		 * 如果请求方式是post,则传递指定的数据send(data)
		 */
		xmlReq.send(null);
		
		// d.接收服务器的响应数据
		/**
		 * onreadystatechange是服务器触发,会通知客户端当前服务的状态
		 * 0 -- 未初始化：还没有调用open方法
		 * 1 -- 正在加载：open方法已被调用，但send方法还没被调用
		 * 2 -- 已加载完毕：send方法已被调用，请求已开始处理
		 * 3 -- 交互中：服务器正在发送响应
		 * 4 -- 完成：响应发送完毕
		 * status 判断数据是否由服务器成功返回到客户端    状态码分为如下情况: 
		 * 404 没找到页面(not found)
		 * 403 禁止访问(forbidden)
		 * 500 内部服务器出错(internal service error)
		 * 200 一切正常(ok)
		 * 304 没有被修改(not modified)(服务器返回304状态，表示源文件没有被修改 )
		 * 在测试的时候如果始终无法出现预期的效果有可能是响应的jsp文件放置位置有误
		 * 将其方在WebContent目录下直接进行测试，无须多加一级目录
		 */
		xmlReq.onreadystatechange=function(){
			if(xmlReq.readyState==4){//4 代表 响应发送完毕  交互完成 
				if(xmlReq.status==200){//2000 代表数据由服务端 成功传递到客户端
					//5.处理数据 客户端返回的数据   是文本类型  可以使用responseText 获取数据
					var textDoc =xmlReq.responseText;
					alert("服务器返回的数据是:"+textDoc);
				}
			}
		}
	}
}


































