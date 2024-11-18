package com.noob.algorithm.dmsxl.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 015 三数之和
 */
public class Solution015 {

    /**
     * 排序 + 双指针（固定1位+双指针寻找三元组）
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 定义结果集合
        List<List<Integer>> res = new ArrayList<>();

        // 1.对数组元素进行排序
        Arrays.sort(nums);

        // 2.双指针（固定1位+双指针寻找三元组）
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            // 特例情况判断
            if(nums[i]>0){
                break; // 如果固定的位置元素大于0，则后面累加和会更大，不可能构成三元组
            }

            // 定义双指针寻找三元组
            int left = i + 1, right = len - 1;
            while (left < right) { // 不能重复用数组元素
                // 判断当前三个元素和与0的关系，决定指针移动方向
                int curSum = nums[i] + nums[left] + nums[right];
                if (curSum == 0) {
                    // 满足三元组，判断去重条件决定是否加入结果集合
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    if (!res.contains(temp)) {
                        res.add(temp);
                    }
                    // 处理完成，双指针向中间靠拢
                    left++;
                    right--;
                } else if (curSum > 0) {
                    // 当前curSum比较大，需让curSum变小
                    right--;
                } else if (curSum < 0) {
                    // 当前curSum比较小，需让curSum变大
                    left++;
                }
            }
        }
        // 返回最终的结果集
        return res;
    }
}
