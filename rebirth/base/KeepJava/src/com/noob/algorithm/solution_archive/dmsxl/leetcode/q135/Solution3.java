
package com.noob.algorithm.solution_archive.dmsxl.leetcode.q135;


/**
 * 135 分发糖果
 */
public class Solution3 {
    /**
     * 动态规划 + 贪心思路
     * 将校验规则分为两条：左、右规则，计算分别满足这两条规则的情况下需要多少个糖果，要同时满足两个规则则需要选择两者较大的那个糖果数
     * 空间优化版本：只需要保存left[],对于右规则的处理，可以在处理右规则的同时顺便处理相应的结果（这是动态规划空间优化的一种常见思路：此处i只依赖于i+1，因此只需要记录每次的i+1即可）
     */
    public int candy(int[] nums) { // int[] ratings
        int n = nums.length;
        int cnt = 0; // 糖果数量累加计数值

        // 左规则：如果nums[i-1]<nums[i]成立，则i号学生会比i-1号学生多一个糖果
        int[] left = new int[n];
        left[0] = 1; // 初始化左规则：left[i] 表示每个学生满足左规则条件下所需的最少糖果数
        for (int i = 1; i < n; i++) {
            // 如果i评分比i-1大则比它多1个糖果即可，如果比i-1评分小则取最小为1
            left[i] = (nums[i] > nums[i - 1]) ? left[i - 1] + 1 : 1;
        }


        // 右规则：如果nums[i]>nums[i+1] 表示i号学生会比i+1号学生多一个糖果（因此此处需要先确认nums[i+1]所需的最少糖果数量，采用逆向遍历的思路）
        int rightCandy = 1; // 初始化右规则：rightCandy始终指向i右边学生分配的最少糖果数
        for (int i = n - 1; i >= 0; i--) { // 逆序遍历(注意此时的起点和终点讨论)
            // 如果i评分比i+1大则比它多1个糖果即可，如果比i+1评分小则取最小为1
            int curCandy = 1;
            if (i < n - 1 && nums[i] > nums[i + 1]) {
                curCandy = rightCandy + 1; // 从n-2的位置才开始计算当前学生i满足有规则所需的最少糖果数
            }
            // 累加同时满足左右规则所需的糖果数
            cnt += Math.max(left[i], curCandy);
            // 更新rightCandy
            rightCandy = curCandy;
        }

        // 返回结果
        return cnt;
    }
}