package com.noob.algorithm.daily.plan01.archive.day26;

import java.util.Arrays;

/**
 * 🟡 031 下一个排列 - https://leetcode.cn/problems/next-permutation/
 */
public class Solution031_01 {

    /**
     * 思路分析：
     * ① 逆序寻找可交换位置（判断是否存在下一个排序）：swapIdx
     * ② 校验是否存在下一个排序
     * - 不存在：直接返回排序后的最小序列
     * - 存在：对[swapIdx+1,n]区域进行排序，随后顺序遍历该区域的元素，找到第1个比swapIdx位置指向元素大的位置进行交换，随后再对[swapIdx+1,n]进行重排
     */
    public void nextPermutation(int[] nums) {
        // ① 逆序寻找可交换位置（判断是否存在下一个排序）
        int swapIdx = -1;
        boolean flag = false;

        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            // 逆序校验是否出现降序（寻找第1个降序序列：例如54876中的48为第1个降序序列(此时4 8 )）
            if (nums[i] > nums[i - 1]) {
                swapIdx = i - 1; // 记录这个可交换的位置
                flag = true;
                break;
            }
        }

        // ② 校验是否存在下一个排序
        if (flag) {
            // 处理可交换位置
            Arrays.sort(nums, swapIdx + 1, n); // 对[swapIdx+1,n]范围元素进行排序，随后选出第1个比swapIdx指向位置元素大的元素进行交换
            for (int i = swapIdx + 1; i < n; i++) {
                if (nums[i] > nums[swapIdx]) {
                    int temp = nums[i];
                    nums[i] = nums[swapIdx];
                    nums[swapIdx] = temp;
                    break;
                }
            }
            // 再次对交换元素的指定范围区域进行排序，确保后面构成的序列最小
            Arrays.sort(nums, swapIdx + 1, n);
        } else {
            Arrays.sort(nums); // 不存在下一个序列，直接返回排序后的结果（最小序列）
        }
    }

}
