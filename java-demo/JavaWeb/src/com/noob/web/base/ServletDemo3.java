package com.noob.web.base;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletDemo3")
public class ServletDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("servlet的init方法在初始化的时候只执行1次......");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("hello servletDemo3......");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("servlet的destory方法在销毁的时候执行......");
	}

}
