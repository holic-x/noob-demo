package com.noob.algorithm.common150.q013;

import java.util.HashMap;

/**
 * 013 罗马数字转整数
 */
public class Solution {

    public int romanToInt(String s) {
        // 定义结果
        int res = 0;

        // 构建字符映射集合
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // 解析字符串（规则：只要当前元素比下一位元素小就减去值，如果比下一位大就加上值）
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int curVal = map.get(s.charAt(i));
            if ((i < len - 1) && (curVal < map.get(s.charAt(i + 1)))) { // 如果当前元素比下一位小则减去当前值，如果比下一位元素大就加上，且i最大可达n-2的位置（注意数组越界）
                res -= curVal;
            } else {
                res += curVal;
            }
        }
        // 返回结果
        return res;
    }


}
