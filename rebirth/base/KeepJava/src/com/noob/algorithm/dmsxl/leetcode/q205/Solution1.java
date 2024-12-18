package com.noob.algorithm.dmsxl.leetcode.q205;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟢 205 同构字符串
 */
public class Solution1 {

    // 哈希表
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) {
            // 长度不一致，无法匹配
            return false;
        }
        // 定义map存储现存的字符映射情况(Map<s,t>),以s为基础，t为对应的映射字符
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            char curS = s.charAt(i);
            char curT = t.charAt(i);
            if (map.containsKey(curS)) {
                // 字符映射关系已存在，校验是否一致(如果不一致则不满足同构条件) curS -> curT
                if (map.get(curS) != curT) {
                    return false;
                }
            } else if (map.containsValue(curT)) { // curT已有映射关系存在，且这个映射关系并不等于curS
                return false;
            } else {
                // 字符映射关系不存在，新增
                map.put(curS, curT);
            }
        }
        // 校验通过
        return true;
    }

    public static void main(String[] args) {
        // String s = "paper",t="title";
        String s = "badc", t = "baba";
        Solution1 solution1 = new Solution1();
        solution1.isIsomorphic(s, t);
    }
}
