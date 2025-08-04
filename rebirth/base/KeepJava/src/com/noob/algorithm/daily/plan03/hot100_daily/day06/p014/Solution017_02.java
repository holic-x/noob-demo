package com.noob.algorithm.daily.plan03.hot100_daily.day06.p014;

import java.util.*;

/**
 * 🟡 017 电话号码的字母组合 - https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 */
public class Solution017_02 {

    Map<Character, String> map = new HashMap<Character, String>() {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    /**
     * 思路分析：
     */
    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) {
            return Collections.emptyList();
        }
        backTrack(digits.toCharArray(), 0);
        return ans;
    }

    private List<String> ans = new ArrayList<>();
    private StringBuffer path = new StringBuffer();

    private void backTrack(char[] digits, int idx) {
        if (idx == digits.length) {
            // 记录值
            ans.add(new String(path.toString()));
            return;
        }

        // 处理当前遍历位置的每个字符元素
        char[] chs = map.get(digits[idx]).toCharArray();
        for (char c : chs) {
            path.append(c); // 尝试处理
            backTrack(digits, idx + 1); // 递归调用
            path.deleteCharAt(path.length() - 1); // 恢复现场
        }

    }

}
