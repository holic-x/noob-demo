package com.noob.web.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noob.web.cookies.Book;
import com.noob.web.cookies.DBBook;

/**
 * 添加至购物车的实现
 * a.从ShoppingIndexServlet中获取点击的书籍id
 * b.从“数据库”中获取指定id的书籍信息
 * c.通过HttpServletRequest对象获取session,
 *   将相应书籍添加至相应的列表并更新session
 *   需要判断当前购物车是否有保存的数据，如果购物车为空则需要
 *   设置session，再将相应书籍添加即可
 * d.转发页面到ListCartServlet显示购物车信息
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取当前传入的书籍id
		String id=request.getParameter("id");
		Book book =(Book) DBBook.getAllBook().get(id);
		// 通过HttpServletRequest对象获取session
		HttpSession session =request.getSession(); 
		//获取Session中是否有保存的数据  
		List list =(List) session.getAttribute("list");
		if(list==null) {
			//购物车为空，设置session属性
			list=new ArrayList<>();
			session.setAttribute("list", list);
		}
		//如果不为空则把新购买的书籍添加到集合中
		list.add(book);
		// 转发页面到ListCartServlet，显示购物车详情
		request.getRequestDispatcher("/ListCartServlet").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
