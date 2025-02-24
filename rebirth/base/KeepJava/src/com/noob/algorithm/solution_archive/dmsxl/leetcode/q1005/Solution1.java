package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1005;

import java.util.Arrays;

/**
 * 1005 K次取反后最大化的数组和
 */
public class Solution1 {

    public int largestSumAfterKNegations(int[] nums, int k) {
        int curMaxSum = 0; // 记录当前的最大数组和

        // 对数组元素进行从小到大排序
        Arrays.sort(nums);

        // 遍历数组，取负数进行取反（根据k进行处理）
        for (int i = 0; i < nums.length; i++) {
            // 如果是负数且K足够，则将元素取反
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1; // 负数取反
                k--; // k 减 1
            }
            // 计算累加和
            curMaxSum += nums[i];
        }

        /**
         * 剩余K为0，对curMaxSum无影响
         * 剩余K为大于0的偶数，一正一负抵消对curMaxSum无影响（因为此时数组已经没有负数可取反收益了，只能选择一正一负）
         * 剩余K为奇数，则需选择当前数组的最小正数来做 一正一负 + 一负 的操作，最终 curMaxSum 取的是 curMaxSum-2*最小正数
         */
        // 遍历完成，如果K仍有剩余则需进行处理（判断当前剩余K是奇数还是偶数）
        if (k % 2 == 0) {
            return curMaxSum;
        } else if (k % 2 == 1) {
            // 对元素进行排序，取到当前的最小整数
            Arrays.sort(nums);
            return curMaxSum - 2 * nums[0];
        }
        return curMaxSum;
    }

}
