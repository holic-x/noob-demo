package com.noob.algorithm.solution_archive.dmsxl.kmw.q127;

import com.noob.algorithm.solution_archive.dmsxl.graph.Pair;
import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
// 节点坐标定义
class Pair {
    public int x;
    public int y;

    public Pair() {

    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 */

/**
 * KMW127-骑士的攻击
 */
public class Solution1 {

     public static int limit = 10; // max限定为10001

    /**
     * @param startX,startY 起始坐标
     * @param endX,endY     目标坐标
     */
    public static int[][] bfs(int startX, int startY, int endX, int endY) {
        // 定义行进方向（马走日、象走田）
        int[][] dir = new int[][]{
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        // 记录到达指定节点路径
        int[][] moved = new int[limit + 1][limit + 1];

        // 构建辅助队列遍历搜索
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(startX, startY));
        while (!queue.isEmpty()) {
            // 取出坐标
            Pair cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;

            // 判断当前遍历节点是否为终点坐标（如果是终点坐标，搜索结束）
            if (curX == endX && curY == endY) {
                break;
            }

            // 往四个方向搜索
            for (int i = 0; i < 8; i++) {
                // 计算下一个位置
                int nextX = cur.x + dir[i][0];
                int nextY = cur.y + dir[i][1];
                // 如果越界则跳过
                if (nextX < 1 || nextX > limit || nextY < 1 || nextY > limit) { // x、y 属于[1,limit]
                    continue;
                }
                // 如果节点没有访问过，步数累加
                if (moved[nextX][nextY] == 0) {
                    moved[nextX][nextY] = moved[curX][curY] + 1;
                    queue.offer(new Pair(nextX, nextY)); // 将坐标加入队列
                }
            }
        }

        // 打印矩阵
        PrintUtil.printGraphMatrix(moved);

        // 返回结果
        return moved;
    }

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入n");
        int n = sc.nextInt();
        System.out.println("\n2.输入n行(起始位置->终点位置)：(a1,a2)->(b1,b2)");
        while (n-- > 0) {
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();
            int b1 = sc.nextInt();
            int b2 = sc.nextInt();
            int[][] moved = Solution1.bfs(a1, a2, b1, b2);
            System.out.println(moved[b1][b2]);
        }
    }
}
