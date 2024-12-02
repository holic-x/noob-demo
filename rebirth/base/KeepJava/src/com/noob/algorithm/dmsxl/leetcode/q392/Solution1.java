package com.noob.algorithm.dmsxl.leetcode.q392;

/**
 * 392 判断子序列：判断 s 是否为 t 的子序列
 */
public class Solution1 {

    /**
     * 双指针法
     */
    public boolean isSubsequence(String s, String t) {
        int sPointer = 0, tPointer = 0;
        while (sPointer < s.length() && tPointer < t.length()) {
            // 判断s、t当前指针指向元素是否相同
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                // 指针向后移动继续比较下一个元素
                sPointer++;
                tPointer++;
            } else {
                // 如果不相同，则tPointer向前移动
                tPointer++;
            }
        }
        // 比较完成，如果sPointer指向字符串末尾说明完全匹配对应子序列要求
        return sPointer == s.length();
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        // solution1.isSubsequence("abc","ahbgdc");
        solution1.isSubsequence("axc", "ahbgdc");
    }
}
