package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

/**
 * 🟢 459 重复的子字符串 - https://leetcode.cn/problems/repeated-substring-pattern/description/
 */
public class Solution459_02 {

    /**
     * 校验非空字符串s是否可以由其字串重复多次构成
     * 思路分析：
     */
    public boolean repeatedSubstringPattern(String s) {

        int n = s.length();
        // 获取所有子串
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 截取子串
                String subStr = s.substring(i, j + 1);
                // 校验子串是否可以重复多次构成s
                if (validStr(subStr, s)) {
                    return true;
                }
            }
        }

        return false;
    }


    // 子串校验
    private boolean validStr(String subStr, String s) {
        int sLen = s.length(), subSLen = subStr.length();
        // 校验重复多次是否有效（余数为0）
        if (sLen % subSLen != 0) {
            return false;
        }
        if (subSLen > sLen / 2) {
            return false;
        }

        // 校验子串重复多次可否构成s
        int turn = sLen / subSLen;
        StringBuffer buffer = new StringBuffer();
        while (turn-- > 0) {
            buffer.append(subStr);
        }
        return s.equals(buffer.toString());
    }

    public static void main(String[] args) {
        Solution459_02 solution = new Solution459_02();
        solution.repeatedSubstringPattern("abab");
    }

}
