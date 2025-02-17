package com.noob.algorithm.daily.archive.plan02.day06.p014;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡 017 电话号码的字母组合 - https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 */
public class Solution017_01 {

    // 构建数字与其对应字母的集合映射
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

    // 构建结果集合
    List<String> res = new ArrayList<>();
    // 构建路径
    StringBuffer path = new StringBuffer();

    /**
     * 思路分析：给定数字[2,9]的字符串，返回所有可能表示的字母组合
     * - 2(abc)、3(edf).....
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        // 调用回溯算法
        backTrack(0, digits);
        // 返回结果集
        return res;
    }


    // 回溯算法
    private void backTrack(int idx, String digits) {
        // 载入结果集合
        if (path.length() == digits.length()) {
            res.add(new String(path.toString()));
            return;
        }

        // 回溯处理(idx表示当前遍历的字符串位置)
        char[] chs = map.get(digits.charAt(idx)).toCharArray();
        for (char ch : chs) {
            path.append(ch);
            backTrack(idx + 1, digits);
            path.deleteCharAt(path.length() - 1);
        }
    }

//    public static void main(String[] args) {
//        Solution017_01 s = new Solution017_01();
//        s.letterCombinations("23");
//    }
}
