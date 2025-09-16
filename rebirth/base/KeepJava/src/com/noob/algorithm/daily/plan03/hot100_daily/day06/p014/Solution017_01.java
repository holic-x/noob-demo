package com.noob.algorithm.daily.plan03.hot100_daily.day06.p014;

import java.util.*;

/**
 * 🟡 017 电话号码的字母组合 - https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 */
public class Solution017_01 {


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

        Queue<String> queue = new LinkedList<>();
        queue.offer(""); // 初始化空字符串入队，用作后续迭代拼接

        // 遍历每个字符
        for (char ch : digits.toCharArray()) {
            // 取出当前队列序列，随后拼接每种可能的字符后重新入队
            char[] targetChs = map.get(ch).toCharArray();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curStr = queue.poll();
                for (char c : targetChs) {
                    String newStr = curStr + c;
                    queue.offer(newStr);
                }
            }
        }

        // 遍历队列中所有元素
        List<String> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (!"".equals(str)) {
                ans.add(str);
            }
        }

        // 返回构建结果
        return ans;
    }


    public static void main(String[] args) {
        Solution017_01 s = new Solution017_01();
        s.letterCombinations("23");
    }
}
