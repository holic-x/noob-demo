package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 202 快乐数 - https://leetcode.cn/problems/happy-number/description/
 * 概要：对于每个正整数，每次将该数替换为它每个位置上的数字的平方和，重复这个过程直到数变为1（也可能是无限循环始终变不到1），如果结果为1则为快乐数
 */
public class Solution202_01 {

    /**
     * 思路分析：
     * 从初始数字开始，计算其下一个数nextN，随后判断数字是否出现过，如果出现过则说明会陷入循环
     */
    public boolean isHappy(int n) {
        // 定义集合存储已经出现过的数字
        Set<Integer> set = new HashSet<>();
        // 当没有出现数字1则一直循环寻找
        while (n != 1) {
            n = nextN(n);
            // 校验数字是否已经出现过
            if (set.contains(n)) {
                return false; // 陷入循环，非快乐数
            }
            set.add(n);
        }

        // 正常跳出循环，验证为快乐数
        return true;
    }

    // 数位分离：获取n的下一个数
    private int nextN(int n) {
        int ans = 0;
        while (n != 0) {
            int x = n % 10; // 获取个位数字
            ans += x * x; // 累计数位平方和
            n = n / 10; // 截取掉个位数，继续进行运算
        }
        // 返回结果
        return ans;
    }
}
