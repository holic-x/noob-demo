package com.noob.algorithm.daily.plan02.day07.p023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 🟡 406 根据身高重建队列 -  https://leetcode.cn/problems/queue-reconstruction-by-height/description/
 */
public class Solution406_01 {

    /**
     * 思路分析：优先身高排序（降序），随后根据前面有几个比他高的升序排序
     */
    public int[][] reconstructQueue(int[][] people) {
        // 对数组元素进行排序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        // 遍历区间，依次取出元素，插入指定位置
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            // 获取插入索引
            int insertIdx = people[i][1];
            // 插入元素到指定位置
            res.add(insertIdx, people[i]);
        }

        // 返回结果
        return res.toArray(new int[res.size()][]);
    }

}
