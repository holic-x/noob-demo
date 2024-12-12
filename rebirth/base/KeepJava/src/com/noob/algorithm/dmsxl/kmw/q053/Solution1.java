package com.noob.algorithm.dmsxl.kmw.q053;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * KMW053-寻宝（最小生成树基础题型）
 */
public class Solution1 {


    /**
     * prim算法
     *
     * @param n     顶点个数
     * @param edges 边及对应权值关系
     */
    public static int prim(int n, Map<String, Integer> edges) {
        // 定义midDist[]存储非生成树节点到最小生成树的最近距离
        int[] midDist = new int[n + 1]; // 编号有效范围从[1,n]
        String[] midDistEdge = new String[n + 1]; // 编号有效范围从[1,n] 同步记录当前最近距离的边
        Arrays.fill(midDist, 10001); // 初始化设定为maxVal（Integer.MAX_VALUE）

        // 定义已加入最小生成树的节点（可以用List，也可以用数组同步表示）
        boolean[] selectedNode = new boolean[n + 1];
        Arrays.fill(selectedNode, false);

        // 构建prim主循环算法(需要选择n次)
        for (int cnt = 1; cnt <= n; cnt++) {
            // 1.选择距离最小生成树最近的节点（即minDist[i]的最小值）
            int cur = -1;
            int curVal = 10002; // 初始化比maxVal大一点，确保初始节点可以被选上
            for (int i = 1; i <= n; i++) {
                // 从非生成树列表节点中筛选`minDist[i]`最小的值
                if (!selectedNode[i]) {
                    if (midDist[i] < curVal) {
                        // 更新选择的节点
                        cur = i;
                        curVal = midDist[i];
                    }
                }
            }

            // 2.将选出的最近的节点加入最小生成树
            selectedNode[cur] = true;

            // 3.更新midDist[i]:更新剩余的非生成树节点与`cur`节点的最近的距离
            for (int i = 0; i <= n; i++) {
                if (!selectedNode[i]) {
                    int edgeDist = edges.getOrDefault(cur + "->" + i, 0);
                    if (edgeDist != 0 && edgeDist < midDist[i]) { // 如果edgeDist为0说明两个节点不存在直达的关系，无需更新
                        midDist[i] = edgeDist;
                        midDistEdge[i] = cur + "->" + i; // 同步记录当前最近的距离关联的边关系
                    }
                }
            }
        }

        // 输出结果
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum += midDist[i];
            System.out.println(midDistEdge[i]);
        }
        System.out.println("最小路径总距离：" + sum);
        return sum;
    }


    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入V顶点数量，E边数");
        int v = sc.nextInt();
        int e = sc.nextInt();
        System.out.println("2.输入边关系（a b c）表示a连接b，权值为c");
        // 通过Map<edge,val>存储边的权值关系(key为`a->b`,value为`c`)
        Map<String, Integer> edges = new HashMap<>();
        while (e-- > 0) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            int val = sc.nextInt();
            // 构建无向图的边关系
            edges.put(node1 + "->" + node2, val);
            edges.put(node2 + "->" + node1, val);
        }

        // 调用prim算法获取最小连通图的路径总和
        Solution1.prim(v, edges);
    }
}
