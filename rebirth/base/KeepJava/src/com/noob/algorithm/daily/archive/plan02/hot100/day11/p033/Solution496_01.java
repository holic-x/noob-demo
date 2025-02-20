package com.noob.algorithm.daily.archive.plan02.hot100.day11.p033;

import java.util.*;

/**
 * 🟢 496 下一个更大元素 I - https://leetcode.cn/problems/next-greater-element-i/description/
 */
public class Solution496_01 {

    /**
     * 思路分析：给出两个无重复元素的数组，寻找n1[i]==n2[j]的下标j，并确定n2[j]的下一个元素
     * 模拟法：确定n2的下一个元素的数组，通过哈希法校验n2元素在n1中是否存在来选择要填充的值
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        // 遍历nums1元素并加入哈希表
        Map<Integer, Integer> map = new HashMap<>(); // map<val,idx>
        for (int i = 0; i < len1; i++) {
            map.put(nums1[i], i);
        }

        // 遍历nums2，获取其下一个元素的数组（遍历的过程中填充结果：看当前元素是否在集合1中也存在）
        int[] res = new int[len1];
        Arrays.fill(res, -1); // 默认填充-1

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len2; i++) {
            int curVal = nums2[i];
            while (!stack.isEmpty() && nums2[stack.peek()] <= curVal) {
                // 弹出栈顶元素，处理结果集
                int top = stack.pop(), topVal = nums2[top];
                // 如果当前遍历元素在集合中存在才需要封装结果集
                if (map.containsKey(topVal)) {
                    // res[top] = i - top; 基于nums2的封装
                    res[map.get(topVal)] = curVal;
                }
            }
            stack.push(i);
        }
        // 返回结果集
        return res;
    }
}
