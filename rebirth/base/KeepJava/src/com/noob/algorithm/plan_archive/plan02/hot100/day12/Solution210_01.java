package com.noob.algorithm.plan_archive.plan02.hot100.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 210 课程表II - https://leetcode.cn/problems/course-schedule-ii/description/
 */
public class Solution210_01 {

    /**
     * 基于依赖关系的前提下，给出一个可以顺利完成所有课程学习的方案（可能存在多种方案，给出任意一种即可）
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 定义列表存储结果集合
        List<Integer> list = new ArrayList<>();

        // 定义数组存储入度（下标对应表示节点）
        int[] inDegrees = new int[numCourses];
        // 定义邻接表存储边关系
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            grid.add(new ArrayList<>());
        }

        // 处理依赖关系[0,1]表示要学习课程0，需先完成课程1，那么其对照的图的边含义为1->0 [u,v] => (v,u)(v->u)
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            inDegrees[u]++; // u节点 入度+1
            grid.get(v).add(u); // 表示v关联u
        }

        // 定义队列存储入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        // 初始化将入度为0的节点加入队列
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // 遍历队列进行处理
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            list.add(cur);
            for (int next : grid.get(cur)) {
                inDegrees[next]--; // 入度-1（cur节点取出，则当前其关联节点入度-1）
                // 将入度为0的节点加入队列
                if (inDegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 遍历完成，最终取出的列表即为所得(如果无法完整遍历所有节点，则说明不可达)
        if (list.size() == numCourses) {
            return list.stream().mapToInt(Integer::valueOf).toArray();
        } else {
            return new int[]{};
        }

    }
}
