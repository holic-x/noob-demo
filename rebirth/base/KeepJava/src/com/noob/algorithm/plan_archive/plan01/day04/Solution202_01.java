package com.noob.algorithm.plan_archive.plan01.day04;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢202 快乐数
 */
public class Solution202_01 {


    /**
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，重复过程直到数字变为1
     * - 如果变为1则为快乐数
     * - 如果一直循环，则说明非快乐数
     * 定义一个集合存储可能出现的数字集合，如果发现数组循环出现则说明为非快乐数
     */
    public boolean isHappy(int n) {
        // 构建辅助集合存储计算过程中可能出现的元素
        Set<Integer> set = new HashSet<>();
        if (n == 1) {
            return true;
        }
        // 当n不为1，一直调用getNext方法获取快乐数
        while (n != 1) {
            if (set.contains(n)) {
                return false; // 出现循环
            }
            // 将遍历元素加入结果集，并计算下一个数，直到出现1或者循环数字出现
            set.add(n);
            n = getNext(n);
        }

        // 循环跳出说明出现了n=1，满足快乐数要求
        return true;

    }

    /**
     * 根据元素x获取其下一个元素（即x会被替换为每个位置上的数字的平方和）
     * 1.数位分离
     * 2.字符串转化遍历处理
     */
    public static int getNext(int x) {
        int next = 0;
        // 数学操作：对10求余取得尾数，然后下一步除以10取出尾数进入下一步计算，直到值为0
        while (x != 0) { // 或者while(x>0)
            int mod = x % 10; // 获取尾数
            next += mod * mod;// 累加数字对应的平方
            x = x / 10; // 取出尾数，计算下一个数位
        }
        // 返回得到的下一个数字
        return next;
    }

    public static void main(String[] args) {
        System.out.println(Solution202_01.getNext(12));
    }


}
