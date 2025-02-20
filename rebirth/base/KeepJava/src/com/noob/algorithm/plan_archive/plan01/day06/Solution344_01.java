package com.noob.algorithm.plan_archive.plan01.day06;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 344 反转字符串
 */
public class Solution344_01 {

    // 思路：逆序处理（非原地算法）
    public void reverseString(char[] s) {
        List<Character> list = new ArrayList<>();
        // 逆序处理
        for (int i = s.length - 1; i >= 0; i--) {
            list.add(s[i]);
        }
        // 回写到原数组中
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
    }
}
