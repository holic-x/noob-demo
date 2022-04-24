package com.noob.web.cookies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDemo2")
public class CookieDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 删除指定的cookie进行测试
		 * 通过request获取客户端所有的cookie，随后根据指定的cookie名称删除
		 * 相应的cookie即可，此处删除有两种形式实现
		 * a.将相应的cookie设置为空
		 * b.创建同名的cookie对原有cookie进行覆盖
		 */
		// 同名覆盖，与上述效果一致
		Cookie cookie = new Cookie("lastAccessTime",System.currentTimeMillis()+"");
		cookie.setMaxAge(0);
		cookie.setPath("/JavaWeb");
		response.addCookie(cookie);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
