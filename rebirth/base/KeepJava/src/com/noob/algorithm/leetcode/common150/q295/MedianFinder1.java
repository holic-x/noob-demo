package com.noob.algorithm.leetcode.common150.q295;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 295 数据流的中位数
 */
public class MedianFinder1 {

    // 维护数组或集合元素
    List<Integer> numList;

    public MedianFinder1() {
        numList = new ArrayList<>();
    }

    public void addNum(int num) {
        numList.add(num);
    }

    public double findMedian() {
        // 遍历当前集合元素，获取中位数
        int curLen = numList.size();
        if (curLen == 0) {
            return 0;
        }
        if (curLen == 1) {
            return numList.get(0);
        }

        // 排序
        Collections.sort(numList);

        if (curLen % 2 == 0) {
            // 偶数的中位数
            return (numList.get(curLen / 2 - 1) + numList.get(curLen / 2)) / 2.0; // 注意int做除法的小数点问题
        } else {
            // 奇数的中位数
            return numList.get(curLen / 2);
        }
    }

}