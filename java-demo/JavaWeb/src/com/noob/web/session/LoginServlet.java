package com.noob.web.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 模拟用户登录:
 * 根据当前用户输入的数据，从模拟数据库中济宁查找
 * 查找失败则显示相应的信息，查找成功则保存数据到session随后跳转到主页
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 获取当前用户输入的账号、密码
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		// 从模拟数据库中查找数据信息
		User findUser = DBUser.findUser(username, password);
		if(findUser==null) {
			response.getWriter().write("用户名或密码错误");
			return;
		}
		// 查找成功则保存findUser到session并跳转到主页
		HttpSession session = request.getSession();
		session.setAttribute("user", findUser);
		response.sendRedirect("/JavaWeb/html/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
