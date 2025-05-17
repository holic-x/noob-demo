package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

/**
 * 🟢 LCR122 路径加密 - https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/
 * 概要：将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
 */
public class Solution122_02 {

    /**
     * 思路分析：
     * 基于遍历字符串和字符串拼接的方式处理
     */
    public String pathEncryption(String path) {

        StringBuffer ans = new StringBuffer();
        for (char ch : path.toCharArray()) {
            if (ch == '.') {
                // 遇到`.`替换为空格
                ans.append(" ");
            } else {
                // 遇到其他字符正常拼接
                ans.append(ch);
            }
        }

        // 返回结果
        return ans.toString();

    }

    public static void main(String[] args) {
        Solution122_02 solution = new Solution122_02();
        System.out.println(solution.pathEncryption("......"));
    }
}
