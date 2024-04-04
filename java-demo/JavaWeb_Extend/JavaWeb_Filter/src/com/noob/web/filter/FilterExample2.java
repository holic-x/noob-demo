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
 * 过滤器的使用案例2：
 * 防盗链：用户可以通过提供的网址访问链接，但不能够直接通过地址栏访问链接
 * 测试的时候清空缓存、通过手动配置web.xml进行测试
 * 可以通过配置url从而保留多个地址栏
 * <url-pattern>/unload/*</url-pattern>
 */

public class FilterExample2 implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// a.设置消息头
		res.setDateHeader("expires", 0);
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Pragma", "no-cache");
		// b.判断客户端访问地址的来源
		/**
		 * 如果是来自于服务器的调用则正常先显示数据，如果是外部链接或者是
		 * 通过地址栏直接访问则显示错误信息
		 */
		String referer = req.getHeader("referer");
		if(referer==null||!referer.contains(req.getServerName())) {
			// 地址来源不是本服务器，则转发到错误提示页面
			req.getRequestDispatcher("/img/error.jpg").forward(request, response);
		}else {
			// 反之正常显示图片
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}


