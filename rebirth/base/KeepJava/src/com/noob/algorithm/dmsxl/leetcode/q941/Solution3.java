package com.noob.algorithm.dmsxl.leetcode.q941;

/**
 * 🟢 941 有效的山脉
 */
public class Solution3 {

    // 规律法校验：校验各种坡度
    public boolean validMountainArray(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return false; // 元素个数小于3无法构成山脉
        }

        // 构建双指针分别从首尾遍历(注意数组越界问题处理，确认指针遍历范围)
        int left = 0, right = len - 1;
        while (left < len - 1 && arr[left] < arr[left + 1]) {
            left++;
        }

        while (right > 0 && arr[right] < arr[right - 1]) {
            right--;
        }

        // 校验left、right是否指向同一个位置，且非指向首、尾位置
        if (left == right && left != 0 && right != len - 1) {
            return true;
        }

        return false;
    }

}
