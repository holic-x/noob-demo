package com.noob.algorithm.daily.archive.plan02.day11.p033;

import java.util.Stack;

/**
 * 🔴 042 接雨水 - https://leetcode.cn/problems/trapping-rain-water/description/
 */
public class Solution042_02 {

    /**
     * 思路分析：计算每个格子的接水量
     * - 单调栈思路：
     * - ① 单调栈存储什么？ 下标索引
     * - ② 单调栈的顺序？  从栈头到栈底（从小到大）
     * - ③ 单调栈判断 校验当前遍历高度与栈顶指向索引元素比较（curVal、topVal）
     * - (1) curVal<topVal 元素入栈
     * - (2) curVal == topVal 更新栈顶元素（遇到相同的柱子则以右边柱子为参考计算宽度）
     * - (3) curVal>topVal 出现凹槽，需要计算与水量
     * - a.第1个栈顶元素（凹槽底部索引（即右侧柱子的前一个柱子）：相同的高度已经被覆盖更新，此处可得到宽度）
     * - b.第2个栈顶元素（凹槽左侧柱子索引）
     */
    public int trap(int[] height) {
        int n = height.length;
        // 构建栈辅助遍历
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化加入索引

        int cnt = 0;

        // 遍历元素
        for (int i = 1; i < n; i++) {
            // 校验栈顶元素s
            if (height[st.peek()] > height[i]) {
                // 元素正常入栈
                st.push(i);
            } else if (height[st.peek()] == height[i]) {
                // 更新栈顶元素（遇到相同的柱子）
                st.pop();
                st.push(i);
            } else {
                while (!st.isEmpty() && height[st.peek()] < height[i]) {
                    /**
                     *  如果遇到比栈顶高的柱子，说明此时出现凹槽，更新雨水量
                     *  1."取出"(pop)第1个栈顶元素为底部柱子索引
                     *  2."获取"(peek)第2个栈顶元素为左侧柱子索引
                     *  3.当前遍历元素i为右侧柱子索引
                     */
                    int midIdx = st.pop(); // 底部柱子索引
                    // NPE 处理(避免取不到第2个元素)
                    if (!st.isEmpty()) {
                        // 计算当前柱子构成的凹槽所接的雨水量
                        int w = i - st.peek() - 1; // 计算宽度
                        int h = Math.min(height[st.peek()], height[i]) - height[midIdx]; // 左右侧柱子中选择最低的高度 减去 中间柱子的高度 得到凹槽接雨水的高度
                        cnt += w * h; // 累加接雨水量
                    }
                }
                // 插入元素
                st.push(i);
            }
        }
        // 返回结果
        return cnt;
    }

}
