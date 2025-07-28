package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * 🟢 459 重复的子字符串 - https://leetcode.cn/problems/repeated-substring-pattern/description/
 */
public class Solution459_02 {

    /**
     * 校验非空字符串s是否可以由其字串重复多次构成
     * 思路分析：
     * - 子串校验，校验每个子串是否可构成目标字符串
     */
    public boolean repeatedSubstringPattern(String s) {
        // 获取子串（[i,j]）
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String subStr = s.substring(i, j + 1);
                if (validSubStr(subStr, s)) {
                    return true;
                }
            }
        }
        // 匹配不通过
        return false;
    }


    /**
     * 校验子串是否可构成目标字符串
     *
     * @param args
     */
    private boolean validSubStr(String subStr, String s) {
        // 长度判断
        int subLen = subStr.length();
        int sLen = s.length();
        // 如果长度不满足则必然不可能构成
        if (sLen / 2 < subLen) {
            return false;
        }

        // 如果不满足倍数关系则必然不可能构成
        int bs = sLen % subLen;
        if (bs != 0) {
            return false;
        }


        // 字符串处理校验
        int x = sLen / subLen;
        StringBuffer toValidStr = new StringBuffer();
        while (x-- > 0) {
            toValidStr.append(subStr);
        }

        // 校验子串拼接后是否满足条件
        return toValidStr.toString().equals(s);
    }


    public static void main(String[] args) {
        Solution459_02 solution = new Solution459_02();
        System.out.println(solution.repeatedSubstringPattern("abab"));
    }

}
