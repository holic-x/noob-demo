package com.noob.algorithm.dmsxl.kmw;

import java.util.Arrays;

/**
 * 054 替换数字
 */
public class Solution054 {

    // 将目标字符串的数字字符替换成`number`
    /*
    public String convert(String target){
        // 遍历字符串数据，判断是否为数字字符，是则进行替换
        for(char ch : target.toCharArray()){
            if(Character.isDigit(ch)){
                target = target.replace(ch + "","number");
            }
        }
        // 返回替换后的字符串
        return target;
    }
     */

    // 统计数字个数+填充扩容后的新数组
    public String convert1(String target) {
        // 遍历字符串数据，判断是否为数字字符，进行统计
        int count = 0;
        char[] targetArr = target.toCharArray();
        for (char ch : targetArr) {
            if (Character.isDigit(ch)) {
                count++;
            }
        }

        // 确定扩容数组的大小
        char[] convertArr = new char[target.length() + 5 * count]; // number 6个字符，减去原有提供的1个字符
        // 遍历源字符串
        int cur = 0; // cur指向新扩容数字的填充位置
        for (char ch : targetArr) {
            // 如果是字符直接填充
            if (Character.isLetter(ch)) {
                convertArr[cur++] = ch; // 填充字母，指针后移
            }

            // 如果是数字，进行替换
            if (Character.isDigit(ch)) {
                convertArr[cur++] = 'n';
                convertArr[cur++] = 'u';
                convertArr[cur++] = 'm';
                convertArr[cur++] = 'b';
                convertArr[cur++] = 'e';
                convertArr[cur++] = 'r';
            }
        }

        // 返回替换后的字符串
        return new String(convertArr);
    }

    // 统计数字个数+在现有数组基础上扩容
    public String convert(String target) {
        // 遍历字符串数据，判断是否为数字字符，进行统计
        int count = 0;
        char[] targetArr = target.toCharArray();
        for (char ch : targetArr) {
            if (Character.isDigit(ch)) {
                count++;
            }
        }

        // 确定扩容数组的大小
        char[] convertArr = Arrays.copyOf(targetArr, target.length() + 5 * count); // number 6个字符，减去原有提供的1个字符
        // 遍历扩容数组，从后往前填充
        int cur = convertArr.length - 1; // cur指向新扩容数字的填充位置（从后往前）
        for (int i = targetArr.length - 1; i >= 0; i--) { // i 指向遍历元素（如果出现替换则i也要保持同步前进）
            if (Character.isLetter(convertArr[i])) {
                convertArr[cur--] = convertArr[i]; // 非数字直接填充
            }
            if (Character.isDigit(convertArr[i])) {
                // 数字，逆序填充(rebmun)
                convertArr[cur--] = 'r';
                convertArr[cur--] = 'e';
                convertArr[cur--] = 'b';
                convertArr[cur--] = 'm';
                convertArr[cur--] = 'u';
                convertArr[cur--] = 'n'; // 填充完成，i继续遍历下个元素
            }
        }
        // 返回替换后的字符串
        return new String(convertArr);
    }

    // 测试
    public static void main(String[] args) {
        Solution054 solution054 = new Solution054();
        String res = solution054.convert("a1b2c3");
        System.out.println(res);
    }

}
