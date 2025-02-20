package com.noob.algorithm.leetcode.common150.q017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17 电话号码的字母组合
 */
public class Solution2 {
    // 定义数字和字母映射集合
    Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(2, "abc");
        put(3, "def");
        put(4, "ghi");
        put(5, "jkl");
        put(6, "mno");
        put(7, "pqrs");
        put(8, "tuv");
        put(9, "wxyz");
    }};

    List<String> ans = new ArrayList<>();
    StringBuffer buffer = new StringBuffer(); // 定义一个字符串，用于跟踪回溯方法调用过程中的字符串

    /**
     * 定义回溯方法: digits 要处理的字符串，idx 当前处理的位置
     */
    public void backTrack(String digits, int idx) {
        // 如果处理位置和字符串长度相等，说明拼接完成（路径完整）
        if (idx == digits.length()) {
            ans.add(buffer.toString()); // 加入结果集
        }else{
            // 否则继续拼接指定数字对应的字符
            String letters = map.get(digits.charAt(idx) - '0'); // 字符转数字
            for (int i = 0; i < letters.length(); i++) {
                buffer.append(letters.charAt(i)); // 将当前字母添加到目前的组合中
                backTrack(digits, idx + 1);// 递归找出剩余数字的所有字母组合（idx+1 表示指向下一个数字）
                buffer.deleteCharAt(idx); // 回溯：删除当前字母，便于尝试下个字母
            }
        }
    }

    // 回溯法：深度优先遍历思想（Map+回溯）
    public List<String> letterCombinations(String digits) {
        // 空字符串验证
        if(digits==null||"".equals(digits)){
            return new ArrayList<>();
        }

        // 字符串不为空，执行方法
        backTrack(digits, 0);
        return ans;
    }

}
