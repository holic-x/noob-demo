package com.noob.web.servletcontext;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletContextDemo2")
public class ServletContextDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取ServletContext对象
		ServletContext sc = this.getServletContext();
		// 获取当前域对象的某个属性，通过response对象向客户端输出数据
		// 此处获取的是域对象的某个属性，用的是getAttribute()方法
		String username = (String) sc.getAttribute("username");
		String password = (String) sc.getAttribute("password");
		response.getWriter().write(username);
		response.getWriter().write(password);
		
		/**
		 *  分析：在此之前必须先加载ServletContextDemo1中的数据设置域对象，
		 *  否则得到的结果为null或者是报错（属性不存在则页面显示报错）
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
