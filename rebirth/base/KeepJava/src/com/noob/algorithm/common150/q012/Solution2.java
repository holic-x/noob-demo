package com.noob.algorithm.common150.q012;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 012 整数转罗马数字
 */
public class Solution2 {

    public String intToRoman(int num) {
        // 构建字符串序列
        StringBuilder roman = new StringBuilder();

        // 罗马数字表示规则：一直找最大的字符值，找到后进行拼接，并减去对应整数值，以此类推，直到num为0（硬核遍历方式）
        while (num > 0) {
            // 从大到小遍历
            if (num >= 1000) {
                num -= 1000;
                roman.append("M");
            } else if (num >= 900) {
                num -= 900;
                roman.append("CM");
            } else if (num >= 500) {
                num -= 500;
                roman.append("D");
            } else if (num >= 400) {
                num -= 400;
                roman.append("CD");
            } else if (num >= 100) {
                num -= 100;
                roman.append("C");
            } else if (num >= 90) {
                num -= 90;
                roman.append("XC");
            } else if (num >= 50) {
                num -= 50;
                roman.append("L");
            } else if (num >= 40) {
                num -= 40;
                roman.append("XL");
            } else if (num >= 10) {
                num -= 10;
                roman.append("X");
            } else if (num >= 9) {
                num -= 9;
                roman.append("IX");
            } else if (num >= 5) {
                num -= 5;
                roman.append("V");
            } else if (num >= 4) {
                num -= 4;
                roman.append("IV");
            } else if (num >= 1) {
                num -= 1;
                roman.append("I");
            }
        }
        // 返回结果
        return roman.toString();
    }

}
