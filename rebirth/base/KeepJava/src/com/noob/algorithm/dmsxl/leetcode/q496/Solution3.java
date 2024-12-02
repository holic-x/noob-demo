package com.noob.algorithm.dmsxl.leetcode.q496;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496 下一个更大的元素I
 */
public class Solution3 {

    // 单调栈
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        // 定义结果集
        int[] res = new int[len1];
        Arrays.fill(res, -1); // 默认置为-1

        // 使用map存储nums1
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            map.put(nums1[i], i);
        }

        // 使用单调栈处理nums2寻找下一个更大的数
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化第0个元素索引入栈
        for (int i = 1; i < len2; i++) {
            // 判断nums2[i]与栈顶元素的大小（栈顶到栈底：递增序列）
            if (nums2[i] <= nums2[st.peek()]) {
                st.push(i); // 元素入栈
            } else {
                while (!st.isEmpty() && nums2[i] > nums2[st.peek()]) {
                    // 弹出元素并记录（判断当前栈顶索引指向元素是否在map中出现）
                    if (map.containsKey(nums2[st.peek()])) {
                        // 记录其下一个更大的元素
                        res[map.get(nums2[st.peek()])] = nums2[i];
                    }
                    st.pop(); // 弹出栈顶元素
                }
                st.push(i);// 元素入栈
            }
        }

        // 返回结果
        return res;
    }
}
