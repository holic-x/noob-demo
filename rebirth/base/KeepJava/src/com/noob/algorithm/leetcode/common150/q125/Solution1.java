package com.noob.algorithm.leetcode.common150.q125;


/**
 * 125 验证回文串
 */
public class Solution1 {
    /**
     * 反转法（正着读和反着读是一样的）
     */
    public boolean isPalindrome(String s) {

        int len = s.length();

        // 获取反转后的字符串
        StringBuilder reverseStr = new StringBuilder();
        for(int i=len-1;i>=0;i--){
            reverseStr.append(s.charAt(i));
        }

        // 依次比较两者是否完全一致
        for (int i=0;i<len;i++){
            if(s.charAt(i) != reverseStr.charAt(i)){
                return false;
            }
        }

        return true;
    }
}
