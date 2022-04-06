package com.noob.exam;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/03/18
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class MainTest {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Java数据统计图");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(new BarChart(0).getChartPanel()); // 添加柱形图
        frame.add(new BarChart(1).getChartPanel()); // 添加柱形图的另一种效果
        frame.add(new PieChart().getChartPanel()); // 添加饼状图
        frame.add(new TimeSeriesChart().getChartPanel()); // 添加折线图
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}
