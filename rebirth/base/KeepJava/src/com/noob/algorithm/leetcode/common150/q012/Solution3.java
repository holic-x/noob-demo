package com.noob.algorithm.leetcode.common150.q012;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 012 整数转罗马数字
 */
public class Solution3 {

    public String intToRoman(int num) {
        // 构建字符串序列
        StringBuilder roman = new StringBuilder();

        // 定义罗马数字映射集(将13种情况进行列举):每次查找都优先找最大的，此处借助需构建有序性（此处也可以通过两个数组做）
        int[] numbers = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M","CM", "D","CD","C","XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // 罗马数字表示规则：一直找最大的字符值，找到后进行拼接，并减去对应整数值，以此类推，直到num为0
        for(int i=0;i<numbers.length;i++) {
            while(num>=numbers[i]){
                roman.append(romans[i]);
                num -= numbers[i];
            }
            if(num==0){
                break;
            }
        }

        // 返回结果
        return roman.toString();
    }

}
