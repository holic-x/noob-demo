package com.noob.algorithm.daily.archive.plan02.day07.p024;

/**
 * 🟡 134 加油站 - https://leetcode.cn/problems/gas-station/submissions/600314289/
 */
public class Solution134_01 {

    /**
     * 思路分析：容量无限，从第i个加油站开往第i+1个加油站需消耗cost[i]油量，且第i个加油站有gas[i]油
     * 给定gas、cost判断是否可以环路一周，可以的话返回出发点的加油站编号，否则返回-1
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startIdx = 0; // 起点的编号
        int curGas = 0; // 当前剩余油量
        int totalGas = 0; // 走到当前位置消耗的总油量

        // 遍历加油站
        for(int i=0;i<gas.length;i++){
            // 到达某个加油站进行加油，并校验油量
            curGas += gas[i] - cost[i];
            totalGas += gas[i] - cost[i];

            // 判断当前剩余油量是否可以支撑走到下一个节点，如果不行则更换起点
            if(curGas<0){
                startIdx = i+1; // 选择下一个位置作为起点
                curGas = 0;
            }
        }

        // 遍历到末尾，判断totalGas是否大于0来校验是否可以支持走1圈
        return totalGas >=0?startIdx:-1;
    }
}
