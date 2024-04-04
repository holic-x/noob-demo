package com.noob.web.el;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noob.web.model.Address;
import com.noob.web.model.Person;

/**
 * 获取数据案例3：根据el表达式获取数组、List集合、Map集合数据
 */
@WebServlet("/ELServletDemo3")
public class ELServletDemo3 extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address a1 = new Address("中国","广东","深圳");
		Person p1 = new Person("1","哈哈","000000","男","我是哈哈...",a1);
		
		Address a2 = new Address("中国","广东","杭州");
		Person p2 = new Person("2","哔哔","000000","男","我是哔哔...",a2);
		
		Address a3 = new Address("中国","广东","惠州");
		Person p3 = new Person("3","嘻嘻","000000","女","我是嘻嘻...",a3);
		
		// 将上述的用户分别添加到数组、list、map集合中进行测试
		Person[] array = {p1,p2,p3};
		List<Person> list = new LinkedList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		Map<String,Person> map = new LinkedHashMap<>();
		map.put("person1", p1);
		map.put("person2", p2);
		map.put("person3", p3);
		
		// 设置领域对象的相关属性
		request.setAttribute("array", array);
		request.setAttribute("list", list);
		request.setAttribute("map", map);
		
		// 转发页面到3.jsp进行测试
		request.getRequestDispatcher("/el/3.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
