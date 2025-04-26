package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 015 三数之和 - https://leetcode.cn/problems/3sum/description/
 */
public class Solution015_01 {
    /**
     * 思路分析：
     * 三数之和：判断是否存在三元组满足三个元素下标各不相同且同时满足`x+y+z=0`,返回所有和为0且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {

        int n = nums.length;

        // 存储结果集
        List<List<Integer>> ans = new ArrayList<>();

        // 对数组元素进行排序
        Arrays.sort(nums);

        // 遍历数组元素
        for (int i = 0; i < n; i++) {
            // 固定x
            int x = nums[i];

            if (x > 0) {
                continue; // 如果第1个元素大于0，后面的元素经过排序后只会越来越大，此处进行剪枝
            }

            // 定义双指针分别从头尾出发寻找三元组
            int left = i + 1, right = n - 1;
            while (left < right) {
                // 计算当前三元组的和
                int curSum = x + nums[left] + nums[right];
                if (curSum == 0) {
                    // 统计结果集合
                    List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right]);
                    if (!ans.contains(list)) { // 此处数据已排序用contains校验是否重复
                        ans.add(list);
                    }
                    // 指针继续移动寻找下一个满足条件的三元组
                    left++;
                    right--;
                } else if (curSum < 0) {
                    // 则需要使和变大，则让left指针右移
                    left++;
                } else if (curSum > 0) {
                    // 则需要使和变小，则让right指针左移
                    right--;
                }
            }
        }

        // 返回结果集
        return ans;
    }

}
