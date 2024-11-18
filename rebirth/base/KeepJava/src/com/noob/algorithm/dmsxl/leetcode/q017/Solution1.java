package com.noob.algorithm.dmsxl.leetcode.q017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 017 电话号码和字母的组合
 */
public class Solution1 {

    List<String> res = new ArrayList<>(); // 定义结果集
    StringBuffer curPath = new StringBuffer(); // 定义当前遍历路径

    // 定义数字和字母映射集合
    Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc"); // 注意字符和数字的比较转换，此处用字符处理
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        // digits 为空判断
        if (digits == null || "".equals(digits)) {
            return new ArrayList<>();
        }

        // 调用回溯方法
        backTrack(digits, 0);
        // 返回结果
        return res;
    }


    /**
     * 回溯法
     *
     * @param digits 遍历字符串
     * @param cur    当前遍历位置
     */
    public void backTrack(String digits, int cur) {
        // 回溯出口：当curPath长度和digits长度一致(或者cur遍历到字符串末尾)
        if (curPath.length() == digits.length()) {
            // 处理结果：将当前路径加入结果集
            res.add(new StringBuffer(curPath).toString());
        }

        // 回溯过程
        for (int i = cur; i < digits.length(); i++) {
            // 根据当前遍历指向数字，循环处理相应的字符
            String curChars = map.get(digits.charAt(i));
            for (int j = 0; j < curChars.length(); j++) {
                // 1.处理节点
                curPath.append(curChars.charAt(j));
                // 2.递归
                backTrack(digits, i + 1); // 递归下一个元素
                // 3.恢复现场
                curPath.deleteCharAt(curPath.length() - 1); // 删除最后一个元素
            }
        }
    }
}
