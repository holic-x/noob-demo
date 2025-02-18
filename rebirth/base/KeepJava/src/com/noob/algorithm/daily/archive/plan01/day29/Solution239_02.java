package com.noob.algorithm.daily.archive.plan01.day29;


import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🔴 239 滑动窗口的最大值 - https://leetcode.cn/problems/sliding-window-maximum/description/
 */
public class Solution239_02 {
    /**
     * 思路：堆（借助堆始终维护当前限定范围的最大值）
     * ① 维护k个元素的大顶堆（滑动窗口），堆顶元素指向当前的最大值（排序规则：优先元素大小，其次索引先后）
     * ② 遍历剩余元素，滑动窗口，更新最大值。定义 cur 指向指向当前max[]最值封装位置（也是滑动窗口左边界的位置）
     * - （1）新元素入堆
     * - （2）根据cur与当前堆顶元素（top,topIdx）的索引位置进行比较，检验当前堆顶元素是否在滑动窗口中
     * - 2.1 topIdx<cur,说明该元素不在滑动窗口内，需要将元素不断移出，直到最值出现在滑动窗口内，记录最值，然后继续下一步遍历
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        // 定义结果集
        int[] max = new int[n - k + 1];

        // ① 维护K个元素的大顶堆（排序规则：优先元素大小，其次索引先后）
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]; // 数据类型：int[]{itemVal,itemIdx}
            }
        });
        // 构建大根堆
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }

        int cur = 0; // cur指向当前窗口左边界（也是max[]数组填充位置）
        // 取出当前第1个窗口位置下的最大值
        max[cur++] = priorityQueue.peek()[0]; // 此处仅仅是读取数值（因为这个最值可能在后面的滑动过程中还会用到）

        // ② 遍历剩余元素，滑动窗口
        for (int i = k; i < n; i++) {
            // 新元素入堆
            priorityQueue.offer(new int[]{nums[i], i});

            // 校验cur（窗口左边界）与堆顶元素索引的关系
            while (priorityQueue.peek()[1] < cur) {
                // 当前堆顶元素不在窗口覆盖范围，需依次弹出，直到找到在窗口范围内的堆顶元素
                priorityQueue.poll();
            }

            // 当堆顶元素出现在窗口覆盖范围内，记录这个最值
            max[cur++] = priorityQueue.peek()[0];
        }

        // 返回最终结果
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7}; // [3,3,5,5,6,7]
        Solution239_02 solution = new Solution239_02();
        int[] res = solution.maxSlidingWindow(nums, 3);
        PrintUtil.print(res);
    }
}
