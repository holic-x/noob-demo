package com.noob.algorithm.solution_archive.dmsxl.leetcode.q015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 015 三数之和
 */
public class Solution1 {

    // 思路分析：对数组元素进行排序，固定 i ，然后分别从i+1\len-1位置向中间靠拢寻找三元组
    public List<List<Integer>> threeSum(int[] nums) {
        // 定义结果集
        List<List<Integer>> res = new ArrayList<>();

        // 1.对数组进行排序
        Arrays.sort(nums);

        // 2.固定i，然后分别从i+1\len-1位置向中间靠拢寻找三元组
        for (int i = 0; i < nums.length - 2; i++) { // 三元组边界，i只需要遍历到倒数第3个位置
            int left = i + 1, right = nums.length - 1; // 定义左右指针，辅助寻找三元组
            while (left < right) { // 元素不能重复
                List<Integer> temp = new ArrayList<>();
                int curSum = nums[i] + nums[left] + nums[right];
                if (curSum == 0) {
                    // 构成三元组，记录集合(此处记录的是元素，而非下标)
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    // 将其加入结果集合(需进行去重处理)
                    if(!res.contains(temp)){
                        res.add(new ArrayList<>(temp));
                    }
                    // 当前三元组判断完成，继续下一个判断，两个指针都向中间靠拢
                    left++;
                    right--;
                } else {
                    // 指针继续移动（判断左右指针移动方向，将curSum与0做对比）
                    if (curSum < 0) {
                        // 和小于0，则要让curSum变大
                        left++;
                    } else if (curSum > 0) {
                        // 和大于0，则要让curSum变小
                        right--;
                    }
                }
            }
        }
        // 返回结果集
        return res;
    }

}
