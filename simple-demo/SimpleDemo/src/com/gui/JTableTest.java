package com.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;

//继承JFrame
public class JTableTest extends JFrame {

    //定义组件
    JTable jTable = null;
    JScrollPane jScrollPane = null;

    //定义JTable的对象
    Vector rowData, columnNames;

    //定义一行数据的对象
    Vector line1;

    public static void main(String[] args) {
        JTableTest jTableTest = new JTableTest();
    }

    //构造函数
    public JTableTest() {
        //设置表格类目
        columnNames = new Vector();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("籍贯");

        //设置表格数据
        rowData = new Vector();
        line1 = new Vector();
        line1.add("001");
        line1.add("孙悟空");
        line1.add("花果山");
        rowData.add(line1);

        line1 = new Vector();
        line1.add("002");
        line1.add("猪八戒");
        line1.add("天空");
        rowData.add(line1);

        line1 = new Vector();
        line1.add("003");
        line1.add("沙悟净");
        line1.add("大海");
        rowData.add(line1);


        line1 = new Vector();
        line1.add("004");
        line1.add("唐三藏");
        line1.add("长安红红火火恍恍惚惚或或或或或或或或或或或或或或或或或");
        rowData.add(line1);

        //生成表格
//        jTable = new JTable(rowData,columnNames);
//         jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
////         jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS );
//        jScrollPane = new JScrollPane(jTable);


        DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
        jTable = new JTable(dtm);
        jTable.setRowHeight(85);
        jTable.setDefaultRenderer(Object.class, new TableViewRenderer());//红色标记部分是用来渲染JTable的自定义绘制器
        jScrollPane = new JScrollPane(jTable);

        //添加组件
        this.add(jScrollPane);

        //窗体设置
        this.setTitle("学生管理系统");
        this.setLocation(200, 200);
        this.setSize(280, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

}


//自定义的表格绘制器
class TableViewRenderer extends JTextArea implements TableCellRenderer {
    public TableViewRenderer() {
        //将表格设为自动换行
        setLineWrap(true); //利用JTextArea的自动换行方法
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object obj, //obj指的是单元格内容
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText(obj == null ? "" : obj.toString()); //利用JTextArea的setText设置文本方法
        return this;
    }
}

