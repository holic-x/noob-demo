package com.noob.web.cookies;

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

/**
 * 案例2：制作书籍访问记录cookie
 * 1.模拟数据库创建相关书籍进行测试
 * 2.提供首页用于显示所有的书籍信息和相应的访问记录
 * 3.提供详情页用于显示指定书籍的详细信息并相应更新数据
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 首页设置编码集统一：
		 * a.展示所有的书籍信息
		 * b.显示所有曾经浏览过的书籍信息
		 * 由于是自定义的cookie存储规则，在获取数据的时候相应地
		 * 需要进行转化
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
			pw.write("<a href='/JavaWeb/InfoServlet?id="+book.getId()
			+"' target='_blank'>"+book.getName()+"</a><br/>");
		}
		
		// b.显示曾经浏览过的所有书籍信息
		pw.write("浏览记录显示如下：<br/>");
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length&&cookies!=null;i++) {
			Cookie ck = cookies[i];
			if(ck.getName().equals("bookHistory")) {
				// 将cookie数据进行拆分(以下划线进行拆分，需要转义)
				String[] ids = ck.getValue().split("\\_");
				// 从书籍中查找相应的记录进行显示即可
				for(String id : ids) {
					Book book = (Book) DBBook.getAllBook().get(id);
					pw.write("id:"+book.getId()+"--名称："+book.getName()+"<br/>");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
