package com.noob.algorithm.dmsxl.kmw.q103;


/**
 * 103 水流问题
 * 水只能流向更低的相邻节点
 */
public class Solution11 {

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
            if (graph[x][y] < graph[nextX][nextY]) {
                continue; // 当前遍历节点比下一选择节点要小，不满足流向规则
            }
            // 递归调用
            dfs(graph, visited, nextX, nextY);
        }
    }

    /**
     * 校验节点是否可以到达边界（第1组边界、第2组边界）
     */
    public static boolean vaildPair(int[][] graph, int x, int y) {
        int n = graph.length, m = graph[0].length;
        boolean[][] visited = new boolean[n][m]; // 每次检索重置遍历矩阵
        // 对当前节点进行dfs操作
        dfs(graph, visited, x, y);

        // 根据当前搜索的结果，判断当前已遍历的节点是否触达边界
        boolean firstBorder = false, secondBorder = false;
        // 按行判断左右边界是否触达
        for (int i = 0; i < n; i++) {
            if (visited[i][0]) {
                firstBorder = true; // 第1组边界（左）
                break;// 只要找到一个触达边界即可满足
            }
        }
        for (int i = 0; i < n; i++) {
            if (visited[i][m - 1]) {
                secondBorder = true; // 第二组边界（右）
                break;
            }
        }

        // 按列判断上下边界是否触达
        for (int j = 0; j < m; j++) {
            if (visited[0][j]) {
                firstBorder = true; // 第1组边界（上）
                break;
            }
        }
        for (int j = 0; j < m; j++) {
            if (visited[n - 1][j]) {
                secondBorder = true; // 第2组边界（下）
                break;
            }
        }

        // 如果两个边界均可触达，则当前路径有效
        return firstBorder && secondBorder;
    }

    public static void main(String[] args) {

        // 1.输入控制
        int[][] graph = {
                {1, 3, 1, 2, 4}, {1, 2, 1, 3, 2}, {2, 4, 7, 2, 1},
                {4, 5, 6, 1, 1,}, {1, 4, 1, 2, 1}
        };
        int n = graph.length, m = graph[0].length;

        // 2.判断每个节点是否可以同时到达第1组边界和第2组边界，如果可以则输出
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vaildPair(graph, i, j)) {
                    System.out.println("i:" + i + "  j:" + j);
                }
            }
        }

    }

}
