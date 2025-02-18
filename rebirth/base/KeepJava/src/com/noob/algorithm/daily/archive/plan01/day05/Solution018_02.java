package com.noob.algorithm.daily.archive.plan01.day05;

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
        int n = nums.length;
        // ① 对数组进行排序
        Arrays.sort(nums);

        // ② 求[x,y,u,v] => 求 基于 x 构成的 满足[y,u,v](y+u+v=target-x的三元组) 构成的四元组
        for (int x = 0; x < n - 3; x++) { // 处理第1个数字
            // 剪枝①：控制外层循环（预比较当前组合值与target的关系）
            if (nums[x] + nums[x + 1] + nums[x + 2] + nums[x + 3] > target)
                break; // 【以x开始连续的4个数构成】后面的组合数更加不可能构成target
            if (nums[x] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target)
                continue; // 【x 加上 数组末尾的3个数 构成】当前的组合数不能构成target，但还需继续遍历下去慢慢接近target

            // 去重①：x 去重（如果 x 出现连续重复则跳过）
            if (x > 0 && nums[x - 1] == nums[x]) continue;

            // 处理三元组
            for (int y = x + 1; y < n - 2; y++) { // 处理第2个数字
                // 剪枝②：控制外层循环（预比较当前组合值与target的关系）
                if (nums[x] + nums[y] + nums[y + 1] + nums[y + 2] > target)
                    break; // 【固定x、y，加上以y开始连续的2个数构成】后面的组合数更加不可能构成target
                if (nums[x] + nums[y] + nums[n - 2] + nums[n - 1] < target)
                    continue; // 【固定x、y 加上 数组末尾的2个数 构成】当前的组合数不能构成target，但还需继续遍历下去慢慢接近target

                // 去重②：y去重（如果 y 出现连续重复则跳过）
                if (y > x + 1 && nums[y - 1] == nums[y]) continue;

                // 定义双指针确定[u,v]
                int u = y + 1, v = n - 1;
                while (u < v) { // 双指针处理第3、4个数字
                    int curSum = nums[x] + nums[y] + nums[u] + nums[v];
                    if (curSum == target) {
                        res.add(new ArrayList<>(Arrays.asList(nums[x], nums[y], nums[u], nums[v]))); // 去重处理（Arrays.asList(nums[x], nums[y], nums[u], nums[v])）

                        // 当次处理完成，指针后移
                        u++;
                        v--;

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
