package com.noob.algorithm.daily.plan03.hot100_daily.day07.p020;

/**
 * 🟡 738 单调递增的数字 - https://leetcode.cn/problems/monotone-increasing-digits/description/
 */
public class Solution738_01 {

    /**
     * 思路分析：
     * 贪心思路：逆序寻找非单调递增的位置（nums[i] > nums[i+1]），记录该位置idx，该位置元素-1，并将该位置后面元素都置为9
     */
    public int monotoneIncreasingDigits(int n) {

        // 将整数转化为字符数组便于处理
        char[] nCh = String.valueOf(n).toCharArray();

        // 逆序处理不满足单调递增的位置，并记录最后一个不满足单调递增的位置idx，将idx+1位置开始的元素都置为9
        int idx = -1;
        for (int i = nCh.length - 2; i >= 0; i--) {
            if (nCh[i] > nCh[i + 1]) {
                nCh[i]--;
                idx = i; // 记录不满足单调递增的位置idx
            }
        }

        // 校验idx
        if (idx == -1) {
            return n; // 说明此时n均为单调递增，则自身即为所得
        }

        // idx!=-1: 将idx+1开始的位置所有元素置为9
        for (int i = idx + 1; i < nCh.length; i++) {
            nCh[i] = '9';
        }
        // 返回结果
        return Integer.valueOf(new String(nCh));

    }

}
