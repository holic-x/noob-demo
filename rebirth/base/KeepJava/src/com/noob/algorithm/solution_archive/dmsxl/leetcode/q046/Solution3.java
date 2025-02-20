package com.noob.algorithm.solution_archive.dmsxl.leetcode.q046;

import java.util.ArrayList;
import java.util.List;

/**
 * 046 全排列
 */
public class Solution3 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 调用回溯方法
        backTrack(nums);
        // 返回结果
        return res;
    }

    // 定义回溯方法
    public void backTrack(int[] nums) {
        // 回溯出口
        if (curPath.size() == nums.length) { // 当当前路径的长度和数组长度一致说明构成了一个排列顺序
            // 表示遍历到叶子节点才将结果载入
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 回溯过程（排列：每次都从0开始检索，但需排除掉已经存在于路径的元素）
        for (int i = 0; i < nums.length; i++) { // for 循环处理（注意此处的起点）
            // 判断当前路径是否已经包含该元素，如果包含则剪掉
            if(curPath.contains(nums[i])){
                continue;
            }

            curPath.add(nums[i]); // 处理节点
            backTrack(nums); // 递归调用
            curPath.remove(curPath.size() - 1); // 复原现场
        }
    }
}
