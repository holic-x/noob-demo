package com.noob.algorithm.dmsxl.leetcode.q797;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 797 所有可能的路径(ACM 模式：邻接矩阵)
 */
public class Solution3 {

    static List<List<Integer>> res = new ArrayList<>(); // 存储所有路径结果
    static List<Integer> path = new ArrayList<>(); // 存放当前路径

    /**
     * 深度优先遍历
     *
     * @param graph 邻接矩阵
     * @param x     当前遍历的节点
     * @param n     终点
     */
    public static void dfs(int[][] graph, int x, int n) {
        // 递归出口：当前遍历的节点x到达n，说明遍历到终点，找到一条可达路径
        if (x == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 回溯处理
        for (int i = 1; i <= n; i++) { // 遍历节点x链接的所有节点
            if (graph[x][i] == 1) { // 找到节点x链接的节点
                // 1.加入
                path.add(i);
                // 2.递归
                dfs(graph, i, n);
                // 3.回溯（恢复现场）
                path.remove(path.size() - 1);
            }
        }
    }

    // 打印路径
    public static void printRes() {
        if (res.isEmpty()) {
            System.out.println("-1");
            return;
        }

        // 路径列表不为空，打印路径
        for (int i = 0; i < res.size(); i++) {
            List<Integer> path = res.get(i);
            StringBuffer pathStr = new StringBuffer();
            for (int j = 0; j < path.size() - 1; j++) {
                pathStr.append(path.get(j)).append(" ");
            }
            pathStr.append(path.get(path.size() - 1)); // 补上最后一个元素
            System.out.println(pathStr); // 打印路径
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1.输入控制
        System.out.println("分别输入节点个数、边数");
        int nodeCnt = sc.nextInt();
        int sideCnt = sc.nextInt();
        System.out.println("输入各边连接情况");
        List<String> sideList = new ArrayList<>(); // 存储边连接情况
        while (sideCnt > 0) {
            String inputSide = sc.nextLine();
            if (!inputSide.equals("")) { // 格式控制
                sideList.add(inputSide);
                sideCnt--;
            }
        }

        // 2.定义二维数组（邻接矩阵）表示图
        int[][] graph = new int[nodeCnt + 1][nodeCnt + 1]; // 节点取值为[1-n]
        // 填充邻接矩阵
        for (String side : sideList) {
            String[] sideNode = side.split("\\s");
            graph[Integer.valueOf(sideNode[0])][Integer.valueOf(sideNode[1])] = 1; // 如果节点间存在连接则对应矩阵位置元素置为1
        }

        // 3.调用dfs深度优先搜索获取所有可达路径
        path.add(1); // 所有路径起点均为1
        dfs(graph, 1, nodeCnt); // 区间从[1,n] graph.length-1

        // 打印路径
        printRes();

    }
}
