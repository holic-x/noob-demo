package com.noob.algorithm.leetcode.common150.q189;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 189 轮转数组
 */
public class Solution1 {
    // 反转法
    public void rotate(int[] nums, int k) {
        /**
         * 此处轮转的体现效果可以理解为先对数组进行反转，然后分别对前k个元素反转、对后n-k个元素反转
         * 此外还需注意轮转次数k和nums长度的关系（以len为一个轮次）
         */
        k = k % nums.length;

        // 将数组nums转化为List进行轮转(Stream流)
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // 反转整个数组
        Collections.reverse(list);

        // 反转前K个元素
        Collections.reverse(list.subList(0,k));

        // 反转后N-K个元素
        Collections.reverse(list.subList(k,list.size()));

        // 输出结果
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }
}
