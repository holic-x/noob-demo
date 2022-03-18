package atm.ui;

import atm.mod.ModDataGen;
import atm.mod.ModUser;
import utils.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * ATM操作主页面
 */
public class AtmSimUI extends JFrame implements MouseListener{
    /**
     * 定义全局组件
     */
    // 用户信息
    private ModUser modUser;

    // 背景面板设置
    private JPanel backgroundPanel = null;

    // 输入提示
    private JLabel operTip = null;

    // 操作按钮组合定义
    private JButton checkBtn =  null;
    private JButton drawBtn =  null;
    private JButton saveBtn =  null;
    private JButton modifyPwdBtn =  null;

    // 在构造方法中初始化组件并设置布局
    public AtmSimUI(ModUser modUser) {
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

        // 添加操作提示
        operTip = new JLabel("请选择要进行的操作",SwingConstants.CENTER);
        operTip.setFont(new Font("华文行楷", Font.PLAIN, 20));
        operTip.setBounds(290, 120, 245, 45);

        // 操作按钮组合定义
        checkBtn = new JButton("查询余额");
        checkBtn.setToolTipText("查询余额");
        checkBtn.setBounds(200, 200, 100, 30);


        drawBtn = new JButton("取款");
        drawBtn.setToolTipText("取款");
        drawBtn.setBounds(200, 250, 100, 30);


        saveBtn = new JButton("存款");
        saveBtn.setToolTipText("存款");
        saveBtn.setBounds(550, 200, 100, 30);


        modifyPwdBtn = new JButton("修改密码");
        modifyPwdBtn.setToolTipText("修改密码");
        modifyPwdBtn.setBounds(550, 250, 100, 30);


        // 为指定组件添加监听事件
        checkBtn.addMouseListener(this);
        drawBtn.addMouseListener(this);
        saveBtn.addMouseListener(this);
        modifyPwdBtn.addMouseListener(this);

        // 将组件加载到面板中
        backgroundPanel.add(operTip);
        backgroundPanel.add(checkBtn);
        backgroundPanel.add(drawBtn);
        backgroundPanel.add(saveBtn);
        backgroundPanel.add(modifyPwdBtn);

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
     * 添加鼠标点击事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == checkBtn) {
            check();
        } else if (e.getSource() == drawBtn) {
            try {
                draw();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }else if (e.getSource() == saveBtn) {
            try {
                save();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }else if (e.getSource() == modifyPwdBtn) {
            modifyPwd();
        }else{
            JOptionPane.showMessageDialog(null, "无效的按钮事件监听！");
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
     * 按钮功能事件处理:查询余额
     */
    private void check(){
        List<ModUser> modUserList = ModDataGen.modUserList();
        String currentUserName = this.modUser.getUserName();
        for (ModUser user : modUserList) {
            if(currentUserName.equals(user.getUserName())){
                JOptionPane.showMessageDialog(this,"您当前账户余额为：￥"+user.getAmount());
            }
        }
    }

    /**
     * 按钮功能事件处理:取款
     */
    private void draw() throws InterruptedException {
        List<ModUser> modUserList = ModDataGen.modUserList();
        String currentUserName = this.modUser.getUserName();
        String drawAmountStr = JOptionPane.showInputDialog(this,"请输入取款金额（必须为100倍数）,总金额不超过5000","100");
        if(drawAmountStr==null||drawAmountStr.equals("")){
            JOptionPane.showMessageDialog(this,"您取消了当前操作");
            return;
        }
        int drawAmount = Integer.valueOf(drawAmountStr);
        if(drawAmount%100!=0){
            JOptionPane.showMessageDialog(this,"取款金额必须为100的倍数");
            return;
        }
        if(drawAmount<0){
            JOptionPane.showMessageDialog(this,"取款金额不能为负数");
            return;
        }
        if(drawAmount>5000){
            JOptionPane.showMessageDialog(this,"取款金额不能超出5000");
            return ;
        }
        // 取款金额校验正常，执行模拟取款操作
        for (ModUser user : modUserList) {
            if(currentUserName.equals(user.getUserName())){
                JOptionPane.showMessageDialog(this,"您当前账户余额为：￥"+user.getAmount()+"，本次取出￥"+drawAmount+"系统正在操作请稍后");
                // 更新用户金额
                user.setAmount(user.getAmount().multiply(new BigDecimal(drawAmount)));
                // 模拟取款操作延时1s
                Thread.sleep(1000);
                JOptionPane.showMessageDialog(this,"取款成功，你当前剩余余额为"+user.getAmount());
            }
        }
    }

    /**
     * 按钮功能事件处理:存款
     */
    private void save() throws InterruptedException {
        List<ModUser> modUserList = ModDataGen.modUserList();
        String currentUserName = this.modUser.getUserName();
        String saveAmountStr = JOptionPane.showInputDialog(this,"请输入存款金额（必须为100倍数）,不能为负数，单次操作不能超过10000","100");
        if(saveAmountStr==null||saveAmountStr.equals("")){
            JOptionPane.showMessageDialog(this,"您取消了当前操作");
            return;
        }
        int saveAmount = Integer.valueOf(saveAmountStr);
        if(saveAmount%100!=0){
            JOptionPane.showMessageDialog(this,"存款金额必须为100的倍数");
            return;
        }
        if(saveAmount<0){
            JOptionPane.showMessageDialog(this,"存款金额不能为负数");
            return;
        }
        if(saveAmount>10000){
            JOptionPane.showMessageDialog(this,"存款金额不能超出10000");
            return ;
        }
        // 取款金额校验正常，执行模拟取款操作
        for (ModUser user : modUserList) {
            if(currentUserName.equals(user.getUserName())){
                JOptionPane.showMessageDialog(this,"您当前账户余额为：￥"+user.getAmount()+"，本次存入￥"+saveAmount+"系统正在操作请稍后");
                // 更新用户金额
                user.setAmount(user.getAmount().add(new BigDecimal(saveAmount)));
                // 模拟存款操作延时1s
                Thread.sleep(1000);
                JOptionPane.showMessageDialog(this,"存款成功，你当前剩余余额为"+user.getAmount());
            }
        }
    }

    /**
     * 按钮功能事件处理:修改密码
     */
    private void modifyPwd(){
        new ModifyInfoUI(this.modUser);
    }

}
