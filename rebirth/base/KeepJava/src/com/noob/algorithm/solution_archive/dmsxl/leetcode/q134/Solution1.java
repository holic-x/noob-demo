package com.noob.algorithm.solution_archive.dmsxl.leetcode.q134;

/**
 * 134 加油站
 */
public class Solution1 {
    /**
     * 从某个加油站出发，可以绕所有加油站一圈
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curGas = 0;   // 当前剩余油量
        int startIdx = 0; // 出发起点的加油站索引
        int totalGas = 0; // 走到某个节点的总油量

        int n = gas.length;
        for (int i = 0; i < n; i++) {
            // 到达某个加油站，进行加油，并判断当前油量是否可以支撑它走到下一个加油站
            totalGas = totalGas + (gas[i] - cost[i]); // 总油量
            curGas = curGas + (gas[i] - cost[i]); // 当前剩余油量
            if (curGas < 0) {
                // 如果当前剩余油量小于0，说明无法走到下一个加油站，则需从下一个加油站开始重新出发
                curGas = 0;
                startIdx = i + 1;
            }
        }
        // 遍历完成，判断最终的totalGas是否大于0
        return totalGas >= 0 ? startIdx : -1;
    }
}