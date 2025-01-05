package com.noob.algorithm.daily.plan01.day20;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 046 全排列
 */
public class Solution046_01 {

    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 全排列：有顺序限定（不同序列表示不同）
    public void backTrack(int[] nums, int index) {
        // 递归出口：全排列的子集大小和nums大小相同（可以理解为一条完整路径）
        if (curPath.size() == nums.length) {
            res.add(new ArrayList<>(curPath));
        }

        // 回溯处理
        for (int i = index; i < nums.length; i++) {
            // 交换元素并处理
            swap(nums, i, index);
            curPath.add(nums[index]);
            // 递归选择
            backTrack(nums, index + 1);
            // 复原现场
            curPath.remove(curPath.size() - 1);
            swap(nums, i, index);
        }
    }

    // 交换指定位置的元素
    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}
