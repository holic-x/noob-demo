package com.noob.algorithm.solution_archive.dmsxl.leetcode.q454;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 454 四数相加
 */
public class Solution3 {

    // 暴力法：分组 + 哈希表
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /**
         * 根据数组进行两两分组：
         * 1.对每组数组进行`u+v`结果计算（双层循环）：统计每个和出现的次数，加入哈希表中存储Map<两数之和,该和出现次数>
         * 2.对步骤1中得到的两个哈希集合进行遍历，看是否存在x+y=0的情况，存在则进行统计
         */
        // nums1、nums2一组
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int curSum = nums1[i] + nums2[j];
                map1.put(curSum, map1.getOrDefault(curSum, 0) + 1);
            }
        }

        // nums3、nums4一组(遍历的时候进行判断)
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int k = 0; k < nums3.length; k++) {
            for (int l = 0; l < nums4.length; l++) {
                int curSum = nums3[k] + nums4[l];
                map2.put(curSum, map2.getOrDefault(curSum, 0) + 1);
            }
        }

        // 判断四数之和(todo 错误处理，需考虑重复等情况)
        int res = 0;
        Set<Integer> set1 = map1.keySet();
        Set<Integer> set2 = map2.keySet();
        for (int x : set1) {
            for (int y : set2) {
                if (x + y == 0) {
                   //  res = res + map1.get(x) + map2.get(y);
                }
            }
        }

        // 返回统计结果
        return res;
    }


}
