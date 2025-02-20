package com.noob.algorithm.solution_archive.dmsxl.leetcode.q406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 406 根据身高重建队列
 */
public class Solution3 {

    public int[][] reconstructQueue(int[][] people) {
        // 1.联合排序（先h后k：先按照身高从高到低排序，身高相同则按照k从小到大排序）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1]; // 如果身高相同，按照k从小到大
            } else {
                return b[0] - a[0]; // 按照身高从高到低
            }
        });

        // 2.遍历排序后的数据，根据每个元素的k插入数据
        int[][] res = new int[people.length][people[0].length]; // 构建结果集
        for (int[] p : people) {
            int idx = p[1];
            // 如果单纯用数组，插入指定位置要看原来位置有没有元素，如果有，需要先把这个位置空出来，然后再插入
            /*
            if (res[idx] == new int[]{0, 0}) {
                res[idx] = p;
            } else {
                res[idx + 1] = p;
            }
             */
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] people = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        Solution3 solution1 = new Solution3();
        solution1.reconstructQueue(people);
    }

}
