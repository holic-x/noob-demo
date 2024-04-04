package com.noob.web.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例3：通过HttpServletRequest对象解决中文乱码问题
 */
@WebServlet("/RequestDemo3")
public class RequestDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决form表单post方式导致的中文乱码问题
		request.setCharacterEncoding("UTF-8");
		// 接受来自code.html的内容
		String content = request.getParameter("content");
		System.out.println(content);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	// 解决form表单get方式导致的中文乱码问题
	private void messycode(HttpServletRequest request) throws IOException {
		String content=request.getParameter("content");
		content=new String(content.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(content);
	}
}
