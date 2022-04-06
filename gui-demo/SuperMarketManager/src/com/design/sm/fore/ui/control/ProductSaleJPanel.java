package com.design.sm.fore.ui.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.design.sm.fore.ui.function.CashierJFrame;
import com.design.sm.model.Accounts;

public class ProductSaleJPanel implements MouseListener{
	// 定义全局组件
	public JPanel backgroundPanel;
	JLabel cashier,shift;
	Accounts loginUser;

	public ProductSaleJPanel(Accounts loginUser) {
		this.loginUser = loginUser;
		initBackgroundPanel();
	}

	/**
	 * 初始化背景面板，定义两个个按钮分别用以进入到不同的管理界面
	 */
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel();
		Box hor = Box.createHorizontalBox();
		Icon cashier_icon = new ImageIcon("icons/toolImage/cashier.png");
		cashier = new JLabel(cashier_icon);
		cashier.setToolTipText("收银台");
		cashier.addMouseListener(this);
		
		Icon shift_icon = new ImageIcon("icons/toolImage/shift.png");
		shift = new JLabel(shift_icon);
		shift.setToolTipText("换班");
		shift.addMouseListener(this);
		hor.add(cashier);
		hor.add(Box.createHorizontalStrut(125));
		hor.add(shift);

		backgroundPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cashier) {
			new CashierJFrame(this,this.loginUser);
		}
		 else if (e.getSource() == shift) {
			 // 员工登录切换
			new ForeLoginJFrame();
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
