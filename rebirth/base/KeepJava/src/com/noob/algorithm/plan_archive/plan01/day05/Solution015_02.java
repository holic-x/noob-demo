package com.noob.algorithm.plan_archive.plan01.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡015 三数之和 （剪枝优化）
 */
public class Solution015_02 {

    /**
     * 【排序+双指针】：从一个整数数组`nums`中获取到满足`x+y+z=0`且不重复的三元组
     * - ① 对`nums`进行排序（为了便于通过指针定位三元组）
     * - ② 外层固定`i`，内层通过双指针分别从剩余的序列的头尾向中间靠拢寻找三元组(根据当前组合和决定指针更新方向)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 定义结果集
        List<List<Integer>> res = new ArrayList<>();
        // ① 对nums排序
        Arrays.sort(nums);
        // ② 遍历检索三元组（外层固定i，内层从剩余序列的头尾出发，定位三元组）
        for (int i = 0; i < nums.length - 2; i++) { // 三元组，所以i取值范围为[0,n-2）
            // 剪枝①：nums在前面进行了排序，如果固定的nums[i]>0,则后面的数只会更大不可能构成三元组和为0，直接跳过
            if (nums[i] > 0) {
                continue;
            }

            // 剪枝②：如果出现重复三元组则跳过（nums已经确定了有序，因此此处如果校验三元组的第一个元素相同的话，已经先出现的元素情况覆盖了，不需要重复处理）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 例如[-1,-1,0,0]: 第一个元素为-1的满足三元组的情况已经在前面就确定了，如果发现相邻相同的话则跳过
            }

            // 构建双指针，检索满足条件的三元组
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                // 定义临时三元组集合
                int curSum = nums[i] + nums[left] + nums[right];
                // 校验是否满足三元组条件
                if (curSum == 0) {
                    // res 的去重校验（contains）通过剪枝来处理，此处可以直接加入
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 本次处理完成，指针移动，继续下一个三元组遍历
                    left++;
                    right--;

                    // 剪枝③：[y,z]的选择，如果y、z出现连续重复则跳过（因为在前面的处理中已唯一确定）
                    while (left < right && nums[left - 1] == nums[left]) { // 左指针控制：y重复则跳过
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) { // 右指针控制：z重复则跳过
                        right--;
                    }
                } else if (curSum < 0) {
                    left++; // curSum<0，要让其变大才可能接近target
                } else if (curSum > 0) {
                    right--;  // curSum>0，要让其变小才可能接近target
                }
            }
        }

        // 返回最终结果
        return res;
    }
}
