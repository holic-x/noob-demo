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
        for (int x = 0; x < len - 3; x++) { // 处理第1个数字
            //if (nums[x] > target) continue; // 剪枝①：如果nums[x]>target则跳过
            if (x > 0 && nums[x - 1] == nums[x]) continue; // 去重①：x 去重（如果 x 出现连续重复则跳过）

            // 处理三元组
            for (int y = x + 1; y < len - 2; y++) { // 处理第2个数字
                if (y > x + 1 && nums[y - 1] == nums[y]) continue; // 去重②：y去重（如果 y 出现连续重复则跳过）

                // 定义双指针确定[u,v]
                int u = y + 1, v = len - 1;
                while (u < v) { // 双指针处理第3、4个数字
                    int curSum = nums[x] + nums[y] + nums[u] + nums[v];
                    if (curSum == target) {
                        res.add(new ArrayList<>(Arrays.asList(nums[x], nums[y], nums[u], nums[v]))); // 去重处理（Arrays.asList(nums[x], nums[y], nums[u], nums[v])）

                        // 当次处理完成，指针后移
                        u++;
                        v--;

                        // 剪枝④：如果[u,v]选择出现连续重复则跳过
                        while (u < v && nums[u - 1] == nums[u]) u++; // 去重③：u去重（如果 u 出现连续重复则跳过）
                        while (u < v && nums[v] == nums[v + 1]) v--; // 去重④：v去重（如果 v 出现连续重复则跳过）

                    } else if (curSum < target) u++;
                    else if (curSum > target) v--;
                }
            }
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, -2, -5, -4, -3, 3, 3, 5};
        Solution018_02 s = new Solution018_02();
        List<List<Integer>> ans = s.fourSum(nums, -11);
        System.out.println();
    }
}
