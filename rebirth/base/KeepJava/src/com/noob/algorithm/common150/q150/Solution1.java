package com.noob.algorithm.common150.q150;

/**
 * 150 加油站
 */
public class Solution1 {
    /**
     *  确认是否可以绕一圈
     * @param gas 当前加油站可提供油量
     * @param cost 从当前加油站到下一个加油站需要消耗的油量
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /**
         * 思路：循环遍历每个加油站，确认其是否可到达下一个加油站
         * 需要记录当前总油量、出发起点的加油站索引、从指定索引开始出发至今油量
         */
        int totalGas = 0;
        int startIdx = 0; // 起点加油站索引
        int curGas = 0;

        // 遍历每个加油站，确认是否可到达下一个加油站，如果出现不可到达的情况，则从下一个位置开始重新出发
        for(int i = 0; i < gas.length; i++) {
            // 更新最大油量和当前可剩油量
            totalGas  = totalGas + (gas[i] - cost[i]);
            curGas = curGas + (gas[i] - cost[i]);
            // 判断当前能否走到下一个加油站（如果出现不可达，则应从下一个加油站重新出发进行计算）
            if(curGas < 0) { // (当前剩余油量+可加油量) 小于 到达下一站所需油量，说明此站不可达下一个站，则需切换起点
                startIdx = i + 1; // 切换下一个起点进行计算
                curGas = 0 ; // 重新切换后，当前剩余油量需重置为0
            }
        }
        // 最终判断totalGas是否大于等于0（表示足够或者刚好能够到达）
        return totalGas>=0?startIdx:-1;
    }
}
