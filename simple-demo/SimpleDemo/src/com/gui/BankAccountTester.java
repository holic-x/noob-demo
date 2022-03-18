package com.gui;

import java.util.Scanner;

/**
 * 银行账户操作模拟
 */
class BankAccount {

    // 余额定义
    private double amount;

    // 操作账单定义
    private StringBuffer operInfo = new StringBuffer();

    // 默认构造函数
    public BankAccount() {
        this.amount = 0;
    }

    // 初始化金额构造函数
    public BankAccount(double amount) {
        this.amount = amount;
    }

    // 存款
    public void deposit(double money) {
        this.amount += money;
        System.out.println("successful operator,your current amount is " + this.amount);
        operInfo.append("|          deposit           |          " + money + "          |" + "\n");
    }

    // 取款
    public void withdraw(double money) {
        if (this.amount < money) {
            System.out.println("Insufficient current balance");
            return;
        }
        this.amount -= money;
        System.out.println("successful operator,your current amount is " + this.amount);
        operInfo.append("|          withdraw          |          " + money + "          |" + "\n");
    }

    // 打印报表
    public void printStatement() {
        System.out.println("current amount:" + this.amount);
        System.out.println("print Statement");
        System.out.println("|          operator          |          Statement          |");
        System.out.println(operInfo.toString());
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

// 创建Tester类
public class BankAccountTester {


    public static void main(String[] args) {
        // 初始化资金1000.00
        BankAccount myBandBankAccount = new BankAccount(1000.00);

        // 初始化菜单
        System.out.println("*************************************");
        System.out.println("   welcome~ please choose:");
        System.out.println("   1.printStatement");
        System.out.println("   2.deposit");
        System.out.println("   3.withdraw");
        System.out.println("   4.exit");
        System.out.println("*************************************");

        Scanner scanner = new Scanner(System.in);
        // 初始化参数
        int operFlag = 0;
        while (operFlag != 4) {
            System.out.print("please choose:");
            switch (scanner.nextInt()) {
                case 1: {
                    myBandBankAccount.printStatement();
                    break;
                }
                case 2: {
                    System.out.print("please input your amount:");
                    double operAmount = scanner.nextDouble();
                    myBandBankAccount.deposit(operAmount);
                    break;
                }
                case 3: {
                    System.out.print("please input your amount:");
                    double operAmount = scanner.nextDouble();
                    myBandBankAccount.withdraw(operAmount);
                    break;
                }
                case 4: {
                    System.out.println("exit");
                    operFlag = 4;
                    break;
                }
            }
        }
    }

}
