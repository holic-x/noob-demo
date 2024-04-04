package com.noob.web.response;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例3：通过response对象生成随机图片（验证码）
 * 设计步骤：
 * a.定义随机字典，实现随机生成6个任意字符
 * b.获取随机颜色作为背景色，并将其反色作为文本颜色
 * c.获取上述两步中得到的文字和颜色，利用画笔绘制文本和背景
 * d.输出绘制的图片到指定页面（可设置噪点）
 */
@WebServlet("/ResponseDemo3")
public class ResponseDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Random r = new Random();
	/**
	 *  a.定义随机字典，实现生成6个随机字符
	 */
	private static char[] ch = {
			'0','1','2','3','4','5','6','7','8','9','a','b','c',
			'd','e','f','g','h','i','j','k','l','m','n','o','p',
			'q','r','s','t','u','v','w','x','y','z','A','B','C',
			'D','E','F','G','H','I','J','K','L','M','N','O','P',
			'Q','R','S','T','U','V','W','X','Y','Z'
	};
	private String getRandom6char() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++) {
			sb.append(ch[r.nextInt(ch.length)]);
		}
		return sb.toString();
	}
 	/**
 	 *  b.获取随机颜色作为背景色，将其反色作为文本颜色
 	 */
	private Color getBackgroundColor() {
		// rgb三原色：red、green、blue
		Color bgcolor = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		return bgcolor;
	}
	private Color getReverseColor(Color c) {
		Color reverse = new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue());
		return reverse;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * c.绘制文本和背景
		 */
		// 获取绘制验证码所需要数据信息(文本、背景色)
		String str = getRandom6char();
		Color bgcolor = getBackgroundColor();
		Color reverseColor = getReverseColor(bgcolor);
		// 创建用于输出文件的对象
		BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		// 获取画笔，绘制文本和背景
		Graphics g = bi.getGraphics();
		// 设置画笔颜色，绘制背景色
		g.setColor(bgcolor);
		g.fillRect(0, 0, 100, 30);
		// 设置画笔颜色和文本样式，绘制文本内容
		g.setColor(reverseColor);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
		g.drawString(str, 18, 20);
		// 可添加绘制噪点
		for(int i=0,n=r.nextInt(100);i<n;i++) {
			g.drawRect(r.nextInt(100), r.nextInt(30), 1, 1);
		}
		/**
		 *  d.设置输出数据的类型,直接输出图片到指定的页面
		 */
		response.setContentType("image/jpeg");
		ImageIO.write(bi, "jpg", response.getOutputStream());
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
