package com.noob.web.base;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpsServer;

/**
 * 方式1：使用配置文件的方式配置servlet
 * 	a.继承HttpServlet并重写service方法
 * 	b.在web.xml中配置servlet相关属性
 * 		<servlet>...</servlet>
 * 		<servlet-mapping>...</servlet-mapping>
 */  
/**  
 *  web.xml中相关配置内容：
 *  a.配置servlet
 *    <servlet>
 *  	  <servlet-name>类名</servlet-name>
 *  	  <servlet-class>类全名</servlet-class>
 *    </servlet>
 *  b.配置servlet的映射
 *    <servlet-mapping>
 *    	  <servlet-name>类名(需与servlet中的名称一致)</servlet-name>
 *  	  <url-pattern>映射路径(访问路径)</url-pattern>
 *    </servlet-mapping>
 *  c.同一个servlet可以对应多个不同的访问路径
 */
public class ServletDemo1 extends HttpServlet{
	
	/**
	 * 说明：
	 * a.service方法是用于处理客户端请求和相应客户端的方法
	 * b.HttpServletRequest request对象是客户端请求服务器，把客户端传递的数据
	 *   都封装在request对象中
	 * c.HttpServletResponse resp对象是服务器响应客户端，把服务器要传递到客户端
	 *   的数据都封装在resp对象中
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 *  利用resp对象向客户端输出数据
		 *  resp.getWrite()获取的是PrintWriter对象
		 */
		resp.getWriter().write("hello servletDemo1......");
	}

}


