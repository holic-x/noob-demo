package com.noob.web.request;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求转发和重定向
 * 请求转发：一个web资源接收到客户端请求后，通知浏览器去调用另外一个web资源进行处理
 * 重定向：一个web资源接收到客户端请求后，通知浏览器去访问另外一个web资源
 */
//@WebServlet("/RequestDemo4")
public class RequestDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		test1(response);
//		test2(request,response);
	}
	
	/**
	 *  重定向：两次请求、两次响应
	 *  相应地址栏的url会定位到指定的位置
	 */
	private void test1(HttpServletResponse response) throws IOException{
		response.sendRedirect("/JavaWeb/html/userRegister.html");
	}
	
	/**
	 *  请求转发:一次请求、一次响应
	 *  不会改变url地址
	 */
	private void test2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher("/html/userRegister.html");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
