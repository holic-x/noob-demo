package com.noob.algorithm.leetcode.hot100.q003;

import java.util.ArrayList;
import java.util.List;

/**
 * 03 无重复的最长子串
 */
public class Solution1 {

    /**
     * 暴力思路：相当于遍历每个子串，校验子串是否重复
     * 遍历每个元素i，判断以当前元素为起点的子串（i,j）是否包括重复元素。如果包括重复元素则更新max
     */
    public int lengthOfLongestSubstring(String s) {
        // 定义最大子串的长度
        int max = 0;

        // 校验以每个元素为起点的子串(此处子串是以i为起点，j为终点)
        for(int i = 0; i < s.length(); i++) { // i 为 当前元素（表示子串的起点）
            for(int j = i + 1; j < s.length(); j++) { // j 为子串的终点
                // 判断子串(i,j)是否包括重复元素，如果不包括则更新max
                if(!validSubStrRepeat(s.substring(i,j))){
                    // 如果子串不包括重复元素，则更新max
                    max = Math.max(max, j - i) ;
                }
            }
        }
        // 返回结果
        return max;
    }

    // 校验子串中是否包含重复元素（可以通过哈希表来校验）
    public boolean validSubStrRepeat(String subStr) {
        // 方式1：HashSet存储的是非重复元素，此处可以依次入集合然后判断size大小是否一致

        // 方式2：集合迭代，判断元素是否已存在，如果存在则说明重复
        List<Character> list= new ArrayList<>();
        for(int i = 0; i < subStr.length(); i++) {
            // 如果出现重复元素，直接返回true
            if(list.contains(subStr.charAt(i))) {
                return true;
            }
            list.add(subStr.charAt(i));
        }
        return false;
    }

}
