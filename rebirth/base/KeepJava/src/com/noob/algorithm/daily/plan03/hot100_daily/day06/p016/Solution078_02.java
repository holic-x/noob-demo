package com.noob.algorithm.daily.plan03.hot100_daily.day06.p016;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 078 子集问题 - https://leetcode.cn/problems/subsets/description/
 */
public class Solution078_02 {

    /**
     * 思路分析：子集问题可以用队列迭代解决
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        // 构建辅助队列遍历处理
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new LinkedList<>()); // 初始化空集合入队

        // 遍历所有元素
        for (int i = 0; i < nums.length; i++) {
            // 取出当前队列的所有内容，拼接当前遍历元素
            int curSize = queue.size();
            while (curSize-- > 0) {
                List<Integer> current = queue.poll();
                // 1. 子集①：不添加当前元素（直接保留）
                queue.offer(new ArrayList<>(current));
                // 2. 子集②：添加当前元素
                current.add(nums[i]);
                queue.offer(new ArrayList<>(current));
            }
        }

        // 队列中的所有子集即为结果
        ans.addAll(queue);

        // 返回处理结果
        return ans;
    }

}
