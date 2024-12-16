package com.noob.algorithm.dmsxl.leetcode.q941;

/**
 * 🟢 941 有效的山脉
 */
public class Solution1 {

    // 规律法校验：校验各种坡度
    public boolean validMountainArray(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return false; // 元素个数小于3无法构成山脉
        }
        // 判断上升趋势和下降趋势
        boolean up = false, down = false;
        int maxIdx = -1; // 记录最大元素的出现位置
        for (int i = 1; i < len; i++) {
            if (arr[i] > arr[i - 1]) {
                if (down || i - maxIdx > 2) {
                    // 如果已经出现过下降趋势,或者再次出现了上升趋势，则不符合
                    return false;
                } else {
                    // 出现上升趋势
                    up = true;
                    maxIdx = i;
                }
            } else if (arr[i] == arr[i - 1]) {
                // 出现平坡
                return false; // 出现平坡一定不满足
            } else {
                // 出现下降趋势
                if (up) {
                    down = true;
                }
            }
        }
        return up && down;
    }
}
