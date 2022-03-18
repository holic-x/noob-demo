package atm.ui;

import atm.mod.ModDataGen;
import atm.mod.ModUser;
import utils.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 信息变更UI
 */
public class ModifyInfoUI extends JFrame implements MouseListener, FocusListener {
    /**
     * 定义全局组件
     */
    // 用户信息
    private ModUser modUser;

    // 背景面板设置
    private JPanel backgroundPanel = null;

    private JLabel operTip = null;
    private JPasswordField oldPwd = null;
    private JPasswordField confirmOldPwd = null;
    private JPasswordField newPwd = null;

    // 输入提示
    private String inputUserNameTip = "请输入用户账号";
    private String inputOldPwdTip = "请输入旧密码";
    private String confirmOldPwdTip = "请再次确认旧密码";
    private String inputNewPwdTip = "请输入新密码";

    // 按钮组合控制
    private JButton updateBtn = null;
    private JButton resetBtn = null;

    // 在构造方法中初始化组件并设置布局
    public ModifyInfoUI(ModUser modUser) {
        // 校验登录用户信息,初始化当前用户信息
        if(modUser!=null){
            this.modUser = modUser;
        }else{
            JOptionPane.showMessageDialog(null, "无效登录用户状态");
            return;
        }

        // 设置窗体标题、图标
        try {
            Image logoImage = null;
            logoImage = ImageIO.read(new File("icons/logo.png"));
            this.setIconImage(logoImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加载背景图片
        Image backgroungImage = null;
        try {
            backgroungImage = ImageIO.read(new File(
                    "icons/loginBackgroundImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 初始化背景面板并设置布局管理器为null
        backgroundPanel = new ImagePanel(backgroungImage);
        backgroundPanel.setLayout(null);

        Font defaultFont = new Font("华文行楷", Font.PLAIN, 20);

        // 添加操作提示
        operTip = new JLabel("您当前正在执行密码修改操作",SwingConstants.CENTER);
        operTip.setFont(defaultFont);
        operTip.setBounds(290, 120, 245, 45);

        // 密码框定义
        oldPwd = new JPasswordField(15);
        oldPwd.setFont(defaultFont);
        oldPwd.setText(inputOldPwdTip);
        oldPwd.setBounds(250, 200, 245, 30);

        confirmOldPwd = new JPasswordField(15);
        confirmOldPwd.setFont(defaultFont);
        confirmOldPwd.setText(confirmOldPwdTip);
        confirmOldPwd.setBounds(250, 240, 245, 30);

        newPwd = new JPasswordField(15);
        newPwd.setFont(defaultFont);
        newPwd.setText(inputNewPwdTip);
        newPwd.setBounds(250, 280, 245, 30);

        // 更新、重置按钮
        updateBtn = new JButton("更新");
        updateBtn.setToolTipText("更新");
        updateBtn.setBounds(300, 375, 100, 30);

        resetBtn  = new JButton("重置");
        resetBtn.setToolTipText("重置");
        resetBtn.setBounds(400, 375, 100, 30);

        // 为组件添加监听事件：文本框添加焦点监听事件、按钮添加鼠标点击监听事件
        oldPwd.addFocusListener(this);
        confirmOldPwd.addFocusListener(this);
        newPwd.addFocusListener(this);
        updateBtn.addMouseListener(this);
        resetBtn.addMouseListener(this);

        // 将所有的组件加载到背景面板
        backgroundPanel.add(operTip);
        backgroundPanel.add(oldPwd);
        backgroundPanel.add(confirmOldPwd);
        backgroundPanel.add(newPwd);
        backgroundPanel.add(updateBtn);
        backgroundPanel.add(resetBtn);

        // 设置焦点事件可用
        this.setFocusable(true);

        // 将背景面板装载到窗体中，并设置窗体的属性
        this.add(backgroundPanel);
        // 设置窗体的相关属性
        this.setTitle("ATM SIM");// 设置窗体标题
        this.setSize(1060, 600);// 设置窗体大小
        this.setVisible(true);// 设置可见性
        this.setLocationRelativeTo(null);// 设置依赖项
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭方式
        this.setResizable(false);// 设置禁止最大化
        this.requestFocus();// 设置格式化窗体
    }

    /**
     * 获取焦点事件
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == oldPwd) {
            if (oldPwd.getText().equals(inputOldPwdTip)) {
                oldPwd.setText("");
                oldPwd.setEchoChar('*');// 设置密码字符
            }
        }else if (e.getSource() == confirmOldPwd) {
            if (confirmOldPwd.getText().equals(confirmOldPwdTip)) {
                confirmOldPwd.setText("");
                confirmOldPwd.setEchoChar('*');// 设置密码字符
            }
        }else if (e.getSource() == newPwd) {
            if (newPwd.getText().equals(inputNewPwdTip)) {
                newPwd.setText("");
                newPwd.setEchoChar('*');// 设置密码字符
            }
        }
    }

    /**
     * 失去焦点事件
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == oldPwd) {
            if (oldPwd.getText().equals("")) {
                oldPwd.setText(inputOldPwdTip);
                oldPwd.setEchoChar('\0');// 设置字符集
            }
        }else if (e.getSource() == confirmOldPwd) {
            if (confirmOldPwd.getText().equals("")) {
                confirmOldPwd.setText(confirmOldPwdTip);
                confirmOldPwd.setEchoChar('\0');// 设置字符集
            }
        }else if (e.getSource() == newPwd) {
            if (newPwd.getText().equals("")) {
                newPwd.setText(inputNewPwdTip);
                newPwd.setEchoChar('\0');// 设置字符集
            }
        }
    }

    /**
     * 添加鼠标点击事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == updateBtn) {
            // 校验密码有效性，更新密码信息
            String oldPwdStr = oldPwd.getText();
            String confirmOldPwdStr = confirmOldPwd.getText();
            String newPwdStr = newPwd.getText();

            // a.校验两次旧密码确认是否一致
            if (!oldPwdStr.equals(confirmOldPwdStr)) {
                JOptionPane.showMessageDialog(this, "两次输入的旧密码不一致，请确认后再次尝试操作");
                return;
            }

            // b.校验新密码有效性
            if (newPwdStr.length() < 6) {
                JOptionPane.showMessageDialog(this, "新密码长度不能少于6位");
                return;
            }
//            String reg = "^.*\\d{6}.*$";
            String reg = "([0-9a-zA-Z])\\1{5}";
            if (newPwdStr.matches(reg)) {
                JOptionPane.showMessageDialog(this, "新密码不能出现连续6位相同的字符，请确认后再次尝试操作");
                return;
            }

            // c.校验旧密码正确性，如果无误则执行修改操作
            List<ModUser> modUserList = ModDataGen.modUserList();
            String currentUserName = this.modUser.getUserName();
            for (ModUser user : modUserList) {
                if (currentUserName.equals(user.getUserName())) {
                    JOptionPane.showMessageDialog(this, "正在更新密码请稍后......");
                    // 更新用户密码
                    user.setPassword(newPwdStr);
                    // 模拟更新操作延时1s
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this, "数据更新成功，如需办理其他业务，请退出系统后重新登录。");
                    this.setVisible(false);
                    this.getParent().getParent().setVisible(true);
                    new LoginUI();
                }
            }
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
