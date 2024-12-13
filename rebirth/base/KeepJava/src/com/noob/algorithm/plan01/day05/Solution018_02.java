package com.noob.algorithm.plan01.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和（剪枝优化版本）
 */
public class Solution018_02 {

    /**
     * 降维+排序+双指针：求[x,y,u,v] => 求 基于 x 构成的 满足[y,u,v](y+u+v=target-x的三元组) 构成的四元组
     * 且x y u v 互不相同
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        // ① 对数组进行排序
        Arrays.sort(nums);

        // ② 求[x,y,u,v] => 求 基于 x 构成的 满足[y,u,v](y+u+v=target-x的三元组) 构成的四元组

        // 固定 x
        for (int x = 0; x < len - 3; x++) {
            // 剪枝①：如果nums[x]>target则跳过
            if (nums[x] > target) {
                continue;
            }

            // 求满足[y,u,v](y+u+v=target-x的三元组)
            int curTarget = target - nums[x]; // 当前三元组校验的目标
            for (int y = x + 1; y < len - 2; y++) {
                // 剪枝②：如果nums[x]>curTarget则跳过
                if (nums[y] > curTarget) {
                    continue;
                }

                // 剪枝③：如果 y 出现连续重复则跳过
                if (y > x + 1 && nums[y - 1] == nums[y]) {
                    continue;
                }

                // 定义双指针确定[u,v]
                int u = y + 1, v = len - 1;
                while (u < v) {
                    int curSum = nums[y] + nums[u] + nums[v];
                    if (curSum == curTarget) {
                        res.add(Arrays.asList(nums[x], nums[y], nums[u], nums[v])); // 去重处理

                        // 当次处理完成，指针后移
                        u++;
                        v--;

                        // 剪枝④：如果[u,v]选择出现连续重复则跳过
                        while (u < v && nums[u - 1] == nums[u]) {
                            u++;
                        }
                        while (u < v && nums[v] == nums[v + 1]) {
                            v--;
                        }

                    } else if (curSum < curTarget) {
                        u++;
                    } else if (curSum > curTarget) {
                        v--;
                    }
                }
            }
        }

        // 返回结果
        return res;
    }
}
