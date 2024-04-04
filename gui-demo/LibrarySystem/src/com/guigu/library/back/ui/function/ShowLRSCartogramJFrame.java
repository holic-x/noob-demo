package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.TextAnchor;

import com.guigu.library.back.ui.control.InfoStatisticsJPanel;
import com.guigu.library.model.Borrowing;
import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.RenewService;
import com.guigu.library.service.ReturningService;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.RenewServiceImpl;
import com.guigu.library.service.impl.ReturningServiceImpl;

public class ShowLRSCartogramJFrame extends JFrame implements MouseListener,
		ItemListener {

	ChartPanel chartPanel;
	JPanel backgroundPanel, toolPanel;
	JLabel tool_export;
	InfoStatisticsJPanel parentPanel;
	JFreeChart chart;
	ButtonGroup group;
	JRadioButton barChart, lineChart, pieChart;

	JLabel label_year, label_month;
	JComboBox selectYear, selectMonth;

	int select = 0;
	// 定义service
	BorrowingService borrowingService = new BorrowingServiceImpl();
	RenewService renewService = new RenewServiceImpl();
	ReturningService returningService = new ReturningServiceImpl();

	public ShowLRSCartogramJFrame(InfoStatisticsJPanel parentPanel, int select) {
		this.parentPanel = parentPanel;
		this.select = select;
		backgroundPanel = new JPanel(new BorderLayout());

		initToolPanel();
		initChartPanel();

		this.add(backgroundPanel);

		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/**
	 * 初始化工具面板
	 */

	private void initToolPanel() {
		toolPanel = new JPanel(new BorderLayout());

		JPanel left = new JPanel();
		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("数据导出");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		group = new ButtonGroup();
		barChart = new JRadioButton("当月借还统计");
		barChart.addMouseListener(this);
		lineChart = new JRadioButton("季度借还统计");
		lineChart.addMouseListener(this);
		pieChart = new JRadioButton("年度借还统计");
		pieChart.addMouseListener(this);
		group.add(barChart);
		group.add(lineChart);
		group.add(pieChart);

		left.add(tool_export);
		left.add(barChart);
		left.add(lineChart);
		left.add(pieChart);

		// 根据当前用户选择进行判断
		if (select == 1) {
			barChart.setSelected(true);
		} else if (select == 2) {
			lineChart.setSelected(true);
		} else if (select == 3) {
			pieChart.setSelected(true);
		}

		JPanel right = new JPanel();
		label_year = new JLabel("年份");
		selectYear = new JComboBox();
		selectYear.addItem("默认年份");
		for (int i = 1999; i < 2500; i++) {
			selectYear.addItem(i);
		}
		selectYear.addItemListener(this);
		label_month = new JLabel("月份");
		selectMonth = new JComboBox();
		selectMonth.addItem("默认月份");
		for (int i = 1; i <= 12; i++) {
			selectMonth.addItem(i);
		}
		selectMonth.addItemListener(this);
		right.add(label_year);
		right.add(selectYear);
		right.add(Box.createHorizontalStrut(10));
		right.add(label_month);
		right.add(selectMonth);

		toolPanel.add(left, BorderLayout.WEST);
		toolPanel.add(right, BorderLayout.EAST);

		backgroundPanel.add(toolPanel, BorderLayout.NORTH);
		backgroundPanel.validate();

	}

	/**
	 * 初始化统计图面板
	 */
	private void initChartPanel() {

		chartPanel = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame
		if (barChart.isSelected()) {
			createBarChart();
		} else if (lineChart.isSelected()) {
//			createLineChart(); 数据显示不明确
			createLineChart2();
		} else if (pieChart.isSelected()) {
			createPieChart();
		}

		backgroundPanel.add(chartPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 当月借还统计：条形统计图
	 */
	public void createBarChart() {
		CategoryDataset dataset = getBarDataSet();
		chart = ChartFactory.createBarChart3D("当月借还统计", // 图表标题
				"借阅/续借/归还", // 目录轴的显示标签
				"处理数目", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		// 设置显示的数值
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		// 显示条目标签
		renderer.setBaseItemLabelsVisible(true);
		// 设置条目标签生成器,在JFreeChart1.0.6之前可以通过renderer.setItemLabelGenerator(CategoryItemLabelGenerator
		// generator)方法实现，但是从版本1.0.6开始有下面方法代替
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
	}

	public CategoryDataset getBarDataSet() {
		// 从数据库中获取信息进行统计，计算当前指定月份的图书借阅情况
		// 获取当前指定的年份、月份
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);// 获取年份
		int month = date.get(Calendar.MONTH) + 1;// 获取月份 需要+1
		// 如果没有指定的年份、月份则默认是当前年份、月份
		if (!selectYear.getSelectedItem().equals("默认年份")) {
			year = Integer.valueOf(selectYear.getSelectedItem() + "");
		}
		if (!selectMonth.getSelectedItem().equals("默认月份")) {
			month = Integer.valueOf(selectMonth.getSelectedItem() + "");
		}
		// 获取数据集
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 获取当前月份所有的图书借阅、续借、归还记录总数
		String dateStr;
		if (month >= 1 && month <= 9) {
			dateStr = year + "-0" + month + "-01";
		} else {
			dateStr = year + "-" + month + "-01";
		}
		String start_time = dateStr;
		String end_time = this.getLastDayOfMonth(year, month);
		try {
			List<Borrowing> list_borrow = borrowingService.findBorrowingByTime(
					start_time, end_time);
			List<Renew> list_renew = renewService.findRenewByTime(start_time,
					end_time);
			List<Returning> list_return = returningService.findReturningByTime(
					start_time, end_time);

			dataset.addValue(list_borrow.size(), "已借出", "借阅数");
			dataset.addValue(list_renew.size(), "续借", "续借数");
			dataset.addValue(list_return.size(), "已归还", "归还数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	/**
	 * 季度借还统计：折线统计图
	 */
	public void createLineChart() {
		// 获取数据集
		XYDataset xydataset = getLineDataset();
		chart = ChartFactory.createTimeSeriesChart("季度借还统计", "日期", "价格",
				xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) chart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		chartPanel = new ChartPanel(chart, true);
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
	}

	public XYDataset getLineDataset() {
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		try {
			// 从数据库中获取信息进行统计，计算当前指定月份的图书借阅情况
			// 获取当前指定的年份、月份
			Calendar date = Calendar.getInstance();
			int year = date.get(Calendar.YEAR);// 获取年份
			// 如果没有指定的年份、月份则默认是当前年份、月份
			if (!selectYear.getSelectedItem().equals("默认年份")) {
				year = Integer.valueOf(selectYear.getSelectedItem() + "");
			}
			String start_time;
			String end_time;
			List<Borrowing> list_borrow;
			List<Renew> list_renew;
			List<Returning> list_return;

			TimeSeries timeseries1 = new TimeSeries("借阅数",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries2 = new TimeSeries("续借数",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries3 = new TimeSeries("归还数",
					org.jfree.data.time.Month.class);
			// 春季
			start_time = year + "-01-01";
			end_time = this.getLastDayOfMonth(year, 3);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(3, year), list_borrow.size());
			timeseries2.add(new Month(3, year), list_renew.size());
			timeseries3.add(new Month(3, year), list_return.size());

			// 夏季
			start_time = year + "-04-01";
			end_time = this.getLastDayOfMonth(year, 6);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(6, year), list_borrow.size());
			timeseries2.add(new Month(6, year), list_renew.size());
			timeseries3.add(new Month(6, year), list_return.size());

			// 秋季
			start_time = year + "-07-01";
			end_time = this.getLastDayOfMonth(year, 9);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(9, year), list_borrow.size());
			timeseries2.add(new Month(9, year), list_renew.size());
			timeseries3.add(new Month(9, year), list_return.size());

			// 冬季
			start_time = year + "-10-01";
			end_time = this.getLastDayOfMonth(year, 12);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(12, year), list_borrow.size());
			timeseries2.add(new Month(12, year), list_renew.size());
			timeseries3.add(new Month(12, year), list_return.size());

			timeseriescollection.addSeries(timeseries1);
			timeseriescollection.addSeries(timeseries2);
			timeseriescollection.addSeries(timeseries3);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return timeseriescollection;
	}

	/**
	 * 历史借还统计：饼状图
	 */
	public void createPieChart() {

		DefaultPieDataset data = getPieDataSet();
		chart = ChartFactory.createPieChart3D("年度借阅/归还统计", data, true, false,
				false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();

		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		pieplot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})"));

		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setNoDataMessageFont(new Font("华文行楷", Font.BOLD, 32));
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		chartPanel = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));

	}

	private DefaultPieDataset getPieDataSet() {
		// 从数据库中获取数据（统计所有的收支情况）--总的收支
		// 获取当前指定的年份、月份
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);// 获取年份
		// 如果没有指定的年份、月份则默认是当前年份、月份
		if (!selectYear.getSelectedItem().equals("默认年份")) {
			year = Integer.valueOf(selectYear.getSelectedItem() + "");
		}

		// 获取数据集
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 获取当前月份所有的图书借阅、续借、归还记录总数
		String start_time = year + "-01-01";
		String end_time = this.getLastDayOfMonth(year, 12);
		DefaultPieDataset dpd = new DefaultPieDataset();
		// 获取所有的销售订单记录
		try {
			List<Borrowing> list_borrow = borrowingService.findBorrowingByTime(
					start_time, end_time);
			List<Returning> list_return = returningService.findReturningByTime(
					start_time, end_time);
			dpd.setValue("已借出", list_borrow.size());
			dpd.setValue("已归还", list_return.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dpd;
	}

	/**
	 * 输出图表数据为图片，可以有指定的格式
	 */
	private static int writeChartAsImage(JFreeChart chart) {
		// 弹出文件选择框
		JFileChooser chooser = new JFileChooser();
		// 后缀名过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"图片文件(*.jpg)", "jpg");
		chooser.setFileFilter(filter);
		// 下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
		int option = chooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) { // 假如用户选择了保存
			File file = chooser.getSelectedFile();
			String fname = chooser.getName(file); // 从文件名输入框中获取文件名
			// 假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
			FileOutputStream FOut = null;
			if (fname.indexOf(".jpg") == -1) {
				file = new File(chooser.getCurrentDirectory(), fname + ".jpg");
			}
			try {
				FOut = new FileOutputStream(file);
				ChartUtilities.writeChartAsJPEG(FOut, 1, chart, 400, 300, null);
				return 1;
			} catch (IOException e) {
				System.err.println("IO异常");
				e.printStackTrace();
				return -1;
			} finally {
				try {
					FOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			// 默认导出数据图片到d盘
			int result = this.writeChartAsImage(chart);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据已导出到指定位置！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉，数据导出失败！再试一遍吧！");
			} else {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			}
		} else if (e.getSource() == barChart) {
			// 显示条形统计图
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		} else if (e.getSource() == lineChart) {
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		} else if (e.getSource() == pieChart) {
			backgroundPanel.remove(chartPanel);
			initChartPanel();
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

	/**
	 * 获取某年某月的最后一天
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.ITEM_STATE_CHANGED) {
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		}
	}

	/**
	 * 季度借还统计：折线统计图 ------ 方式2
	 */
	public void createLineChart2() {
		// 获取数据集
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = getLineDataset2();
		chart = ChartFactory.createLineChart("折线图",// 图名字
				"年份",// 横坐标
				"数量",// 纵坐标
				mDataset,// 数据集
				PlotOrientation.VERTICAL, true, // 显示图例
				true, // 采用标准生成器
				false);// 是否生成超链接

		CategoryPlot mPlot = (CategoryPlot) chart.getPlot();
		mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		mPlot.setRangeGridlinePaint(Color.BLUE);// 背景底部横虚线
		mPlot.setOutlinePaint(Color.RED);// 边界线
	}

	public CategoryDataset getLineDataset2() {
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		try {
			// 从数据库中获取信息进行统计，计算当前指定月份的图书借阅情况
			// 获取当前指定的年份、月份
			Calendar date = Calendar.getInstance();
			int year = date.get(Calendar.YEAR);// 获取年份
			// 如果没有指定的年份、月份则默认是当前年份、月份
			if (!selectYear.getSelectedItem().equals("默认年份")) {
				year = Integer.valueOf(selectYear.getSelectedItem() + "");
			}
			String start_time;
			String end_time;
			List<Borrowing> list_borrow;
			List<Renew> list_renew;
			List<Returning> list_return;

			TimeSeries timeseries1 = new TimeSeries("借阅数",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries2 = new TimeSeries("续借数",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries3 = new TimeSeries("归还数",
					org.jfree.data.time.Month.class);
			// 春季
			start_time = year + "-01-01";
			end_time = this.getLastDayOfMonth(year, 3);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "借阅数", "春季");
			mDataset.addValue(list_renew.size(), "续借数", "春季");
			mDataset.addValue(list_return.size(), "归还数", "春季");

			// 夏季
			start_time = year + "-04-01";
			end_time = this.getLastDayOfMonth(year, 6);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "借阅数", "夏季");
			mDataset.addValue(list_renew.size(), "续借数", "夏季");
			mDataset.addValue(list_return.size(), "归还数", "夏季");

			// 秋季
			start_time = year + "-07-01";
			end_time = this.getLastDayOfMonth(year, 9);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "借阅数", "秋季");
			mDataset.addValue(list_renew.size(), "续借数", "秋季");
			mDataset.addValue(list_return.size(), "归还数", "秋季");

			// 冬季
			start_time = year + "-10-01";
			end_time = this.getLastDayOfMonth(year, 12);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "借阅数", "冬季");
			mDataset.addValue(list_renew.size(), "续借数", "冬季");
			mDataset.addValue(list_return.size(), "归还数", "冬季");

		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return mDataset;
	}

}