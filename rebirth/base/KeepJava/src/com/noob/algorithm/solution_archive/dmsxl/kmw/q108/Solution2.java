package com.noob.algorithm.solution_archive.dmsxl.kmw.q108;

import com.noob.algorithm.solution_archive.dmsxl.graph.disJointSet.DisJointSet;

import java.util.Scanner;

/**
 * KMW108 冗余连接
 */
public class Solution2 {

    public static void main(String[] args) {
        // 定义输出边结果
        int[] res = new int[2];

        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入节点个数N、边数M：");
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 初始化并查集
        DisJointSet disJoint = new DisJointSet();
        disJoint.init(n+1); // 节点有效编号范围[1,n]

        System.out.println("2.输入边：");
        sc.nextLine();
        while(m-->0){
            String[] inputStr = sc.nextLine().split("\\s+");
            int u = Integer.valueOf(inputStr[0]);
            int v = Integer.valueOf(inputStr[1]);
            // 判断u、v是否在同一个集合中
            boolean isSame = disJoint.isSame(u,v);
            if(isSame){
                // 如果两个点已经在集合中，说明这条边为冗余边
                res[0] = u;
                res[1] = v;
                System.out.println(res[0] + " " + res[1]);
                return ;
            }else{
                // 如果两个点不在同一集合中，加入这条边
                disJoint.join(u,v);
            }
        }
    }

}
