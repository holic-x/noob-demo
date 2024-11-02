package com.noob.algorithm.common150.q066;

import java.util.Arrays;

/**
 * 066 加一
 */
public class Solution1 {
    /**
     * 数学规律法：逆序遍历数字元素
     * 1.判断数字是否为9，为9则置为0（且带有进位1，这个进位1和加1的作用是等价的，因此不需要额外定义变量维护carry）
     */
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1;i>=0;i--){
            if(digits[i]==9){
                digits[i]=0; // 继续遍历
            }else{
                // 如果当前元素非9，执行+1，并返回
                digits[i] += 1;
                return digits;
            }
        }
        // 如果for完整遍历完成，说明还存在进位，则需要加长数组，在头部补足进位
//        int[] newDigits = new int[digits.length+1];
//        Arrays.copyOfRange(digits,1,newDigits.length);
//        newDigits[0] = 1;
//        return newDigits;

        digits= new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{9,9};
        Solution1 solution1 = new Solution1();
        solution1.plusOne(digits);
    }
}
