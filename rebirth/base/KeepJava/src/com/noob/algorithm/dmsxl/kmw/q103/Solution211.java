package com.noob.algorithm.dmsxl.kmw.q103;

/**
 * 103 水流问题
 * 水只能流向更低的相邻节点
 */
public class Solution211 {

    /**
     * dfs 深搜
     *
     * @param graph   二维矩阵
     * @param visited 已遍历节点
     * @param x、y     当前遍历节点
     * @param preH    上一个遍历节点
     */
    public static void dfs(int[][] graph, boolean[][] visited, int x, int y, int preH) {
        int n = graph.length, m = graph[0].length;
        // 遇到边界或者已经访问过的节点（先校验边界避免越界），直接返回
        if ((x < 0 || x >= n || y < 0 || y >= m) || visited[x][y]) {
            return;
        }
        // 如果不满足水流搜索方向（此处为从低到高搜索）
        if (graph[x][y] < preH) {
            return; // 此处校验的是从低到高流向(当前节点值要大于上一节点值，不满足则直接返回)
        }

        // 标记已遍历节点
        visited[x][y] = true;

        // 分别从4个方向检索(等价于原来的dir判断)，此处将递归条件判断放在前置判断中，因此此处可以直接进行dfs递归处理
        dfs(graph, visited, x, y + 1, graph[x][y]);
        dfs(graph, visited, x + 1, y, graph[x][y]);
        dfs(graph, visited, x, y - 1, graph[x][y]);
        dfs(graph, visited, x - 1, y, graph[x][y]);
    }

    public static void main(String[] args) {

        // 1.输入控制
        int[][] graph = {
                {1, 3, 1, 2, 4}, {1, 2, 1, 3, 2}, {2, 4, 7, 2, 1},
                {4, 5, 6, 1, 1,}, {1, 4, 1, 2, 1}
        };
        int n = graph.length, m = graph[0].length;

        // 2.分别从第1组边界、第2组边界出发，记录已遍历节点
        boolean[][] firstBorder = new boolean[n][m];
        boolean[][] secondBorder = new boolean[n][m];

        // 遍历行：左（第1组边界）、右（第2组边界）
        for (int i = 0; i < n; i++) {
            dfs(graph, firstBorder, i, 0, Integer.MIN_VALUE); // 左（第1组边界）
            dfs(graph, secondBorder, i, m - 1, Integer.MIN_VALUE); // 右（第2组边界）

        }

        // 遍历列：上（第1组边界）、下（第2组边界）
        for (int j = 0; j < m; j++) {
            dfs(graph, firstBorder, 0, j, Integer.MIN_VALUE); // 上（第1组边界）
            dfs(graph, secondBorder, n - 1, j, Integer.MIN_VALUE); // 下（第2组边界）
        }

        // 3.判断这两个标记数组是否存在公共已遍历节点，如果存在则说明这个公共节点是既可达边界1又可达边界2的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (firstBorder[i][j] && secondBorder[i][j]) {
                    System.out.println("i:" + i + "  j:" + j);
                }
            }
        }
    }

}
