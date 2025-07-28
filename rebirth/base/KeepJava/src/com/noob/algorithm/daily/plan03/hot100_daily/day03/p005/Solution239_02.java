package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🔴 239 滑动窗口最大值 - https://leetcode.cn/problems/sliding-window-maximum/submissions/598655671/
 */
public class Solution239_02 {

    /**
     * 思路分析：
     * 给定整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
     * 返回 滑动窗口中的最大值
     * - 堆：优先级概念
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;


        // 构建堆作为滑动窗口的辅助容器，元素按照元素大小、索引大小的从小到大的顺序进行排序入堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // 定义int[]{a,b} 分别存储元素值、索引
            @Override
            public int compare(int[] o1, int[] o2) {
                // 先比较元素值，后比较元素索引
                return o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0];
            }
        });

        // 初始化k个元素入堆
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        // 定义结果集合
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0]; // 第一个元素即为最大值
        int cur = 1; // cur为当前待填充的指针位置

        // 遍历剩余元素处理最大值数据
        for (int i = k; i < n; i++) {
            // 元素加入
            pq.offer(new int[]{nums[i], i});
            /**
             * 校验当前堆顶元素和cur的相对位置
             * - cur相当于滑动窗口的左边界
             * - 堆顶元素表示当前滑动窗口的最大值，需确保其索引在有效的窗口范围内（即[cur,right）,按照顺序遍历已经确保了右边界，此处则只需要校验左边界，如果越界则剔除这个可能的最大值）
             * 即右侧扩展，左侧限制，记录当前堆的有序性，并确保最大值对应的索引在窗口的有效范围内，也不需要重复对现有窗口内的元素重复排序
             */
            // 校验cur与堆顶元素索引的相对位置,如果最大值没有出现在有效的窗口范围，则不断将其剔除(此处需注意topIdx的更新)
            while (pq.peek()[1] < cur) {
                pq.poll();
            }

            /*
            if (topIdx >= cur) {
                // 最大值出现在有效的窗口范围，记录该最大值
                ans[cur++] = pq.peek()[0];
            }
             */

            // 最大值出现在有效的窗口范围，记录该最大值
            ans[cur++] = pq.peek()[0];
        }

        // 返回结果
        return ans;
    }


    public static void main(String[] args) {
        Solution239_02 s = new Solution239_02();
        s.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }


}
