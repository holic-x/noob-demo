package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.guigu.library.model.Users;
import com.guigu.library.utils.ImagePanel;

public class LibraryInfoJPanel {
	// 定义全局组件
	JPanel backgroundPanel;

	Users user;

	public LibraryInfoJPanel(Users user) {
		this.user = user;
		initBackgroundPanel();
	}

	/**
	 * 初始化背景面板
	 */
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		try {
			Image backgroundImage = ImageIO.read(new File(
					"icons/toolImage/message.png"));
			ImagePanel centerBackground = new ImagePanel(backgroundImage);
			// 将初始化完成的面板加载到背景面板中
			backgroundPanel.add(centerBackground, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
