package com.noob.algorithm.leetcode.common150.q205;

/**
 * 205 同构字符串
 */
public class Solution1 {

    public boolean isIsomorphic(String s, String t) {
        for(int i = 0; i < s.length(); i++){
            if(s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
