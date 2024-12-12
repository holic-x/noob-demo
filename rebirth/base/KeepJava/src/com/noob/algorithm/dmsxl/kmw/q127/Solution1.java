package com.noob.algorithm.dmsxl.kmw.q127;

import com.noob.algorithm.dmsxl.graph.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * KMW127-骑士的攻击
 */
public class Solution1 {

    public static int limit = 1000;

    /**
     * @param startX,startY 起始坐标
     * @param endX,endY     目标坐标
     */
    public static int[][] bfs(int startX, int startY, int endX, int endY) {
        // 定义行进方向（马走日、象走天）
        int[][] dir = new int[][]{
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(startX, startY));

        int[][] moved = new int[limit + 1][limit + 1];
        moved[startX][startY] = 1; // 初始化

        while (!queue.isEmpty()) {
            // 取出坐标
            Pair cur = queue.poll();

            // 往四个方向搜索
            for (int i = 0; i < 4; i++) {
                // 计算下一个位置
                int nextX = cur.x + dir[i][0];
                int nextY = cur.y + dir[i][1];
                // 如果越界则跳过
                if (nextX < 1 || nextX > limit || nextY < 1 || nextY > limit) { // x、y 属于[1,limit]
                    continue;
                }
                // 如果节点没有访问过，步数累加
                if (moved[nextX][nextY] != 0) {
                    moved[nextX][nextY]++;
                    queue.offer(new Pair(nextX, nextY));
                }
            }
        }

        // 返回结果
        return moved;
    }

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入n");
        int n = sc.nextInt();
        System.out.println("2.输入n行(起始位置->终点位置)：(a1,a2)->(b1,b2)");
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
