package com.noob.algorithm.leetcode.common150.q151;

/**
 * 151 反转字符串中的单词
 */
public class Solution1 {
    /**
     * 思路分析：字符串切割思路：先把单词拆出来，然后逆序遍历单词数组
     */
    public String reverseWords(String s) {
        // 定义结果
        StringBuilder res = new StringBuilder();

        // 字符串切割，拆分单词(注意首尾空格和连续空格的情况)
        s = s.trim(); // 去除首尾空格
        String[] words = s.split("\\s+"); //  s.split(" ") 单个空格切分

        // 逆序遍历，插入结果集
        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i]);
            // 单词补充空格（如果遍历到最后一个单词则不需要补充）
            if (i > 0) {
                res.append(" ");
            }
        }

        // 返回结果
        return res.toString();
    }

    public static void main(String[] args) {
        String str = " hello world ";
        Solution1 solution = new Solution1();
        System.out.println(solution.reverseWords(str));
    }
}
