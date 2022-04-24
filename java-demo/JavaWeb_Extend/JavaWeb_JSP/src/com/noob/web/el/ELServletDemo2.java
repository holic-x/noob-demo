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
 * 获取数据案例2：
 * 获取JavaBean的属性，此处以Peron领域对象为例进行分析
 */
@WebServlet("/ELServletDemo2")
public class ELServletDemo2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置消息头
		response.setContentType("text/html;charset=UTF-8");
		// 创建Person对象保存到作用域中
		Address address = new Address("中国","广东","深圳");
		Person person = new Person("1","哈哈","000000","男","我是哈哈...",address);
		request.setAttribute("person", person);
		// 转发页面到2.jsp
		request.getRequestDispatcher("/el/2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
