package com.noob.algorithm.dmsxl.graph;

import java.util.Scanner;

/**
 * 图：输入辅助操作方法
 */
public class GraphInputUtil {

    // 输入控制，封装邻接矩阵
    public static int[][] getMatrixGraph(int choose) {
        Scanner sc = new Scanner(System.in);
        // System.out.println("请选择是否需要手动构建（1），如果非手动则返回默认测试用例");
        // int choose = sc.nextInt();

        if (choose != 1) {
            // n=4 m=5
            int[][] graph = new int[][]{
                    {1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}
            };
            return graph;
        }

        // 手动构建
        System.out.println("输入整数N（矩阵行）、M（矩阵列）");
        String[] nm = sc.nextLine().trim().split("\\s+");
        int n = Integer.valueOf(nm[0]), m = Integer.valueOf(nm[1]);
        // 定义邻接矩阵
        int[][] graph = new int[n][m];

        System.out.println("输入N行，每行包含M个数字（数字为1或0）");
        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < input.length; j++) {
                graph[i][j] = Integer.valueOf(input[j]);
            }
        }
        return graph;
    }

    // 输入控制，封装邻接表
    /*
    public static int[][] getMatrixGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入整数N（矩阵行）、M（矩阵列）");
        String[] nm = sc.nextLine().trim().split("\\s+");
        int n = Integer.valueOf(nm[0]), m = Integer.valueOf(nm[1]);
        // 定义邻接矩阵
        int[][] graph = new int[n][m];

        System.out.println("输入N行，每行包含M个数字（数字为1或0）");
        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < input.length; j++) {
                graph[i][j] = Integer.valueOf(input[j]);
            }
        }
        return graph;
    }
     */


}
