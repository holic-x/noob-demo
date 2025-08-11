package com.noob.algorithm.daily.plan03.hot100_daily.day07.p023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 🟡 406 根据身高重建队列 -  https://leetcode.cn/problems/queue-reconstruction-by-height/description/
 */
public class Solution406_01 {

    /**
     * 思路分析：
     * 排序规则：优先身高排序，其次按照前面有几个比他高的升序排序
     * - 身高优先排序，确保身高有序
     * - 选择插入位置
     */
    public int[][] reconstructQueue(int[][] people) {

        // 1.优先身高排序（降序，确保身高高的人先排好），其次按照前面有几个比他高的升序排序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        // 插入处理
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            ans.add(people[i][1], people[i]);
        }

        // 返回结果
        return ans.toArray(new int[ans.size()][]);
    }

}
