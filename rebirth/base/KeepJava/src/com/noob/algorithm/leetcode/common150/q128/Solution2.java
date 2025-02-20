package com.noob.algorithm.leetcode.common150.q128;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 128 最长连续序列
 */
public class Solution2 {

    /**
     * 去重+哈希表：
     * （1）先对数组元素进行去重，引入哈希表HashSet存储
     * （2）遍历set，寻找每个连续序列的开头（如果某个元素的前一个数不存在，则其可以作为连续序列的开头）
     * - 找到开头元素，进一步进入内存循环遍历获取到连续序列（依次递增判断，直到序列断开）
     */
    public int longestConsecutive(int[] nums) {
        int max = Integer.MIN_VALUE;
        // 去重
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        if(set.isEmpty()){
            return 0;
        }

        /**
         * 遍历元素：寻找开头元素，并敲定以开头元素为起点的连续序列长度
         */
        for(int num : nums){
            // 判断是否为开头元素（当前num的前一个数不存在，则其可作为连续序列的开头元素），如果非开头元素则可忽略
            if(!set.contains(num-1)){
                // 计算当前轮次的连续序列长度
                int curLen = 1; // 起始值为1（元素本身也是一个连续序列）
                int curNum = num; // 当前连续序列起始点

                // 进入内层循环进行校验，直到连续序列断掉
                while(set.contains(curNum+1)){
                    // 集合中存在下一个值，则更新连续序列长度
                    curLen++;
                    curNum++; // 指向下一个位置
                }
                // 更新最大的连续序列长度
                max = Math.max(max,curLen);
            }
        }

        // 返回结果
        return max==Integer.MIN_VALUE?1:max;
    }
}
