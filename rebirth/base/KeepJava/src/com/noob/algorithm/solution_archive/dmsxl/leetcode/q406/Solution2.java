package com.noob.algorithm.solution_archive.dmsxl.leetcode.q406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 406 根据身高重建队列
 */
public class Solution2 {

    public int[][] reconstructQueue(int[][] people) {
        // 1.联合排序（先h后k：先按照身高从高到低排序，身高相同则按照k从小到大排序）
        Arrays.sort(people, (a, b) -> {
            if(a[0]==b[0]){
                return a[1]-b[1]; // 如果身高相同，按照k从小到大
            }else{
                return b[0]-a[0]; // 按照身高从高到低
            }
        });

        // 2.遍历排序后的数据，根据每个元素的k插入数据
        List<int[]> list = new ArrayList<>(); // 借助List构建插入顺序（int[]为对应的元素），选用List主要考虑便于插入操作
        for(int[] p : people){
            list.add(p[1],p); // add(index,item); 将元素插入指定索引位置（对照此处根据k插入元素）
        }
        return list.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        int[][] people = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        Solution2 solution1 = new Solution2();
        solution1.reconstructQueue(people);
    }

}
