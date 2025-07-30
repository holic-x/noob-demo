package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

/**
 * 🟢 383 赎金信 - https://leetcode.cn/problems/ransom-note/description/
 * 概要：判断 ransomNote 中 的字符 能否由 magazine 构成（均由小写字母构成）
 */
public class Solution383_01 {

    /**
     * 思路分析：
     * 判断  ransomNote 能不能由 magazine 里面的字符构成
     * 计数法：统计字符个数校验出现次数是否一致，此处设定均由小写字母构成则可用int[]来记录，索引表示对应小写字母，对应索引位置元素表示相应出现次数
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 记录magazine字符出现次数
        int[] mCnt = new int[26]; // 针对小写字母
        for (char ch : magazine.toCharArray()) {
            mCnt[ch - 'a']++;
        }

        // 遍历randomNote，校验现存mCnt是否可支撑组成
        for (char ch : ransomNote.toCharArray()) {
            if (mCnt[ch - 'a'] == 0) {
                return false;
            } else {
                mCnt[ch - 'a']--; // 消耗一次
            }
        }

        // 校验通过（满足字符构成支撑）
        return true;
    }

}
