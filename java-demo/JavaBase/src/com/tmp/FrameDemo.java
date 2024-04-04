package com.tmp;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author：holic-x
 * @date: 2022/5/15
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FrameDemo {

    public static void main(String[] args) {
        Frame f = new Frame("login");
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setLayout(null);
        Button b = new Button("anniu");
        b.setSize(100,100);
        b.setLocation(10,10);
//        b.setLocation(50,100);
        f.add(b);
        f.setVisible(true);
    }

}
