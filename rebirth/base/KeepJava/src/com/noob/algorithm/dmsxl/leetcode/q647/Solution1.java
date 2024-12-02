package com.noob.algorithm.dmsxl.leetcode.q647;

/**
 * 647 回文子串
 */
public class Solution1 {
    // 暴力搜索：嵌套循环判断每一个子串是否为回文字符串
    public int countSubstrings(String s) {
        int cnt = 0;
        // 遍历判断每一个子串是否为回文字符串（i确定子串起点；j确定子串终点）
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String subStr = s.substring(i, j + 1); // 注意substring截断位置[i,j)
                if (validHuiwen(subStr)) {
                    cnt++;
                }
            }
        }
        // 返回结果
        return cnt;
    }

    // 双指针法校验回文
    public boolean validHuiwen(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            // 如果对应位置匹配则继续遍历下一位，如果不匹配则说明非回文
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            // 匹配则继续遍历下一个为止
            left++;
            right--;
        }
        // 校验通过
        return true;
    }

}
