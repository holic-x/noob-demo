package com.noob.algorithm.dmsxl.graph.minShortPath.floyd;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Floyd 算法（简化代码版本）
 */
public class FloydBase {

    // public static int MAX_VAL = Integer.MAX_VALUE;
    public static int MAX_VAL = 10005; // 边的最大距离是10^4(不选用Integer.MAX_VALUE是为了避免相加导致数值溢出)

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N M");
        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println("2.输入M条边");

        // ① dp定义（grid[i][j][k] 节点i到节点j 可能经过节点K（k∈[1,n]））的最短路径
        int[][][] grid = new int[n + 1][n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    grid[i][j][k] = grid[j][i][k] = MAX_VAL; // 其余设置为最大值
                }
            }
        }

        // ② dp 推导：grid[i][j][k] = min{grid[i][k][k-1] + grid[k][j][k-1], grid[i][j][k-1]}

        // 处理边
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            grid[u][v][0] = grid[v][u][0] = weight; // 初始化（处理k=0的情况） ③ dp初始化
        }

        // ④ dp构建：floyd 推导
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j][k] = Math.min(grid[i][k][k - 1] + grid[k][j][k - 1], grid[i][j][k - 1]);
                }
            }
        }

        System.out.println("3.输入[起点-终点]计划个数");
        int x = sc.nextInt();

        System.out.println("4.输入每个起点src 终点dst");

        while (x-- > 0) {
            int src = sc.nextInt();
            int dst = sc.nextInt();
            // 根据floyd推导结果输出计划路径的最小距离
            if (grid[src][dst][n] == MAX_VAL) {
                System.out.println("-1");
            } else {
                System.out.println(grid[src][dst][n]);
            }
        }
    }
}
