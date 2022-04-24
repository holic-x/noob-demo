package com.noob.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**   
 * 过滤器的基本使用    
 * a.实现Filter接口(javax.servlet)
 * b.配置web.xml中相关过滤器属性(与servlet的配置方法大同小异)
 * <filter>
 * 		<filter-name>过滤器名称</filter-name>
 * 		<filter-class>类全名</filter-class>
 * </filter>
 * <filter-mapping>
 * 		<filter-name>过滤器名称</filter-name>
 * 		<url-pattern>/*</url-pattern>
 * </filter-mapping>
 * c.可以配置多个Filter,在访问的时候其顺序取决于Filter在web.xml中
 * 	配置的先后顺序，且其进入的顺序与返回的顺序恰恰相反，可以参考filter
 * 	的执行过程进行理解分析
 * 	此处案例结果显示：...1 3 4 2...
 */

public class FilterDemo1 implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter1....init......");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("在执行目标资源文件之前执行.....1");
		/**
		 * 如果想要执行到目标资源则需要使用FilterChain对象
		 * 放行到目标资源，否则无法执行下一步
		 */
		chain.doFilter(request, response);
		System.out.println("在执行目标资源文件之后执行......2");
	}

	@Override
	public void destroy() {
		System.out.println("filter1....destory......");
	}

}


