package com.noob.web.servletcontext;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例1：多个servlet共享数据
 * 在一个servlet中实现ServletContext对象存储数据
 * 在另一个servlet中获取该数据
 */
@WebServlet("/ServletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * ServletContext可以作为域对象，进行存取数据
		 * 获取ServletContext对象有两种方式
		 * a.通过ServletConfig对象获取:this.getServletConfig().getServletContext()
		 * b.直接获取:this.getServletContext()
		 */
		ServletContext sc1 = this.getServletConfig().getServletContext();
		ServletContext sc2 = this.getServletContext();
		// 利用域对象存取数据
		sc1.setAttribute("username", "noob");
		sc2.setAttribute("password", "000000");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
