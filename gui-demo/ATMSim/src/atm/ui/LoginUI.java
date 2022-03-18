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
 * ATM模拟登录UI
 */
public class LoginUI extends JFrame implements MouseListener, FocusListener {
    /**
     * 定义全局组件
     */
    // 背景面板设置
    private JPanel backgroundPanel = null;

    private JLabel atmSysName = null;
    private JTextField username = null;
    private JPasswordField password = null;

    // 输入提示
    private String inputUserNameTip = "请输入用户账号";
    private String inputPwdTip = "请输入用户密码";

    // 按钮组合控制
    private JButton loginBtn = null;
    private JButton resetBtn = null;

    // 在构造方法中初始化组件并设置布局
    public LoginUI() {
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

        // 添加ATM名称
        atmSysName = new JLabel("模拟ATM系统",SwingConstants.CENTER);
        atmSysName.setFont(defaultFont);
        atmSysName.setBounds(290, 120, 245, 45);


        // 添加用户名、密码文本框
        username = new JTextField(15);
        username.setFont(defaultFont);
        username.setText(inputUserNameTip);
        username.setBounds(300, 231, 245, 45);
        password = new JPasswordField(15);
        password.setFont(defaultFont);
        password.setText(inputPwdTip);
        password.setBounds(300, 300, 245, 45);

        // 登录、重置按钮

        loginBtn = new JButton("登录");
        loginBtn.setToolTipText("登录");
        loginBtn.setBounds(300, 375, 64, 64);

        resetBtn  = new JButton("重置");
        resetBtn.setToolTipText("重置");
        resetBtn.setBounds(400, 375, 64, 64);

        // 为组件添加监听事件：文本框添加焦点监听事件、按钮添加鼠标点击监听事件
        username.addFocusListener(this);
        password.addFocusListener(this);
        loginBtn.addMouseListener(this);
        resetBtn.addMouseListener(this);

        // 将所有的组件加载到背景面板
        backgroundPanel.add(atmSysName);
        backgroundPanel.add(username);
        backgroundPanel.add(password);
        backgroundPanel.add(loginBtn);
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
        if (e.getSource() == username) {
            if (username.getText().equals(inputUserNameTip)){
                username.setText("");
            }
        } else if (e.getSource() == password) {
            if (password.getText().equals(inputPwdTip)) {
                password.setText("");
                password.setEchoChar('*');// 设置密码字符
            }
        }
    }

    /**
     * 失去焦点事件
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == username) {
            if (username.getText().equals("")){
                username.setText(inputUserNameTip);
            }
        } else if (e.getSource() == password) {
            if (password.getText().equals("")) {
                password.setText(inputPwdTip);
                password.setEchoChar('\0');// 设置字符集
            }
        }
    }

    /**
     * 添加鼠标点击事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginBtn) {
            if (username.getText().equals(inputUserNameTip)) {
                JOptionPane.showMessageDialog(null, "用户名/账号不能为空！");
            } else if (password.getText().equals(inputPwdTip)) {
                JOptionPane.showMessageDialog(null, "密码不能为空！");
            } else {
                // 获取数据进行校验
                String username_string = username.getText();
                String password_string = password.getText();
                List<ModUser> modUserList = ModDataGen.modUserList();
                boolean loginTag = false;
                ModUser findModUser = null;
                for (ModUser modUser : modUserList) {
                    if (modUser.getUserName().equals(username_string)) {
                        // 检测到有效用户，进一步校验密码
                        if (modUser.getPassword().equals(password_string)) {
                            // 密码校验通过，用户身份验证成功进入主页面
                            loginTag = true;
                            findModUser = modUser;
                        } else {
                            JOptionPane.showMessageDialog(null, "密码输入错误！");
                            return;
                        }
                    }
                }
                // 校验登录状态
                if(loginTag){
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    this.setVisible(false);
                    new AtmSimUI(findModUser);
                }else{
//                    JOptionPane.showMessageDialog(null, "用户名或密码错误");
                    JOptionPane.showMessageDialog(null, "系统处理异常，请联系管理员进行处理");
                }
            }
        } else if (e.getSource() == resetBtn) {
            username.setText(inputUserNameTip);
            password.setText(inputPwdTip);
            password.setEchoChar('\0');
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
