package com.noob.web.el;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	获取数据案例1：
 *	使用el表达式可以依次从四大领域中从小到大依次搜索、获取数据
 *	page -- request -- session -- application
 * */
@WebServlet("/ELServletDemo1")
public class ELServletDemo1 extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置消息头
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("username", "noob");
		request.getSession().setAttribute("password", "000000");
		request.getServletContext().setAttribute("descr", "哈哈");
		// 转发页面到1.jsp进行测试
		request.getRequestDispatcher("/el/1.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
