package com.noob.algorithm.dmsxl.util;

/**
 * DP 数组打印工具类
 */
public class PrintDPUtil {

    public static void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print("[" + dp[i] + "]" + "-");
        }
        System.out.println();
    }

    public static void printMatrix(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print("[" + dp[i][j] + "]" + "-");
            }
            System.out.println(); // 换行
        }
    }
}
