package com.noob.web.cookies;

import java.util.LinkedHashMap;
import java.util.Map;

/**       
 * 模拟数据库    
 */
public class DBBook {
	// 模拟数据库创建书籍信息
	private static Map<String,Book> map = new LinkedHashMap<>();
	// 添加书籍信息
	static {
		map.put("1",new Book("1","JavaWeb入门基础","张三","书籍描述...."));
		map.put("2",new Book("2","Oracle数据库入门基础","李四","书籍描述...."));
		map.put("3",new Book("3","Mysql数据库入门基础","王五","书籍描述...."));
		map.put("4",new Book("4","数据库实践","赵六","书籍描述...."));
		map.put("5",new Book("5","JavaEE企业级开发","小七","书籍描述...."));
		map.put("6",new Book("6","算法设计与分析","刘八","书籍描述...."));
		map.put("7",new Book("7","数据结构与算法分析","李一","书籍描述...."));
		map.put("8",new Book("8","C++程序设计","haha","书籍描述...."));
	}
	// 定义公有方法获取所有的书籍信息
	public static Map getAllBook() {
		return map;
	}
}


