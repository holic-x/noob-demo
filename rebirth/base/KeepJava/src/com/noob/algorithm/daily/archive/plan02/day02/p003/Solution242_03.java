package com.noob.algorithm.daily.archive.plan02.day02.p003;

/**
 * 🟢 242 有效的字母异位词 - https://leetcode.cn/problems/valid-anagram/description/
 */
public class Solution242_03 {
    /**
     * 思路分析：判断t是否为s的字母异位词
     * ① 计数法：每个字母的个数一致匹配
     * ② 排序法：排序后的字母序列完全一致
     */
    public boolean isAnagram(String s, String t) {
        // 长度不同，不可能互为字母异位词
        if (s.length() != t.length()) {
            return false;
        }
        // 定义哈希表存储s的每个字母出现次数（可以用map或者数组（因为字母序列有限可以用数组支持快速访问））
        int[] nums = new int[26]; // 定义数组存储每个字符出现的次数（索引与字母`a`-`z`相对照）
        for (char ch : s.toCharArray()) {
            nums[ch - 'a']++;
        }

        // 校验字符串序列t
        for (char ch : t.toCharArray()) {
            // 预使用字符
            nums[ch - 'a']--;
            // 校验使用后字符是否满足限定要求
            if (nums[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
