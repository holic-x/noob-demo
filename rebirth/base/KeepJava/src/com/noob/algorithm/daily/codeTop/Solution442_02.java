package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡 442 数组中重复的数据 - https://leetcode.cn/problems/find-all-duplicates-in-an-array/description/
 */
public class Solution442_02 {

    /**
     * 遍历计数
     * 时间复杂度O(n)  空间复杂度O(n)
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        // 统计元素出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 遍历map
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                ans.add(entry.getKey());
            }
        }
        // 返回结果
        return ans;
    }
}
