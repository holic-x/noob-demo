package com.algorithm.bankerAlgorithm;

import java.util.Scanner;

public class BankerAlgorithmDemoTest {

    /**
     * 自定义方法初始化资源状态表参数
     */
    private static ResourceState initResourceStateAuto(int processNum, int resourceNum) {
        // 下述为根据用户输入选择，测试银行家算法
        int max[][] = new int[processNum][resourceNum];
        int allocation[][] = new int[processNum][resourceNum];
        int available[] = new int[processNum];
        // need初始化（剩余需求量 =max[][]-allocation[][]）
        int need[][] = new int[processNum][resourceNum];
        for (int i = 0; i < processNum; i++) {
            for (int j = 0; j < resourceNum; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        // 初始化状态表
        ResourceState rs = new ResourceState(processNum, resourceNum, max, allocation, available, need);
        // 打印参数
        rs.showData();
        return rs;
    }

    /**
     * 自定义方法初始化资源状态表参数(系统自动生成参数)：
     * 考虑参数随机生成具备不确定性，此处考虑调整为自定义参数设定
     */
    private static ResourceState initResourceStateParamAuto(ResourceState rs) {
        // 待完善（随机数据生成）
        Scanner sc = new Scanner(System.in);
        int max[][] = new int[rs.getProcessNum()][rs.getResourceNum()];
        rs.setMax(max);

        int allocation[][] = new int[rs.getProcessNum()][rs.getResourceNum()];
        rs.setAllocation(allocation);

        int available[] = new int[rs.getResourceNum()];
        rs.setAvailable(available);

        // 更新need
        int need[][] = new int[rs.getProcessNum()][rs.getResourceNum()];
        for (int i = 0; i < rs.getProcessNum(); i++) {
            for (int j = 0; j < rs.getResourceNum(); j++) {
                need[i][j] = rs.getMax()[i][j] - rs.getAllocation()[i][j];
            }
        }
        rs.setNeed(need);

        // 显示T0状态下各类资源的相关数据
        System.out.println("显示T0状态下各类资源的相关数据如下");
        rs.showData();
        return rs;
    }

    /**
     * 自定义方法初始化资源状态表参数(人工输入)
     */
    private static ResourceState initResourceStateParamManual(ResourceState rs) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请按照进程顺序输入Max（各进程对每类资源的最大需求量）：");
        for (int i = 0; i < rs.getProcessNum(); i++) {
            System.out.print("请输入第P" + i + "个进程对应的max值：");
            // 定义字符串数组用以接收数据
            String[] st = sc.next().split("-");
            for (int j=0;j<rs.getResourceNum();j++) {  // 强制类型转化
                rs.getMax()[i][j] = Integer.valueOf(st[j]);
            }
        }

        System.out.println("请按照进程顺序输入allocation（各进程对每类资源当前的占有量）：");
        for (int i = 0; i < rs.getProcessNum(); i++) {
            System.out.print("请输入第P" + i + "个进程对应的allocation值");
            String[] st = sc.next().split("-");  //定义字符数组用以接收数据
            for (int j=0;j<rs.getResourceNum();j++) {
                rs.getAllocation()[i][j] = Integer.valueOf(st[j]);
            }

        }

        System.out.println("请依次输入available（当前系统的各类资源的剩余量）：");
        String[] st = sc.next().split("-");  // 定义字符数组用以接收数据
        for (int j=0;j<rs.getResourceNum();j++) {
            rs.getAvailable()[j]=Integer.valueOf(st[j]);
        }


        // 更新need
        int need[][] = new int[rs.getProcessNum()][rs.getResourceNum()];
        for (int i = 0; i < rs.getProcessNum(); i++) {
            for (int j = 0; j < rs.getResourceNum(); j++) {
                need[i][j] = rs.getMax()[i][j] - rs.getAllocation()[i][j];
            }
        }
        rs.setNeed(need);

        // 显示T0状态下各类资源的相关数据
        System.out.println("显示T0状态下各类资源的相关数据如下");
        rs.showData();
        return rs;
    }


    public static void main(String[] args) {

        // 下述为根据用户输入选择，测试银行家算法
        int request[] = null;
        Scanner sc = new Scanner(System.in);

        /**
         * 1.请求用户输入自定义进程数量、资源种类(用于初始化各类数组)
         */
        System.out.print("请输入自定义进程数量:");
        int processNum = sc.nextInt();
        System.out.print("\n请输入自定义资源种类:");
        int resourceNum = sc.nextInt();
        /**
         * 2.根据指定参数动态初始化资源状态表，打印状态表信息
         */
        ResourceState rs = initResourceStateAuto(processNum, resourceNum);

        /**
         * 3.根据用户选择处理资源状态表数据
         * 资源状态表数量可随机生成或者通过用户手工输入
         */
        System.out.println("请选择操作（进行资源状态表数量初始化）：1.随机函数生成；2.手动输入");
        int chooseNum = sc.nextInt();
        switch (chooseNum) {
            case 1: {
                // rs = initResourceStateParamAuto(rs);
                System.out.println("随机初始化数据");
                break;
            }
            case 2: {
                // 手动输入初始化参数
                rs = initResourceStateParamManual(rs);
                break;
            }
            default: {
                System.err.println("无效操作！");
            }
        }

        // 循环操作选择
        int choose = 0;
        /**
         * 4.进程操作
         */
        System.out.println("请输入所要操作的进程序号（起始值从0开始）:");
        int p = sc.nextInt();
        System.out.println("请依次输入该进程申请的各类资源数据:");
        // 定义字符数组用以接收数据
        String[] st1 = sc.next().split("-");
        //将字符数组强制转化为int型数组(定义int型数组，用以接收char型数组)
        int req[] = new int[rs.getResourceNum()];
        int r = 0;
        for (String s : st1) {
            int m = Integer.parseInt(s);
            req[r] = m;
            r++;
        }
        request = req;

        // 创建一个银行家测试对象bad
        BankerAlgorithmDemo bad = new BankerAlgorithmDemo();
        // 调用银行家算法进行测试
        bad.callBankerAlgorithm(rs, p, request);
        System.out.println("调用银行家算法和安全性算法后各资源的数据显示如下（成功/终断）");
        rs.showData();

        System.out.println("是否还要进行下一次资源分配？  1.继续下一次资源分配   2.退出程序");
        choose = sc.nextInt();
        while (choose == 1) {
            // 重新开辟空间保存原来的对象，用以进行下一步操作
            // ResourceState newRs = new ResourceState(rs);
            System.out.println("请开始进行下一次资源分配... ...");
            System.out.println("请输入所要操作的进程序号（起始值从0开始）:");
            int operProcessTag = sc.nextInt();
            System.out.println("请依次输入该进程申请的各类资源数据:");
            // 定义字符数组用以接收数据
            String[] st2 = sc.next().split("-");
            for (int i=0;i<rs.getResourceNum();i++) {
                request[i] = Integer.parseInt(st2[i]);
            }
            System.out.println("显示T0状态下各类资源的相关数据如下");
            rs.showData();
            // 调用银行家算法进行测试
            bad.callBankerAlgorithm(rs, operProcessTag, request);
            System.out.println("调用银行家算法和安全性算法后各资源的数据显示如下（成功/终断）");
            rs.showData();
            System.out.println("是否还要进行下一次资源分配？  1.继续下一次资源分配   2.退出程序");
            choose = sc.nextInt();
        }
    }
}
