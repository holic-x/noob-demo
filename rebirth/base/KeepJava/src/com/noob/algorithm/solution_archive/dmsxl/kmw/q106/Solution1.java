package com.noob.algorithm.solution_archive.dmsxl.kmw.q106;


/**
 * KMW106 岛屿周长
 */
public class Solution1 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * getCnt 用于统计每个岛屿"陆地"的周长（邻接海域、邻接边界）
     *
     * @param graph 邻接矩阵
     * @param x,y   当前遍历节点坐标
     */
    public static int getCnt(int[][] graph, int x, int y) {
        if (graph[x][y] != 1) {
            return 0; // 如果为非陆地，不执行任何操作
        }
        int curCnt = 0;
        // 分别从4个方向出发，校验当前节点邻接的4个方向的情况
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            // 如果邻接节点越界，则累加周长
            if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph[0].length) {
                curCnt++;
                continue; // 跳过
            }
            // 如果邻接海域，则累加周长
            if (graph[nextX][nextY] == 0) {
                curCnt++;
            }
        }
        // 返回当前陆地的边长计算情况
        return curCnt;
    }

    public static void main(String[] args) {
        // 1.输入控制
        // int[][] graph = GraphInputUtil.getMatrixGraph(0);
//        int[][] graph = new int[][]{
//                {0,0,0,0,0},{0,1,0,1,0},{0,1,1,1,0},{0,1,1,1,0},{0,0,0,0,0} // 岛屿周长14
//        };
//        int[][] graph = new int[][]{
//                {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 1, 1, 0} // 岛屿周长8
//        };
//        int[][] graph = new int[][]{
//                {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},{0, 0, 0, 0, 0} // 岛屿周长0
//        };
        int[][] graph = new int[][]{
                {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1} // 岛屿周长4*5=20
        };

        // 2.获取岛屿周长(遍历每个节点，计算陆地的累加周长)
        int res = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                res += getCnt(graph, i, j); // 累加每个节点的校验结果（在方法内部进行统一处理）
            }
        }

        // 返回结果
        System.out.println("岛屿周长为：" + res);
    }
}