package com.noob.algorithm.solution_archive.leetcode.hot100.q191;

/**
 * 191 位1的个数
 */
public class Solution2 {
    public int hammingWeight(int n) {
        // 将十进制转化为二进制数(自定义实现)
        // String nbin = Integer.toBinaryString(n); 借助工具类实现
        StringBuffer nbin = new StringBuffer();
        while (n > 0) {
            nbin.append(n % 2);
            n = n / 2;
        }

        // 判断设置位个数
        int count = 0;
        int x = nbin.length() - 1;
        while (x >= 0) {
            if (nbin.charAt(x) == '1') {
                count++;
            }
            x--;
        }
        // 返回统计结果
        return count;
    }
}
