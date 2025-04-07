package com.noob.algorithm.daily.codeTop;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 🟡 189 轮转数组 - https://leetcode.cn/problems/rotate-array/description/
 */
public class Solution189_01 {

    /**
     * 思路分析：给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
     * 3次反转：全反转、前k反转、后n-k反转
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        // 定义结果集（将数组转为List）
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // 计算反转轮次
        k = k % nums.length;

        // 反转整个集合
        Collections.reverse(list);

        // 反转前K个数据
        Collections.reverse(list.subList(0, k));

        // 反转后n-k个数据
        Collections.reverse(list.subList(k, n));

        // 回填结果数据
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }
}
