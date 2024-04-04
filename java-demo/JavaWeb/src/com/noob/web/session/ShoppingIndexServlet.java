package com.noob.web.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noob.web.cookies.Book;
import com.noob.web.cookies.DBBook;

/**
 * 模拟商品购物车的实现
 */
@WebServlet("/ShoppingIndexServlet")
public class ShoppingIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 首页设置编码集统一：
		 * 展示所有的书籍信息并实现点击添加指定商品到相应的购物车
		 */
		// 设置编码集统一
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		// a.显示所有的书籍信息
		pw.write("所有书籍信息显示如下:<br/>");
		Set<Map.Entry<String,Book>> set = DBBook.getAllBook().entrySet();
		for(Map.Entry<String, Book> entry : set) {
			Book book =entry.getValue();
			/**
			 * 为每本书籍创建超链接,传入id作为参数
			 * <a href='/JavaWeb/InfoServlet?id=..' target='_blank'>...</a>
			 */
			pw.write("<a href='/JavaWeb/BuyServlet?id="+book.getId()
			+"' target='blank'>"+book.getName()+"</a><br/>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
