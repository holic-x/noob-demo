package com.noob.web.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 案例1：通过HttpServletRequest对象常用的方法
 */
@WebServlet("/RequestDemo1")
public class RequestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * HttpServletRequest对象的作用主要是响应客户端请求，从客户端中获取数据
		 * 常用方法
		 * a.获取和路径相关的数据
		 * 	getRequestURL():获取完整路径(http://localhost:8080/工程名/请求资源)
		 * 	getRequestURI():获取短路径(工程名/请求资源)
		 * b.获取服务器的地址、查询参数等数据
		 * 	getRemoteAddr():获取服务器地址
		 * 		(本机访问：127.0.0.1对应0:0:0:...1   外部访问：192.168.8.8)
		 * 	getQueryString():获取通过地址栏传递的参数(没有传递返回null)
		 * 		(传递参数：?参数名=参数值     ?username=noob)
		 * 	getRemotePort():获取服务器对外端口号(不是8080)
		 * 	getMethod():获取客户端请求的方式(get、post)
		 * 	getServerPort()：获取访问端口号(8080)
		 * 	getServletPath():获取请求的资源信息(/RequestDemo1)
		 */
		// a.获取和路径相关的数据
		System.out.println("完整路径："+request.getRequestURL());
		System.out.println("短路径："+request.getRequestURI());
		// b.获取相关参数
		System.out.println("服务器地址："+request.getRemoteAddr());
		System.out.println("地址栏传递参数："+request.getQueryString());
		System.out.println("服务器对外端口号："+request.getRemotePort());
		System.out.println("客户端请求方式："+request.getMethod());
		System.out.println("访问端口号："+request.getServerPort());
		System.out.println("请求的资源信息："+request.getServletPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
