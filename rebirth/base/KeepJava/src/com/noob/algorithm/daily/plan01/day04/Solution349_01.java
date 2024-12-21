package com.noob.algorithm.daily.plan01.day04;

import java.util.*;

/**
 * 🟢349 两个数组的交集
 */
public class Solution349_01 {

    /**
     * 哈希法：
     * 1.将nums1数组元素加入set（去重）
     * 2.遍历nums2数组元素x，如果x在set中存在则加入结果集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // 1.将nums1数组元素加入set（去重）
        Set<Integer> set1 = new HashSet<>();
        for(int i=0;i< nums1.length;i++){
            set1.add(nums1[i]);
        }
        // 2.遍历nums2数组元素x，如果x在set中存在则加入结果集
        Set<Integer> res = new HashSet<>();
        for(int i=0;i<nums2.length;i++){
            if(set1.contains(nums2[i])){
                res.add(nums2[i]);
            }
        }

        // 返回交集
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Solution349_01 s = new Solution349_01();
        s.intersection(new int[]{4,9,5},new int[]{9,4,9,8,4});
    }

}
