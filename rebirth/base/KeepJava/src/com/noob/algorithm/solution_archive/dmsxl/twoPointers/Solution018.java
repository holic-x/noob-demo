package com.noob.algorithm.solution_archive.dmsxl.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 018 四数之和
 */
public class Solution018 {

    /**
     * 排序 + 双指针（固定2位+双指针寻找四元组）
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 定义结果集合
        List<List<Integer>> res = new ArrayList<>();

        // 1.对数组元素进行排序
        Arrays.sort(nums);

        // 2.双指针（固定1位+双指针寻找三元组）
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                // 定义双指针寻找四元组
                int left = j + 1, right = len - 1;
                while (left < right) { // 不能重复用数组元素
                    // 判断当前四个元素和与target的关系，决定指针移动方向
                    int curSum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (curSum == target) {
                        // 满足四元组，判断去重条件决定是否加入结果集合
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        if (!res.contains(temp)) {
                            res.add(temp);
                        }
                        // 处理完成，双指针向中间靠拢
                        left++;
                        right--;
                    } else if (curSum > target) {
                        // 当前curSum比较大，需让curSum变小
                        right--;
                    } else if (curSum < target) {
                        // 当前curSum比较小，需让curSum变大
                        left++;
                    }
                }
            }
        }
        // 返回最终的结果集
        return res;
    }
}
