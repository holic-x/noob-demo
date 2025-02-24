package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1207;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 🟢 1207 独一无二的出现次数
 */
public class Solution1 {

    // 统计 + 哈希
    public boolean uniqueOccurrences(int[] arr) {
        // 定义map存储每个元素的出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int cur : arr) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        // 遍历map，判断每个数的出现次数是否为独一无二的
        Set<Integer> set = new HashSet<>(); // 存储出现次数（如果校验出现重复的出现次数，说明非独一无二）
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (set.contains(entry.getValue())) {
                return false;
            }
            // 当前次数还没出现，加入set集合
            set.add(entry.getValue());
        }
        // 校验通过说明满足
        return true;
    }

}
