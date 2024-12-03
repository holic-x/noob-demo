package com.noob.algorithm.dmsxl.leetcode.q042;

import java.util.Stack;

/**
 * 042 接雨水
 */
public class Solution2 {

    // 单调栈：行维度（计算凹槽体积容量：v = w * h）
    public int trap(int[] height) {

        // 构建辅助栈
        Stack<Integer> st = new Stack<>();
        st.push(0);

        int res = 0;
        // 遍历元素，计算凹槽接水量
        for (int i = 1; i < height.length; i++) {
            // 单调栈（栈顶到栈底：单调递增（从小到大））
            if (height[i] < height[st.peek()]) {
                // 符合单调递增，直接入栈
                st.push(i);
            } else if (height[i] == height[st.peek()]) {
                // 如果height[i]=height[top],雨水量的计算需要参考右边的柱子，此处遇到相等的情况需要将原来的弹出
                st.pop();
                st.push(i);
            } else {
                // 如果height[i]>height[top],依次弹出栈顶元素并计算雨水量
                while (!st.isEmpty() && height[i] > height[st.peek()]) {
                    int mid = st.pop();
                    if (!st.isEmpty()) { // NPE 处理
                        int h = Math.min(height[i], height[st.peek()]) - height[mid]; // 高度计算
                        int w = i - st.peek() - 1; // 宽度计算
                        res += h * w; // 容量计算并累加结果
                    }
                }
                st.push(i);
            }
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Solution2 solution1 = new Solution2();
        System.out.println("res:" + solution1.trap(height));
    }
}
