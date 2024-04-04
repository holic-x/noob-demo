package com.noob.web.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noob.web.cookies.Book;
import com.noob.web.cookies.DBBook;

/**
 * ListCartServlet显示购物车详情
 */
@WebServlet("/ListCartServlet")
public class ListCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码集
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		// 通过HttpServletRequest获取session
		HttpSession session = request.getSession();
		List<Book> list = (List<Book>) session.getAttribute("list"); 
		// 如果list为空则显示相应的提示信息
		if(list==null||list.size()==0) {
			pw.write("抱歉，您当前没有任何购物记录！<br/>");
		}else {
			pw.write("当前购买书籍如下：<br/>");
			// 按照当前的list进行分组，将重复记录进行合并
			Map<String,Integer> map = getMergeList(list);
			// 遍历显示数据信息即可
			Set<Map.Entry<String,Integer>> set = map.entrySet();
			for(Map.Entry<String,Integer> entry : set) {
				// 根据映射关系获取书籍的id、书籍的相应数据和对应的数目
				Book book = (Book) DBBook.getAllBook().get(entry.getKey());
				int count = entry.getValue();
				pw.write("书籍id："+book.getId()+"<br/>");
				pw.write("书籍名称："+book.getName()+"<br/>");
				pw.write("书籍作者："+book.getAuthor()+"<br/>");
				pw.write("书籍描述："+book.getDescr()+"<br/>");
				pw.write("当前数目："+count+"<br/><br/>");
			}
		}
	}
	
	// 定义方法合并重复记录
	public Map<String,Integer> getMergeList(List<Book> list){
		// 定义map集合存放书籍id和相应的加入购物车的次数
		Map<String,Integer> map = new HashMap<>();
		for(int i=0;i<list.size();i++) {
			/**
			 *  判断当前是否存在该书籍的计数记录,如果存在则直接改动数据
			 *  如果不存在则添加计数记录
			 */
			if(!map.containsKey(list.get(i).getId())) {
				map.put(list.get(i).getId(), 1);
			}else {
				// 获取当前的书籍id和对应的计数
				String id = list.get(i).getId();
				int count = map.get(id)+1;
				map.replace(id,count);
			}
		}
		
		
		
		return map;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
