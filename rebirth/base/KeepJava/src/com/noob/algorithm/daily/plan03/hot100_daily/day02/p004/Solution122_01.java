package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 LCR122 路径加密 - https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/
 * 概要：将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
 */
public class Solution122_01 {

    /**
     * 将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
     * 思路分析：字符串切割 随后进行拼接
     */
    public String pathEncryption(String path) {
        String[] strs = path.split("\\.");
        return String.join(" ",strs);
    }

    public static void main(String[] args) {
        Solution122_01 solution = new Solution122_01();
        solution.pathEncryption("......");
    }
}
