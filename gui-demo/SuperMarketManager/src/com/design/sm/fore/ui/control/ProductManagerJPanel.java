package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.design.sm.fore.ui.function.CategoryManagerJFrame;
import com.design.sm.fore.ui.function.ProductUpDownJFrame;
import com.design.sm.fore.ui.function.UnitsManagerJFrame;
import com.design.sm.model.Accounts;

public class ProductManagerJPanel implements MouseListener {
	// 定义全局组件
	public JPanel backgroundPanel;
	JLabel put_on, pull_off, unit_manager, category_manager;
	Accounts loginUser;

	public ProductManagerJPanel(Accounts loginUser) {
		this.loginUser = this.loginUser;
		initBackgroundPanel();
	}

	/**
	 * 初始化背景面板，定义四个按钮分别用以进入到不同的管理界面
	 */
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel();
		Box hor1 = Box.createHorizontalBox();
		put_on = new JLabel();
		put_on.setToolTipText("商品上架");
		Icon puton_icon = new ImageIcon("icons/buttonImage/puton.png");
		put_on.setIcon(puton_icon);
		put_on.addMouseListener(this);
		
		pull_off = new JLabel();
		pull_off.setToolTipText("商品下架");
		Icon pulloff_icon = new ImageIcon("icons/buttonImage/pulloff.png");
		pull_off.setIcon(pulloff_icon);
		pull_off.addMouseListener(this);
		hor1.add(put_on);
		hor1.add(Box.createHorizontalStrut(125));
		hor1.add(pull_off);

		Box hor2 = Box.createHorizontalBox();
		unit_manager = new JLabel();
		unit_manager.setToolTipText("单位管理");
		Icon unit_icon = new ImageIcon("icons/buttonImage/unit.png");
		unit_manager.setIcon(unit_icon);
		unit_manager.addMouseListener(this);
		category_manager = new JLabel();
		category_manager.setToolTipText("分类管理");
		Icon category_icon = new ImageIcon("icons/buttonImage/category.png");
		category_manager.setIcon(category_icon);
		category_manager.addMouseListener(this);
		hor2.add(unit_manager);
		hor2.add(Box.createHorizontalStrut(125));
		hor2.add(category_manager);

		Box ver = Box.createVerticalBox();
		ver.add(hor1);
		ver.add(Box.createVerticalStrut(125));
		ver.add(hor2);
		
		backgroundPanel.add(ver);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == put_on) {
			new ProductUpDownJFrame(this.loginUser);
		} else if (e.getSource() == put_on) {
			new ProductUpDownJFrame(this.loginUser);
		} else if (e.getSource() == unit_manager) {
			new UnitsManagerJFrame(this);
		} else if (e.getSource() == category_manager) {
			new CategoryManagerJFrame(this);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}