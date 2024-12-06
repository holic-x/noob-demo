package com.noob.algorithm.dmsxl.graph;

import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    // 输入控制，封装邻接表（节点编号范围为[1,n]）
    public static List<List<Integer>> getTableGraph(int choose) {
        Scanner sc = new Scanner(System.in);
        // System.out.println("请选择是否需要手动构建（1），如果非手动则返回默认测试用例");
        // int choose = sc.nextInt();

        if (choose != 1) {
            List<List<Integer>> graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            graph.add(Arrays.asList(new Integer[]{2, 3}));
            graph.add(Arrays.asList(new Integer[]{1, 4}));
            graph.add(new ArrayList<>());
            graph.add(new ArrayList<>());

            return graph;
        }

        System.out.println("输入整数N（节点个数）、K（输入边数）");
        String[] nk = sc.nextLine().trim().split("\\s+");
        int n = Integer.valueOf(nk[0]), k = Integer.valueOf(nk[1]);

        // 定义邻接矩阵表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        System.out.println("输入k行，每行包含2个数字（表示A->B的边）");
        while (k-- > 0) {
            String[] input = sc.nextLine().trim().split("\\s+");
            int source = Integer.valueOf(input[0]);
            int target = Integer.valueOf(input[1]);
            graph.get(source).add(target);
        }
        return graph;
    }

    public static void main(String[] args) {
//        List<List<Integer>> graph = GraphInputUtil.getTableGraph(0);
        List<List<Integer>> graph = GraphInputUtil.getTableGraph(1);
        PrintUtil.printGraphTable(graph);
    }

}
