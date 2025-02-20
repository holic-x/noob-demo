package com.noob.algorithm.solution_archive.dmsxl.kmw.q103;

/**
 * 103 水流问题
 * 水只能流向更低的相邻节点
 */
public class Solution21 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};// 四个方向

    /**
     * dfs 深搜
     *
     * @param graph   二维矩阵
     * @param visited 已遍历节点
     * @param x、y     当前遍历节点
     */
    public static void dfs(int[][] graph, boolean[][] visited, int x, int y) {
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true; // 标记已遍历节点

        // 分别从4个方向检索
        int n = graph.length, m = graph[0].length;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            // 判断是否越界
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue; // 越界则跳过
            }
            // 判断是否满足流向规则
            if (graph[x][y] > graph[nextX][nextY]) {
                continue; // 此处校验的是从低到高流向
            }
            // 递归调用
            dfs(graph, visited, nextX, nextY);
        }
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
            dfs(graph, firstBorder, i, 0); // 左（第1组边界）
            dfs(graph, secondBorder, i, m - 1); // 右（第2组边界）

        }

        // 遍历列：上（第1组边界）、下（第2组边界）
        for (int j = 0; j < m; j++) {
            dfs(graph, firstBorder, 0, j); // 上（第1组边界）
            dfs(graph, secondBorder, n - 1, j); // 下（第2组边界）
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
