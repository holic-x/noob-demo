package com.noob.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noob.db.DBUser;

@WebServlet("/CheckUserNameServlet")
public class CheckUserNameServlet extends HttpServlet {
	/**
	 * 此处需要注意xml的书写规范，如果出错则打印数据进行分析
	 * 查看是否是xml的编辑有误
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter pw=response.getWriter();
        
        String username=request.getParameter("username");
        boolean flag =DBUser.checkUserName(username);
        //把结果拼接为xml 然后响应到客户端 
        StringBuilder sb =new StringBuilder();
        sb.append("<result>");
        sb.append("<res>");
        if(flag) {
            sb.append("1");
        }else {
            sb.append("0");
        }
        sb.append("</res>");
        sb.append("</result>");
        //<result><res>1</res></result>
        System.out.println(sb.toString());
        pw.println(sb.toString());
        pw.flush();
        pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
