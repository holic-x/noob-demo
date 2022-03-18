package ui.control;

import utils.MyFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ForeIndexJFrame extends JFrame implements MouseListener{
    /**
     * 定义全局组件
     */
    // 背景面板设置
    private JPanel backgroundPanel = null;

    /**
     * 候选人录入面板相关组件定义
     */
    private JPanel checkCandidatePanel = null;

    // 输入提示
    private JLabel voteTip = null;
    // 候选人名单列表相关组件定义
    private JTextField candidateList  = null;
    private String[] candidateNameList = null;
    private JCheckBox[] checkCandidateGroup = null;
    private JButton checkCandidateBtn =  null;
    private JButton resetCandidateBtn =  null;

    /**
     * 选举操作面板相关组件定义
     */
    private JPanel votePanel = null;
    private JButton voteBtn =  null;
    private JButton refreshBtn =  null;
    private JButton sortBtn =  null;

    /**
     * 选举结果面板相关组件定义
     */
    private JPanel voteResPanel = null;
    private JTextField voteRes  = null;
    JTextArea voteResInfo = new JTextArea();

    /**
     * 定义全局变量记录投票信息
     */
    private int totalVote = 0;
    private String[] voteNameList = null;
    private Integer[] voteNumList = null;

    // 投票信息统计
    private int validNum = 0;
    private int invalidNum = 0;
    private int abandonNum = 0;

    /**
     *  初始化候选人录入面板
     */
    private void initCheckCandidateUI(){

        checkCandidatePanel = new JPanel();
        checkCandidatePanel.setLayout(new GridLayout(4,1));

        JLabel sysNameLabel = new JLabel("简易投票系统",SwingConstants.CENTER);
        sysNameLabel.setFont(MyFont.JLabelFont);

        voteTip = new JLabel("首先输入候选人的名字（人数不超过10，名字之间用空格进行分隔）",SwingConstants.CENTER);
        voteTip.setFont(MyFont.JTableFont);

        // 候选人列表组件定义
        candidateList = new JTextField(30);
        candidateList.setFont(MyFont.JTableFont);

        JPanel btnPanel = new JPanel();
        checkCandidateBtn = new JButton("确认");
        checkCandidateBtn.setSize(30,30);

        resetCandidateBtn = new JButton("取消");
        resetCandidateBtn.setSize(30,30);

        JLabel checkTip = new JLabel("用下面的选择框统计选票");
        btnPanel.add(checkCandidateBtn);
        btnPanel.add(resetCandidateBtn);
        btnPanel.add(checkTip);

        // 为指定组件添加监听事件
        checkCandidateBtn.addMouseListener(this);
        resetCandidateBtn.addMouseListener(this);

        // 将组件加载到面板中
        checkCandidatePanel.add(sysNameLabel);
        checkCandidatePanel.add(voteTip);
        checkCandidatePanel.add(candidateList);
        checkCandidatePanel.add(btnPanel);
    }


    /**
     * 重置投票数据(在初始化投票系统时调用)
     */
    private void initVoteData(){
        totalVote = 0;
        voteNameList = null;
        voteNumList = null;
        validNum = 0;
        invalidNum = 0;
        abandonNum = 0;
        // 清空投票结果文本提示栏信息
        voteRes.setText("暂无统计信息，请开始新的投票");
        voteResInfo.setText("暂无统计信息");
    }

    /**
     *  初始化选举操作面板
     *  （每次录入候选人名单点击确认表示重置投票系统）
     */
    private void initVoteUI(String oper){
        votePanel = new JPanel();
        votePanel.setLayout(new GridLayout(2,1));
        // 默认10个候选人补位
        JPanel candidateShowPanel = new JPanel();
        candidateShowPanel.setLayout(new GridLayout(2,5));
        if(candidateNameList!=null&&candidateNameList.length!=0){
            int candidateNum = candidateNameList.length;
            // 校验参选人数是否有效
            if(candidateNum>10){
                JOptionPane.showMessageDialog(null, "参选人数超出10人，请确认后再执行！");
            }else{
                if("init".equals(oper)){
                    // 初始化重置操作，重置投票数据
                    initVoteData();
                    voteNameList = new String[candidateNum];
                    voteNumList = new Integer[candidateNum];
                    checkCandidateGroup = new JCheckBox[candidateNum];
                    for (int i=0;i<candidateNum;i++) {
                        JCheckBox defaultCheckbox = new JCheckBox(candidateNameList[i]);
                        voteNameList[i] = candidateNameList[i];
                        voteNumList[i] = 0;
                        checkCandidateGroup[i] = defaultCheckbox;
                        candidateShowPanel.add(defaultCheckbox);
                    }
                }else if("refresh".equals(oper)){
                    // 更新操作，保留原始数据，单纯更新组件信息
                    checkCandidateGroup = new JCheckBox[candidateNum];
                    for (int i=0;i<candidateNum;i++) {
                        JCheckBox defaultCheckbox = new JCheckBox(candidateNameList[i]);
                        checkCandidateGroup[i] = defaultCheckbox;
                        candidateShowPanel.add(defaultCheckbox);
                    }
                }
                JOptionPane.showMessageDialog(null, "名单处理成功！");
            }
        }else{
            // 默认初始化配置
            candidateShowPanel.add(new JLabel("暂无候选人列表信息"));
        }

        JPanel btnPanel = new JPanel();
        voteBtn = new JButton("投票");
        refreshBtn = new JButton("刷新");
        sortBtn = new JButton("排序");
        btnPanel.add(voteBtn);
        btnPanel.add(refreshBtn);
        btnPanel.add(sortBtn);

        // 为组件添加监听事件：文本框添加焦点监听事件、按钮添加鼠标点击监听事件
        voteBtn.addMouseListener(this);
        refreshBtn.addMouseListener(this);
        sortBtn.addMouseListener(this);

        // 将相关组件装载到指定面板
        votePanel.add(candidateShowPanel);
        votePanel.add(btnPanel);
    }

    /**
     * 刷新投票面板
     */
    public void refreshVoteUI(String oper) {
        // 移除当前数据面板中的所有数据
        backgroundPanel.remove(votePanel);
        this.initVoteUI(oper);
        backgroundPanel.add(votePanel,BorderLayout.CENTER);
        backgroundPanel.validate();// 验证
//        backgroundPanel.repaint();
    }

    /**
     *  初始化选举结果面板
     */
    private void initVoteResUI(){

        voteResPanel = new JPanel();
        voteResPanel.setLayout(new GridLayout(2,1));

        // 选举结果定义
        JPanel resPanel = new JPanel();
        JLabel voteResTip = new JLabel("选举结果");

        // 选举结果数据展示
        voteRes  = new JTextField(50);
        voteRes.setFont(MyFont.JTableFont);
        resPanel.add(voteResTip);
        resPanel.add(voteRes);
        resPanel.setEnabled(false);

        // 选举人明细数据展示
        JScrollPane voteDetailPanel = new JScrollPane();
//        JPanel voteDetailPanel = new JPanel();
        if(voteNameList!=null&&voteNameList.length!=0){
            // 重置文本信息
            voteResInfo.setText("");
            voteRes.setText("已经统计了："+totalVote+"张选票，其中弃权票："+abandonNum+" 作废票："+invalidNum);
            for(int i=0;i<voteNameList.length;i++){
//                JTextField tmpJTextField = new JTextField(voteNameList[i]+"得票数："+voteNumList[i]);
//                voteDetailPanel.add(tmpJTextField);
//                voteDetailPanel.setViewportView(tmpJTextField);
                // 编辑文本信息
                voteResInfo.append("【"+voteNameList[i]+"】得票数："+voteNumList[i]+"\r\n");
            }
            voteDetailPanel.setViewportView(voteResInfo);
        }else{
            JTextField tmpJTextField = new JTextField("暂无统计结果");
            System.out.println("暂无统计结果");
        }

        // 将相关组件装载到指定面板
        voteResPanel.add(resPanel);
        voteResPanel.add(voteDetailPanel);
    }

    // 在构造方法中初始化组件并设置布局
    public ForeIndexJFrame() {
        // 设置窗体标题、图标
        try {
            Image logoImage = null;
            logoImage = ImageIO.read(new File("icons/logo.png"));
            this.setIconImage(logoImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundPanel = new JPanel();

        /**
         * a.简易投票系统展示，分别加载选举人录入&选举面板
         * b.添加组件监听事件完善功能
         */
        initCheckCandidateUI();
        initVoteUI("init");
        initVoteResUI();

        // 将相关所有的组件加载到背景面板(重绘面板保证组件位置)
        backgroundPanel.setLayout(new BorderLayout(10,5));
        backgroundPanel.add(checkCandidatePanel,BorderLayout.NORTH);
        backgroundPanel.add(votePanel,BorderLayout.CENTER);
        backgroundPanel.add(voteResPanel,BorderLayout.SOUTH);

        // 将背景面板装载到窗体中，并设置窗体的属性
        this.add(backgroundPanel);
        // 设置窗体的相关属性
        this.setTitle("简易投票系统");// 设置窗体标题
        this.setSize(1060, 600);// 设置窗体大小
        this.setVisible(true);// 设置可见性
        this.setLocationRelativeTo(null);// 设置依赖项
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭方式
        this.setResizable(false);// 设置禁止最大化
        this.requestFocus();// 设置格式化窗体
    }

    /**
     * 根据录入名单填充候选人列表信息
     */
    private void initCandidateList(){
        String candidateListText = candidateList.getText();
        // 特殊符号切割
        StringTokenizer st = new StringTokenizer(candidateListText," ");
//        String[] candidateNameList = candidateListText.split(" ");
        int count=0;
        String[] strList = candidateListText.split(" ");
        while (st.hasMoreElements()){
            strList[count] = (String) st.nextToken();
            count++;
        }
        // 候选人名单更新初始化投票面板
        candidateNameList = strList;
        refreshVoteUI("init");
    }

    /**
     * 校验投票信息并刷新记录
     */
    private void validVote(){
        // 获取选票信息
        int voteNum = 0;
        ArrayList<String> voteInfo = new ArrayList<>();
        if(checkCandidateGroup==null||checkCandidateGroup.length==0){
            // 无有效的投票记录
            JOptionPane.showMessageDialog(null, "未检测到有效的投票操作");
            return ;
        }
        for(int i=0;i<checkCandidateGroup.length;i++){
            if(checkCandidateGroup[i].isSelected()){
                // 校验选票信息
                voteNum ++ ;
                voteInfo.add(checkCandidateGroup[i].getText());
            }
        }

        // 校验选票有效性
        totalVote++;
        if(voteNum==0){
            abandonNum++;
        }else if(voteNum>3){
            invalidNum++;
        }else{
            validNum++;
            // 数据有效填充投票信息，刷新记录
            for(int i=0;i<voteNameList.length;i++){
                if(voteInfo.contains(voteNameList[i])){
                    voteNumList[i]++;
                }
            }
        }

        // 移除当前数据面板中的所有数据
        backgroundPanel.remove(voteResPanel);
        this.initVoteResUI();
        backgroundPanel.add(voteResPanel,BorderLayout.SOUTH);
        backgroundPanel.validate();// 验证
//        backgroundPanel.repaint();
    }

    /**
     * 排序
     */
    private void sort(){
        // 根据当前选票信息对候选人列表进行排序处理
        if(voteNameList==null||voteNameList.length==0){
            JOptionPane.showMessageDialog(null, "未检测到有效的投票名单，无法执行排序操作");
            return ;
        }
        for(int i=0;i<voteNameList.length;i++){
            for(int j=i+1;j<voteNameList.length;j++){
                if(voteNumList[j]>voteNumList[i]){
                    // 交换位置
                    String tmpName = voteNameList[i];
                    int tmpNum = voteNumList[i];
                    voteNameList[i] = voteNameList[j];
                    voteNumList[i] = voteNumList[j];
                    voteNameList[j] = tmpName;
                    voteNumList[j] = tmpNum;
                }
            }
        }
        // 重新初始化候选人名单
        this.candidateNameList = voteNameList;
        refreshVoteUI("refresh");
    }

    /**
     * 添加鼠标点击事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == checkCandidateBtn) {
            String candidateListText = candidateList.getText();
            if (candidateListText.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入有效的候选人名单");
            } else{
                // 校验候选人名单信息，填充候选人列表数据
                initCandidateList();
            }
        } else if (e.getSource() == resetCandidateBtn) {
           // 重置候选人录入名单信息（单纯重置文本内容，不作任何功能触发）
            candidateList.setText("");
        }else if (e.getSource() == voteBtn) {
            // 投票按钮触发选举结果面板刷新
            validVote();
        }else if (e.getSource() == refreshBtn) {
            // 刷新按钮触发
            System.out.println("刷新点击");
        }else if (e.getSource() == sortBtn) {
            // 排序按钮触发
            sort();
        }else{
            System.out.println("无效点击");
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
