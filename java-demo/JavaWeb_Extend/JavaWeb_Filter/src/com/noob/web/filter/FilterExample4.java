package com.noob.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器的使用案例4：HTML转义
 */
class MyHtmlRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	public MyHtmlRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		// 获取用户的输入;
		String value = this.request.getParameter(name);
		if (value == null) {
			return null;
		}
		// 需要把用户输入的数据进行转义<>& " 把这四种转义即可
		char[] content = new char[value.length()];
		value.getChars(0, value.length(), content, 0); // 得到用户输入的每一个字符
		StringBuffer result = new StringBuffer(content.length + 150);
		// 循环用户输入的每一个字符如果是特殊符号则进行替换
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
				break;
			}
		}
		return result.toString();
	}

}

public class FilterExample4 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 通过MyHtmlRequest类进行HTML转义
		MyHtmlRequest myrequst =new MyHtmlRequest(req);
		// 放行资源（此处传入的是经过处理之后的内容）
		chain.doFilter(myrequst, res);
	}

	@Override
	public void destroy() {
	}

}
