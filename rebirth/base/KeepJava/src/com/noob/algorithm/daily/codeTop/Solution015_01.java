package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡015 三数之和
 */
public class Solution015_01 {


    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();

        // 对nums进行排序
        Arrays.sort(nums);

        /**
         * 寻找[x,y,z]三元组
         */
        for (int i = 0; i < len - 2; i++) { // 固定x
            if (nums[i] > 0) {
                break; // 跳过校验
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 跳过当次校验
            }

            // 内层基于双指针寻找y,z
            int left = i + 1, right = len - 1;
            while (left < right) {
                // 校验当前构成的三元组之和是否为0
                int curSum = nums[i] + nums[left] + nums[right];
                if (curSum == 0) {
                    // 记录结果（需做去重处理：通过剪枝进行去重）
                    List<Integer> tempList = new ArrayList<>();
//                    tempList.add(i);
//                    tempList.add(left);
//                    tempList.add(right);
//                    res.add(tempList);
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 继续遍历下个组合
                    left++;
                    right--;

                    // 往前寻找下一个组合（如果出现连续重复则跳过）
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (curSum > 0) {
                    right--;
                } else if (curSum < 0) {
                    left++;
                }
            }

        }

        // 返回构建的结果集
        return res;
    }
}
