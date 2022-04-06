package com.design.sm.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * 自定义工具类
 * 完成图片的加载
 */
public class ImagePanel extends JPanel{
	//a.定义背景图片
	Image image;
	//b.初始化背景图片
	public ImagePanel(Image image)
	{
		this.image = image;
		//获取当前屏幕的宽度和高度
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//设置绘制图片的大小
		this.setSize(width,height);
	}
	//c.绘制图片
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//绘制图片来源、绘制位置、绘制大小、绘制当前对象（this）
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
