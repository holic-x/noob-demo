package com.noob.algorithm.solution_archive.leetcode.common150.q190;

/**
 * 190 颠倒二进制位
 * todo 思路错误
 */
public class Solution1 {

    public int reverseBits(int n) {
        String target = String.valueOf(n);
        // 将二进制转化为十进制
        int x = 0;
        int targetVal = 0;
        for (int i = target.length()-1; i >= 0; i--) {
            targetVal += (target.charAt(i) - '0') * Math.pow(2,x);
            x ++;
        }

        // 将十进制进行逆序
        StringBuffer sb = new StringBuffer(targetVal + "");
        int reverseVal = Integer.valueOf(sb.reverse().toString());
        // 将逆序后的十进制转化为二进制
        StringBuffer res = new StringBuffer();
        while(reverseVal!=0){
            res.append(reverseVal % 2);
            reverseVal = reverseVal / 2;
        }

        // 返回处理好的数据
        return Integer.valueOf(res.reverse().toString());
    }

    public static void main(String[] args) {
        int n =  1111111101;
        Solution1 solution1 = new Solution1();
        solution1.reverseBits(n);
    }
}
