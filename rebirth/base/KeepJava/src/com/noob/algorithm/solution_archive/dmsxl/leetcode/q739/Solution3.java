package com.noob.algorithm.solution_archive.dmsxl.leetcode.q739;

import java.util.Stack;

/**
 * 739 每日温度
 */
public class Solution3 {

    // 双层循环检索：外层遍历i，内层寻找下一个比i位置元素大的元素（不存在则为0）
    public int[] dailyTemperatures(int[] t) { // temperatures
        // 定义数组存储温度变化情况
        int[] res = new int[t.length];

        // 构建辅助栈用作单调栈（从栈头到栈底是单调递增：求右边第一个大的元素）
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化栈（第1个元素入栈）

        /**
         * 单调栈处理：遍历元素 t[i] t[st.top()]
         * ① t[i] < t[st.peek()] 符合单调递增顺序，入栈
         * ② t[i] = t[st.peek()] 求大于当前元素的情况，此处等于也正常入栈
         * ③ t[i] > t[st.peek()] 将栈顶元素小于t[i]的元素依次弹出（并更新:res[st.top()]=i-st.top()），并将i位置元素入栈
         */
        for (int i = 1; i < t.length; i++) {
            // 将栈顶元素小于t[i]的元素依次弹出
            while (!st.isEmpty() && t[i] > t[st.peek()]) { // 栈不为空判断
                res[st.peek()] = i - st.peek();
                st.pop();
            }
            // 将当前元素入栈
            st.push(i);
        }

        // 返回结果
        return res;
    }
}

