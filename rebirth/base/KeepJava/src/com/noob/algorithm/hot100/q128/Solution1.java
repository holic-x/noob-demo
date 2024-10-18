package com.noob.algorithm.hot100.q128;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 最长连续序列：给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
 */
public class Solution1 {

    /**
     * 可以先对数组元素进行排序、去重，然后循环遍历找出数字连续最长的序列段（这个序列段可能在开头也可能在中间）
     * 1次循环：每个元素都有可能当队头（判断以当前元素为队头的最大连续序列），在循环的过程中比较并得到最大值
     * （因为此处不要求序列元素在原数组中连续）
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {

        // 1.对数组元素进行排序、去重
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (!list.contains(num)) { // 去重
                list.add(num);
            }
        }
        Collections.sort(list); // 排序（从小到大）

        // 2.循环遍历数组，找出最长的序列（记录以每个元素作为起点的最大连续序列长度，获取到当前的最大的连续序列的长度）
        int curMaxLength = 1; // 当前起点位置的最大连续序列长度（元素本身就是一个最小的连续序列，此处初始值为1）
        int maxLength = 1; // 最大序列长度
        // 判断连续序列（如果出现相邻两个数只差不为1的情况下则说明连续序列断掉，需要考虑每个元素都作为起点的情况）
        for (int i = 0; i < list.size() - 1; i++) {
            // 循环遍历列表，判断以每个元素作为起始点的最长序列
            if (list.get(i + 1) - list.get(i) == 1) {
                curMaxLength++;
                maxLength = Math.max(curMaxLength, maxLength); // 更新max
            } else {
                // 当出现连续的情况，说明序列断掉了执行计数器归0
                curMaxLength = 1;
                break;
            }
        }
        return maxLength;
    }
}
