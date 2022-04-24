package com.noob.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
 * 过滤器的使用案例3： 脏话过滤器
 * 配置web.xml中的相关属性
 * 通过DirtyRequest类进行脏话替换
 * 但此处还不够完善，针对标签输入内容可能会导致异常的情况需要进行处理
 */
// DirtyRequest类的主要作用是重写requst中的方法进行增强
class DirtyRequest extends HttpServletRequestWrapper {
	// 模拟自定义脏话字典
	private List<String> dirtyWords = Arrays.asList("坑逼", "傻子", "畜生", "傻瓜");
	private HttpServletRequest request;

	public DirtyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	// 对request对象的getParmater方法进行增强
	@Override
	public String getParameter(String name) {

		// 获取表单的数据的内容的时候进行替换数据
		String value = this.request.getParameter(name);
		if (value == null) {
			return null;
		}
		/**
		 *  如果不是为空则循环迭代所有从数据库读取到的脏话字典
		 *  然后匹配用户的输入是否存在脏话如果存在则进行替换
		 */
		for (String dirtyword : dirtyWords) {
			if (value.contains(dirtyword)) {
				value = value.replace(dirtyword, "*****");
			}
		}
		return value;
	}
}

public class FilterExample3 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 通过DirtyRequest类进行脏话过滤
		DirtyRequest qr = new DirtyRequest(req);
		// 放行资源（此处传入的是经过处理之后的内容）
		chain.doFilter(qr, res);
	}

	@Override
	public void destroy() {
	}

}
