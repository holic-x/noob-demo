package com.noob.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**     
 * 在web.xml中进行相应配置
 * 过滤器的使用案例1：
 * 通过过滤器解决全站乱码问题  
 * 1.所有的客户端请求：request请求 全部经过当前过滤器进行处理设置编码集
 * 2.输出到客户端：response 全部经过当前过滤器进行处理设置编码集
 */

public class FilterExample1 implements Filter{

	private FilterConfig filterConfig;
	// 指定要进行设置的编码集
	private String defaultCharset="UTF-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	/**
	 * 一般在获取客户端数据或输出数据到客户端的时候为了解决乱码问题难免要设置
	 * 指定的编码集，通过过滤器进行统一的设定，只需要进行相应的配置即可
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// a.将指定的内容转化为相应的HttpServletRequest、HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// b.获取当前系统默认的编码集
		String charset = filterConfig.getInitParameter("charset");
		// c.设置统一的编码集
		if(charset==null) {
			charset=defaultCharset;
		}
		// request设置：由客户端请求服务端的编码集
		request.setCharacterEncoding(charset);
		// response设置：由服务端输出到客户端的编码集，设置成统一
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+defaultCharset);
		// d.放行到目标资源（此处放行的是经过处理之后的request、response）
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		
	}

}


