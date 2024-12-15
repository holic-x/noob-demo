package com.noob.algorithm.plan01.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和
 */
public class Solution018_01 {

    /**
     * 降维+排序+双指针：求[x,y,u,v] => 求 基于 x 构成的 满足[y,u,v](y+u+v=target-x的三元组) 构成的四元组
     * 且x y u v 互不相同
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        // ① 对数组进行排序
        Arrays.sort(nums);

        // ② 求[x,y,u,v] => 求 基于 x 构成的 满足[y,u,v](y+u+v=target-x的三元组) 构成的四元组,

        // 固定 x
        for (int x = 0; x < len - 3; x++) {
            // 求满足[y,u,v](y+u+v=target-x的三元组)
            int curTarget = target - nums[x]; // 当前三元组校验的目标
            for (int y = x + 1; y < len - 2; y++) {
                // 定义双指针确定[u,v]
                int u = y + 1, v = len - 1;
                while (u < v) {
                    int curSum = nums[y] + nums[u] + nums[v];
                    if (curSum == curTarget) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[x]);
                        temp.add(nums[y]);
                        temp.add(nums[u]);
                        temp.add(nums[v]);
                        // 去重处理
                        if (!res.contains(temp)) {
                            res.add(temp);
                        }
                        // 当次处理完成，指针后移
                        u++;
                        v--;
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
