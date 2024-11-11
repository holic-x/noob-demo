package com.noob.algorithm.dmsxl.leetcode.q344;

/**
 * 344 反转字符串
 */
public class Solution2 {

    // 思路2：O(n) 辅助数组反转
    public void reverseString(char[] s) {
        char[] reverseArr = new char[s.length];
        int cur = 0;// 定义新数组游标指针
        for(int i=s.length-1;i>=0;i--){
            reverseArr[cur++] = s[i];
        }
        // 重新封装数组
        for(int i=0;i<reverseArr.length;i++){
            s[i] = reverseArr[i];
        }
    }

}
