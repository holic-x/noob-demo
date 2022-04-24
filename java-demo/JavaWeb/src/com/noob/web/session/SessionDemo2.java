package com.noob.web.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 通过session对象获取通过SessionDemo1存储的session数据
 */
@WebServlet("/SessionDemo2")
public class SessionDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 通过HttpServletRequest对象获取session
		HttpSession session = request.getSession();
		System.out.println("获取的数据为："+session.getAttribute("username"));
		// 分析：如果SessionDemo1中存储了数据则获取相应的内容，反之返回null
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
