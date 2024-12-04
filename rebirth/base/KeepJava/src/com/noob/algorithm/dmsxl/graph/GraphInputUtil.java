package com.noob.algorithm.dmsxl.graph;

import java.util.Scanner;

/**
 * 图：输入辅助操作方法
 */
public class GraphInputUtil {

    // 输入控制，封装邻接矩阵
    public static int[][] getGraph() {
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



}
