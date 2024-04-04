package com.noob.web.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 案例1:利用session作为域对象，用于存取数据
 * 1个浏览器独占1个session对象
 */
@WebServlet("/SessionDemo1")
public class SessionDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 通过HttpServletRequest对象获取session
		HttpSession session = request.getSession();
		// 将session作为域对象存储数据
		session.setAttribute("username", "noob");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
