package com.noob.algorithm.solution_archive.dmsxl.graph.minShortPath.astar;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.*;

/**
 * A Star算法 基于BFS版本改造
 * KMW127-骑士的攻击
 */
class Knight {
    public int x; // 节点坐标x
    public int y; // 节点坐标y

    // 权值处理
    public int G; // 起点到该节点的路径消耗
    public int H;// 该节点到终点的路径消耗
    public int F; // F=G+H 权值（ 表示 dist(start,cur)+dist(cur,end)} : 起点经由cur节点到终点的距离和）

    public Knight() {

    }

    public Knight(int x, int y, int G, int H) {
        this.x = x;
        this.y = y;
        this.G = G;
        this.H = H;
        this.F = G + H;
    }
}

public class AStarByBFS {

    public static int limit = 10; // max限定为10001

    // 欧拉距离公式算法
    public static int distance(int x1, int y1, int x2, int y2) {
        // 此处设定不开根号，提高计算精度
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    /**
     * @param startX,startY 起始坐标
     * @param endX,endY     目标坐标
     */
    public static int[][] astar(int startX, int startY, int endX, int endY) {
        // 定义行进方向（马走日、象走田）
        int[][] dir = new int[][]{
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };
        // int[][] dir={{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};

        // 记录到达指定节点路径
        int[][] moved = new int[limit + 1][limit + 1];

        // 构建优先队列遍历搜索（按照PairDist的F排序，即排序的权值为F）
        PriorityQueue<Knight> queue = new PriorityQueue<>(new Comparator<Knight>() {
            @Override
            public int compare(Knight o1, Knight o2) {
                return o1.F - o2.F;
            }
        });
        // 初始化起点
        Knight startKnight = new Knight(startX, startY, 0, distance(startX, startY, endX, endY));
        queue.offer(startKnight);

        while (!queue.isEmpty()) {
            // 取出坐标
            Knight cur = queue.poll(); // 确保每次弹出的都是F最小的元素
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
                    // 计算欧拉距离(需注意，此处的入队的节点是cur指定的下一个节点next，那么其距离应该是[start->next->end])
                    int getG = cur.G + 5; // 马走日(1*1+2*2=5) （起点到cur，cur走日步到next可获得最短路径）
                    int getH = distance(nextX, nextY, endX, endY); // 指定的next节点到终点的距离
                    queue.offer(new Knight(nextX, nextY, getG, getH)); // 将下一个坐标加入队列
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
            int[][] moved = AStarByBFS.astar(a1, a2, b1, b2);
            System.out.println(moved[b1][b2]);
        }
    }
}
