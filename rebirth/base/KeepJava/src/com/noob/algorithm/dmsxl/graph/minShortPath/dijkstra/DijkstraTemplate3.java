package com.noob.algorithm.dmsxl.graph.minShortPath.dijkstra;

import java.util.Arrays;
import java.util.Scanner;

/**
 * dijkstra 迪杰斯特拉算法（邻接矩阵处理方式）
 * 补充选择的路径
 */
public class DijkstraTemplate3 {

    public static void dijkstra(int n, int[][] graph, int startIdx) {
        int maxValue = Integer.MAX_VALUE;

        // 构建minDist[]: 源点到指定节点的最短距离
        int[] minDist = new int[n + 1]; // 此处节点有效范围选择[1,n]
        for (int i = 0; i <= n; i++) {
            // 初始化（此处根据源点校验，源点到源点自身的距离设置为0）
            if (i == startIdx) {
                minDist[i] = 0; // 源点到自身的距离为0
            } else {
                minDist[i] = maxValue; // 其他节点的最短距离默认设置为范围内的最大值
            }
        }

        // 构建遍历标识
        boolean[] selected = new boolean[n + 1]; // 节点有效范围选择[1,n]
        Arrays.fill(selected, false); // 初始化设置为未被遍历过（未被选择）

        // 构建选择的路径（"(u->v)"形式）
        String[] selectedPath = new String[n + 1]; // 节点有效范围选择[1,n]


        // 主循环（dijkstra算法核心：dijkstra三部曲）
        for (int cnt = 1; cnt <= n; cnt++) {
            // 1.选择距离源点的最近节点
            int cur = -1; // 定义选择指针
            int minVal = maxValue; // 如果minDist全初始化为maxValue，则此处取maxValue + 1确保循环正常进入处理
            for (int i = 1; i <= n; i++) {
                if (!selected[i] && minDist[i] < minVal) { // 从未遍历节点中选择
                    cur = i;
                    minVal = minDist[i];
                }
            }

            // 2.将这个最近节点标记为已被选择（遍历）
            selected[cur] = true;

            // 3.更新经由当前选择节点扩展的新路径（cur可到达的其他节点）
            for (int v = 1; v <= n; v++) { // 遍历节点v
                if (!selected[v] && graph[cur][v] != -1) { // 需校验cur->v直接是否可达，通过weight校验是否为负数（此处设置一个特殊标识-1标识u->v不可达）来进行排除
                    // minDist[v] = Math.min(minDist[v], minDist[cur] + graph[cur][v]);
                    // 更新midDist[i]的同时更新路径
                    int curMin = minDist[cur] + graph[cur][v];
                    if (curMin < minDist[v]) {
                        minDist[v] = curMin; // 更新源点到节点v最短路径
                        selectedPath[v] = "(" + cur + "->" + v + ")"; // 更新当前的节点路径选择
                    }
                }
            }
        }

        // 处理结果：输出结果
        for (int i = 1; i <= n; i++) {
            System.out.println(selectedPath[i] + minDist[i]);
        }
    }

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N（节点个数）M边数");
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println("2.输入边");

        // 构建邻接矩阵，初始化设置节点不可达标识为-1
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], -1);
        }

        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph[u][v] = weight;
        }

        // 调用算法
        DijkstraTemplate3 dijkstraTemplate = new DijkstraTemplate3();
        dijkstraTemplate.dijkstra(n, graph, 1);
    }
}
