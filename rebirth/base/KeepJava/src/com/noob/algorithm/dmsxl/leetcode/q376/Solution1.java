package com.noob.algorithm.dmsxl.leetcode.q376;

/**
 * 376 摆动序列
 */
public class Solution1 {
    // 返回nums中作为摆动序列的最长子序列长度
    public int wiggleMaxLength(int[] nums) {
        // 特例判断
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        if (nums.length == 2 && nums[0] != nums[1]) {
            return 2;
        }

        // 其余情况：峰 谷 选择（实际就是小大小或者大小大这种序列的选择，遇到相等差值就跳过）
        int preDiff = nums[1] - nums[0]; // 记录 x y z 相邻元素中的 x y 之间的差值
        int res = preDiff == 0 ? 1 : 2; // 初始化判断：如果差值为0则说明相等只能选1个元素，如果差值不为0则说明不相等可以构成上升或下降（即可以选2个元素）
        // 遍历剩余元素（依次加入剩余元素，判断是否可以构成小大小 或者 大小大 的情况）
        for (int i = 2; i < nums.length; i++) {
            // 获取当前元素与前一个元素的差值
            int curDiff = nums[i] - nums[i - 1];

            // 判断其【是否满足与前一个差值符号相反】的条件，如果满足则可以将这个元素加入到当前的摆动序列中
            if (preDiff <= 0 && curDiff > 0 || preDiff >= 0 && curDiff < 0) { // 判断当前序列的上升下降趋势 与 之前的是否相反
                res++; // 满足条件，加入该元素(出现了峰 或 谷)
                preDiff = curDiff; // 更新当前序列的上升|下降趋势
            }
        }
        // 返回结果
        return res;
    }
}
