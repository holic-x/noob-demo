package com.noob.algorithm.dmsxl.leetcode.q941;

/**
 * 🟢 941 有效的山脉
 */
public class Solution2 {

    // 规律法校验：校验各种坡度
    public boolean validMountainArray(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return false; // 元素个数小于3无法构成山脉
        }

        // ① 扫描连续上坡，得到maxIdx
        int maxIdx = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] > arr[i]) {
                // 当出现断层则记录maxIdx位置，跳出遍历
                maxIdx = i - 1;
                break;
            } else if (arr[i - 1] == arr[i]) {
                // 出现平坡，不满足
                return false;
            }
        }

        // maxIdx 的位置不能出现在arr的首、尾
        if (maxIdx == 0 || maxIdx == len - 1) {
            return false;
        }

        // ② 继续从maxIdx+1位置扫描连续下坡，如果出现断层则无法构成山脉
        for (int i = maxIdx + 1; i < len; i++) {
            if (arr[i - 1] <= arr[i]) {
                return false; // 出现断层（平坡或上坡）
            }
        }

        // 满足条件
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Solution2 s = new Solution2();
        s.validMountainArray(nums);
    }
}
