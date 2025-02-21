package com.noob.algorithm.daily.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 207 课程表 - https://leetcode.cn/problems/course-schedule/description/
 */
public class Solution207_01 {

    /**
     * 思路分析：
     * ① 根据边关系（依赖关系）封装入度集合（数组、Map，存储当前节点对应的入度）
     * ② 构建队列辅助遍历，每次存队列中取出[入度为0]的节点，然后更新其关联节点的入度信息
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // ① 根据边关系构建每个节点的入度
        int[] inDegrees = new int[numCourses];
        for (int[] edge : prerequisites) {
            // [u,v] 表示u->v
            int u = edge[0], v = edge[1];
            inDegrees[v]++; // 下标对应节点
        }

        // ② 构建队列辅助遍历
        Queue<Integer> queue = new LinkedList<>();
        // 初始化将入度为0的节点入队
        for (int i = 0; i < inDegrees.length; i++) {
            // 入度为0说明没有前置依赖，可以优先取出
            if (inDegrees[i] == 0) {
                queue.offer(i); // 下标对应节点
            }
        }

        // 定义结果集
        List<Integer> res = new ArrayList<>();
        // 当队列不为空，遍历队列
        while (!queue.isEmpty()) {
            // 取出节点
            int u = queue.poll();
            res.add(u); // 加载取出的节点到结果集
            // 遍历处理该节点关联的节点，更新入度信息
            for (int v : prerequisites[u]) {
                inDegrees[v]--; // 取出u节点，则其指向v节点对应入度-1
                // 每次处理完成，将入度为0的节点v入队
                if (inDegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // 结果处理（如果取出的节点个数和实际节点个数匹配，说明不存在环，可获取拓扑排序）
        return res.size() == numCourses;
    }
}
