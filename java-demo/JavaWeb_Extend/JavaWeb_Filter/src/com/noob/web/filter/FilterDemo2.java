package com.noob.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**       
 * 定义配置过滤器filter2...   
 */
public class FilterDemo2 implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter2....init......");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("在执行目标资源文件之前执行.....3");
		/**
		 * 如果想要执行到目标资源则需要使用FilterChain对象
		 * 放行到目标资源，否则无法执行下一步
		 */
		chain.doFilter(request, response);
		System.out.println("在执行目标资源文件之后执行......4");
	}

	@Override
	public void destroy() {
		System.out.println("filter2....destory......");
	}

}


