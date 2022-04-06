package com.noob.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/03/18
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class BarChart {
    public static void main(String[] args) {
        //generate the dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(2.0, "Row1", "Column1");
        dataset.addValue(3.0, "Row2", "Column1");
        dataset.addValue(4.0, "Row1", "Column2");
        dataset.addValue(5.0, "Row2", "Column2");
        //generate the chart
        JFreeChart chart = ChartFactory.createBarChart("Test", "AxisLabel",
                "ValueLabel", dataset, PlotOrientation.VERTICAL, true, true,
                false);
        CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        //显示条目标签
        renderer.setBaseItemLabelsVisible(true);
        //设置条目标签生成器,在JFreeChart1.0.6之前可以通过renderer.setItemLabelGenerator(CategoryItemLabelGenerator generator)方法实现，但是从版本1.0.6开始有下面方法代替
        renderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        //设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        ChartFrame frame = new ChartFrame("柱状图", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
