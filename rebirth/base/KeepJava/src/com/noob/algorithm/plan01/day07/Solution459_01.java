package com.noob.algorithm.plan01.day07;

/**
 * 🟢 459 重复的子字符串
 */
public class Solution459_01 {

    /**
     * 校验字符串s是否可以由它的子串重复多次构成
     */
    public boolean repeatedSubstringPattern(String s) {
        // 校验每个可能的子串
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String subStr = s.substring(i, j + 1);
                if (!subStr.equals(s) && valid(subStr, s)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 校验subStr子串是否可以构成s
    public static boolean valid(String subStr, String s) {
        int subLen = subStr.length(), sLen = s.length();

        if (sLen % subLen != 0) {
            return false; // 如果长度不适配则不满足
        }
        // 校验subStr子串是否可以构成s
        StringBuffer sb = new StringBuffer();
        int x = sLen / subLen;
        while (x-- > 0) {
            sb.append(subStr);
        }
        return s.equals(sb.toString());
    }

    public static void main(String[] args) {
        String str = "aba";
        Solution459_01 s = new Solution459_01();
        s.repeatedSubstringPattern(str);
    }

}
