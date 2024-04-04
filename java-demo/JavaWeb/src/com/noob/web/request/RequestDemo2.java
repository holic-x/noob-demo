package com.noob.web.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例2：通过HttpServletRequest对象获取提交表单的数据
 */
@WebServlet("/RequestDemo2")
public class RequestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获取提交表单的数据信息
		String username =  request.getParameter("username");
		String password =  request.getParameter("pwd");
		String sex =  request.getParameter("sex");
		String year =  request.getParameter("year");
		String month =  request.getParameter("month");
		String day =  request.getParameter("day");
		String birthday = year+"-"+month+"-"+day;
		String[] likes =  request.getParameterValues("likes");
		String eduction =  request.getParameter("eduction");
		// 2.将获取到的数据信息封装为model对象并通过相应的service保存到数据库中
		User user = new User(username, password, sex, birthday, likes, eduction);
		System.out.println(user);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
