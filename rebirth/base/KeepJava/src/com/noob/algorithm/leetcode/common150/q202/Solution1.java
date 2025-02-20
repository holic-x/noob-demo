package com.noob.algorithm.leetcode.common150.q202;

import java.util.HashSet;

/**
 * 202 快乐数
 */
public class Solution1 {

    /**
     * 快乐数校验：
     * 判断 n 通过数位分离、计算平方和之后能否得到1，这个过程中如果出现循环则永远不可能到达1
     */
    public boolean isHappy(int n) {
        // 定义一个哈希集合，存储已经计算过的数字，如果出现非1的循环数字则说明其非快乐数，否则一直往下找，直到找到结果为1
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n); // 将已经计算的元素加入集合
            n = getNext(n); // 获取下一个值
        }
        return n == 1;
    }

    // 根据n获取下一个值（数位分离、计算平方和）
    public int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10; // 取余（每次取最尾部的数字进行分离计算）
            sum += d * d;
            n = n / 10; // 去除尾部继续计算
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println("isHappy:" + solution1.isHappy(7));
    }

}
