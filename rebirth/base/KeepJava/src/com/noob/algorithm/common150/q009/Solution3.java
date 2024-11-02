package com.noob.algorithm.common150.q009;

/**
 * 009 回文数
 */
public class Solution3 {
    /**
     * 在不使用字符串或者数组的情况下进行判断
     */
    public boolean isPalindrome(int x) {

        /**
         * 数学规律分析：
         * 1.过滤：如果x为负数或者其以0结尾(x==0是满足的)，则其回文序列不符合数学约定，对于这部分的数据可以先过滤掉
         * 2.反转：如果为正数，则判断反转后的数字和原数字是否一致
         */
        // 过滤
        if (x < 0 ) { // x < 0 || (x % 10 == 0 && x != 0) 如果是全反转，则整数部分直接交由后面的反转逻辑校验
            return false;
        }

        // 反转数字(只需要反转一半)
        int reverseNum = 0;
        int num = x;
        // 基于数位分离的思想
        while (num != 0) {
            reverseNum = reverseNum * 10 + num % 10; // 每次截取最后一个位置的数字，随后构建逆序序列
            num = num / 10;
        }
        return reverseNum == x;
    }

}

