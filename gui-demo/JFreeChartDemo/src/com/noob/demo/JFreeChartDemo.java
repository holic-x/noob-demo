package com.noob.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/03/18
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class JFreeChartDemo {
    public static void main(String[] args) {
        DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
        dpd.setValue("管理人员", 25); // 输入数据
        dpd.setValue("市场人员", 25);
        dpd.setValue("开发人员", 45);
        dpd.setValue("其他人员", 10);
        JFreeChart chart = ChartFactory.createPieChart("某公司人员组织数据图", dpd, true,
                true, false);
        // 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

        // 解决中文显示问题
        Font titleFont = new Font("隶书", Font.ITALIC, 18);
        Font font = new Font("宋体", Font.BOLD, 12);
        Font legendFont = new Font("宋体", Font.BOLD, 15);

        chart.getTitle().setFont(titleFont);
        chart.getLegend().setItemFont(legendFont);

        // 设置饼状图的相关属性
        PiePlot plot = (PiePlot) chart.getPlot();
        // 设置字体
        plot.setLabelFont(font);
        // 设置无数据时的信息
        plot.setNoDataMessage("无对应的数据，请重新查询。");
        // 设置无数据时的信息显示颜色
        plot.setNoDataMessagePaint(Color.red);
        // 去掉lable（默认是显示对应的名称，可通过设置显示相应的内容）
        plot.setLabelGenerator(null);
        // 去掉标签连接线
//		plot.setLabelLinksVisible(false);
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}={1}({2})", NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        // 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}={1}({2})"));
        // 饼图标签显示百分比方法
        // plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
        // 饼图标签使用百分比显示，保留一位小数
        // plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",
        // new DecimalFormat("0.0"), new DecimalFormat("0.0%")));
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        // 显示对应的实际数值
        // plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));

        // 设置第一个 饼块section 的开始位置，默认是12点钟方向
        plot.setStartAngle(90);

        // // 设置分饼颜色
//		  plot.setSectionPaint(字符串数据, 分饼颜色);
        plot.setSectionPaint("其他人员", new Color(255, 255, 255));

        // 亦可导出图片数据到指定的路径中
        writeChartAsImage(chart); // 先将数据图导出，否则有时存在空指针异常

        // 将数据显示在JFrame界面上
        ChartFrame chartFrame = new ChartFrame("某公司人员组织数据图", chart);
        // chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); // 以合适的大小展现图形
        chartFrame.setVisible(true);// 图形是否可见

    }

    private static void writeChartAsImage(JFreeChart chart) {
        // 用不同的方式导出jpg、png图片格式
        FileOutputStream fos_jpg = null;
        File file_png = null;
        File file_jpg = null;
        try {
            fos_jpg = new FileOutputStream("D:\\manager.jpg");
            file_png = new File("D:\\manager.png");
            file_jpg = new File("D:\\manager1.jpg");
            // 如果是File类型的文件，需要先判断指定的File是否存在,不存在则相应的创建文件
            if (!file_png.exists()) {
                file_png.createNewFile();
            }
            if (!file_jpg.exists()) {
                file_jpg.createNewFile();
            }
            // 导出图像、数目、数据、图像大小
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300, null);
            ChartUtilities.saveChartAsPNG(file_png, chart, 400, 300);
            ChartUtilities.saveChartAsJPEG(file_jpg, chart, 400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos_jpg.close();
            } catch (Exception e) {
            }
        }
    }
}
