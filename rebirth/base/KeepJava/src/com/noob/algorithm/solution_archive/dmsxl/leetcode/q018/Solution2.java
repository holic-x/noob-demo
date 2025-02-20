package com.noob.algorithm.solution_archive.dmsxl.leetcode.q018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 018 四数之和
 */
public class Solution2 {

    // 排序 + 双指针 （仿三元组的思路，此处固定两个位置，然后双指针从剩余元素的头尾向中间靠拢）
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 定义结果集
        List<List<Integer>> res = new ArrayList<>();
        // 1.排序：对数组进行排序
        Arrays.sort(nums);

        // 2.双指针：双指针验证四元组
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int curSum = nums[i] + nums[j] + nums[left] + nums[right];
                    // 判断curSum与target的关系，以此确定指针移动方向
                    if (curSum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        // 判断是否存在集合，不存在重复则补充
                        if (!res.contains(temp)) {
                            res.add(temp);
                        }
                        // 指针继续移动，判断下一个匹配的四元组
                        left++;
                        right--;
                    } else if (curSum < target) {
                        // 需要让curSum变大
                        left++;
                    } else if (curSum > target) {
                        // 需要让curSum变小
                        right--;
                    }
                }
            }
        }
        // 返回结果集合
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 2, 2, 2};
        Solution2 solution1 = new Solution2();
        solution1.fourSum(nums, 8);
    }

}
