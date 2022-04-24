package com.noob.web.servletcontext;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例2：获取全局的web初始化参数
 * 在web.xml中定义全局的初始化参数
 *  -- 定义全局的web初始化参数
 *  <context-param>
 *  	<param-name>属性名称</param-name>
 *  	<param-value>属性值</param-value>
 *  </context-param>
 */
@WebServlet("/ServletContextDemo3")
public class ServletContextDemo3 extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = this.getServletConfig().getServletContext();
		// 此处获取的是全局的web初始化参数，用的是getInitParameter()方法
		String content = (String) sc.getInitParameter("content");
		System.out.println("content:"+content);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
