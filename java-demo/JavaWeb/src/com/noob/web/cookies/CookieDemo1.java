package com.noob.web.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例分析1：利用cookie完成显示上次用户访问的时间
 */
@WebServlet("/CookieDemo1")
public class CookieDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 2.得到Cookie的相关数据并显示到客户端
		/**
		 * 设置编码集UTF-8
		 */
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		/**
		 * 获取客户端中保存的cookie
		 * 此处通过HttpServletRequest对象获取的是所有的Cookie数据
		 * 循环遍历所有的cookie，根据key值查找指定的cookie
		 */
		Cookie[] cookies = request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++) {
			Cookie ck = cookies[i];
			if(ck.getName().equals("lastAccessTime")) {
				// 查找成功显示数据信息
				Long time = Long.parseLong(ck.getValue());
				Date date = new Date(time);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pw.write("上次访问当前页面的时间："+sdf.format(date));
			}
		}
		
		// 1.给用户以Cookie的形式发送最新的时间保存到Cookie中
		/**
		 * 创建Cookie，其包含两个参数：
		 * key用于保存Cookie的名称
		 * value用于保存Cookie对应的值
		 */
		Cookie cookie = new Cookie("lastAccessTime",System.currentTimeMillis()+"");
		/**
		 * 设置Cookie的相关属性（有效期、保存路径等）
		 * setMaxAge():设置Cookie的有效期
		 * setPath():设置Cookie保存的路径信息
		 */
		cookie.setMaxAge(10000);
		cookie.setPath("/JavaWeb");
		/**
		 * 将Cookie添加到客户端
		 */
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
