package com.noob.algorithm.dmsxl.leetcode.q1047;

import java.util.HashSet;
import java.util.Set;

/**
 * 1047 删除字符串中的所有相邻重复项
 */
public class Solution1 {

    // 替换法
    public String removeDuplicates(String s) {
        Set<String> set = new HashSet<>();
        set.add("aa");
        set.add("bb");
        set.add("cc");
        set.add("dd");
        set.add("ee");
        set.add("ff");
        set.add("gg");
        set.add("hh");
        set.add("ii");
        set.add("jj");
        set.add("kk");
        set.add("ll");
        set.add("mm");
        set.add("nn");
        set.add("oo");
        set.add("pp");
        set.add("qq");
        set.add("rr");
        set.add("ss");
        set.add("tt");
        set.add("uu");
        set.add("vv");
        set.add("ww");
        set.add("xx");
        set.add("yy");
        set.add("zz");

        while (validRepeat(s, set)) {
            // 存在重复，则进行置换
            for (String cur : set) {
                if (s.contains(cur)) {
                    s = s.replace(cur, ""); // 删除重复出现的字符
                }
            }
        }

        // 返回结果
        return s;
    }

    // 校验s中是否包括重复的字符序列
    public boolean validRepeat(String s, Set<String> set) {
        for (String cur : set) {
            if (s.contains(cur)) {
                return true;
            }
        }
        return false;
    }
}
