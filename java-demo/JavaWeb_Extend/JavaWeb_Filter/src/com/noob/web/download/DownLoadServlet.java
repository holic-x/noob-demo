package com.noob.web.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载指定的文件
 */
@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = request.getParameter("filename");// 06e00bf0-ded5-47b5-926a-ea1c56a8dcd8_1.jpg
		String name = filename.substring(filename.lastIndexOf("_") + 1); // 1.jpg

		String path = makeFilePath(name, "E:/upload");

		File file = new File(path + "/" + filename);

		if (!file.exists()) {
			request.setAttribute("msg", "您要下载的资源不存在!!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		response.setHeader("content-disposition", "attachment;filename=" + name);
		FileInputStream fis = new FileInputStream(file);
		OutputStream sout = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int hasRead = 0;
		while ((hasRead = fis.read(buffer)) != -1) {
			sout.write(buffer, 0, hasRead);
		}
		fis.close();
		sout.close();

	}

	public String makeFilePath(String filename, String path) {
		// 根据filename的hashcode码进行创建不同的子文件
		int hashCode = filename.hashCode();
		int dir1 = hashCode & 0xf; // 0-15之间的数字
		int dir2 = (hashCode & 0xf0) >> 4; // 0-15之间的数字
		String dir = path + "/" + dir1 + "/" + dir2;
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
