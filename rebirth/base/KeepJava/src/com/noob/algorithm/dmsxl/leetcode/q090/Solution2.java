package com.noob.algorithm.dmsxl.leetcode.q090;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 090 子集II
 */
public class Solution2 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 数组排序(用于后面去重处理)
        Arrays.sort(nums);
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 回溯算法
    public void backTrack(int[] nums, int startIndex) {
        // 将路径载入结果集(在回溯过程中进行了去重处理，此处直接载入子集到结果集即可)
        res.add(new ArrayList<>(curPath));

        // 递归出口
//        if(startIndex>=nums.length){
//            return;
//        }

        // 回溯过程
        for (int i = startIndex; i < nums.length; i++) {
            // 剪枝：去除重复集合的情况，避免同一层取到同样元素
            if (i > startIndex && nums[i - 1] == nums[i]) {
                continue;
            }

            // 处理节点
            curPath.add(nums[i]);
            // 递归
            backTrack(nums, i + 1);
            // 回退,复原现场
            curPath.remove(curPath.size() - 1);
        }
    }
}
