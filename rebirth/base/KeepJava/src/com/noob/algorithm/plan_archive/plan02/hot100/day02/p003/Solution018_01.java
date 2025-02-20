package com.noob.algorithm.plan_archive.plan02.hot100.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和 - https://leetcode.cn/problems/4sum/description/
 */
public class Solution018_01 {

    /**
     * 思路分析：四数之和（求满足a+b+c+d=target的四元组，元素各不相同且元组不能重复）
     * 暴力思路：四层循环嵌套处理
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 对数组元素进行排序
        Arrays.sort(nums);
        // 遍历数组得到四元组
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int x = j + 1; x < n - 1; x++) {
                    for (int y = x + 1; y < n; y++) {
                        int a = nums[i], b = nums[j], c = nums[x], d = nums[y];
                        // 元素需各不相同
                        int curSum = a + b + c + d;
                        // 校验目标值
                        if (curSum == target) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(a);
                            temp.add(b);
                            temp.add(c);
                            temp.add(d);
                            if (!res.contains(temp)) {
                                res.add(temp);
                            }
                        }
                    }
                }
            }
        }
        // 返回结果集
        return res;
    }
}
