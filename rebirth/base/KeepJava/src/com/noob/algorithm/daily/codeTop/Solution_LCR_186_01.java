package com.noob.algorithm.daily.codeTop;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LCR 186 文物朝代判断 - https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/description/
 */
public class Solution_LCR_186_01 {

    // 判断5个元素是否可以构成连续递增的序列，如果出现编号为0则视为连续情况

    /*
    会存在忽略掉一些边界情况的问题
    public boolean checkDynasty(int[] places) {
        for (int i = 1; i < places.length; i++) {
            if (places[i] == 0) {
                places[i] = places[i - 1] + 1;// 将其置为连续参考
            }
            if (places[i] - places[i - 1] != 1) {
                return false; // 如果当前朝代号 与 前朝 相差不为1，说明不连续
            }
        }
        return true;
    }
     */

    // 哈希表
    public boolean checkDynasty(int[] places) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        // 定义set存储元素
        Set<Integer> set = new HashSet<>();
        // 遍历元素，统计未知0的个数
        int unknown = 0;
        for (int i = 0; i < places.length; i++) {
            if (places[i] == 0) {
                unknown++;
            } else {
                if (set.contains(places[i])) {
                    return false;
                }
                // 更新最小值和最大值，并将元素加入集合
                min = Math.min(min, places[i]);
                max = Math.max(max, places[i]);
                set.add(places[i]);

            }
        }
        return max - min < 5;// 最大编号朝代 - 最小编号朝代 < 5 则连续
    }


    // 排序 + 遍历
    public boolean checkDynasty2(int[] places) {
        // 对数组元素进行排序
        Arrays.sort(places);
        // 遍历元素，统计未知0的个数
        int unknown = 0;
        for (int i = 0; i < places.length - 1; i++) {
            if (places[i] == 0) {
                unknown++;
            } else if (places[i] == places[i + 1]) {
                return false; // 存在重复，提前返回false
            }
        }
        return places[4] - places[unknown] < 5;// 最大编号朝代 - 最小编号朝代 < 5 则连续
    }
}
