package com.noob.algorithm.common150.q172;

/**
 * 阶乘后的零（172）
 * 阶乘法：需考虑数值溢出的情况
 */
public class Solution1 {
    /**
     * 1.计算阶乘数
     * 2.统计尾随0
     */
    public int trailingZeroes(int n) {
        // 定义阶乘结果
        int res = 1;
        while(n>0){
            res *= n;
            n--;
        }
        /*
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
         */

        // 统计尾随0
        int count = 0;
        while (res > 0) {
            if (res % 10 == 0) {
                count++; // 存在尾随0，计数+1
                res = res / 10; // 去掉尾部元素，进行下一次判断
            } else {
                // 遇到非尾随0直接结束(尾随0：接收)
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 7;
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.trailingZeroes(13));
    }
}
