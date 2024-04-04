package com.noob.web.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * InfoServlet用以显示浏览的书籍的具体信息
 * 与此同时更新cookie记录
 */
@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置统一的编码集
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		// 根据用户传入的参数id得到书籍的相信信息
		String id = request.getParameter("id");
		Book book = (Book) DBBook.getAllBook().get(id);
		pw.write("您当前浏览的书籍详细信息列举如下：<br/>");
		pw.write("书籍id:"+book.getId()+"<br/>");
		pw.write("书籍名称:"+book.getName()+"<br/>");
		pw.write("书籍作者:"+book.getAuthor()+"<br/>");
		pw.write("书籍描述:"+book.getDescr()+"<br/>");
		// 用户当前浏览了该书籍，则需要更新cookie记录，自定义规则更新数据
		String bookHistory = getBookHistory(request,id);
		Cookie ck = new Cookie("bookHistory", bookHistory);
		ck.setMaxAge(1*30*24*3600);
		ck.setPath("/JavaWeb");
		response.addCookie(ck);
	}

	public String  getBookHistory(HttpServletRequest request,String id) {
	     /** 
	      * 此处假设之多保存3个浏览记录，多余的内容进行覆盖
	      * bookHistory=null        1       bookHistory=1 
	      * bookHistory=1           3       bookHistory=3_1
	      * bookHistory=3_1         1       bookHistory=1_3 
	      * bookHistory=1_3         4       bookHistory=4_1_3
	      * bookHistory=4_1_3       3       bookHistory=3_4_1
	      * bookHistory=3_4_1       5       bookHistory=5_3_4
	      */
	    String bookHistory=null;
	    
	    //得到客户端的访问记录  
	    Cookie [] cookies =request.getCookies();
	    for(int i=0;cookies!=null && i<cookies.length;i++) {
	        if(cookies[i].getName().equals("bookHistory")) {
	            bookHistory=cookies[i].getValue();//从客户端查询到具体的数据
	        }
	    }
	    /**
	     * 判断当前的cookie是否为null,如果为null则说明是第一次访问某个
	     * 书籍链接，因此直接返回对应的id即可
	     *  bookHistory=null       1         bookHistory=1 
	     */
	    if(bookHistory==null) {
	        return id;
	    }
	    /**
	     * 如果当前的cookie不为null,则说明此前已有相应的记录，则需要根据不同
	     * 的情况对数据进行拆分分析、再更新
	     * 分类讨论方式：
	     * a.判断当前的cookie中是否包含当前访问的书籍信息
	     * 如果包含则将其删除再将其添加至头部（表示最新访问）
	     * 如果不包含则将判断当前是否超出“3个”的限制：
	     * 		超出则去除最后一个并将当前查阅信息添加至头部
	     * 		不超出则可直接添加至头部
	     */
	    //拆分cookie数据，保存到列表中
	    List l=Arrays.asList(bookHistory.split("\\_"));
	    LinkedList<String> list =new LinkedList<>();
	    list.addAll(l); //把List集合的数据全部添加到LinkedList中
	   
	    //根据不同的情况进行不同的处理
	    if(list.contains(id)) {
	       // 如果当前书籍借阅记录已存在
	       list.remove(id);//移除指定的数据
	       list.addFirst(id);//把指定的数据添加到头部
	    }else {
	        // 如果当前借阅记录数已满足大于等于3
	        if(list.size()>=3) {
	            list.removeLast();//移除最后一个
	            list.addFirst(id);//把新访问的数据添加到头部;
	        }else {
	        	// 如果当前借阅记录数小于3，则直接在头部添加新访问的数据
	        	list.addFirst(id);
	        }
	    }
	    
	    //完成后把数据从List集合拼接为相应的String字符串再保存到cookie中
	    StringBuffer sb =new StringBuffer();
	    for (String str : list) {
            sb.append(str+"_");
        }
	    // 此时获取的字符串形式为：4_2_3_，最终返回的字符串应当去除最后一个下划线
	    return sb.deleteCharAt(sb.length()-1).toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
