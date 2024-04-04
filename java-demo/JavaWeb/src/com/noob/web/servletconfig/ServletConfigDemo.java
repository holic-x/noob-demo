package com.noob.web.servletconfig;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ServletConfig对象的使用
 * 1.在web.xml中配置相关的内容
 * 		<servlet>
 * 			<servlet-name>...</servlet-name>
 * 			<servlet-class>...</servlet-class>
 * 		配置初始化信息，在程序中便可通过ServletConfig对象获取
 * 			<init-param>
 * 				<param-name>属性名</param-name>
 * 				<param-value>属性值</param-value>
 * 			</init-param>
 * 		</servlet>
 */
public class ServletConfigDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 定义ServletConfig对象,这个对象是针对单个Servlet
	 * 获取配置信息和配置内容,可以通过init方法进行初始化
	 */
	private ServletConfig servletConfig ;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.servletConfig = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// a.通过ServletConfig对象获取配置文件的信息,并通过response对象输出到客户端
		String username = servletConfig.getInitParameter("username");
		String password = servletConfig.getInitParameter("password");
		response.getWriter().write("username:"+username+"--password:"+password);
		
		// b.获得所有的请求参数的key值即name属性（返回类型为枚举类型）,依次遍历
		Enumeration<String> params = servletConfig.getInitParameterNames();
		while(params.hasMoreElements()) {
			// 获取相应的属性名称和属性值
			String name = params.nextElement();
			String value = servletConfig.getInitParameter(name);
			response.getWriter().write("username:"+username+"--password:"+password);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
