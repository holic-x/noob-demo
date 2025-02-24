package com.noob.algorithm.plan_archive.plan01.day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡017 电话号码的字母组合
 */
public class Solution017_01 {

    public List<String> res = new ArrayList<>(); // 记录结果集
    public StringBuffer curPath = new StringBuffer(); // 记录路径

    // 构建电话号码与字母的映射关系
    public Map<Character, String> map = new HashMap<Character, String>() {
        {
            put('2', "abc"); // 注意字符和数字的比较转换，此处用字符处理
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        backTrack(0, digits);
        return res;
    }


    // 回溯算法
    public void backTrack(int idx, String digits) {
        // 遍历完所有数字序列则记录结果集
        if (curPath.length() == digits.length()) {
            res.add(curPath.toString());
            return;
        }

        // 回溯处理
        for (int i = idx; i < digits.length(); i++) {
            // 获取字符对应的序列
            String chs = map.get(digits.charAt(idx));
            for (char c : chs.toCharArray()) {
                curPath.append(c);
                backTrack(i + 1, digits); // 递归处理下一个位置
                curPath.deleteCharAt(curPath.length() - 1);
            }
        }
    }

}
