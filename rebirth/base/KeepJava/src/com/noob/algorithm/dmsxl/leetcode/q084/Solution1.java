package com.noob.algorithm.dmsxl.leetcode.q084;

import java.util.Stack;

/**
 * 084 柱状图中的最大的矩形
 */
public class Solution1 {

    /**
     * 单调栈思路
     * （1）单调栈中存储内容 =》索引
     * （2）单调栈元素顺序 =》栈顶到栈底：降序（从大到小）
     * （3）遍历元素h[i]与栈顶元素h[top]:
     * - h[i]>h[top]:入栈
     * - h[i]=h[top]:入栈
     * - h[i]<h[top]: 依次弹出元素（直至h[i]<h[top]不满足），以弹出的元素为基础计算所能构建的最大矩形：
     * - 错误思路：min{h[x]} * n (弹出元素中选择最小的高度乘以弹出个数得到矩形面积) （以1 5 6为例）
     * - 正确思路：分别得到高度（第1个栈顶元素索引指向）、左边界（第2个栈顶元素索引指向）、右边界（当前遍历元素索引i） =》area = w * h = (right-left-1) * h[mid]
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        // 构建辅助栈
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化

        // 定义最大面积
        int maxArea = 0;

        // 构建新数组首尾补充0，用于处理heights连续单调递减或连续单调递增的情况（如果不补充0，则出现连续递增的话得到结果为0不符合特例要求）
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;

        // 遍历元素(对新数组进行处理)
        for (int i = 1; i < newHeights.length; i++) {
            if (newHeights[i] >= newHeights[st.peek()]) {
                st.push(i);
            } else {
                while (!st.isEmpty() && newHeights[i] < newHeights[st.peek()]) {
                    int mid = st.pop(); // 弹出第一个栈顶元素作为高度（讨论每个弹出元素作为mid（高度）时的面积分析）
                    int left = st.peek(); // 其左边界为当前栈顶元素所在索引
                    int right = i; // 其右边界为当前遍历位置i
                    int w = right - left - 1; // 计算宽度
                    int h = newHeights[mid]; // 计算高度
                    maxArea = Math.max(maxArea, w * h); // 更新最大面积
                }
                // 将当前元素入栈
                st.push(i);
            }
        }

        // 返回结果
        return maxArea;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] heights1 = new int[]{2, 1, 5, 6, 2, 3};
        int[] heights2 = new int[]{2, 4}; // 连续单调递增
        int[] heights3 = new int[]{4, 3}; // 连续单调递减

        System.out.println(solution1.largestRectangleArea(heights1));;
        System.out.println(solution1.largestRectangleArea(heights2));;
        System.out.println(solution1.largestRectangleArea(heights3));;
    }
}
