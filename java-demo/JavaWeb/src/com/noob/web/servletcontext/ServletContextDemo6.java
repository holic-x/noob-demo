package com.noob.web.servletcontext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例4：读取文件文件资源
 * 在src下放置文件资源jdbc.properties,正常情况下直接通过“/jdbc.properties”
 * 便可直接定位访问到该文件资源，但是在此处需要考虑文件工程源码与项目发布的路径并不一致
 * 的问题，从而导致在原有路径并无法获取指定的文件资源
 * 通过查看对比该文件资源在工程源码的路径与项目发布的路径中的准确位置
 * a.工程源码路径：
 *   D:\dev\workspace\eclipse\Projects\JavaWeb\src\jdbc.properties
 * b.项目发布路径：
 *   D:\dev\soft\tomcat\apache-tomcat-8.5.23
 *   \webapps\JavaWeb\WEB-INF\classes\jdbc.properties
 */
@WebServlet("/ServletContextDemo6")
public class ServletContextDemo6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void test1() throws IOException {
		// 普通获取文件资源的方式（无效、报错）
		FileInputStream fis = new FileInputStream("/jdbc.properties");
		System.out.println(fis);
	}
	
	private void test2() {
		// 利用ServletContext获取文件资源
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/jdbc.properties");
		System.out.println(in);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		test1();
		test2();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
