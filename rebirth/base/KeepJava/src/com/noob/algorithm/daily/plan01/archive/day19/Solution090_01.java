package com.noob.algorithm.daily.plan01.archive.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 090 子集II
 */
public class Solution090_01 {

    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    public void backTrack(int[] nums, int index) {
//        if(index==nums.length){
//            return;
//        }

        if (!res.contains(curPath)) {
            res.add(new ArrayList<>(curPath));
        }

        for (int i = index; i < nums.length; i++) {
//            if (i > index && nums[i - 1] == nums[i]) {
//                continue;
//            }
            curPath.add(nums[i]);
            backTrack(nums, i + 1);
            curPath.remove(curPath.size() - 1);
        }
    }


}
