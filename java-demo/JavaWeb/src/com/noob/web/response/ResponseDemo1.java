package com.noob.web.response;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例1：通过response对象输出中文到客户端
 */

@WebServlet("/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 方式1：使用程序控制浏览器的编码集
	 * response.setHeader("Content-type", "text/html;charset=UTF-8");
	 */
	private void test1(HttpServletResponse response) throws IOException{
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write("setHeader方法：使用了程序控制浏览器的编码集为UTF-8"+"<br/>");
	}
	
	/**
	 * 方式2：使用程序控制浏览器的编码集
	 * response.setContentType("text/html;charset=UTF-8");
	 */
	private void test2(HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("setContentType方法：使用了程序控制浏览器的编码集为UTF-8"+"<br/>");
	}
	
	/**
	 * 方式3：仅仅设置输出的编码集
	 * （但需注意设置的输出编码集必须与浏览器的编码集一致，否则无效）
	 * 如果使用方式3需要每个浏览器均进行匹配，常常与setHeader联用
	 */
	private void test3(HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("GB2312");
//		response.setCharacterEncoding("UTF-8");如果浏览器编码集不为UTF-8则设置无效
		response.getWriter().write("setCharacterEncoding方法：仅仅设置输出的编码集"+"<br/>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 测试
		test1(response);
		test2(response);
		test3(response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
