package com.noob.algorithm.dmsxl.leetcode.q205;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟢 205 同构字符串
 */
public class Solution2 {

    // 哈希表(双map处理)
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) {
            // 长度不一致，无法匹配
            return false;
        }
        // 定义map存储现存的字符映射情况(Map<s,t>),以s为基础，t为对应的映射字符
        Map<Character, Character> map1 = new HashMap<>(); // map1: s->t
        Map<Character, Character> map2 = new HashMap<>(); // map2: t->s
        for (int i = 0; i < sLen; i++) {
            char curS = s.charAt(i);
            char curT = t.charAt(i);
            if (!map1.containsKey(curS)) {
                map1.put(curS, curT); // 构建s->t的映射关系(先入为主，如果已存s，说明s已经被其他字符映射，则不更新)
            }
            if (!map2.containsKey(curT)) {
                map2.put(curT, curS); // 构建t->s的映射关系(先入为主，如果已存t，说明t已经被其他字符映射，则不更新)
            }
            // 每次遍历校验更新后的映射关系是否合理
            if (map1.get(curS) != curT || map2.get(curT) != curS) {
                return false;
            }
        }
        // 校验通过
        return true;
    }

}
