package com.noob.web.base;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方式2：使用注解的方式配置servlet
 * 直接创建servlet（按照提示完成自动加载需要的内容）
 * 在实际的开放中通常使用到的是doGet()、doPost()方法
 * 也可用service()方法
 * @WebServlet("映射路径")
 */
@WebServlet("/ServletDemo2")
public class ServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 *	实际开发中 需要重写的是doGet方法和doPost方法 ,不需要重写service方法  
	 *	service方法 会根据客户端请求的方式是get还是post,分别调用doGet方法和doPost方法
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("hello servletDemo2......");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
