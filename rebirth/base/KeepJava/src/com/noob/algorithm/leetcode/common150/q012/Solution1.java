package com.noob.algorithm.leetcode.common150.q012;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 012 整数转罗马数字
 */
public class Solution1 {

    public String intToRoman(int num) {
        // 构建字符串序列
        StringBuilder roman = new StringBuilder();

        // 定义罗马数字映射集(将13种情况进行列举):每次查找都优先找最大的，此处借助需构建有序性（此处也可以通过两个数组做）
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        // 罗马数字表示规则：一直找最大的字符值，找到后进行拼接，并减去对应整数值，以此类推，直到num为0
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while (it.hasNext()) { // 依次遍历映射集
            Map.Entry<Integer, String> entry = it.next();
            int key = entry.getKey();
            String value = entry.getValue();
            while (num >= key) { // 如果num>=key 说明需进行转化（因为映射集本身从大到小进行排序，因此此处就是在转化最大值）
                roman.append(value);
                num -= key;
            }
            if (num == 0) { // 当num为0的时候，退出，直到所有的字符遍历完成
                break;
            }
        }
        return roman.toString();
    }

}
