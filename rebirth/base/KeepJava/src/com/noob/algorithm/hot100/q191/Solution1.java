package com.noob.algorithm.hot100.q191;

/**
 * 191 位1的个数
 */
public class Solution1 {

    public int hammingWeight(int n) {
        // 将十进制转化为二进制数
        String nbin = Integer.toBinaryString(n);
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
