package com.noob.algorithm.daily.archive.plan02.day03.p005;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🔴 239 滑动窗口最大值 - https://leetcode.cn/problems/sliding-window-maximum/submissions/598655671/
 */
public class Solution239_02 {

    /**
     * 思路分析：基于优先队列思路（借助优先队列维护窗口元素顺序，通过校验窗口中的max索引判断其是否在当前窗口的有效范围）
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // 构建k个元素的大顶堆（大顶堆item：元素值、对应索引(可用数组或者类对象表示)），堆顶元素指向当前窗口的最大值
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // new int[]{val,index} => o1[0]为val，o2[1]为index
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] == o1[0] ? o1[1] - o2[1] : o2[0] - o1[0]; // 优先val比较(从大到小)，val相等则基于index(从小到大)
            }
        });
        // 初始化堆（载入k个元素）
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] res = new int[n - k + 1];
        int cur = 0; // 定义res结果集合填充指针,cur 为窗口的左边界
        res[cur++] = pq.peek()[0];

        // 遍历剩余的数组元素封装结果集合(窗口的有效取值范围[cur,cur+k-1],cur 即表示窗口的左边界)
        for (int i = k; i < n; i++) {
            // a.将新元素加入pq
            pq.offer(new int[]{nums[i], i});
            // b.校验当前窗口内的top元素是否在有效的取值范围内，如果不存在则需将其弹出
            while (pq.peek()[1] < cur) { // 结合窗口有效的取值范围进行校验，此处只需要校验左边界（右边的还没纳入）
                pq.poll(); // 弹出不满足窗口范围的最大值
            }
            // c.直到窗口内的top满足有效的取值范围，将其载入结果集（此处不需要弹出，其可能作为下一个窗口的最大值，等待下一轮判断后处理）
            res[cur++] = pq.peek()[0];
        }
        // 返回结果集
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 2;
        Solution239_02 solution = new Solution239_02();
        solution.maxSlidingWindow(nums, k);
    }
}
