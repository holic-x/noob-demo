package com.noob.web.servletcontext;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例3：实现servlet之间的转发
 */
@WebServlet("/ServletContextDemo4")
public class ServletContextDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *  利用ServletContext实现servlet之间的转发
	 *  需要注意的是转发之前定义的内容显示都是无效的，只显示转发之后的内容，
	 *  但程序代码还是照样执行
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("hello!ServletContextDemo4......");
		/**
		 *  实现转发
		 *  a.定义ServletContext对象
		 *  b.定义RequestDispatcher对象获取转发路径
		 *  c.利用RequestDispatcher对象调用forward方法实现转发
		 */
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/ServletContextDemo5");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
