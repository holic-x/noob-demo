package com.noob.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 二级联动测试
 */
@WebServlet("/LinkerServlet")
public class LinkerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");;
		// 获取用户选择的省份id
		String targetId = request.getParameter("id");
		String xml_start="<selects>";
		String xml_end="</selects>";
		String xml="";
		if(targetId.equals("0")) {
			xml = "<select><value>0</value><text>未选择</text></select>";
		}else if(targetId.equals("1")) {
			xml = "<select><value>1</value><text>杭州市</text></select>";
			xml += "<select><value>2</value><text>温州市</text></select>";
			xml += "<select><value>3</value><text>宁波市</text></select>";
			xml += "<select><value>4</value><text>嘉兴市</text></select>";
		}else if(targetId.equals("2")) {
			xml = "<select><value>1</value><text>济南市</text></select>";
			xml += "<select><value>2</value><text>青岛市</text></select>";
			xml += "<select><value>3</value><text>潍坊市</text></select>";
			xml += "<select><value>4</value><text>济宁市</text></select>";
		}else if(targetId.equals("3")) {
			xml = "<select><value>1</value><text>南京市</text></select>";
			xml += "<select><value>2</value><text>苏州市</text></select>";
			xml += "<select><value>3</value><text>南通市</text></select>";
			xml += "<select><value>4</value><text>常州市</text></select>";
		}else if(targetId.equals("4")) {
			xml = "<select><value>1</value><text>厦门市</text></select>";
			xml += "<select><value>2</value><text>福州市</text></select>";
			xml += "<select><value>3</value><text>龙岩市</text></select>";
			xml += "<select><value>4</value><text>福安市</text></select>";
		}else if(targetId.equals("5")) {
			xml = "<select><value>1</value><text>兰州市</text></select>";
			xml += "<select><value>2</value><text>敦煌市</text></select>";
			xml += "<select><value>3</value><text>定西市</text></select>";
			xml += "<select><value>4</value><text>白银市</text></select>";
		}else if(targetId.equals("6")) {
			xml = "<select><value>1</value><text>广州市</text></select>";
			xml += "<select><value>2</value><text>潮阳市</text></select>";
			xml += "<select><value>3</value><text>珠海市</text></select>";
			xml += "<select><value>4</value><text>澄海市</text></select>";
		}
		String res = xml_start+xml+xml_end;
		response.getWriter().write(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
