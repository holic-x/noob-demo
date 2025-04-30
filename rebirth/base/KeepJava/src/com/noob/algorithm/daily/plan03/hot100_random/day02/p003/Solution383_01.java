package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

/**
 * 🟢 383 赎金信 - https://leetcode.cn/problems/ransom-note/description/
 */
public class Solution383_01 {

    /**
     * 思路分析：判断 ransomNote 中 的字符 能否由 magazine 构成（均由小写字母构成）
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 统计magazine中字符出现次数
        int[] mCnt = new int[26];
        for (char ch : magazine.toCharArray()) {
            mCnt[ch - 'a']++; // 统计字符出现次数
        }

        // 校验ransomNote是否可由指定字符构成
        for (char ch : ransomNote.toCharArray()) {
            if (mCnt[ch - 'a'] <= 0) {
                // 剩余字符不足
                return false;
            }
            mCnt[ch - 'a']--; // 使用掉一个字符
        }
        // 校验通过
        return true;
    }

}
