package com.noob.algorithm.solution_archive.leetcode.common150.q274;

import java.util.Arrays;
import java.util.Collections;

/**
 * 274 H指数
 */
public class Solution1 {

    // 核心：找n个论文，而且这n个论文被引用的次数不小于n，n取最大值
    public int hIndex(int[] citations) {
        // 1.排序
        Arrays.sort(citations); // 此处默认是升序排序

        /**
         *  下标   0 1 2 3 4
         *  原有   3 0 6 1 5
         *  正序   0 1 3 5 6
         *   对比可以看到，正序排序序列，要找出这个最大的K，需要逆序遍历，判断下标和对应的元素大小
         *   nums[i] 对应论文出现次数  i 对应论文篇数
         */
        // 2.遍历（由于前面是升序排序，因此此处要逆序遍历）
        int k = 0; // k 表示h值
        for(int i=citations.length-1; i>=0; i--) {
            if(citations[i] > k) { // citations[i] > k 表示找到一篇至少被引用k+1次的论文，因此h值可以+1
                k++;
            }
        }
        // 返回结果
        return k;
    }
}
