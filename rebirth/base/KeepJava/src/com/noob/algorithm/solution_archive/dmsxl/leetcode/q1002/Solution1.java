package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1002;

import java.util.*;

/**
 * 🟢1002 查找共用字符
 */
public class Solution1 {
    // todo 待完善
    public List<String> commonChars(String[] words) {
        /**
         * 选择words[0]中的字符作为判断基础（因为字符要出现在所有字符串，则可任意选择一个字符串）
         * 此处如果选用Set存储就会忽略掉元素重复出现的情况
         * 例如：
         * ["bella","label","roller"]
         * =》["e","l","l"]
         */
        Set<Character> set = new HashSet<>();
        for (char cur : words[0].toCharArray()) {
            set.add(cur);
        }

        List<String> res = new ArrayList<>();
        // 校验其他字符串中是否均出现这些字符
        for (char cur : set) {
            boolean allMatch = true;
            for (int i = 1; i < words.length; i++) {
                boolean match = false;
                for (char x : words[i].toCharArray()) {
                    if (x == cur) {
                        match = true;
                        break; // 跳出内层循环
                    }
                }
                allMatch &= match;
            }
            if (allMatch) {
                res.add(String.valueOf(cur));
            }
        }
        // 返回结果
        return res;
    }
}
