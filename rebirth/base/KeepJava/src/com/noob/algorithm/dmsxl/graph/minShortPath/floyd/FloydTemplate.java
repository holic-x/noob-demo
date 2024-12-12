package com.noob.algorithm.dmsxl.graph.minShortPath.floyd;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Floyd 算法（中规中矩动态规划版本）
 */
public class FloydTemplate {

    // public static int MAX_VAL = Integer.MAX_VALUE;
    public static int MAX_VAL = 10005; // 边的最大距离是10^4(不选用Integer.MAX_VALUE是为了避免相加导致数值溢出)

    public static int[][][] floyd(int n, int[][] grid) {
        // 1.dp 定义: dp[i][j][k] 表示节点i到节点j 以[1...k-1]节点集合作为中间节点 的最短路径
        int[][][] dp = new int[n + 1][n + 1][n + 1]; // 编号有效范围[1,n]

        /**
         * 2.dp 递推
         * 选择经过K：dp[i][j][k] = dp[i][k][k-1] + dp[k][j][k-1]
         * 选择不经过K：dp[i][j][k] = dp[i][j][k-1]
         */

        // 3.dp 初始化
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // int[] arr1 = dp[i][j];  Arrays.fill(arr1, MAX_VAL); // 需要拿到一维数组，才能通过fill正常封装
                dp[i][j][0] = dp[j][i][0] = grid[i][j]; // k=0的处理
                for (int k = 1; k <= n; k++) {
                    dp[i][j][k] = dp[j][i][k] = MAX_VAL;
                }
            }
        }

        // 4.dp 构建（确定遍历顺序:i j构成平面，k垂直于平面）（🔔）
        /*
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j][k] = dp[j][i][k] = Math.min(dp[i][k][k - 1] + dp[k][j][k - 1], dp[i][j][k - 1]);
                }
            }
        }
         */

        // 遍历顺序：j、k构成平面，i垂直于平面（❌错误的遍历顺序）
        /*
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    dp[i][j][k] = dp[j][i][k] = Math.min(dp[i][k][k - 1] + dp[k][j][k - 1], dp[i][j][k - 1]);
                }
            }
        }
         */

        // 遍历顺序：k、j构成平面（k循环放中间），i垂直于平面（❌错误的遍历顺序）
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= n; k++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j][k] = dp[j][i][k] = Math.min(dp[i][k][k - 1] + dp[k][j][k - 1], dp[i][j][k - 1]);
                }
            }
        }

        // 返回结果
        return dp;
    }

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N M");
        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println("2.输入M条边");
        int[][] grid = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(grid[i], MAX_VAL); // 初始化为最大值（如果不存在直接连接则设置为最大值）
        }
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            grid[u][v] = grid[v][u] = weight;
        }

        System.out.println("3.输入[起点-终点]计划个数");
        int x = sc.nextInt();

        System.out.println("4.输入每个起点src 终点dst");
        List<int[]> plans = new ArrayList<>(); // int[]{src,dst}
        while (x-- > 0) {
            int src = sc.nextInt();
            int dst = sc.nextInt();
            plans.add(new int[]{src, dst});
        }

        // 调用算法
        int[][][] res = FloydTemplate.floyd(n, grid);
        for (int i = 0; i < plans.size(); i++) {
            int src = plans.get(i)[0], dst = plans.get(i)[1];
            if (res[src][dst][n] == MAX_VAL) {
                System.out.println("-1");
            } else {
                System.out.println(res[src][dst][n]);
            }
        }
    }
}
