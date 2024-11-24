package com.noob.algorithm.dmsxl.leetcode.q046;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 046 全排列
 */
public class Solution2 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集

    public List<List<Integer>> permute(int[] nums) {

        // 将数组转为list
        List<Integer> curPath = new ArrayList<>();
        for (int num : nums) {
            curPath.add(num);
        }

        // 调用回溯方法
        backTrack(curPath, 0);
        // 返回结果
        return res;
    }

    // 定义回溯方法(此处处理的是nums序列)
    public void backTrack(List<Integer> nums, int idx) {
        // 回溯出口
        if (idx == nums.size() - 1) {
            res.add(new ArrayList<>(nums)); // 当遍历到数据末尾最后一个元素，说明不需要继续进行交换了，直接记录当前序列
            return;
        }

        // 回溯过程
        for (int i = idx; i < nums.size(); i++) {
            // 处理节点：交换位置，载入节点路径
            Collections.swap(nums, i, idx);
            // 递归调用
            backTrack(nums, idx + 1); // 处理下个位置
            // 复原现场
            Collections.swap(nums, idx, i);
        }
    }
}
