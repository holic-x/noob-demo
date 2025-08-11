package com.noob.algorithm.daily.plan03.hot100_daily.day06.p017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 047 全排列II - https://leetcode.cn/problems/permutations-ii/description/
 */
public class Solution047_01 {

    /**
     * 思路分析：给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
     * 全排列（交换思路） + 去重剪枝
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 数据排序
        Arrays.sort(nums);
        // 调用回溯算法
        boolean[] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        backTrack(nums, visited);
        // 返回结果
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private void backTrack(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 回溯处理
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }

            // 回溯处理
            path.add(nums[i]);
            visited[i] = true;
            backTrack(nums, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

}
