package com.noob.algorithm.daily.archive.plan01.day19;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 078 子集
 */
public class Solution078_01 {

    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    public void backTrack(int[] nums, int index) {
//        if(index==nums.length){
//            return;
//        }

        res.add(new ArrayList<>(curPath));

        for (int i = index; i < nums.length; i++) {
            curPath.add(nums[i]);
            backTrack(nums, i + 1);
            curPath.remove(curPath.size() - 1);
        }
    }


}