package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡 442 数组中重复的数据 - https://leetcode.cn/problems/find-all-duplicates-in-an-array/description/
 */
public class Solution442_01 {

    /**
     * 正负标记法
     * 时间复杂度O(n)  空间复杂度O(1)
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]); // 当前元素
            if(nums[x-1]>0){
                nums[x - 1] = -nums[x - 1]; // 将其标记为负数
            }else{
                ans.add(x); // 如果为负数，说明该数已经出现过1次，需载入结果集
            }
        }
        // 返回结果
        return ans;
    }
}
