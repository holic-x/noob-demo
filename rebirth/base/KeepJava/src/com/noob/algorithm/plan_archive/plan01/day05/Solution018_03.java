package com.noob.algorithm.plan_archive.plan01.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和（剪枝优化版本） todo 转化为三数之和
 */
public class Solution018_03 {

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
            // 去重处理：如果出现连续重复的x则跳过
            if (x > 0 && nums[x - 1] == nums[x]) {
                continue;
            }

            // 求满足[y,u,v](y+u+v=target-x的三元组)
            int curTarget = target - nums[x];
            List<List<Integer>> threeSumList = threeSum(Arrays.copyOfRange(nums, x + 1, len), curTarget);
            for (int i = 0; i < threeSumList.size(); i++) {
                threeSumList.get(i).add(nums[x]);
                res.add(threeSumList.get(i));
            }
        }

        // 返回结果
        return res;
    }

    // 三元组求解
    public List<List<Integer>> threeSum(int[] nums, int target) {
        // 定义结果集
        List<List<Integer>> res = new ArrayList<>();
        // ① 对nums排序
        Arrays.sort(nums);
        // ② 遍历检索三元组（外层固定i，内层从剩余序列的头尾出发，定位三元组）
        for (int i = 0; i < nums.length - 2; i++) { // 三元组，所以i取值范围为[0,n-2）
            // 去重处理
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 例如[-1,-1,0,0]: 第一个元素为-1的满足三元组的情况已经在前面就确定了，如果发现相邻相同的话则跳过
            }

            // 构建双指针，检索满足条件的三元组
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                // 校验是否满足三元组条件
                if (curSum == target) {
                    // res 的去重校验（contains）通过剪枝来处理，此处可以直接加入
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp); // Arrays.asList(nums[i], nums[left], nums[right])

                    // 本次处理完成，指针移动，继续下一个三元组遍历
                    left++;
                    right--;

                    while (left < right && nums[left - 1] == nums[left]) { // 左指针控制：y重复则跳过
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) { // 右指针控制：z重复则跳过
                        right--;
                    }
                } else if (curSum < target) {
                    left++; // 要让其变大才可能接近target
                } else if (curSum > target) {
                    right--;  // 要让其变小才可能接近target
                }
            }
        }

        // 返回最终结果
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        Solution018_03 s = new Solution018_03();
        s.fourSum(nums, 0);
    }
}
