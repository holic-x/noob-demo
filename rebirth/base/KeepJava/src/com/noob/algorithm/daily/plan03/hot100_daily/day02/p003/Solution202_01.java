package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.HashSet;

/**
 * 🟢 202 快乐数 - https://leetcode.cn/problems/happy-number/description/
 */
public class Solution202_01 {

    /**
     * 思路分析：
     * 快乐数：将数替换为其每一个位置上的数字的平方和，重复这个过程：
     * - 可能是无限循环始终变不到1
     * - 可能是结果为1表示为快乐数
     */
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }

        // 定义哈希表存储已经出现过的数字，如果重复出现则说明进入循环
        HashSet<Integer> set = new HashSet<>();
        int nextN = getNextN(n);
        while (nextN != 1) {
            if (set.contains(nextN)) {
                return false;
            }
            set.add(nextN);
            // 更新nextN
            nextN = getNextN(nextN);
        }
        // 跳出循环则说明满足==1条件，为快乐数
        return true;
    }

    // 获取n的下一个数（计算每个位置上的数字的平方和）
    private int getNextN(int n) {
        int sum = 0;
        while (n != 0) {
            // 数位分离处理
            int r = n % 10;
            sum += r * r;
            // 处理完成，截断末位
            n = n / 10;
        }
        // 返回生成的数
        return sum;
    }

}
