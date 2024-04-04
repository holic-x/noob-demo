package com.noob.web.download;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 显示当前上传目录下所有的文件信息
 */
@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 定义当前的上传路径根目录
		String filepath = "E:/upload";
		// 迭代当前目录结构
		Map map = new HashMap();
		// 列出指定文件夹下所有的文件 包括子目录中的内容 然后进行迭代后的结果存放在map中
		listFile(new File(filepath), map);
		// 转发页面，显示数据信息
		request.setAttribute("map", map);
		request.getRequestDispatcher("/download/download.jsp").forward(request, response);
	}

	private void listFile(File file, Map map) {
		if (file.isDirectory()) {// 代表是文件夹
			File[] files = file.listFiles();
			// 循环所有的子文件
			for (File fi : files) {
				listFile(fi, map);
			}

		} else {
			// 代表是文件
			// 得到文件的真实名称 06e00bf0-ded5-47b5-926a-ea1c56a8dcd8_1.jpg 真实名称是 1.jpg
			String realname = file.getName().substring(file.getName().indexOf("_") + 1);
			map.put(file.getName(), realname);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
