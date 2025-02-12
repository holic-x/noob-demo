package com.noob.algorithm.daily.plan02.day02.p003;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 202 快乐数 - https://leetcode.cn/problems/happy-number/description/
 */
public class Solution202_01 {

    /**
     * 思路分析：将正整数n，替换为每个位置上的数字的平方之和
     * - 如果重复这个过程得到最终结果为1，那么其为快乐数
     * - 如果重复这个过程陷入死循环，则非快乐数
     */
    public boolean isHappy(int n) {
        // 哈希法：定义哈希表存储已经出现过的数字
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            // 获取下一个校验数
            int nextN = getNext(n);
            if (set.contains(nextN)) {
                // 已经出现过该数，会造成循环，说明非快乐数
                return false;
            }
            set.add(nextN);
            n = nextN; // n 指向下一个数字
        }
        // 满足n==1 跳出循环，说明是快乐数
        return true;
    }

    // 获取正整数n的下一个校验数字（即通过数位分离得到的数字）
    private int getNext(int n) {
        int nextN = 0;
        // 获取n的每个数位的平方之和
        while (n != 0) {
            // 获取个位
            int a = n % 10;
            nextN += a * a; // 累加数位平方和
            // 去除个位
            n = n / 10;
        }
        // 返回
        return nextN;
    }

    public static void main(String[] args) {
        int n = 19;
        Solution202_01 solution = new Solution202_01();
        solution.isHappy(n);
        // 82 68 100 1
    }
}
