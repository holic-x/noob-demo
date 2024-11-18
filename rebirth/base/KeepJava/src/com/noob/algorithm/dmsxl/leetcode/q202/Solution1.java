package com.noob.algorithm.dmsxl.leetcode.q202;

import java.util.HashSet;
import java.util.Set;

/**
 * 202 快乐数
 */
public class Solution1 {

    public boolean isHappy(int n) {
        // 定义集合存放数字（判断calN是否已经出现过集合中且非1）
        Set<Integer> set = new HashSet<>();
        int getN = calN(n);
        set.add(getN);

        while (getN != 1) {
            getN = calN(getN);
            // 判断是否出现在集合中
            if (set.contains(getN)) {
                return false; // 出现循环
            } else {
                set.add(getN);
            }
        }

        // 跳出循环说明最终为1
        return true;
    }

    public int calN(int n) {
        // 计算 n (每个位数的平方之和)
        int calN = 0;
        while (n != 0) {
            // 获取个位上的数字，拿到平方和
            calN += (n % 10) * (n % 10);
            n = n / 10; // 数位分离继续计算下一个位置
        }
        // 返回计算结果
        return calN;
    }

    //    public static void main(String[] args) {
//        Solution1 solution1 = new Solution1();
//        solution1.isHappy(19);
//    }

}
