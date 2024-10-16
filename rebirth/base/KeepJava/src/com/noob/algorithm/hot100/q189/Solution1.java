package com.noob.algorithm.hot100.q189;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 189 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
 */
public class Solution1 {
    /**
     * 规律：轮转的规律转化（此处可以转化为List便于反转）
     * 1.先将数组进行反转
     * 2.轮转K位，前K进行反转、后N-k进行反转
     * 3.封装反转结果
     */
    public void rotate(int[] nums, int k) {

        // 定义结果集（将数组转为List）
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // 计算反转轮次
        k = k % nums.length;

        // 反转整个数组
        Collections.reverse(list);
        // 反转前K个数据
        Collections.reverse(list.subList(0, k));
        // 反转后N-K个数据
        Collections.reverse(list.subList(k, list.size()));

        // 输出结果
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
    }
}
