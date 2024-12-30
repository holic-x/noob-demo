package com.noob.algorithm.daily.plan01.day17;

import java.util.*;

/**
 * 🟡017 电话号码的字母组合
 */
public class Solution017_02 {

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

        // 构建队列辅助遍历
        Deque<String> queue = new LinkedList<>();
        queue.offer("");

        // 每次取出队列中的元素进行拼接然后重新放回
        for (int i = 0; i < digits.length(); i++) {
            // 获取当前数字字符对应的字符序列
            char[] chs = map.get(digits.charAt(i)).toCharArray();
            int curQueueSize = queue.size();
            for (int k = 0; k < curQueueSize; k++) {
                String curStr = queue.poll();
                for (char c : chs) {
                    queue.offer(curStr + c);
                }
            }
        }

        // 处理结果集
        List<String> res = new ArrayList<>(); // 记录结果集
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }

        // 返回结果集
        return res;
    }


}
