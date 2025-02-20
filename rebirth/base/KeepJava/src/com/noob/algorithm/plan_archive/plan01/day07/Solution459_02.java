package com.noob.algorithm.plan_archive.plan01.day07;

/**
 * 🟢 459 重复的子字符串
 */
public class Solution459_02 {

    /**
     * 校验字符串s是否可以由它的子串重复多次构成
     * 1.只校验满足倍数关系的子串（且只需要检验前面一般元素的子串）
     * 2.校验是否满足i∈[n',n)，有 s[i]=s[i−n']
     */
    public boolean repeatedSubstringPattern(String s) {
        int sLen = s.length();
        for (int i = 1; i <= sLen / 2; i++) {
            // 检验长度是否满足倍数关系
            if (sLen % i == 0) {
                boolean mark = true;
                for (int j = i; j < sLen; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        mark = false;
                    }
                }
                // 校验是否满足
                if (mark) {
                    return true;
                }
            }
        }
        return false;
    }

}
