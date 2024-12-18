package com.noob.algorithm.dmsxl.leetcode.q925;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢925 长按键入
 */
public class Solution1 {

    // 错误思路：去重校验（❌❌❌会忽略掉name中可能真的存在重复字符的情况）
    public boolean isLongPressedName(String name, String typed) {
        Set<Character> set = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        for(char cur : typed.toCharArray()){
            if (!set.contains(cur)) {
                set.add(cur);
                sb.append(cur);
            }
        }
        return name.equals(sb.toString());
    }
}
