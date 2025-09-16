package com.noob.algorithm.daily.plan03.hot100_daily.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 207 课程表 - https://leetcode.cn/problems/course-schedule/description/
 */
public class Solution207_01 {

    /**
     * 思路分析：todo
     * 拓扑算法：
     * - 入度为0的节点先入队
     * - 每次取出节点，随后处理关联的依赖关系（取出节点处理，随后其他与之绑定的节点的入度就会减少）
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 矩阵维护a->b的连接关系
        // 根据边关系统计入度（{u,v} 表示u->v之间有1条边，即v的入度+1）

        // 此处prerequisites是边关系 不是一个完整的表示n*n 需要根据边关系构建邻接表
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            int u = edge[0], v = edge[1];
            // u->v 有边 v 入度+1
            edges.get(u).add(v); // 构建边关系
            inDegrees[v]++;
        }

        // 将所有入度为0的节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int cnt = 0;
        // 遍历节点，取出节点，处理边关系
        while (!queue.isEmpty()) {
            int v = queue.poll();
            cnt++;

            // 节点取出，则v->x x 的入度-1
            for (int x : edges.get(v)) {
                inDegrees[x]--;

                // 处理完成校验当前入度是否为0
                if (inDegrees[x] == 0) {
                    queue.offer(x);
                }
            }

        }

        // 如果最终取出的节点和课程数量对照则说明拓扑排序满足
        return cnt == numCourses;
    }
}
