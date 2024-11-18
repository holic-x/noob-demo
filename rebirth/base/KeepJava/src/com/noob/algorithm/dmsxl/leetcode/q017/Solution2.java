package com.noob.algorithm.dmsxl.leetcode.q017;

import java.util.*;

/**
 * 017 电话号码和字母的组合
 */
public class Solution2 {

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

        // 构建辅助队列
        Deque<String> queue = new LinkedList<>();
        queue.offer("");

        // 迭代法
        for (int i = 0; i < digits.length(); i++) {
            //  记录当层元素个数，取出队列元素，根据当前遍历字符串指向数字进行拼接，随后更新入队
            int curSize = queue.size();
            for (int j = 0; j < curSize; j++) {
                // 取出队列中当层元素进行拼接
                String curPath = queue.poll();
                String charMap = map.get(digits.charAt(i));
                // 处理新字符
                for (int k = 0; k < charMap.length(); k++) {
                    queue.offer(curPath + charMap.charAt(k)); // 拼接新元素，入队
                }
            }
        }

        // 目标字符串遍历完成，取出目前队列元素
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.letterCombinations("23");
    }
}
