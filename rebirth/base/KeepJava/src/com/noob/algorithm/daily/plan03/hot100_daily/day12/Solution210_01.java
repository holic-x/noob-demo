package com.noob.algorithm.daily.plan03.hot100_daily.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 210 课程表II - https://leetcode.cn/problems/course-schedule-ii/description/
 */
public class Solution210_01 {

    /**
     * 思路分析：
     * 拓扑排序概念核心：
     * ① 构建每个节点的入度数组
     * ② 初始化队列，将节点入度为0的节点载入队列（表示其无前置依赖，可以直接取用（学习））
     * ③ 遍历队列，每次取出一个节点（学习一门课程），随后关联更新这个节点的邻居节点的入度关系（如果邻居节点入度在更新后也为0说明可以纳入学习计划，载入队列）
     * - 依次类推，每次选择一个节点学习，当队列中所有节点处理完成也就构成了一个学习计划
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // 构建节点的入度数组
        int[] inDegrees = new int[numCourses];
        // 构建邻接表存储边关系
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 遍历数组 确定节点依赖关系([u,v]选修u之前必须先学v =》v->u)
        for (int[] edge : prerequisites) {
            int u = edge[0], v = edge[1];
            // 更新入度
            inDegrees[u]++;
            // 构建邻接表
            graph.get(v).add(u);
        }

        // 初始化队列，入度为0节点入队
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i); // 节点入队
            }
        }

        // 构建学习计划
        List<Integer> ans = new ArrayList<>();

        // 遍历队列
        while (!queue.isEmpty()) {
            // 取出节点
            int cur = queue.poll();

            // 载入计划
            ans.add(cur);

            // 更新关联邻居的依赖关系
            for (int neighbor : graph.get(cur)) { // 遍历cur->x 这个x即为neighbor
                inDegrees[neighbor]--; // 入度递减
                // 校验入度
                if (inDegrees[neighbor] == 0) {
                    // 载入队列
                    queue.offer(neighbor);
                }
            }
        }

        // 如果最终的计划列表和目标课程数量一致，说明这个方案可行
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
