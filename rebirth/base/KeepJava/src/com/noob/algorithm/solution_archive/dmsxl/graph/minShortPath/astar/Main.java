package com.noob.algorithm.solution_archive.dmsxl.graph.minShortPath.astar;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.*;

/**
 * A star算法
 */

public class Main {
    
    // F = G + H
    // G = 从起点到该节点路径消耗
    // H = 该节点到终点的预估消耗

    static int[][] moves;

    static int dir[][] = {
            {-2, -1},
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
    };

    static int c;
    static int d;

    static class Node {
        int x;
        int y;
        int g;
        int h;
        int f;
    }

     // 统一不开根号，这样可以提高精度
    public static int distance(int x, int y) {
        return (x - c) * (x - c) + (y - d) * (y - d);
    }

    public static void main(String[] args) {
//        moves = new int[1001][1001];
        moves = new int[11][11];
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(aa -> aa.f));
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            c = scanner.nextInt();
            d = scanner.nextInt();

            Node node = new Node();
            node.x = a;
            node.y = b;
            node.g = 0;
            node.h = distance(a, b);
            node.f = node.g + node.h;


            queue.offer(node);
            while (!queue.isEmpty()) {
                Node curNode = queue.poll();
                if (curNode.x == c && curNode.y == d) break;
                for (int ii = 0; ii < 8; ii++) {
                    int x = curNode.x + dir[ii][0];
                    int y = curNode.y + dir[ii][1];
                    if (x < 1 || x > 1000 || y < 1 || y > 1000)
                        continue;
                    if (moves[x][y] == 0) {
                        moves[x][y] = moves[curNode.x][curNode.y] + 1;
                        Node nextNode = new Node();
                        nextNode.x =x;
                        nextNode.y =y;
                         // 开始计算F
                        nextNode.g = curNode.g + 5; // 统一不开根号，这样可以提高精度，马走日，1 * 1 + 2 * 2 = 5
                        nextNode.h = distance(nextNode.x, nextNode.y);
                        nextNode.f = nextNode.g + nextNode.h;
                        queue.offer(nextNode);
                    }
                }
            }

            PrintUtil.printGraphMatrix(moves); // 打印


            System.out.println(moves[c][d]);
            queue.clear();
            for (int[] move : moves) {
                Arrays.fill(move, 0); // 将第i行的所有元素初始化为-1
            }
        }
    }
}