package com.tmp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class math {
    public static void main(String[] args) throws IOException {
        myJFrame myJFrame = new myJFrame();
        myJFrame.setVisible(true);
    }
}
class myJFrame extends JFrame implements ActionListener{
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton ten;
    private JButton eleven;
    private JButton twelve;
    private JLabel label;
    private BufferedImage image;

    public myJFrame() throws IOException {
        setBounds(500, 100, 1000, 850);
        setTitle("利用幂函数f(x)=x^r对“sky.jpg”数字图像R\\G\\B通道的像素值进行变换");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        BufferedImage image = ImageIO.read(new File("D:\\Desktop\\1.png"));
        one = new JButton("0.1");
        two = new JButton("0.4");
        three = new JButton("0.6");
        four = new JButton("0.8");
        five = new JButton("1.0");
        six = new JButton("1.2");
        seven = new JButton("1.5");
        eight = new JButton("1.8");
        nine = new JButton("2.0");
        ten = new JButton("2.5");
        eleven = new JButton("5.0");
        twelve = new JButton("10.0");
        label = new JLabel(new ImageIcon("D:\\Desktop\\1.png"));
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        ten.addActionListener(this);
        eleven.addActionListener(this);
        twelve.addActionListener(this);
        add(one);
        add(two);
        add(three);
        add(four);
        add(five);
        add(six);
        add(seven);
        add(eight);
        add(nine);
        add(ten);
        add(eleven);
        add(twelve);
        add(label);
        one.setBounds(50,50,60,50);
        two.setBounds(130,50,60,50);
        three.setBounds(210,50,60,50);
        four.setBounds(290,50,60,50);
        five.setBounds(370,50,60,50);
        six.setBounds(450,50,60,50);
        seven.setBounds(530,50,60,50);
        eight.setBounds(610,50,60,50);
        nine.setBounds(690,50,60,50);
        ten.setBounds(770,50,60,50);
        eleven.setBounds(850,50,60,50);
        twelve.setBounds(930,50,60,50);
        label.setBounds(50, 150, 900, 500);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            changeColor(Double.parseDouble(((JButton)e.getSource()).getText().trim()));
            ImageIO.write(image, "jpg", new File("D:\\Desktop\\6.jpg"));
            ImageIcon img = new ImageIcon("C:\\Users\\HUAWEI\\Desktop\\6.jpg");
            img.getImage().flush();
            label.setIcon(img);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage changeColor(double n) throws IOException {
        image = ImageIO.read(new File("D:\\Desktop\\6.jpg"));
        Color color = null;
        for(int i = 0; i < image.getWidth();i ++){
            for(int j = 0; j < image.getHeight();j ++){
                int p = image.getRGB(i, j);
                int r = (int) Math.pow((p >> 16) & 0xff,n);
                int g = (int) Math.pow((p >> 8) & 0xff,n);
                int b = (int) Math.pow(p & 0xff,n);
                if(r>255)r=255;
                if(g>255)g=255;
                if(b>255)b=255;
                color = new Color(r,g,b);
                image.setRGB(i, j, color.getRGB());
            }
        }
        return image;
    }
}

