package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.design.sm.fore.ui.control.BaseDataJPanel;
import com.design.sm.fore.ui.control.ProductManagerJPanel;
import com.design.sm.fore.ui.control.StockManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.utils.MyFont;

public class ProductPurchaseJFrame extends JFrame {

	// 定义全局组件
	JPanel backgroundPanel;
	// 定义父组件
	StockManagerJPanel parentPanel;
	JTabbedPane jTabbedPane;
	Accounts loginUser;
	public ProductPurchaseJFrame(StockManagerJPanel parentPanel,Accounts loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		initBackgroundPanel();
		this.add(backgroundPanel);
		// 设置窗体大小
		this.setTitle("商品采购");
		this.setSize(1000, 550);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		/**
		 *  添加相关的属性(采购商品、采购记录、库存预警、回收站、监督机制)
		 *  采购商品：提供商品信息的模糊查询功能、库存预警功能
		 *  采购清单：将需要进购的商品加入采购清单：单选按钮组合：未处理0   已处理1
		 *  采购记录：单选按钮组合：未处理（已提交但还未审核）   已处理（提交并通过审核）  无效订单（未通过审核）
		 *  退货：
		 *  回收站：删除采购记录（放入回收站--删除标识为1，如果是彻底删除则相应的的采购明细也是级联删除）
		 *  
		 */
		jTabbedPane.addTab("采购商品", new ProductPurchaseJPanel(loginUser).backgroundPanel);// 添加采购商品页面
		jTabbedPane.addTab("采购清单", new PurchaseListJPanel(loginUser).backgroundPanel);// 添加采购清单页面
		jTabbedPane.addTab("采购记录", new PurchaseRecordJPanel(loginUser).backgroundPanel);// 添加采购记录页面
		jTabbedPane.addTab("回收站", new StockInRecycleBinJPanel(loginUser).backgroundPanel);// 添加回收站页面
		backgroundPanel.add(jTabbedPane, BorderLayout.CENTER);
	}
	
}
