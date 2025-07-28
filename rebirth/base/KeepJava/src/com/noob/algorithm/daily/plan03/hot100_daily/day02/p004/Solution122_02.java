package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 LCR122 路径加密 - https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/
 * 概要：将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
 */
public class Solution122_02 {

    /**
     * 将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
     * 思路分析：遍历替换
     */
    public String pathEncryption(String path) {
        char[] chs = path.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '.') {
                chs[i] = ' ';
            }
        }
        // 返回替换内容
        return new String(chs);
    }

    public static void main(String[] args) {
        Solution122_02 solution = new Solution122_02();
        solution.pathEncryption("......");
    }
}
