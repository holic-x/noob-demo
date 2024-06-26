package com.guigu.library.main;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.guigu.library.back.ui.control.BackLoginJFrame;

public class BackEntrance {
	public static void main(String[] args) {
		try {
			// 防止白屏现象
			System.setProperty("sun.java2d.noddraw", "true");
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			// 隐藏设置按钮
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new BackLoginJFrame();
	}
}
