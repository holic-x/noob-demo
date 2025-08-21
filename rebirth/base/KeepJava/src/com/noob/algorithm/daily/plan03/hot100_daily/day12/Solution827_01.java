package com.noob.algorithm.daily.plan03.hot100_daily.day12;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 🔴 827 最大人工岛 - https://leetcode.cn/problems/making-a-large-island/
 */
public class Solution827_01 {


    int[][] dir = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 思路分析：
     * 核心：只能变更一个地方构建最大的人工岛
     * - 硬核：遍历每个可能改造的地方，然后验证改造后的结果
     * - 技巧：先标记岛屿编号，然后改造某个海域的时候验证四周的邻接（如果邻接岛屿说明打通，如果邻接海域则只能选择较大的进行处理）
     * - - max{U,D,L,R} 选择关联覆盖最广的岛屿处理
     */
    public int largestIsland(int[][] grid) {

        int m = grid.length, n = grid[0].length;

        // 存储岛屿面积情况（{岛屿编号，岛屿面积}）
        Map<Integer, Integer> areaMap = new HashMap<>();

        boolean[][] visited = new boolean[m][n];

        // 第1次处理：渲染岛屿
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    // 重置计数器
                    curArea = 0; // 重置岛屿面积统计
                    dfs(grid, i, j, visited);
                    // 每次处理完成 更新岛屿映射
                    areaMap.put(curNum, curArea);
                    curNum++; // 编号+1
                }
            }
        }
        PrintUtil.printMatrix(grid);

        // 第2次处理：改造海域
        int maxArea = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // 如果是海域，校验如果进行改造的话，邻接可获得的最大面积（选择最右方案）
                    int area = 1; // 当前改造节点的面积（初始化）

                    HashSet<Integer> sign = new HashSet<>(); // 标记这4个方位是否出现重复编号，如果出现重复说明岛屿本来就已经连接

                    // 4个邻接方向校验
                    for (int x = 0; x < 4; x++) {
                        int nextX = i + dir[x][0];
                        int nextY = j + dir[x][1];
                        // 校验邻接的节点并更新可行方案 + 加上邻接的岛屿面积
                        // 越界处理
                        if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= m) {
                            continue;
                        }
                        // area += (grid[nextX][nextY] != 0 ? areaMap.get(grid[nextX][nextY]) : 0); // todo
                        // 此处需要注意有可能邻接的方向本身就构成一个岛屿，因此内部还要避免重复统计岛屿
                        if (!sign.contains(grid[nextX][nextY])) {
                            area += (grid[nextX][nextY] != 0 ? areaMap.get(grid[nextX][nextY]) : 0);
                            sign.add(grid[nextX][nextY]); // 标记当前岛屿编号在这次改造计划中已经累加过
                        }
                    }
                    // 当前节点改造完成，更新maxArea
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea != -1 ? maxArea : m * n;
    }


    // 处理：渲染地图，为每个岛屿进行编号，并记录每个编号的陆地面积
    int curNum = 2; // 岛屿编号（从2开始进行编号）
    int curArea = 0; // 当前岛屿面积


    private void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // 越界校验
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }

        // 如果已经遍历或者为海域则无需处理
        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }

        // 如果是陆地则递归计算面积并被每个陆地进行标记
        visited[x][y] = true;
        grid[x][y] = curNum; // 标记岛屿
        curArea++; // 岛屿面积统计

        // 递归处理4个方向
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            dfs(grid, nextX, nextY, visited);
        }
    }


    public static void main(String[] args) {
        Solution827_01 s = new Solution827_01();
        int[][] grid = new int[][]{{0, 0}, {0, 1}};
        int res = s.largestIsland(grid);
        System.out.println(res);
    }


}
