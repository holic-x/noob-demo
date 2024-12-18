package com.noob.algorithm.dmsxl.leetcode.q1365;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 🟢 1365 有多少小于当前数字的数字
 */
public class Solution3 {

    // 排序法
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length; // 定义数组长度
        int[] res = Arrays.copyOf(nums, len); // 定义统计结果数组

        // 定义map记录每个元素有多少个比它小的数字 Map<item,cnt>
        HashMap<Integer, Integer> map = new HashMap<>();
        // ① 排序：对res数组元素进行排序（从小到大）
        Arrays.sort(res);

        // ② 统计：对排序后的res数组进行遍历，记录每个元素对应的比它小的数字个数
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(res[i])) { // 如果遇到了相同的数字，则不需要更新该数字的统计情况（处理重复元素的情况）
                map.put(res[i], i);
            }
        }

        // ③ 更新：遍历res数组（重新更新res）,根据map处理结果集
        for (int i = 0; i < res.length; i++) {
            res[i] = map.get(nums[i]);
        }

        // 返回处理结果
        return res;
    }
}
