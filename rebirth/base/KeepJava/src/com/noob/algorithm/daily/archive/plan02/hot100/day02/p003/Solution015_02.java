package com.noob.algorithm.daily.archive.plan02.hot100.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 015 三数之和 - https://leetcode.cn/problems/3sum/description/
 */
public class Solution015_02 {
    /**
     * 思路分析：判断是否存在三元组满足nums[i]+nums[j]+nums[k]=0（x+y+z=0）
     * 哈希法：固定1位（x），双指针对向遍历确定y,z  去重优化
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 对数组元素进行排序
        Arrays.sort(nums);
        // 遍历
        for (int i = 0; i < n - 2; i++) {
            // 外层循环确定x
            int x = nums[i];

            // 减枝：如果x>0,则基于排序后面的数肯定大于0，不可能构成符合结果集的元组
            if (x > 0) {
                continue; // 跳过
            }

            // 如果出现连续重复的x则跳过
            if (i >= 1 && nums[i - 1] == nums[i]) {
                continue; // 跳过
            }

            // 双指针确定y，z
            int left = i + 1, right = n - 1;
            while (left < right) {
                int y = nums[left], z = nums[right];
                int curSum = x + y + z;
                // 根据curSum与target的关系确定指针移动方向
                if (curSum == 0) {
                    // 处理结果集
                    List<Integer> temp = new ArrayList<>();
                    temp.add(x);
                    temp.add(y);
                    temp.add(z);

                    // 集合去重（效率低下）
                    /*
                    if (!res.contains(temp)) {
                        res.add(temp);
                    }
                     */
                    res.add(temp);

                    // 指针后移
                    left++;
                    right--;

                    // 去重剪枝(基于排序去重思路，判断连续重复出现的元素)
                    while (left < n - 1 && nums[left - 1] == nums[left]) {
                        left++;
                    }
                    while (right > 0 && nums[right + 1] == nums[right]) {
                        right--;
                    }
                } else if (curSum < 0) {
                    // 需要让curSum变大
                    left++;
                } else if (curSum > 0) {
                    right--;
                }
            }
        }
        // 返回结果集
        return res;
    }

}
