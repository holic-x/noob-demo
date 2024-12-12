package com.noob.algorithm.dmsxl.leetcode.q739;

/**
 * 739 每日温度
 */
public class Solution1 {

    // 双层循环检索：外层遍历i，内层寻找下一个比i位置元素大的元素（不存在则为0）
    public int[] dailyTemperatures(int[] temperatures) {
        // 定义数组存储温度变化情况
        int[] answer = new int[temperatures.length];

        // 双层循环遍历
        for(int i=0;i<temperatures.length;i++){ // 外层确定i位置
            for(int j=i+1;j<temperatures.length;j++){ // 内层寻找比temperatures[i]大的元素，如果找不到则answer[i]为0
                if(temperatures[j]>temperatures[i]){
                    answer[i]=j-i;
                    break; // 找到了下一个更高的温度，跳出内层循环
                }
            }
        }

        // 返回结果
        return answer;
    }
}
