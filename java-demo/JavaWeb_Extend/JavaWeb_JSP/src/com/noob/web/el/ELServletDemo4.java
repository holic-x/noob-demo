package com.noob.web.el;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noob.web.model.Address;
import com.noob.web.model.Person;

/**
 * 使用el表达式执行运算：有常见的运算符的使用
 * 此处只简单列举常见的问题案例
 * 判断对象是否为空
 * 	empty运算符：检查对象是否为null或“空”
 * 	二元表达式：${user!=null?user.name : "提示"} 
 * 常见的还有 [ ] 和  . 号运算符
 */
@WebServlet("/ELServletDemo4")
public class ELServletDemo4 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置消息头
		response.setContentType("text/html;charset=UTF-8");
		// 设置领域对象
		Address address = new Address("中国","广东","深圳");
		Person person = new Person("1","哈哈","000","男","我是哈哈",address);
		request.setAttribute("person", person);
		// 将页面跳转到4.jsp进行测试
		request.getRequestDispatcher("el/4.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
